package configuration;

import function.UserFunction;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import shiro.MyRealm;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kazaf on 16-11-7.
 */
@Configuration
@Import(DataConfig.class)
public class ShiroConfig {

    @Resource
    private UserFunction userFunction;

    @Bean
    public MyRealm myRealm(){
        MyRealm myRealm=new MyRealm();
        myRealm.setUserFunction(userFunction);
        return myRealm;
    }

    @Bean
    public DefaultSecurityManager securityManager(){
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        securityManager.setRealm(myRealm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shitoFilter=new ShiroFilterFactoryBean();
        shitoFilter.setSecurityManager(securityManager());
        shitoFilter.setLoginUrl("/index");
        shitoFilter.setUnauthorizedUrl("/unauthorized");
        //Map<String,String> chaiMap=new HashMap<String,String>();
        //chaiMap.put(" "," ");
        //shitoFilter.setFilterChainDefinitionMap(new HashMap<String,String>());

        return shitoFilter;
    }
}
