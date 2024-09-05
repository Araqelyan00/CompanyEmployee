<%@ page import="java.util.List" %>
<%@ page import="companyEmployee.model.Company" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Employee</title>

</head>
<%List<Company> companies = (List<Company>) request.getAttribute("company"); %>
<body>
<a href="/employees"> Back </a>

<h2>Create Employee</h2>
<form action="/createEmployee" method="post" enctype="multipart/form-data">
    name: <input type="text" name="employeeName"><br>
    surname: <input type="text" name="employeeSurname"><br>
    email: <input type="text" name="employeeEmail"><br>
    company:
    <select name="companyID">
        <% for (Company company : companies) { %>
        <option value="<%=company.getCompanyId()%>"><%=company.getCompanyName()%> <%=company.getCompanyCountry()%></option>
        <% }%>
    </select><br>
    image:
    <input type="file" name="employeePicLink"> <br>
    <input type="submit" value="Create">
</form>
</body>
</html>
