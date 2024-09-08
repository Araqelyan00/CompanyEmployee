<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tasks</title>
    <link rel="stylesheet" href="../css/header_styles.css"/>
    <link rel="stylesheet" href="../css/main_styles.css"/>
</head>
<body>

<header>
    <div class="welcome-text-section">
        <h2>Tasks</h2>
    </div>
    <div class="menu-section">
        <a href="/createTask">Create Task</a>
    </div>
    <div>
        <a href="/"> Back </a>
    </div>
</header>

<main>
<table border="1">
    <tr class="ths">
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Priority</th>
        <th>Status</th>
        <th>Due Date</th>
        <th>Assigned To</th>
        <th>Actions</th>
    </tr>
    <jsp:useBean id="tasks" scope="request" type="java.util.List"/>
    <c:forEach var="task" items="${tasks}">
        <tr class="tds">
            <td>${task.id}</td>
            <td>${task.title}</td>
            <td>${task.description}</td>
            <td>${task.priority}</td>
            <td>${task.status}</td>
            <td>${task.dueDate}</td>
            <td>${task.assignedTo}</td>
            <td>
                <a href="editTask?id=${task.id}">Edit</a>
                <a href="deleteTask?id=${task.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</main>
</body>
</html>