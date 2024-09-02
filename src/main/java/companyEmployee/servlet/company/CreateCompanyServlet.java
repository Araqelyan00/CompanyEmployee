package companyEmployee.servlet.company;

import companyEmployee.manager.CompanyManager;
import companyEmployee.model.Company;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@WebServlet("/createCompany")
public class CreateCompanyServlet extends HttpServlet {
    private CompanyManager companyManager = new CompanyManager();
    private List<String> countries = Arrays.asList("Armenia", "Russia", "USA", "Canada", "France", "Germany");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("countriesList", countries);
        req.getRequestDispatcher("WEB-INF/createCompany.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String country = req.getParameter("country");
        Company company = new Company();
        company.setCompanyName(name);
        company.setCompanyCountry(country);
        companyManager.saveCompany(company);
        resp.sendRedirect("/companies");
    }
}
