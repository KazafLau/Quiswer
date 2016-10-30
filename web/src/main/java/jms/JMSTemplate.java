package jms;

import entities.Question;
import entities.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kazaf on 16/10/16.
 */
@Component
public class JMSTemplate {

    @Autowired
    @Qualifier("jmsTemplate")
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("QuestionJMS")
    private JmsTemplate QuestionJMS;

    @Autowired
    @Qualifier("RequestJMS")
    private JmsTemplate RequestJMS;

    public JmsTemplate getQuestionJMS() {
        return QuestionJMS;
    }
    public void setQuestionJMS(JmsTemplate questionJMS) {
        QuestionJMS = questionJMS;
    }

    public JmsTemplate getRequestJMS() {
        return RequestJMS;
    }
    public void setRequestJMS(JmsTemplate requestJMS) {
        RequestJMS = requestJMS;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Autowired
    @Qualifier("responseQueue")
    private Destination responseDestination;

    public void sendMessage(Destination destination, final String message) {
        System.out.println("---------------生产者发送消息-----------------");
        System.out.println("---------------生产者发了一个消息：" + message);
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                //textMessage.setJMSReplyTo(responseDestination);
                return textMessage;
            }
        });
    }

    public String receiveMessage(Destination destination){
        TextMessage textMessag=(TextMessage)jmsTemplate.receive(destination);
        System.out.println(textMessag);
        String message="";
        try{
            message=textMessag.getText();}
        catch (Exception e){
            e.printStackTrace();
        }
        return message;

    }

    public void sendQuestion(Destination destination, Question question){
        QuestionJMS.convertAndSend(destination,question);
    }

    public void sendRequest(Destination destination, Request request){
        RequestJMS.convertAndSend(destination,request);
    }

    public List<Request> receiveRequest(Destination destination){
        Request request;
        List<Request> requestList=new ArrayList<Request>();
        while(true){
            request=(Request)RequestJMS.receiveAndConvert(destination);
            if(null!=request){requestList.add(request);}
            else {break;}
        }
        return requestList;
    }

    public List<Question> receiveQuestion(Destination destination){

        Question question;
        List<Question> questionList=new ArrayList<Question>();
        while(true){
            question=(Question) QuestionJMS.receiveAndConvert(destination);
            System.out.println(question.getQuestion_text()+"  from:"+question.getQuestion_from()+"   to:"+question.getQuestion_to());
            if(null!=question){questionList.add(question);}
            else {break;}
        }
        return questionList;
    }


}