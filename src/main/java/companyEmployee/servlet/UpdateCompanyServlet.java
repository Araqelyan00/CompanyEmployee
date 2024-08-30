package companyEmployee.servlet;

import companyEmployee.manager.CompanyManager;
import companyEmployee.model.Company;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateCompany")
public class UpdateCompanyServlet extends HttpServlet {
    private CompanyManager companyManager = new CompanyManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int companyId = Integer.parseInt(req.getParameter("companyId"));
        Company company = companyManager.getCompanyByID(companyId);
        req.setAttribute("company", company);
        req.getRequestDispatcher("WEB-INF/updateCompany.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int companyId = Integer.parseInt(req.getParameter("companyId"));
        String companyName = req.getParameter("companyName");
        String companyCountry = req.getParameter("companyCountry");
        Company company = new Company(companyId, companyName, companyCountry);
        companyManager.updateCompany(company);
        resp.sendRedirect("/companies");
    }
}
