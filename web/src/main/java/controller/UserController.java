package controller;

import entities.Question;
import entities.Request;
import entities.User;
import function.FriendsFunction;
import function.QuestionFunction;
import function.RequestFunction;
import function.UserFunction;
import jms.JMSTemplate;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kazaf on 16/10/7.
 */

@Controller
public class UserController {

    @Resource
    private UserFunction userFunction;
    @Resource
    private FriendsFunction friendsFunction;
    @Resource
    private RequestFunction requestFunction;
    @Resource
    private QuestionFunction questionFunction;

    @Qualifier("user3questionList")
    private ArrayList<Question> userquestionList;

    @Autowired
    HttpSession session;

    @Autowired
    private JMSTemplate jmsTemplate;
    //private User user;

    @RequestMapping(value = "/regi")
    public String UserRegister(HttpServletRequest req, HttpServletResponse resp){
        User user=new User();
        user.setUsername(req.getParameter("username"));
        user.setUserpassword(req.getParameter("password"));
        user.setUsermail(req.getParameter("email"));
        //TODO 注册的时候建立个以自己id的topic,保存在ActiveMQ服务器中,以后结交好友后,好友可直接订阅
        String result=userFunction.Register(user);
        if(result.equals("index")){
            String email=user.getUsermail();
            int id=userFunction.GetUserID(email);
            user.setUserid(id);
            //ActiveMQTopic usertopic=new ActiveMQTopic(user.getUserid()+"topic");
        }
        return result;
    }

    @RequestMapping(value = "/login")
    public String UserLogin(HttpServletRequest req, HttpServletResponse resp){
        User user=new User();
        user.setUsermail(req.getParameter("email"));
        user.setUserpassword(req.getParameter("password"));
        user=userFunction.Login(user);
        if(user.getUsername() != null) {
            /**
             * 登陆后 应该获得:
             *
             * 1.好友列表:好友列表可通过FriendsFunction.ShowFriends(userid)罗列出来,并存储在session中,如果有新的变化时
             *   可以先修改session中的attribute,再修改数据库中的表
             *
             * 2.好友新鲜事:好友新鲜事可通过多个ActiveMQTopic的订阅来获取,为每一个好友建立一个Topic(这里可以想个办法池化的管理
             *   所有Topic),然后一一的订阅所有好友Topic(friendID+"news"),把所有消息读出存储到session中,每次刷新页面时.........
             *
             * 3.新的问答请求:新的问答请求应该通过订阅自己的Queue来实现,该Queue为userID+"question",
             *
             * 4.新的好友请求:新的好友申请应该通过订阅自己的Queue来实现,该Queue为userID+"friends"
             *
             * */
            this.ActiveMQHelper(req,resp,user);
            List<User> friendsList=friendsFunction.ShowFriends(user.getUserid());
            req.getSession().setAttribute("friendslist",friendsList);//1.好友列表
            req.getSession().setAttribute("user",user);//将user信息保存在session中 这样登陆后不需要每次都查询userid
            req.getSession().setAttribute("username",user.getUsername());

            List<Request> requestList=requestFunction.listRequest(user.getUserid());
            req.getSession().setAttribute("requestlist",requestList);

            List<Question> questionList=questionFunction.ShowAllQuestions(user.getUserid());
            req.getSession().setAttribute("questionlist",questionList);

            /*
            if(user.getUserid()==3){
                session.setAttribute("userquestionList",userquestionList);
                for(Question question:userquestionList){
                    System.out.println(" from:"+question.getQuestion_from()+" question:"+question.getQuestion_text());
                }
            }*/

            return "home";
        }
        else
        {
        return "passworderror";
        }
    }

    private void ActiveMQHelper(HttpServletRequest req,HttpServletResponse resp,User user){
        ActiveMQQueue friendqueue=new ActiveMQQueue(user.getUserid()+"friend");//自己的好友申请Queue
        ActiveMQQueue questionqueque=new ActiveMQQueue(user.getUserid()+"question");//自己的问题接收Queue



        req.getSession().setAttribute("friendqueue",friendqueue);
        req.getSession().setAttribute("questionqueue",questionqueque);
    }

    public String UserUpdate(){
        //TODO
        return "";
    }




}
