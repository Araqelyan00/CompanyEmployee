package companyEmployee.listener;

import companyEmployee.model.User;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

import java.util.Date;

public class MySessionAttributeListener implements HttpSessionAttributeListener {
    public void attributeAdded(HttpSessionBindingEvent event) {
        String id = event.getSession().getId();
        String attrName = event.getName();
        User attrValue = (User) event.getValue();

        if (attrName.equalsIgnoreCase("user")){
            System.out.println("User with " + attrValue.getUserEmail() + " email Logged in at " + new Date() + " Session ID = " + id);
        }
    }
}
