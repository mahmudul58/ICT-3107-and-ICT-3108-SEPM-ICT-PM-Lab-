<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Management</title>
    <style>
        body { font-family: sans-serif; padding: 20px; }
        .container { max-width: 400px; margin: auto; border: 1px solid #ccc; padding: 20px; border-radius: 8px; }
        input[type="text"], input[type="email"] { width: 100%; padding: 8px; margin: 5px 0 15px 0; }
        .btn-group { display: flex; justify-content: space-between; }
        input[type="submit"] { padding: 10px; cursor: pointer; }
    </style>
</head>
<body>
<div class="container">
    <h2>Enter Student Details</h2>
    <h4 style="color: green">${message}</h4>

    <form action="student" method="post">
        <label>Name:</label>
        <input type="text" name="name" placeholder="Enter Name">

        <label>Email (Used for Update/Delete):</label>
        <input type="email" name="email" placeholder="Enter Email" required>

        <label>Course (New Field):</label>
        <input type="text" name="course" placeholder="Enter Course">

        <div class="btn-group">
            <input type="submit" name="action" value="Insert">
            <input type="submit" name="action" value="View">
            <input type="submit" name="action" value="Update">
            <input type="submit" name="action" value="Delete">
        </div>
    </form>
</div>
</body>
</html>