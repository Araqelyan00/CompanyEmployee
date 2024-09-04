package companyEmployee.servlet.other;

import companyEmployee.manager.UserManager;
import companyEmployee.model.User;
import companyEmployee.model.UserType;
import companyEmployee.util.EmailUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserManager userManager = new UserManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("userName");
        String surname = req.getParameter("userSurname");
        String email = req.getParameter("userEmail");
        String password = req.getParameter("userPassword");
        UserType type = UserType.valueOf(req.getParameter("userType"));
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
            req.getRequestDispatcher("WEB-INF/register.jsp").forward(req, resp);
        }
    }
}
