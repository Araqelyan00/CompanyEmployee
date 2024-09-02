package companyEmployee.servlet.employee;

import companyEmployee.manager.EmployeeManager;
import companyEmployee.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        req.setAttribute("employees", result);
        req.getRequestDispatcher("WEB-INF/employees.jsp").forward(req, resp);
    }
}
