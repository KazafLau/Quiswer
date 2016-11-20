package configuration;

import jms.JMSConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Kazaf on 16/10/7.
 */
@Configuration
@Import(ShiroConfig.class)
public class AppConfig {


}
