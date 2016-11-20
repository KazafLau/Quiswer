import configuration.AppConfig;
import configuration.ShiroConfig;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by kazaf on 16-11-10.
 */
public class testShiro {

    public void login(String useremail,String password) {
        ApplicationContext cx = new AnnotationConfigApplicationContext(ShiroConfig.class);
        SecurityManager securityManager = (SecurityManager) cx.getBean("securityManager");
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(useremail,password);
        subject.login(token);
    }

    @After
    public void tearDown() throws Exception{
        ThreadContext.unbindSubject();//退出时解除subject的绑定
    }

    @Test
    public void testLogin(){
        login("liuli525@163.com","19881129");
    }


   // Subject subject=securityManager.createSubject();
}
