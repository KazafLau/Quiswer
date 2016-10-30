package jms.spring;

import entities.Question;
import jms.JMSConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kazaf on 16/10/28.
 */
public class QuestionListener  {

    @Autowired
    //@Qualifier("user3questionList")
    private ArrayList<Question> user3questionlist;

    public void handleMessage(String message) {
        System.out.println("QuestionListener通过handleMessage接收到一个纯文本消息，消息内容是：" + message);
    }

    public String receiveMessage(String message) {
        System.out.println("QuestionListener通过receiveMessage接收到一个纯文本消息，消息内容是：" + message);
        return "这是QuestionListener对象的receiveMessage方法的返回值。";
    }

    public void receiveMessage(Question question){
        //TODO 应该把收到的question都放到一个池里面,每次从池里取出新的question

        user3questionlist.add(question);





        System.out.println("=========user3questionlist number:"+user3questionlist.size());
        System.out.println("QuestionListener通过receiveMessage(Question question)接收到一个纯文本消息，消息内容是：" + question.getQuestion_text());
    }
}
