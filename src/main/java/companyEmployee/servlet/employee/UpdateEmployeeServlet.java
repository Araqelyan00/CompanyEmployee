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

@WebServlet("/updateEmployee")
@MultipartConfig
public class UpdateEmployeeServlet extends HttpServlet {
    private EmployeeManager employeeManager = new EmployeeManager();
    private CompanyManager companyManager = new CompanyManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int employeeId = Integer.parseInt(req.getParameter("employeeID"));
            Employee employee = employeeManager.getEmployeeById(employeeId);
            List<Company> all = companyManager.getAll();
            req.setAttribute("company", all);
            req.setAttribute("employee", employee);
            req.getRequestDispatcher("WEB-INF/updateEmployee.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid employee ID");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while retrieving the employee");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Log raw parameter values
            String employeeIdParam = req.getParameter("employeeID");
            String companyIdParam = req.getParameter("companyID");
            System.out.println("Raw employeeID: " + employeeIdParam);
            System.out.println("Raw companyID: " + companyIdParam);

            if (employeeIdParam == null || companyIdParam == null) {
                throw new NumberFormatException("Missing parameters");
            }

            int id = Integer.parseInt(employeeIdParam);
            int companyId = Integer.parseInt(companyIdParam);

            String name = req.getParameter("employeeName");
            String surname = req.getParameter("employeeSurname");
            String email = req.getParameter("employeeEmail");
            Part profilePicPart = req.getPart("employeePicLink");
            String pictureName = null;
            if (profilePicPart != null && profilePicPart.getSize() > 0) {
                pictureName = System.nanoTime() + "_" + profilePicPart.getSubmittedFileName();
                profilePicPart.write(SharedConstant.UPLOAD_FOLDER + pictureName);
            }
            Company company = companyManager.getCompanyByID(companyId);
            Employee employee = new Employee(id, name, surname, email, pictureName, company);

            // Debugging print statements
            System.out.println("Employee ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Surname: " + surname);
            System.out.println("Email: " + email);
            System.out.println("Picture Name: " + pictureName);
            System.out.println("Company ID: " + companyId);

            employeeManager.updateEmployee(employee);
            resp.sendRedirect("/employees");
        } catch (NumberFormatException e) {
            System.err.println("Number format exception: " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace(); // Print stack trace to server logs
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating the employee");
        }
    }
}
