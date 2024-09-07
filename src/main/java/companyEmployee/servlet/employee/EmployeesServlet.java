package companyEmployee.servlet.employee;

import companyEmployee.manager.EmployeeManager;
import companyEmployee.model.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/employees")
public class EmployeesServlet extends HttpServlet {

    private EmployeeManager employeeManager = new EmployeeManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Employee> result = null;
        int noOfPages = 1;
        int page = 1;
        if (keyword == null || keyword.equals("")) {
            int recordsPerPage = 4;
            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            }
            result = employeeManager.viewAllEmployees((page-1)*recordsPerPage, recordsPerPage);
            int noOfRecords = employeeManager.getNoOfRecords();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        } else {
            result = employeeManager.search(keyword);
        }

        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("employee", result);
        req.getRequestDispatcher("WEB-INF/employees.jsp").forward(req, resp);
    }
}
