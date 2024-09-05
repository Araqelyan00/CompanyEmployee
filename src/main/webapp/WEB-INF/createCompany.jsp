<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Company</title>

</head>
<%List<String> countries = (List<String>) request.getAttribute("companyCountry"); %>
<body>
<a href="/companies"> Back </a>

<h2>Create Company</h2>
<form action="/createCompany" method="post">
    name: <input type="text" name="companyName"><br>
    country:
    <select name="companyCountry">
        <% for (String country : countries) { %>
        <option value="<%=country%>"><%=country%></option>
        <% }%>
    </select>
    <input type="submit" value="create">
</form>
</body>
</html>
