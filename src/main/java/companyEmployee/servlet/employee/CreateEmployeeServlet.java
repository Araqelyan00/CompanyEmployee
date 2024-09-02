package companyEmployee.servlet.employee;

import companyEmployee.constants.SharedConstant;
import companyEmployee.manager.CompanyManager;
import companyEmployee.manager.EmployeeManager;
import companyEmployee.model.Company;
import companyEmployee.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
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
        req.setAttribute("companies", all);
        req.getRequestDispatcher("WEB-INF/company.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int companyId = Integer.parseInt(req.getParameter("companyId"));
        Part profilePicPart = req.getPart("profilePic");
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
