package kazaflau;

import entities.Question;
import entities.Request;
import entities.User;
import function.FriendsFunction;
import function.QuestionFunction;
import function.RequestFunction;
import function.UserFunction;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

/**
 * Created by kazaf on 16-10-3.
 */
public class TestUserFunciton {



    private static UserFunction userFunction;


    //@BeforeClass
    public static void init(){
        ApplicationContext cx= new AnnotationConfigApplicationContext("configuration.DataConfig");
        userFunction=(UserFunction)cx.getBean(UserFunction.class);
    }


    //@Test
    public void testRegister(){
        User user=new User("Ozil","19881129","Ozil@163.com");
        System.out.println("========testRegister========");
        System.out.println(userFunction.Register(user));
        //注册的时候 应该注意防止邮箱重复
    }

    //@Test
    public void testLogin(){
        User user=new User("Kucka","19881129","kukall@163.com");
        System.out.println("========testLogin========");
        System.out.println(userFunction.Login(user).getUserid());
        //当存在 多个重复邮箱时 需要解决一下
    }

    //@Test
    public void testShowAllUsers(){
        System.out.println("========testShowAllUsers========");
        List<User> userList=userFunction.ShowAllUsers();
        for(User user:userList){
        System.out.println(user.getUserid()+"    "+user.getUsername()+"    "+user.getUserpassword()+"    "+user.getUsermail());
        }
    }

    //@Test
    public void testGetUserID(){
        String useremail="liu.kazaf@icloud.com";
        System.out.println("========testGetUserID========");
        System.out.println(userFunction.GetUserID(useremail));

    }

   // @Test
    public void testDAOGetUser(){
        System.out.println(userFunction.GetUserDAO(3).getUsername());

    }


}
