package companyEmployee.servlet.employee;

import companyEmployee.constants.SharedConstant;
import companyEmployee.manager.EmployeeManager;
import companyEmployee.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


@WebServlet("/removeEmployee")
public class RemoveEmployeeServlet extends HttpServlet {

    private EmployeeManager employeeManager = new EmployeeManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
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
