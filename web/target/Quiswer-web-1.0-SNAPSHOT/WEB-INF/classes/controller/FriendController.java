package controller;

import entities.Question;
import entities.Request;
import entities.User;
import function.QuestionFunction;
import function.RequestFunction;
import jms.JMSTemplate;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

/**
 * Created by Kazaf on 16/10/7.
 */
@Controller
public class FriendController {

    @Resource
    private RequestFunction requestFunction;
    @Resource
    private QuestionFunction questionFunction;

    @Autowired
    private JMSTemplate jmsTemplate;

    @Autowired
    private HttpSession session;


    public String FriendRequest(HttpServletRequest req, HttpServletResponse resp){
        //TODO 申请添加好友申请
        String message=null;
        User user=(User) req.getSession().getAttribute("user");
        if(user==null){
            System.out.println("Login wrong that the session has no user----from FriendController.FriendRequst");
        }else{
            int friendID=Integer.parseInt(req.getParameter("friendID"));
            if(requestFunction.addRequest(user.getUserid(),friendID,req.getParameter("text")).equals("ok")){
                message="request sent successfully";
            }
        }
        return message;
    }

    @RequestMapping(value = "/Nomainate")
    public String NomanaitedFriend(HttpServletRequest req, HttpServletResponse resp){
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

    public String AnswerRequest(HttpServletRequest req,HttpServletResponse resp){
        String message=null;
        Request request;
        int request_id=Integer.parseInt(req.getParameter("request_id"));
        request=requestFunction.findRequest(request_id);
        //页面上 有"同意"和"拒绝"两个选项,都是通过修改request_state来表示出来,1表示同意,2表示拒绝
        int answer;
        if(req.getParameter("answer").equals("yes")){answer=1;}
        else {answer=2;}
        request.setRequest_state(answer);
        message=requestFunction.responseRequest(request);
        return message;
    }

}
