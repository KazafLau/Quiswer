package function;

import conf.Conf;
import entities.Friends;
import entities.User;

import javax.annotation.Resource;


/**
 * Created by kazaf on 16-10-1.
 */
public class UserFunction {



    @Resource
    private Conf conf;

    public String Register(User user){
        if(conf.getUserDao().getUser(user.getUserid())!=null)
        return user.getUsername()+" already registered";
        else {
            conf.getUserDao().insertUser(user);
            conf.getSession().commit();
            return user.getUsername()+" register successfully";
        }
    }

    public String Modify(User user){
        return "";
    }

    public String Login(User user){
        User tempuser=conf.getUserDao().getUserbyEmail(user.getUsermail());
        if(tempuser==null) return "email has not registered";
        else
        {
            if (user.getPassword().equals(tempuser.getPassword()))
             return "login successfully";
            else return "password is wrong";
        }
    }
}
