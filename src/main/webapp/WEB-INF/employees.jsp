<%@ page import="java.util.List" %>
<%@ page import="companyEmployee.model.Employee" %>
<%@ page import="companyEmployee.model.User" %>
<%@ page import="companyEmployee.model.UserType" %>
<%@ page import="companyEmployee.model.Company" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees</title>
    <link rel="stylesheet" href="../css/header_styles.css"/>
    <link rel="stylesheet" href="../css/main_styles.css"/>
    <link rel="stylesheet" href="../css/buttons.css"/>
    <link rel="stylesheet" href="../css/input.css"/>
</head>

<body>
<% List<Employee> employees = (List<Employee>) request.getAttribute("employee");
    User user = (User) session.getAttribute("user");
    String keyword = request.getParameter("keyword") == null ||
            request.getParameter("keyword").equals("null") ? "" : request.getParameter("keyword");

%>

<header>
    <div class="welcome-text-section">
        <h2>Employees</h2>
    </div>
    <div class="menu-section large-menu-section">
        <a href="/createEmployee">Create Employee</a>
        <form action="/employees" method="get" style="margin: 0">
            <input type="text" name="keyword" value="<%=keyword%>" class="input">
            <input type="submit" value="Search" class="button">
        </form>
    </div>
    <div>
        <a href="/"> Back </a>
    </div>
</header>

<main>
    <table border="1">
        <tr class="ths">
            <th style="width: 100px">Image</th>
            <th>Id</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Email</th>
            <th>Company name</th>
            <% if (user.getUserType() == UserType.ADMIN) { %>
            <th>Action</th>
            <%}%>

        </tr>
        <% if (employees != null && !employees.isEmpty()) {%>
        <% for (Employee employee : employees) { %>
        <tr class="tds">
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
                | <a href="/updateEmployee?employeeID=<%=employee.getEmployeeId()%>">Update</a></td>
            <%}%>
        </tr>
        <%}%>
        <%}%>
    </table>
</main>
</body>
</html>
