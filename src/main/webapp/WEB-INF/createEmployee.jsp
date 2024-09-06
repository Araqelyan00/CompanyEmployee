<%@ page import="java.util.List" %>
<%@ page import="companyEmployee.model.Company" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Employee</title>
    <link rel="stylesheet" href="../css/header_styles.css"/>
    <link rel="stylesheet" href="../css/main_styles.css"/>
    <link rel="stylesheet" href="../css/buttons.css"/>
    <link rel="stylesheet" href="../css/input.css"/>
</head>
<%List<Company> companies = (List<Company>) request.getAttribute("company"); %>
<body>
<header>
    <div class="welcome-text-section"><h2>Create Employee</h2></div>
    <div><a href="/employees"> Back </a></div>
</header>
<main>
<form action="/createEmployee" method="post" enctype="multipart/form-data">
    <div class="field">
        <div>Name</div>
        <div><input type="text" name="employeeName" placeholder="Name"  class="input"></div>
    </div>
    <div class="field">
        <div>Surname</div>
        <div><input type="text" name="employeeSurname"  placeholder="Surname"  class="input"></div>
    </div>
    <div class="field">
        <div>Email</div>
        <div><input type="text" name="employeeEmail"  placeholder="Email"  class="input"></div>
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
        <div><input type="file" name="employeePicLink"  placeholder="Image"></div>
    </div>
    <input type="submit" value="Create" class="button">

</form>
</main>
</body>
</html>
