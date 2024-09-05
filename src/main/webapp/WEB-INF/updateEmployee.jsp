<%@ page import="companyEmployee.model.Employee" %>
<%@ page import="companyEmployee.model.Company" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Employee</title>

</head>
<body>
<%
    List<Company> companies = (List<Company>) request.getAttribute("company");
    Employee employee = (Employee) request.getAttribute("employee");
%>
<a href="/employees"> Back </a>

<h2>Update Employee</h2>
<form action="/updateEmployee" method="post" enctype="multipart/form-data">
    <input name="employeeID" type="hidden" value="<%= employee != null ? employee.getEmployeeId() : "" %>">
    name: <input type="text" name="employeeName" value="<%= employee != null ? employee.getEmployeeName() : "" %>"><br>
    surname: <input type="text" name="employeeSurname" value="<%= employee != null ? employee.getEmployeeSurname() : "" %>"><br>
    email: <input type="text" name="employeeEmail" value="<%= employee != null ? employee.getEmployeeEmail() : "" %>"><br>
    company:
    <select name="companyID">
        <% if (companies != null) {
            for (Company company : companies) { %>
        <option value="<%= company.getCompanyId() %>"><%= company.getCompanyName() %> <%= company.getCompanyCountry() %></option>
        <%  }
        } %>
    </select><br>
    image:
    <input type="file" name="employeePicLink"> <br>
    <input type="submit" value="Update">
</form>
</body>
</html>
