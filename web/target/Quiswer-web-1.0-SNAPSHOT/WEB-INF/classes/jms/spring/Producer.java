package jms.spring;

import jms.JMSConfig;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * Created by Kazaf on 16/10/13.
 */
@Component
public class Producer {

    @Resource
    JmsTemplate jmsTemplate;

    @Resource
    Connection connection;
    Session session;
    MessageConsumer consumer;

    @Autowired
    @Qualifier("defaultResponseQueue")
    private Destination responseDestination;

    public void sendMessage(Destination destination, final String message,Destination destination2) {
        System.out.println("---------------生产者发送消息-----------------");
        System.out.println("---------------生产者发了一个消息：" + message);
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                textMessage.setJMSReplyTo(destination2);
                System.out.println(destination2);
                return textMessage;
            }
        });
    }

    public String receiveMessage(String destination,String clientID){
        String message = "";
        try {
            connection.setClientID(clientID);
            connection.start();
            session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            Topic destin=session.createTopic(destination);
            //consumer=session.createConsumer(destination);
            consumer=session.createDurableSubscriber(destin,clientID);
            while (true) {
                TextMessage textMessag = (TextMessage) consumer.receive();
                //message = textMessag.getText();
                if(null!=textMessag){
                    message = textMessag.getText();

                }else{
                    message = "end of the empty message";
                    break;
                }
            }
        }catch (Exception e) {
                e.printStackTrace();
            }
        return message;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

}
