import entities.Question;
import jms.JMSConfig;
import jms.JMSTemplate;
import jms.spring.Producer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * Created by Kazaf on 16/10/13.
 */
public class ProducerConsumerTest {

    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JMSConfig.class);

    //@Resource
    //private Producer producer;

    //@Autowired
    //@Qualifier("responseQueue")
    //private Destination destination;

    private Producer producer=ctx.getBean(Producer.class);

    //private JmsTemplate jmsTemplate=ctx.getBean(JmsTemplate.class);

    private JMSTemplate JMsTemplate=ctx.getBean(JMSTemplate.class);

    private Destination destination=(Destination) ctx.getBean("topicDestination");

    private DefaultMessageListenerContainer defaultMessageListenerContainer=(DefaultMessageListenerContainer) ctx.getBean("TopicMessageListenerContainer");

    private Destination responseDestination=(Destination) ctx.getBean("defaultResponseQueue");

    //@Test
    public void testSessionAwareMessageListener() {
        producer.getJmsTemplate().setDeliveryMode(DeliveryMode.PERSISTENT);
        producer.sendMessage(destination, "this is the tpoic3 1!!",responseDestination);
    }

   // @Test
    public void testSubscriber(){
        //producer.getJmsTemplate().getConnectionFactory().createConnection().setClientID();
       System.out.println( producer.receiveMessage("topic3","011767"));
        //System.out.println(producer.receiveMessage("topic4"));
    }


    Question question=new Question("hello it's me",3,9,1);

    Destination questionQueue=(Destination)ctx.getBean("testquestionQueue");

    Destination questionTopic=(Destination) ctx.getBean("testquestionTopic");

    DefaultMessageListenerContainer questionmessageListenerContainer=(DefaultMessageListenerContainer)ctx.getBean("questionmessageListenerContainer");

    @Test
    public void testsendQuestion(){
        for(int i=0;i<10;i++){
            System.out.print(i+"  ");
        JMsTemplate.sendQuestion(questionQueue,question);
            questionmessageListenerContainer.setDestination(questionQueue);
        }
    }

    //@Test
    public void testreceiveQuestion(){
        JMsTemplate.receiveQuestion(questionTopic);


    }


}