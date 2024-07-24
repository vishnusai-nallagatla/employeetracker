<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Associate Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            text-align: center;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            border-radius: 8px;
        }
        .header {
            margin-bottom: 20px;
        }
        .buttons {
            display: flex;
            justify-content: center;
            gap: 10px;
        }
        .button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .button:hover {
            background-color: #0056b3;
        }
       
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Welcome, Associate</h1>
        </div>
        
        <div class="buttons">
            <a href="associateDetails.jsp" class="button">Associate Details</a>
            <a href="employeeDetails.jsp" class="button">Employee Details</a>
            <a href="assignProject.jsp" class="button">Assign Project</a>
            <a href="workUpdate.jsp" class="button">Work Update</a>
            <a href="employeeCharts.jsp" class="button">Employee Charts</a>
        </div>
    </div>
</body>
</html>
