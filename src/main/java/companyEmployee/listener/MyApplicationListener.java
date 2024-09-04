package companyEmployee.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.util.Date;

public class MyApplicationListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Server started at " + new Date());
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Server stopped at " + new Date());
    }
}
