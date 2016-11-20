package jms;

import entities.Question;
import jms.spring.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kazaf on 16/10/13.
 */
//@Configuration
@ComponentScan("jms")
public class JMSConfig {

    @Bean public Connection connection(){
        Connection connection=null;
        ActiveMQConnectionFactory factory=targetconnectionFactory();
        try{
        connection=factory.createConnection();}
        catch (Exception e){
            e.printStackTrace();
        }
        return  connection;
    }

    @Bean
    public ActiveMQConnectionFactory targetconnectionFactory(){
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    };

    @Bean
    public PooledConnectionFactory pooledConnectionFactory(){
        return new PooledConnectionFactory(targetconnectionFactory());
    }

    @Bean
    public SingleConnectionFactory connectionFactory(){
        return new SingleConnectionFactory(targetconnectionFactory());
    }

    @Bean
    public Producer producer(){return new Producer();}

    @Bean
    public JmsTemplate jmsTemplate(){
        return new JmsTemplate(connectionFactory());
    }

    @Bean
    public JmsTemplate QuestionJMS(){
        JmsTemplate jmsTemplate=new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setMessageConverter(questionConvertor());
        return jmsTemplate;
    }

    @Bean
    public JmsTemplate RequestJMS(){
        JmsTemplate jmsTemplate=new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setMessageConverter(requestConvertor());
        return jmsTemplate;
    }

    @Bean
    public ActiveMQQueue queueDestination(){
        return new ActiveMQQueue("queue");
    }

    @Bean
    public ActiveMQQueue sessionAwareQueue(){
        return new ActiveMQQueue("sessionAwareQueue");
    }

    @Bean
    public ActiveMQTopic topicDestination(){
        return new ActiveMQTopic("topic3");
    }

    @Bean
    public ActiveMQQueue adapterQueue(){return new ActiveMQQueue("adapterQueue");}

    @Bean
    public ActiveMQQueue responseQueue(){return  new ActiveMQQueue("responseQueue");}



    @Bean
    public ConsumerListener consumerListener(){
        return new ConsumerListener();
    }

    @Bean
    public ResponseQueueListener responseQueueListener(){return new ResponseQueueListener();}

    @Bean
    public ConsumerMessageListener consumerMessageListener(){
        return new ConsumerMessageListener();
    };

    @Bean
    public ConsumerSessionAwareMessageListener consumerSessionAwareMessageListener(){
        ConsumerSessionAwareMessageListener consumerSessionAwareMessageListener= new ConsumerSessionAwareMessageListener();
        consumerSessionAwareMessageListener.setDestination(queueDestination());
        return consumerSessionAwareMessageListener;
    };




    @Bean
    public ActiveMQQueue defaultResponseQueue(){return new ActiveMQQueue("defaultResponseQueue");}

    @Bean
    public DefaultResponseQueueListener defaultResponseQueueListener(){return new DefaultResponseQueueListener();}

    @Bean
    public DefaultMessageListenerContainer sessionAwareListenerContainer(){
        DefaultMessageListenerContainer defaultMessageListenerContainer=new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory());
        defaultMessageListenerContainer.setDestination(sessionAwareQueue());
        defaultMessageListenerContainer.setMessageListener(consumerSessionAwareMessageListener());
        return defaultMessageListenerContainer;
    }

    @Bean
    public DefaultMessageListenerContainer responseQueueMessageListenerContainer(){
        DefaultMessageListenerContainer defaultMessageListenerContainer=new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory());
        defaultMessageListenerContainer.setDestination(responseQueue());
        defaultMessageListenerContainer.setMessageListener(responseQueueListener());
        return defaultMessageListenerContainer;
    }

    @Bean
    public DefaultMessageListenerContainer defaultResponseQueueMessageListenerContainer(){
        DefaultMessageListenerContainer defaultMessageListenerContainer=new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory());
        defaultMessageListenerContainer.setDestination(defaultResponseQueue());
        defaultMessageListenerContainer.setMessageListener(defaultResponseQueueListener());
        return  defaultMessageListenerContainer;
    }





    //Topic

    @Bean
    public TopicLIstener topicLIstener(){return  new TopicLIstener();}

    @Bean
    public DefaultMessageListenerContainer TopicMessageListenerContainer(){
        DefaultMessageListenerContainer defaultMessageListenerContainer=new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory());
        defaultMessageListenerContainer.setDestination(topicDestination());
        defaultMessageListenerContainer.setMessageListener(topicLIstener());
        defaultMessageListenerContainer.setPubSubDomain(true);
        defaultMessageListenerContainer.setSubscriptionDurable(true);
        defaultMessageListenerContainer.setSessionAcknowledgeMode(1);
        return defaultMessageListenerContainer;
    }



    @Bean//QuestionTopic
    public ActiveMQTopic testquestionTopic(){return new ActiveMQTopic(3+"question");}

    @Bean//QuestionQueue
    public ActiveMQQueue testquestionQueue(){return new ActiveMQQueue(3+"question");}

    @Bean //Question转化器
    public QuestionConvertor questionConvertor(){return new QuestionConvertor();}

    @Bean//QuestionListener
    public QuestionListener questionListener(){return new QuestionListener();}

    @Bean//QuestionMessageListenerAdapter
    public MessageListenerAdapter questionmessageListenerAdapter(){
        MessageListenerAdapter messageListenerAdapter= new MessageListenerAdapter(questionListener());
        messageListenerAdapter.setDefaultListenerMethod("receiveMessage");
        //messageListenerAdapter.setDefaultResponseDestination(defaultResponseQueue());
        messageListenerAdapter.setMessageConverter(questionConvertor());
        return  messageListenerAdapter;
    }

    @Bean//QuestionMessageListenerContainer
    public DefaultMessageListenerContainer questionmessageListenerContainer(){
        DefaultMessageListenerContainer defaultMessageListenerContainer=new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory());
        defaultMessageListenerContainer.setDestination(testquestionQueue());
        defaultMessageListenerContainer.setMessageListener(questionmessageListenerAdapter());
        return  defaultMessageListenerContainer;
    }

    @Bean
    public ArrayList<Question> user3questionList(){return new ArrayList<Question>();}



    @Bean
    public ActiveMQQueue testrequestQueue(){return new ActiveMQQueue(9+"request");}

    @Bean //Request转化器
    public RequestConvertor requestConvertor(){return new RequestConvertor();}
}
