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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //@Autowired
   // private JMSTemplate jmsTemplate;
    //private User user;

    @RequestMapping(value = "/regi")
    public String UserRegister(@RequestParam String username,@RequestParam String password,@RequestParam String email){
        User user=new User();
        user.setUsername(username);
        user.setUserpassword(password);
        user.setUsermail(email);
        String result=userFunction.Register(user);
        if(result.equals("index")){
            int id=userFunction.GetUserID(email);
            user.setUserid(id);
        }
        return result;
    }

    @RequestMapping(value = "/login")
    public ModelAndView UserLogin(@RequestParam String email,@RequestParam String password,HttpSession session){
        User user=new User();
        user.setUsermail(email);
        user.setUserpassword(password);
        user=userFunction.Login(user);
        if(user.getUsername() != null) {
            ModelAndView modelandview=new ModelAndView();
            session.setAttribute("user",user);
            session.setAttribute("requestmap",requestFunction.MapRequestwithName(user.getUserid()));
            session.setAttribute("questionmap",questionFunction.ShowALLQuestionswithName(user.getUserid()));
            session.setAttribute("friendslist",friendsFunction.ShowFriends(user.getUserid()));
            modelandview.addObject("username",user.getUsername());
            modelandview.addObject("userid",user.getUserid());
           // modelandview.addObject("requestmap",requestFunction.MapRequestwithName(user.getUserid()));
            //modelandview.addObject("questionmap",questionFunction.ShowALLQuestionswithName(user.getUserid()));
           // modelandview.addObject("friendslist",friendsFunction.ShowFriends(user.getUserid()));
            modelandview.setViewName("home");
            return modelandview;
        }
        else
        {
        return new ModelAndView("passworderror");
        }
    }


    public String UserUpdate(){
        //TODO
        return "";
    }




}
