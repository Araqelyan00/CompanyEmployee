<%@ page import="companyEmployee.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>

</head>
<body>
<% User user = (User) session.getAttribute("user"); %>
<a href="/logout">logout</a> <br>
<h2>Welcome <%=user.getUserName()%> <%=user.getUserSurname()%></h2> <br>
<a href="/employees"> Employees</a> |
<a href="/companies"> Companies</a>

</body>
</html>
