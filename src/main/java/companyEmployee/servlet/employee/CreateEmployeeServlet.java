package companyEmployee.servlet.employee;

import companyEmployee.constants.SharedConstant;
import companyEmployee.manager.CompanyManager;
import companyEmployee.manager.EmployeeManager;
import companyEmployee.model.Company;
import companyEmployee.model.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.List;

@WebServlet("/createEmployee")
@MultipartConfig(maxFileSize = 1024*1024*5, maxRequestSize = 1024*1024*10, fileSizeThreshold = 1024*1024)
public class CreateEmployeeServlet extends HttpServlet {

    private EmployeeManager employeeManager = new EmployeeManager();
    private CompanyManager companyManager = new CompanyManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Company> all = companyManager.getAll();
        req.setAttribute("company", all);
        req.getRequestDispatcher("WEB-INF/createEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("employeeName");
        String surname = req.getParameter("employeeSurname");
        String email = req.getParameter("employeeEmail");
        int companyId = Integer.parseInt(req.getParameter("companyID"));
        Part profilePicPart = req.getPart("employeePicLink");
        String pictureName = null;
        if (profilePicPart != null && profilePicPart.getSize() > 0) {
            pictureName = System.nanoTime() + "_" + profilePicPart.getSubmittedFileName();
            profilePicPart.write(SharedConstant.UPLOAD_FOLDER + pictureName);
        }

        Employee employee = Employee.builder()
                .employeeName(name)
                .employeeSurname(surname)
                .employeeEmail(email)
                .employeePicName(pictureName)
                .company(companyManager.getCompanyByID(companyId))
                .build();

        employeeManager.saveEmployee(employee);
        resp.sendRedirect("/employees");

    }
}
