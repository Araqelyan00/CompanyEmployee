<%@ page import="companyEmployee.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<% User user = (User) session.getAttribute("user"); %>
Welcome <%=user.getUserName()%> <%=user.getUserSurname()%> <a href="/logout">logout</a> <br>
<a href="/employees"> Employees</a> |
<a href="/companies"> Companies</a>

</body>
</html>
