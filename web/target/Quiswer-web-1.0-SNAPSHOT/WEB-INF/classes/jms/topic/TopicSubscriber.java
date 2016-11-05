package jms.topic;

/**
 * Created by Kazaf on 16/10/11.
 */import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQTempTopic;
import org.apache.activemq.command.ActiveMQTopic;


public class TopicSubscriber {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        try {
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

           Topic topic = session.createTopic("myTopic.messages");
            MessageConsumer consumer = session.createConsumer(topic);
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    TextMessage tm = (TextMessage) message;
                    if(tm!=null){
                    try {
                        System.out.println("Received message: " + tm.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                    }
                    else {
                        System.out.println("tm is null");
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}

