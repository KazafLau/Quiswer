import configuration.AppConfig;
import configuration.DataConfig;
import function.UserFunction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by kazaf on 16-11-6.
 */
public class testDruid {

    //@Test
    public void testGetUser(){
        ApplicationContext cx= new AnnotationConfigApplicationContext(AppConfig.class);
        UserFunction userFunction=(UserFunction)cx.getBean(UserFunction.class);
        System.out.println(userFunction.GetUserDAO(30).getUsername());
    }
}
