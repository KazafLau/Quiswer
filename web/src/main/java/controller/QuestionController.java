package controller;

import entities.Question;
import entities.User;
import function.QuestionFunction;
import jms.JMSTemplate;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by kazaf on 16-11-5.
 */
@Controller
public class QuestionController {

    @Resource
    private QuestionFunction questionFunction;

    //@Autowired
    //private JMSTemplate jmsTemplate;

    @RequestMapping(value = "/Nomainate")
    public String AddQuestion(HttpSession session, @RequestParam String privacy,@RequestParam int friendID,@RequestParam String question){
        User tempuser=(User)session.getAttribute("user");
        int privacynum=0;
        if(privacy.equals("public")){privacynum=1;}
        Question question_temp=new Question();
        question_temp.setQuestion_text(question);
        question_temp.setQuestion_from(tempuser.getUserid());
        question_temp.setQuestion_to(friendID);
        question_temp.setQuestion_privacy(privacynum);
        questionFunction.AddQuestion(question_temp);
        return "asksuccess";
    }

    @RequestMapping(value = "/Answer")
    public ModelAndView AnswerQuestion(@RequestParam int questionID,@RequestParam String questionanswer){
        questionFunction.AnswerQuestion(questionID,questionanswer);
        return  new ModelAndView("answersuccess");
    }

}
