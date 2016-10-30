package jms.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Kazaf on 16/10/11.
 */
public class TopicSender {

    private static final int SEND_NUMBER=5;

    public static void sendMessage(Session session,MessageProducer producer)throws Exception{
        for(int i=0;i<SEND_NUMBER;i++){
            TextMessage message=session.createTextMessage("这是第四条信息里的:"+i);
            System.out.println("TopicSender send the message: "+message.getText());
            producer.send(message);
        }

    }

    public static void main(String[] args){
        ConnectionFactory connectionFactory;
        Connection connection=null;
        Session session=null;
        Topic destination;
        MessageProducer producer;
        connectionFactory=new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,ActiveMQConnection.DEFAULT_PASSWORD,ActiveMQConnection.DEFAULT_BROKER_URL);
        try{
            connection=connectionFactory.createConnection();
            connection.start();
            session=connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            destination=session.createTopic("topic3");
            producer=session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            sendMessage(session,producer);
            session.commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (session != null) {
                    session.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
