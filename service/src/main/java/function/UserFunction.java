package function;

import conf.Conf;
import dao.UserDao;
import entities.Friends;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    private  UserDao userDao;

    public String Register(User user){

        if(userDao.getUserbyEmail(user.getUseremail())!=null)
        {System.out.println("UserFunction--Register:"+user.getUsername()+" already registered");
        return "registererror";}
        else {
            userDao.insertUser(user);
           // conf.getSession().commit();
            System.out.println("UserFunction--Register:"+user.getUsername()+" register successfully");
            return "index";
        }
    }

    public String Modify(User user){
        return "";
    }

    public User Login(User user){
        User tempuser=userDao.getUserbyEmail(user.getUsermail());
        if(tempuser==null){
            System.out.println("UserFunction--Login:email has not registered");}
        else
        {
            if (user.getUserpassword().equals(tempuser.getUserpassword()))
            {
                System.out.println("UserFunction--Login:login successfully");
                uSer=tempuser;
            }
            else
            {   System.out.println("UserFunction--Login:the database password:"+tempuser.getUserpassword());
                System.out.println("UserFunction--Login:the user password:"+user.getUserpassword());
                System.out.println("UserFunction--Login:password is wrong");}
        }

        return tempuser;
    }

    public List<User> ShowAllUsers(){
       List<User> userList= userDao.showallusers();
        return  userList;
    }

    public int GetUserID(String useremail){
        User tempuser=userDao.getUserbyEmail(useremail);
        if(tempuser==null){
            System.out.println("UserFunction--GetUserID:email has not registered");
            return 0;}
        else
        {
            System.out.println("UserFunction--GetUserID:Get userid successfully");
            return tempuser.getUserid();
        }
    }

    public User GetActiveUser(int userid){
        User tempuser=userDao.getActiveUser(userid);
        if(tempuser==null){
            System.out.println("UserFunction--GetActiveUser:userid has not registered");
            return null;}
        else
        {
            System.out.println("UserFunction--GetActiveUser:Get user successfully");
            return tempuser;
        }
    }

    public User GetUserDAO(int userid){
        return userDao.getUser(userid);
    }

    public List<User> ShowUsersWithName(String username){
        if(userDao.showUserswithName(username)==null){
            System.out.println("UserFunction--ShowUsersWithName:there's no user with this name");
        }
        return userDao.showUserswithName(username);
    }

}
