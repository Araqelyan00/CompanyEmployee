<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Task</title>
    <link rel="stylesheet" href="../css/header_styles.css"/>
    <link rel="stylesheet" href="../css/main_styles.css"/>
    <link rel="stylesheet" href="../css/buttons.css"/>
    <link rel="stylesheet" href="../css/input.css"/>
</head>
<body>
<header>
    <div>
        <h2 class="welcome-text-section">Create Task</h2>
    </div>
    <div>
        <a href="/tasks"> Back </a>
    </div>
</header>

<main>
    <form action="/tasks" method="post">
        <div class="field">
            <div><label for="title">Title</label></div>
            <div><input  class="input" type="text" id="title" name="title" required ><br></div>
        </div>

        <div class="field">
            <div><label for="description">Description</label></div>
            <div><textarea id="description" name="description" required ></textarea><br></div>
        </div>

        <div class="field">
            <div><label for="priority">Priority</label></div>
            <div><select id="priority" name="priority" style="width:250px">
                <option value="Low">Low</option>
                <option value="Medium">Medium</option>
                <option value="High">High</option>
            </select><br></div>
        </div>

        <div class="field">
            <div><label for="status">Status</label></div>
            <div><select id="status" name="status" style="width:250px">
                <option value="Pending">Pending</option>
                <option value="In Progress">In Progress</option>
                <option value="Completed">Completed</option>
            </select><br></div>
        </div>

        <div class="field">
            <div><label for="due_date">Due Date</label></div>
            <div><input type="date" id="due_date" name="due_date" required style="width:250px"><br></div>
        </div>

        <div class="field">
            <div><label for="assigned_to">Assigned To</label></div>
            <div><select id="assigned_to" name="assigned_to" required style="width:250px">
                ${employeeOptions}
            </select><br></div>
        </div>

        <button type="submit" class="button">Add Task</button>
    </form>
</main>
</body>
</html>
