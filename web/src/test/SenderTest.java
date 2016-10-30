import jms.JMSConfig;
import jms.JMSTemplate;
import jms.spring.Producer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

/**
 * Created by Kazaf on 16/10/14.
 */
public class SenderTest {


    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JMSConfig.class);
    Producer producer=ctx.getBean(Producer.class);

    private String message="hello from here liuli";

    public void Send(){

        Topic topicDestination=new ActiveMQTopic("liuli");
        producer.getJmsTemplate().setDeliveryMode(DeliveryMode.PERSISTENT);
        //producer.sendMessage(topicDestination, message);
    }


    public static void main(String[] args){
        SenderTest senderTest=new SenderTest();
        senderTest.Send();
    }


}
