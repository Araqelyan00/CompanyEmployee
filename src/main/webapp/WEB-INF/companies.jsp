<%@ page import="companyEmployee.model.Company" %>
<%@ page import="java.util.List" %>
<%@ page import="companyEmployee.model.User" %>
<%@ page import="companyEmployee.model.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Companies</title>
    <link rel="stylesheet" href="../css/header_styles.css"/>
    <link rel="stylesheet" href="../css/main_styles.css"/>
</head>
<% List<Company> companies = (List<Company>) request.getAttribute("company");
    User user = (User) session.getAttribute("user");
%>
<body>
<header>
    <div class="welcome-text-section">
        <h2>Companies</h2>
    </div>
    <div class="menu-section">
        <a href="/createCompany">Create Company</a>
    </div>
    <div>
        <a href="/"> Back </a>
    </div>
</header>

<main>
    <table border="1">
        <tr class="ths">
            <th>Id</th>
            <th>Name</th>
            <th>Country</th>
            <% if (user.getUserType() == UserType.ADMIN) { %>
            <th>Action</th>
            <%}%>
        </tr>
        <% if (companies != null && !companies.isEmpty()) {%>
        <% for (Company company : companies) { %>
        <tr class="tds">
            <td><%=company.getCompanyId()%>
            </td>
            <td><%=company.getCompanyName()%>
            </td>
            <td><%=company.getCompanyCountry()%>
            </td>
            <% if (user.getUserType() == UserType.ADMIN) { %>

            <td><a href="/removeCompany?companyID=<%=company.getCompanyId()%>">Delete</a> |
                <a href="/updateCompany?companyID=<%=company.getCompanyId()%>">Update</a></td>
            <%}%>
        </tr>
        <%}%>
        <%}%>
    </table>
</main>
</body>
</html>
