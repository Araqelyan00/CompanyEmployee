<%--<%@ page import="companyEmployee.model.Company" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Create Company</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<% Company company = (Company) request.getAttribute("company"); %>--%>
<%--<a href="/companies"> Back </a>--%>

<%--<h2>Update Company</h2>--%>
<%--<form action="/updateCompany" method="post">--%>
<%--    <input name="id" type="hidden" value="<%=company.getCompanyId()%>">--%>
<%--    name: <input type="text" name="name" value="<%=company.getCompanyName()%>"><br>--%>
<%--    country: <input type="text" name="country" value="<%=company.getCompanyCountry()%>"> <br>--%>
<%--    <input type="submit" value="update">--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page import="companyEmployee.model.Company" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Company</title>
</head>
<body>
<% Company company = (Company) request.getAttribute("company"); %>
<a href="/companies"> Back </a>

<h2>Update Company</h2>
<form action="/updateCompany" method="post">
    <input name="companyID" type="hidden" value="<%=company.getCompanyId()%>">
    name: <input type="text" name="companyName" value="<%=company.getCompanyName()%>"><br>
    country: <input type="text" name="companyCountry" value="<%=company.getCompanyCountry()%>"> <br>
    <input type="submit" value="update">
</form>
</body>
</html>