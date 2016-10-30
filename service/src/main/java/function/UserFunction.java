package function;

import conf.Conf;
import entities.Friends;
import entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sound.midi.SysexMessage;
import java.util.List;


/**
 * Created by kazaf on 16-10-1.
 */
@Component
public class UserFunction {

   private User uSer;

    public User getUser() {
        return uSer;
    }

    public void setUser(User uSer) {
        this.uSer = uSer;
    }

    @Resource
    private Conf conf;

    public String Register(User user){

        if(conf.getUserDao().getUserbyEmail(user.getUseremail())!=null)
        {System.out.println(user.getUsername()+" already registered");
        return "registererror";}
        else {
            conf.getUserDao().insertUser(user);
            conf.getSession().commit();
            System.out.println(user.getUsername()+" register successfully");
            return "index";
        }
    }

    public String Modify(User user){
        return "";
    }

    public User Login(User user){
        User tempuser=conf.getUserDao().getUserbyEmail(user.getUsermail());
        if(tempuser==null){
            System.out.println("email has not registered");}
        else
        {
            if (user.getUserpassword().equals(tempuser.getUserpassword()))
            {
                System.out.println("login successfully");
                uSer=tempuser;
            }
            else
            {   System.out.println("the database password:"+tempuser.getUserpassword());
                System.out.println("the user password:"+user.getUserpassword());
                System.out.println("password is wrong");}
        }

        return tempuser;
    }

    public List<User> ShowAllUsers(){
       List<User> userList= conf.getUserDao().showallusers();
        return  userList;
    }

    public int GetUserID(String useremail){
        User tempuser=conf.getUserDao().getUserbyEmail(useremail);
        if(tempuser==null){
            System.out.println("email has not registered");
            return 0;}
        else
        {
            System.out.println("Get userid successfully");
            return tempuser.getUserid();
        }
    }


}
