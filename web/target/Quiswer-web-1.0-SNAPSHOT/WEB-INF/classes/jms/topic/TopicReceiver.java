package jms.topic;

import jms.JMSConfig;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.fusesource.hawtdispatch.internal.TimerThread;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;

/**
 * Created by Kazaf on 16/10/11.
 */
public class TopicReceiver implements Runnable {

    private String threadName;

    public TopicReceiver(String threadName) {
        this.threadName = threadName;
    }

    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JMSConfig.class);
    private JmsTemplate jmsTemplate=ctx.getBean(JmsTemplate.class);
    //Destination destination=new ActiveMQTopic("topic3");

    @Override
    public void run() {

        ConnectionFactory connectionFactory;
        Connection connection=null;
        Session session=null;
        Topic destination;
        MessageConsumer consumer;

        connectionFactory=new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,ActiveMQConnection.DEFAULT_PASSWORD,ActiveMQConnection.DEFAULT_BROKER_URL);


       try {

            connection = connectionFactory.createConnection();
           connection.setClientID(threadName);
           connection.start();
           session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
           destination=session.createTopic("topic3");
           //consumer=session.createConsumer(destination);
           consumer=session.createDurableSubscriber(destination,threadName);
           while (true){
               TextMessage message=(TextMessage)consumer.receive(1000);
               if(null!=message){
                   System.out.println("线程 "+threadName+" 读取到 :"+message.getText());
               }else {
                   System.out.println("消息已读取完!");
                   break;
               }
           }
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

    public static void main(String[] args){
        TopicReceiver receiver1=new TopicReceiver("no.1");
        TopicReceiver receiver2=new TopicReceiver("no.2");
        TopicReceiver receiver3=new TopicReceiver("no.3");
        TopicReceiver receiver4=new TopicReceiver("no.4");
        Thread thread1=new Thread(receiver1);
        Thread thread2=new Thread(receiver2);
        Thread thread3=new Thread(receiver3);
        Thread thread4=new Thread(receiver4);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();


    }
}
