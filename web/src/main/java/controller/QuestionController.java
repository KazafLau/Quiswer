package controller;

import entities.Question;
import entities.User;
import function.QuestionFunction;
import jms.JMSTemplate;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kazaf on 16-11-5.
 */
@Controller
public class QuestionController {

    @Resource
    private QuestionFunction questionFunction;

    @Autowired
    private JMSTemplate jmsTemplate;

    @RequestMapping(value = "/Nomainate")
    public String AddQuestion(HttpServletRequest req, HttpServletResponse resp){
        //TODO 点名好友发送问题,该点名为p2p的Queue队列,从页面获取1点名者的ID(friendID),2问题内容(question),问卷内容通过("question"),
        User tempuser=(User)req.getSession().getAttribute("user");
        int privacy=0;
        if(req.getParameter("privacy").equals("public")){privacy=1;}
        int friendID=Integer.parseInt(req.getParameter("friendID"));
        //Question question=new Question(req.getParameter("question"),friendID,tempuser.getUserid(),privacy,new Timestamp(System.currentTimeMillis()));
        Question question=new Question();
        question.setQuestion_text(req.getParameter("question"));
        question.setQuestion_from(tempuser.getUserid());
        question.setQuestion_to(friendID);
        question.setQuestion_privacy(privacy);
        questionFunction.AddQuestion(question);
        ActiveMQQueue nomainate=new ActiveMQQueue(friendID+"question");//TODO 这里应该有个ActiveMQQueue的池,从里面取出来相应ID的QUeue
        jmsTemplate.sendQuestion(nomainate,question);
        return "asksuccess";
    }

    @RequestMapping(value = "/Answer")
    public ModelAndView AnswerQuestion(HttpServletRequest req, HttpServletResponse resp){
        int questionID=Integer.parseInt(req.getParameter("questionID"));
        String answer=req.getParameter("questionanswer");
        questionFunction.AnswerQuestion(questionID,answer);
        return  new ModelAndView("answersuccess");
    }

}
