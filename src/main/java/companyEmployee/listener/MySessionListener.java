package companyEmployee.listener;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.Date;

public class MySessionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent se) {
        String sessionId = se.getSession().getId();
        System.out.println("Session created at " + new Date() + " Session ID = " + sessionId);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        String sessionId = se.getSession().getId();
        System.out.println("Session destroyed at " + new Date() + " Session ID = " + sessionId);
    }
}
