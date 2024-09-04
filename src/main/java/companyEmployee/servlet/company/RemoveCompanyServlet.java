package companyEmployee.servlet.company;

import companyEmployee.manager.CompanyManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeCompany")
public class RemoveCompanyServlet extends HttpServlet {
    private CompanyManager companyManager = new CompanyManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        int id = Integer.parseInt(req.getParameter("companyID"));
//        companyManager.removeByCompanyId(id);
//        resp.sendRedirect("/companies");
        try {
            int id = Integer.parseInt(req.getParameter("companyID")); // Ensure parameter name matches JSP
            companyManager.removeByCompanyId(id);
            resp.sendRedirect("/companies");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid company ID");
        }
    }
}
