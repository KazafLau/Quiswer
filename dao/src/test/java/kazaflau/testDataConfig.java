package kazaflau;

import dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by kazaf on 16-11-6.
 */
public class testDataConfig {

    public void testdataconfif(){
        ApplicationContext cx=new AnnotationConfigApplicationContext("configuration.DataConfig");


    }
}
