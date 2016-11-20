import static org.junit.Assert.*;

import controller.UserController;
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

/**
 * Created by kazaf on 16-11-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/xmlconfig/springmvc.xml")
public abstract class ControllerBaseTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private HttpSession session;

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

        Login("liuli525@163.com","19881129");


    }

    private void Login(String email,String password){
        request.setParameter("email",email);
        request.setParameter("password",password);
        userController.UserLogin(email,password,session);
        session=request.getSession();
    }


}
