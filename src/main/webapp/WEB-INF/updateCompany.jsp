<%@ page import="companyEmployee.model.Company" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Company</title>
    <link rel="stylesheet" href="../css/header_styles.css"/>
    <link rel="stylesheet" href="../css/main_styles.css"/>
    <link rel="stylesheet" href="../css/buttons.css"/>
    <link rel="stylesheet" href="../css/input.css"/>
</head>
<body>
<% Company company = (Company) request.getAttribute("company"); %>
<header>
    <div class="welcome-text-section">
        <h2>Update Company</h2>
    </div>
    <div>
        <a href="/companies"> Back </a>
    </div>
</header>

<main>
    <form action="/updateCompany" method="post">
        <input name="companyID" type="hidden" value="<%=company.getCompanyId()%>">
        <div  class="field">
            <div>
                Name
            </div>
            <div>
                <input type="text" name="companyName"   class="input">
            </div>
        </div>
        <div  class="field">
            <div>
                Country
            </div>
            <div >
                <input type="text" name="companyCountry" class="input" value="<%=company.getCompanyName()%>">

            </div>
        </div>
        <input type="submit" value="Update" class="button" value="<%=company.getCompanyCountry()%>">
    </form>
</main>
</body>
</html>