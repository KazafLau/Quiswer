package controller;

import entities.User;
import function.FriendsFunction;
import function.UserFunction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by kazaf on 16-11-16.
 */
@Controller
public class FriendsController {

    @Resource
    private FriendsFunction friendsFunction;
    @Resource
    private UserFunction userFunction;

    @RequestMapping(value = "/searchfriend")
    public ModelAndView FriendSearch(@RequestParam String friendname, HttpSession session){
        List<User> friendlist=(List<User>) session.getAttribute("friendslist");
        List<User> friendwithname=(List<User>)userFunction.ShowUsersWithName(friendname);
        Map<User,String> friendmap=new HashMap<>();

        for(User user1:friendwithname){
            for(User user2:friendlist){
                if(user1.getUserid()==user2.getUserid()){
                    friendmap.put(user1,"already friend");
                    friendwithname.remove(user1);
                    break;
                }
            }
        }
        for(User user3:friendwithname){
            friendmap.put(user3,"not friend");
        }

        pritnMap(friendmap);

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("friendswithname",friendmap);
        modelAndView.setViewName("friendsearch");
        return modelAndView;
    }

    private void pritnMap(Map<User,String> a){
        a=new HashMap<User,String>();
        Iterator iter = a.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
           User temp=(User)entry.getKey();
           String val = (String)entry.getValue();
            System.out.println(temp.getUsername()+" "+temp.getUserid()+" "+val);
            }

    }
}
