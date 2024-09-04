package companyEmployee.servlet.company;

import companyEmployee.manager.CompanyManager;
import companyEmployee.model.Company;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/companies")
public class CompaniesServlet extends HttpServlet {

    private CompanyManager companyManager = new CompanyManager();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Company> all = companyManager.getAll();
        req.setAttribute("company", all);
        req.getRequestDispatcher("WEB-INF/companies.jsp").forward(req, resp);
    }
}
