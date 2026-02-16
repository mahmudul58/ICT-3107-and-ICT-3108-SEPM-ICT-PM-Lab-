<!DOCTYPE html>
<html>
<head>
    <title>Department Manager</title>
    <style>
        body { font-family: sans-serif; padding: 20px; }
        form { background: #f9f9f9; padding: 20px; border: 1px solid #ddd; width: 300px; }
        input[type="text"], input[type="number"] { width: 100%; margin-bottom: 10px; padding: 5px; }
        input[type="submit"] { margin-top: 5px; padding: 5px 10px; cursor: pointer; }
    </style>
</head>
<body>

<h2>Department Management System</h2>

<form method="get" action="TestServlet">
    <label for="dname">Department Name:</label>
    <input type="text" id="dname" name="deptName" required placeholder="e.g. CSE">
    <br><br>

    <label for="num">Number of Students:</label>
    <input type="number" id="num" name="numStudents" placeholder="e.g. 100">
    <br><br>

    <h3>Actions:</h3>
    <input type="submit" name="action" value="Insert">
    <input type="submit" name="action" value="Update">
    <input type="submit" name="action" value="Delete">
    <br><br>
    <input type="submit" name="action" value="View" formnovalidate>
</form>

</body>
</html>