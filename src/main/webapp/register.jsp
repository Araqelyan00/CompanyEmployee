<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register Page</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<%
    if(session.getAttribute("user") != null) {
        response.sendRedirect("/home");
    }
    String msg = (String) request.getAttribute("str");
%>

<div class="main-block">
    <h1>Registration</h1>
    <%if(msg != null){%>
    <span style="color: red"><%=msg%></span>
    <%}%>
    <form action="/register" method="post">
        <hr>
        <div class="account-type">
            <input type="radio" value="USER" id="radioTwo" name="userType" checked/>
            <label for="radioTwo" class="radio">USER</label>

            <input type="radio" value="ADMIN" id="radioOne" name="userType" />
            <label for="radioOne" class="radio">ADMIN</label>
        </div>
        <hr>
        <label id="icon" for="name"><i class="fas fa-user"></i></label>
        <input type="text" name="userName" id="name" placeholder="Name" required/>

        <label id="icon" for="surname"><i class="fas fa-user"></i></label>
        <input type="text" name="userSurname" id="surname" placeholder="Surname" required/>

        <label id="icon" for="email"><i class="fas fa-envelope"></i></label>
        <input type="text" name="userEmail" id="email" placeholder="Email" required/>

        <label id="icon" for="password"><i class="fas fa-unlock-alt"></i></label>
        <input type="password" name="userPassword" id="password" placeholder="Password" required/>
        <hr>
        <div class="btn-block">
            <button type="submit">Register</button>
            <a href="/"><button type="button">Back</button></a>
        </div>
    </form>
</div>
</body>
</html>
