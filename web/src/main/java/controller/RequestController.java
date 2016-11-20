package controller;

import entities.Question;
import entities.Request;
import entities.User;
import function.QuestionFunction;
import function.RequestFunction;
import function.UserFunction;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by Kazaf on 16/10/7.
 */
@Controller
public class RequestController {

    @Resource
    private RequestFunction requestFunction;

    @Resource
    private UserFunction userFunction;

    private String answerrequest;



    @Autowired
    private HttpSession session;


    @RequestMapping(value = "/addfriend")
    public String FriendRequest(@RequestParam int friendID,@RequestParam String message,HttpSession session){
        User user=(User) session.getAttribute("user");
        if(user==null){
            System.out.println("Login wrong that the session has no user----from FriendController.FriendRequst");
            return "FriendWrong";
        }else{
            //int friendID=Integer.parseInt(req.getParameter("friendID"));
         requestFunction.addRequest(user.getUserid(),friendID,message);
            return "friendrequestsentok";
        }

    }


    @RequestMapping(value = "/friendrequest")
    public ModelAndView AnswerRequest(@RequestParam int request_id, @RequestParam int answer,@RequestParam String request_from,HttpSession session){
        Request request=requestFunction.findRequest(request_id);
        request.setRequest_state(answer);
        answerrequest=requestFunction.responseRequest(request);
        System.out.println(request.getRequest_to()+" "+answerrequest+" "+request.getRequest_from());
        ModelAndView modelAndView=new ModelAndView();
        String model="you "+answerrequest+" "+request_from;
        modelAndView.addObject("sentence",model);
        modelAndView.setViewName(answerrequest);
        Map<Request,String> requestMap=(Map<Request,String>) session.getAttribute("requestmap");
        requestMap.remove(request);
        session.setAttribute("requestmap",requestMap);
        if(answer==1){
            List<User> friendlist=(List<User>) session.getAttribute("friendslist");
            friendlist.add(userFunction.GetActiveUser(request.getRequest_from()));
            session.setAttribute("friendslist",friendlist);
        }
        return modelAndView;
    }

}
