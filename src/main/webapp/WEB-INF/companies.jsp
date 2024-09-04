<%@ page import="companyEmployee.model.Company" %>
<%@ page import="java.util.List" %>
<%@ page import="companyEmployee.model.User" %>
<%@ page import="companyEmployee.model.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Companies</title>
</head>
<% List<Company> companies = (List<Company>) request.getAttribute("company");
    User user = (User) session.getAttribute("user");
%>
<body>
<a href="/"> Back </a>
<h2>Companies</h2> <a href="/createCompany">Create Company</a>
<table border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>country</th>
        <% if (user.getUserType() == UserType.ADMIN) { %>
        <th>action</th>
        <%}%>
    </tr>
    <% if (companies != null && !companies.isEmpty()) {%>
    <% for (Company company : companies) { %>
    <tr>
        <td><%=company.getCompanyId()%>
        </td>
        <td><%=company.getCompanyName()%>
        </td>
        <td><%=company.getCompanyCountry()%>
        </td>
        <% if (user.getUserType() == UserType.ADMIN) { %>

        <td><a href="/removeCompany?companyID=<%=company.getCompanyId()%>">Delete</a>
            <a href="/updateCompany?companyID=<%=company.getCompanyId()%>">Update</a></td>
        <%}%>
    </tr>
    <%}%>
    <%}%>
</table>
</body>
</html>
