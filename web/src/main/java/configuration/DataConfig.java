package configuration;

/**
 * Created by kazaf on 16-11-6.
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan(basePackages = "dao")
@ComponentScan(basePackages = "function")
public class DataConfig {


    @Bean
    public DataSource dataSource(){
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/Quiswer?useUnicode=true&amp;characterEncoding=UTF-8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("19881129");
        druidDataSource.setInitialSize(0);
        druidDataSource.setMaxActive(20);
        druidDataSource.setMinIdle(0);
        druidDataSource.setMaxWait(60000);

        return druidDataSource;
    }

    @Bean
    public DruidStatInterceptor druidStatInterceptor(){
        return  new DruidStatInterceptor();
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws IOException{
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        ResourcePatternResolver resource=new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resource.getResources("classpath:mapper/*Mapper.xml"));
        return sqlSessionFactoryBean;
    }


}
