<%@ page import="companyEmployee.model.Employee" %>
<%@ page import="companyEmployee.model.Company" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Employee</title>
    <link rel="stylesheet" href="../css/header_styles.css"/>
    <link rel="stylesheet" href="../css/main_styles.css"/>
    <link rel="stylesheet" href="../css/buttons.css"/>
    <link rel="stylesheet" href="../css/input.css"/>
</head>
<body>
<%
    List<Company> companies = (List<Company>) request.getAttribute("company");
    Employee employee = (Employee) request.getAttribute("employee");
%>
<header>
    <div class="welcome-text-section"><h2>Update Employee</h2></div>
    <div><a href="/employees"> Back </a></div>
</header>

<main>
    <form action="/updateEmployee" method="post" enctype="multipart/form-data">
        <input name="employeeID" type="hidden" value="<%= employee != null ? employee.getEmployeeId() : "" %>">
        <div class="field">
            <div>Name</div>
            <div><input type="text" name="employeeName" class="input" value="<%=employee.getEmployeeName()%>"></div>
        </div>
        <div class="field">
            <div>Surname</div>
            <div><input type="text" name="employeeSurname" class="input" value="<%=employee.getEmployeeSurname()%>"></div>
        </div>
        <div class="field">
            <div>Email</div>
            <div><input type="text" name="employeeEmail" class="input" value="<%=employee.getEmployeeEmail()%>"></div>
        </div>
        <div class="field">
            <div>Company</div>
            <div><select name="companyID" style="width: 250px; border-radius: 4px">
                <% for (Company company : companies) { %>
                <option value="<%=company.getCompanyId()%>"><%=company.getCompanyName()%> <%=company.getCompanyCountry()%></option>
                <% }%>
            </select></div>
        </div>
        <div class="field">
            <div>Image</div>
            <div><input type="file" name="employeePicLink" value="<%=employee.getEmployeePicName()%>"></div>
        </div>
        <input type="submit" value="Update" class="button">

    </form>
</main>
</body>
</html>
