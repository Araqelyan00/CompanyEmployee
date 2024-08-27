package companyEmployee.manager;

import companyEmployee.db.DBConnectionProvider;
import companyEmployee.model.Company;
import companyEmployee.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private CompanyManager companyManager = new CompanyManager();

    public void saveEmployee(Employee employee) {
        try (Statement st = connection.createStatement()) {
            String sql = "INSERT INTO employee(employeeName, employeeSurname, employeeEmail, employeePicLink, companyId) VALUES ('%s', '%s', '%s', '%s', '%d')";
            String sqlFormatted = String.format(sql, employee.getEmployeeName(), employee.getEmployeeSurname(), employee.getEmployeeEmail(), employee.getEmployeePicLink(), employee.getCompany().getCompanyId());
            st.executeUpdate(sqlFormatted, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                employee.setEmployeeId(rs.getInt(1));
            }
            System.out.println("Employee saved into DB.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Employee getEmployeeById(int employeeId) {
        String sql = "SELECT * FROM employee WHERE employeeId = ?";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return getEmployeeFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Employee getEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("employeeId"));
        employee.setEmployeeName(rs.getString("employeeName"));
        employee.setEmployeeSurname(rs.getString("employeeSurname"));
        employee.setEmployeeEmail(rs.getString("employeeEmail"));
        employee.setEmployeePicLink(rs.getString("employeePicLink"));
        int companyId = rs.getInt("companyId");
        employee.setCompany(companyManager.getCompanyByID(companyId));
        return employee;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM employee");
            while (rs.next()) {
                employees.add(getEmployeeFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public List<Employee> getAllEmployeesByCompanyId(int companyId) {
        List<Employee> employees = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM employee where companyId = " + companyId);
            while (rs.next()) {
                employees.add(getEmployeeFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public void removeByEmployeeId(int employeeId) {
        String sql = "DELETE FROM employee WHERE employeeId = " + employeeId;
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Employee> search(String keyword) {
        List<Employee> employees = new ArrayList<>();
        try {
            String sql = "SELECT * FROM employee WHERE employeeName LIKE ? OR employeeSurname LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            keyword = "%" + keyword + "%";
            ps.setString(1, keyword);
            ps.setString(2, keyword);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employees.add(getEmployeeFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET employeeName = ?, employeeSurname = ?, employeeEmail = ?, employeePicLink = ?, companyId = ? WHERE employeeId = ?";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(String.format(sql, employee.getEmployeeName(), employee.getEmployeeSurname(), employee.getEmployeeEmail(), employee.getEmployeePicLink(), employee.getCompany().getCompanyId(), employee.getEmployeeId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}