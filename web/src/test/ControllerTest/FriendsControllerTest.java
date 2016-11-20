package ControllerTest;

import controller.FriendsController;
import entities.User;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by kazaf on 16-11-20.
 */
public class FriendsControllerTest extends ControllerBaseTest {

    @Resource
    private FriendsController friendsController;

    @Test
    public void FriendSearch(){
        ModelAndView testmv=new ModelAndView();
        testmv=friendsController.FriendSearch("Kucka",super.session);
        Map<User,String> friendmap=(Map<User,String>)testmv.getModel().get("friendswithname");
        if(friendmap.keySet().isEmpty()!=true){
        for(User user:friendmap.keySet()){
            System.out.println(user.getUsername()+"  "+user.getUserid()+"  "+friendmap.get(user));
        }
       }
    }
}
