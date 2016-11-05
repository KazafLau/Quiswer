package function;

import conf.Conf;
import entities.Question;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kazaf on 16-10-6.
 */
@Component
public class QuestionFunction {


    @Resource
    private Conf conf;

    public String AddQuestion(Question question){
        conf.getQuestionDao().addquestion(question);
        conf.getSession().commit();
        return "ok";
    }

    public String AnswerQuestion(int questionid,String answer){
        Question question=conf.getQuestionDao().findquestion(questionid);
        if(question==null){
            return  "the question_id is wrong ";
        }else{
            int userid=question.getQuestion_to();
            int friendid=question.getQuestion_from();
            question.setQuestion_answer(answer);
            //question.setAnswer_time(new Timestamp(System.currentTimeMillis()));
            conf.getQuestionDao().answerquestion(question);
            conf.getSession().commit();

        return "the question is answered successfully!";
        }
    }

    public List<Question> ShowAllQuestions(int userid){

        return (ArrayList)conf.getQuestionDao().showallquestions(userid);
    }


    public Map<Question,String> ShowALLQuestionswithName(int userid){
        Map<Question,String> questionMap=new HashMap<Question, String>();
        for(Question question:ShowAllQuestions(userid)){
            questionMap.put(question,conf.getUserDao().getUserfromID(question.getQuestion_from()).getUsername());//这里获取的都是活跃客户的问题，如果遇到已经注销客户的问题则查询不到userid
        }
        return questionMap;
    }
}
