package companyEmployee.servlet;

import companyEmployee.manager.CompanyManager;
import companyEmployee.model.Company;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/companies")
public class CompaniesServlet extends HttpServlet {

    private CompanyManager companyManager = new CompanyManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Company> all = companyManager.getAll();
        req.setAttribute("companies", all);
        req.getRequestDispatcher("WEB-INF/companies.jsp").forward(req, resp);
    }
}
