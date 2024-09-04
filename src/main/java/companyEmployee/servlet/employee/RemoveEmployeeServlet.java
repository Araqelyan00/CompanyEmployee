package companyEmployee.servlet.employee;

import companyEmployee.constants.SharedConstant;
import companyEmployee.manager.EmployeeManager;
import companyEmployee.model.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


@WebServlet("/removeEmployee")
public class RemoveEmployeeServlet extends HttpServlet {

    private EmployeeManager employeeManager = new EmployeeManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("employeeID"));
        Employee byID = employeeManager.getEmployeeById(id);
        if (byID != null) {
            if (byID.getEmployeePicName() != null || byID.getEmployeePicName().equalsIgnoreCase("null")) {
                File file = new File(SharedConstant.UPLOAD_FOLDER + byID.getEmployeePicName());
                if (file.exists()) {
                    file.delete();
                }
            }
            employeeManager.removeByEmployeeId(id);
        }
        resp.sendRedirect("/employees");
    }
}
