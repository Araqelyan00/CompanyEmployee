package companyEmployee.manager;

import companyEmployee.db.DBConnectionProvider;
import companyEmployee.model.Task;
import companyEmployee.util.EmailUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private Connection connection;

    public TaskManager(Connection connection) {
        this.connection = connection;
    }

    public void addTask(Task task) throws SQLException {
        String query = "INSERT INTO tasks (title, description, priority, status, due_date, assigned_to) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getPriority());
            stmt.setString(4, task.getStatus());
            stmt.setDate(5, new java.sql.Date(task.getDueDate().getTime()));
            stmt.setInt(6, task.getAssignedTo());
            stmt.executeUpdate();
        }

        // Assuming you have a method to get the email of the assigned user
        String assignedToEmail = getAssignedToEmail(task.getAssignedTo());
        EmailUtil.sendEmail(assignedToEmail, "New Task Assigned", "You have been assigned a new task: " + task.getTitle());
    }

    public String getAssignedToEmail(int assignedTo) throws SQLException {
        String email = null;
        String query = "SELECT employeeEmail FROM employee WHERE employeeID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, assignedTo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    email = rs.getString("employeeEmail");
                }
            }
        }
        return email;
    }

    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setPriority(rs.getString("priority"));
                task.setStatus(rs.getString("status"));
                task.setDueDate(rs.getDate("due_date"));
                task.setAssignedTo(rs.getInt("assigned_to"));
                task.setCreatedAt(rs.getTimestamp("created_at"));
                task.setUpdatedAt(rs.getTimestamp("updated_at"));
                tasks.add(task);
            }
        }
        return tasks;
    }

    // Additional methods for updating and deleting tasks
}
