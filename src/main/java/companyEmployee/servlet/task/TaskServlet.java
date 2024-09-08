package companyEmployee.servlet.task;

import companyEmployee.db.DBConnectionProvider;
import companyEmployee.manager.EmployeeManager;
import companyEmployee.manager.TaskManager;
import companyEmployee.model.Employee;
import companyEmployee.model.Task;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {
    private TaskManager taskManager;
    private EmployeeManager employeeManager = new EmployeeManager();
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    @Override
    public void init() throws ServletException {
        taskManager = new TaskManager(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Task> tasks = taskManager.getAllTasks();
            List<Employee> employees = employeeManager.getAllEmployees(); // Fetch employees

            StringBuilder options = new StringBuilder();
            for (Employee employee : employees) {
                options.append("<option value=\"")
                        .append(employee.getEmployeeId())
                        .append("\">")
                        .append(employee.getEmployeeName())
                        .append("</option>");
            }

            request.setAttribute("employeeOptions", options.toString());

            request.setAttribute("tasks", tasks);
            request.getRequestDispatcher("/WEB-INF/tasks.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String priority = request.getParameter("priority");
        String status = request.getParameter("status");
        Date dueDate = Date.valueOf(request.getParameter("due_date"));
        int assignedTo = Integer.parseInt(request.getParameter("assigned_to"));

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setPriority(priority);
        task.setStatus(status);
        task.setDueDate(dueDate);
        task.setAssignedTo(assignedTo);

        try {
            taskManager.addTask(task);
            response.sendRedirect("/tasks");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}