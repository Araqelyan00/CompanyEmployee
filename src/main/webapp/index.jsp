<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main Page</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<%
    if (session.getAttribute("user") != null) {
        response.sendRedirect("/home");
    }
    String msg = (String) session.getAttribute("msg");
%>

<div class="main-block">
    <h1>Login</h1>
    <%if(msg != null){%>
    <span style="color: red"><%=msg%></span>
    <%}%>
    <% if (msg != null) {%>
    <span style="color: red"><%=msg%></span><br>
    <%
            session.removeAttribute("msg");
        }%>
    <form action="/login" method="post">
        <hr>
        <label id="icon" for="email"><i class="fas fa-envelope"></i></label>
        <input type="text" name="email" id="email" placeholder="Email" required/>

        <label id="icon" for="password"><i class="fas fa-unlock-alt"></i></label>
        <input type="password" name="password" id="password" placeholder="Password" required/>
        <hr>
        <div class="btn-block">
            <button type="submit">Login</button>
            <a href="/register.jsp"><button type="button">Register</button></a>
        </div>
    </form>
</div>
</body>
</html>
