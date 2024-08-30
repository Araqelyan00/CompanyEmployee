package companyEmployee.servlet;

import companyEmployee.manager.CompanyManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeCompany")
public class RemoveCompanyServlet extends HttpServlet {
    private CompanyManager companyManager = new CompanyManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        companyManager.removeByCompanyId(id);
        resp.sendRedirect("/companies");
    }
}
