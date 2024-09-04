<%@ page import="java.util.List" %>
<%@ page import="companyEmployee.model.Employee" %>
<%@ page import="companyEmployee.model.User" %>
<%@ page import="companyEmployee.model.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees</title>
</head>
<% List<Employee> employees = (List<Employee>) request.getAttribute("employee");
    User user = (User) session.getAttribute("user");
    String keyword = request.getParameter("keyword") == null ||
            request.getParameter("keyword").equals("null") ? "" : request.getParameter("keyword");

%>
<body>
<a href="/"> Back </a>
<h2>Employees</h2> <a href="/createEmployee">Create Employee</a>
<form action="/employees" method="get">
    <input type="text" name="keyword" value="<%=keyword%>">
    <input type="submit" value="search">
</form>
<table border="1">
    <tr>
        <th>image</th>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>company name</th>
        <% if (user.getUserType() == UserType.ADMIN) { %>
        <th>action</th>
        <%}%>

    </tr>
    <% if (employees != null && !employees.isEmpty()) {%>
    <% for (Employee employee : employees) { %>
    <tr>
        <td>
            <% if (employee.getEmployeePicName() == null || employee.getEmployeePicName().equalsIgnoreCase("null")) { %>
            <img src="/img/default_pic.png" width="100">
            <%} else {%>
            <a href="/getImage?employeePicLink=<%=employee.getEmployeePicName()%>"><img
                    src="/getImage?employeePicLink=<%=employee.getEmployeePicName()%>" width="100"> </a></td>
        <%}%>
        <td><%=employee.getEmployeeId()%>
        </td>
        <td><%=employee.getEmployeeName()%>
        </td>
        <td><%=employee.getEmployeeSurname()%>
        </td>
        <td><%=employee.getEmployeeEmail()%>
        </td>
        <td><%=employee.getCompany().getCompanyName()%>
        </td>
        <% if (user.getUserType() == UserType.ADMIN) { %>

        <td><a href="/removeEmployee?employeeID=<%=employee.getEmployeeId()%>">Delete</a>
<%--            / <a href="updateEmployee?employeeID=<%=employee.getEmployeeId()%>">Update</a></td>--%>
        <%}%>
    </tr>
    <%}%>
    <%}%>
</table>
</body>
</html>
