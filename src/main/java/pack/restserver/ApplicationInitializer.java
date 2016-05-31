package pack.restserver;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import pack.restserver.config.WebConfig;
import pack.restserver.repository.ClothRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer{

    private final static String DISPATCHER = "dispatcher";

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext cntxt = new AnnotationConfigWebApplicationContext();
        cntxt.register(WebConfig.class);
        servletContext.addListener(new ContextLoaderListener(cntxt));

        ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER, new DispatcherServlet(cntxt));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}
