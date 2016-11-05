package jms.spring;

import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * Created by Kazaf on 16/10/13.
 */
@Component
public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<TextMessage> {


    private Destination destination;

    @Override
    public void onMessage(TextMessage message, Session session) throws JMSException {
        System.out.println("收到一条消息");
        System.out.println("消息内容是：" + message.getText());
        MessageProducer producer = session.createProducer(destination);
        Message textMessage = session.createTextMessage("ConsumerSessionAwareMessageListener。。。");
        producer.send(textMessage);
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
