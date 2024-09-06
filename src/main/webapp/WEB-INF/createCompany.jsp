<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Company</title>
    <link rel="stylesheet" href="../css/header_styles.css"/>
    <link rel="stylesheet" href="../css/main_styles.css"/>
    <link rel="stylesheet" href="../css/buttons.css"/>
    <link rel="stylesheet" href="../css/input.css"/>
</head>
<%List<String> countries = (List<String>) request.getAttribute("companyCountry"); %>
<body>
<header>
    <div class="welcome-text-section">
        <h2>Create Company</h2>
    </div>
    <div>
        <a href="/companies"> Back </a>
    </div>
</header>

<main>
<form action="/createCompany" method="post">
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
            <select name="companyCountry" style="width: 250px; border-radius: 4px">
                <% for (String country : countries) { %>
                <option value="<%=country%>"><%=country%></option>
                <% }%>
            </select>
        </div>
    </div>

    <input type="submit" value="Create" class="button">
</form>
</main>
</body>
</html>
