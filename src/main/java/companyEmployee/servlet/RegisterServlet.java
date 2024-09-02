package companyEmployee.servlet;

import companyEmployee.manager.UserManager;
import companyEmployee.model.User;
import companyEmployee.model.UserType;
import companyEmployee.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserManager userManager = new UserManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserType type = UserType.valueOf(req.getParameter("type"));
        String str = "";

        if (name == null || name.trim().equals("")) {
            str += "Name is reqiered <br/>";
        }
        if (surname == null || surname.trim().equals("")) {
            str += "Surname is reqiered <br/>";
        }
        if (email == null || email.trim().equals("")) {
            str += "Email is reqiered <br/>";
        } else if (!EmailUtil.patternMatches(email)){
            str += "Email is incorrect <br/>";
        }

        if (password == null || password.trim().equals("")) {
            str += "Password is reqiered <br/>";
        } else if (password.length() < 6) {
            str += "Password length must be larger then 6 symbols. <br/>";
        }

        if (str.equals("")) {
            User user = userManager.getUserByEmail(email);
            if (user == null) {
                userManager.saveUser(User.builder()
                                .userName(name)
                                .userSurname(surname)
                                .userEmail(email)
                                .userPassword(password)
                                .userType(type)
                                .build());
            }
            resp.sendRedirect("/");
        } else {
            req.setAttribute("str", str);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }
}
