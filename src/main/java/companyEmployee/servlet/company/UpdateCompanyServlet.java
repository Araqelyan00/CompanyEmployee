package companyEmployee.servlet.company;

import companyEmployee.manager.CompanyManager;
import companyEmployee.model.Company;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateCompany")
public class UpdateCompanyServlet extends HttpServlet {
    private CompanyManager companyManager = new CompanyManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int companyId = Integer.parseInt(req.getParameter("companyID")); // Ensure parameter name matches JSP
            Company company = companyManager.getCompanyByID(companyId);
            req.setAttribute("company", company);
            req.getRequestDispatcher("WEB-INF/updateCompany.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid company ID");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            int companyId = Integer.parseInt(req.getParameter("companyID"));
            String companyName = req.getParameter("companyName");
            String companyCountry = req.getParameter("companyCountry");
            Company company = new Company(companyId, companyName, companyCountry);
            companyManager.updateCompany(company);
            resp.sendRedirect("/companies");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid company ID");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating the company");
        }
    }
}
