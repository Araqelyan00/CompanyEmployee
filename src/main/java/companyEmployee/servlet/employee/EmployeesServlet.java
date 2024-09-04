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
        if (keyword == null || keyword.equals("")) {
            result = employeeManager.getAllEmployees();
        } else {
            result = employeeManager.search(keyword);
        }
        req.setAttribute("employee", result);
        req.getRequestDispatcher("WEB-INF/employees.jsp").forward(req, resp);
    }
}
