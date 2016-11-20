package configuration;

import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by kazaf on 16-10-6.
 */
public class SpringWebApplicationInitializer /*extends AbstractAnnotationConfigDispatcherServletInitializer*/ {

    //@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //super.onStartup(servletContext);
        servletContext.addListener(new RequestContextListener());

    }

    //@Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //@Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    //@Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SpringMVCConfig.class};
    }


    /*
    @Override
    protected Filter[] getServletFilters() {
        DelegatingFilterProxy shiroFilter=new DelegatingFilterProxy();
        shiroFilter.setTargetFilterLifecycle(true);
        return new Filter[]{shiroFilter};
    }*/

}
