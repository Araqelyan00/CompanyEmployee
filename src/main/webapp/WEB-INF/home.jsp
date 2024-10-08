<%@ page import="companyEmployee.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
    <link rel="stylesheet" href="../css/header_styles.css"/>
</head>
<body>
<% User user = (User) session.getAttribute("user"); %>
<header>
    <div class="welcome-text-section">
        <h2>Welcome <%=user.getUserName()%> <%=user.getUserSurname()%></h2>
    </div>
    <div class="menu-section">
        <a href="/employees" style="margin-right: 10px"> Employees</a>
        <a href="/companies" style="margin-right: 10px"> Companies</a>
        <a href="/tasks">Tasks</a>
    </div>
    <div>
        <a href="/logout">Logout</a>
    </div>
</header>


</body>
</html>
