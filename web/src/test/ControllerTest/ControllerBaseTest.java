package ControllerTest;

import static org.junit.Assert.*;

import controller.UserController;
import entities.Question;
import entities.Request;
import entities.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.session.HttpServletSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.mock.web.portlet.MockActionResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by kazaf on 16-11-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/xmlconfig/springmvc.xml")
public abstract class ControllerBaseTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    protected HttpSession session;

    @Resource
    private UserController userController;

    @Before
    public void setUp(){
        request=new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response=new MockHttpServletResponse();

        String resource = "classpath:/xmlconfig/springmvc.xml";
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext(resource);
        org.apache.shiro.mgt.SecurityManager securityManager =
                (org.apache.shiro.mgt.SecurityManager)appCtx.getBean("securityManager");
        SecurityUtils.setSecurityManager(securityManager);

        User user1=new User("liuli525@163.com","19881129");
        User user2=new User("ArsenalGunner@yahoo.com","fabiansky");

        Login(user2);
        SessionAttributes();

    }

    private void Login(User user){
        request.setParameter("email",user.getUseremail());
        request.setParameter("password",user.getUserpassword());
        session=request.getSession();
        userController.UserLogin(user.getUseremail(),user.getUserpassword(),session);

    }

    private void SessionAttributes(){
        Map<Request,String> requestmap=(Map<Request,String>)session.getAttribute("requestmap");
        if(requestmap.keySet().isEmpty()!=true)
        {
            System.out.println("======================requestmap================================");
            for(Request request1:requestmap.keySet()){
                System.out.println(requestmap.get(request1)+"  "+request1.getRequest_message());
            }
            System.out.println("================================================================");
        }

        Map<Question,String> questionmap=(Map<Question,String>)session.getAttribute("questionmap");
        if(questionmap.keySet().isEmpty()!=true){
            System.out.println("======================questionmap================================");
            for(Question question:questionmap.keySet()){
                System.out.println(questionmap.get(question)+" asks: "+question.getQuestion_text()+"  id:"+question.getQuestion_id());
            }
            System.out.println("=================================================================");
        }

        List<User> userList=(List<User>)session.getAttribute("friendslist");
        if(userList!=null){
            System.out.println("======================friendslist================================");
            for (User user:userList){
                System.out.println(user.getUsername()+" "+user.getUserid());
            }
            System.out.println("==============================================================");
        }

    }


}
