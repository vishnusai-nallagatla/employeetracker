<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Banking Operations</title>
<style>
body {
    margin: 0;
    padding: 0;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #f0f2f5;
    display: flex;
    flex-direction: column;
    align-items: center;
    min-height: 100vh;
}

.container {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
    border-radius: 15px;
    background-color: #ffffff;
    padding: 30px;
    margin: 30px 0;
    max-width: 1000px;
    width: 90%;
	margin-top: 50px;
}

.box {
    width: calc(25% - 20px);
	height: 50px;
    margin: 10px;
    padding: 25px;
    text-align: center;
    border-radius: 12px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    transition: all 0.3s ease;
    cursor: pointer;
	margin-top: 50px;
}

.box:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
}

.box:nth-child(1) { background: linear-gradient(135deg, #6e8efb, #a777e3); }
.box:nth-child(2) { background: linear-gradient(135deg, #ff9a9e, #fad0c4); }
.box:nth-child(3) { background: linear-gradient(135deg, #ffecd2, #fcb69f); }
.box:nth-child(4) { background: linear-gradient(135deg, #84fab0, #8fd3f4); }
.box:nth-child(5) { background: linear-gradient(135deg, #87CEEB, #1E90FF); }

.text {
    font-size: 18px;
    font-weight: 600;
    color: #ffffff;
    text-transform: uppercase;
    letter-spacing: 1px;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
}

#loadedContent {
    display: none;
    position: relative;
    margin-top: 30px;
    width: 90%;
    max-width: 1000px;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
    border-radius: 15px;
    padding: 30px;
    background-color: #ffffff;
}

a {
    text-decoration: none;
    color: #333;
    font-weight: 600;
    transition: color 0.3s ease;
}

a:hover {
    color: #6e8efb;
}

.navbar {
    width: 100%;
    background-color: #ffffff;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    padding: 10px 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    flex-wrap: wrap; /* Allow items to wrap on smaller screens */
}

.navbar h1 {
    color: #333;
    font-size: 24px;
    font-weight: 700;
    text-transform: uppercase;
    margin: 0;
	padding-left: 30px;
}

.navbar-left {
    flex: 1; /* Take up remaining space */
}

.navbar-right {
    margin-left: auto;

}

.logout-btn {
    padding: 10px 20px;
    background-color: #6e8efb; /* Match with box background color */
    color: #ffffff;
    border-radius: 8px;
    font-weight: 600;
    transition: background-color 0.3s ease;
}

.logout-btn:hover {
    background-color: #a777e3; /* Match with hover effect of box */
}

@media (max-width: 768px) {
    .container {
        flex-direction: column; /* Stack items vertically */
    }

    .box {
        width: 80%; /* Full width on smaller screens */
    }
	.navbar h1 {
    color: #333;
    font-size: 19px;
    
   }
   .box {
	height: 20px;
    margin-top: 0px;
    }
	.container{
		margin-top: 20px;
	}
	.navbar h1 {
   
	padding-left: 10px;
}


.navbar-right {
    margin-left: 0px;

}
  
}


</style>
</head>
<body>
    
    <div class="navbar">
        <div class="navbar-left">
            <h1>Admin Dashboard</h1>
        </div>
        <div class="navbar-right">
            <a href="mainPage.html" class="logout-btn">Log Out</a>
        </div>
    </div>

    <div class="container">
        <a href="CreateEmployee.jsp" class="box">
            <div class="text">Create</div>
        </a>
        <a href="edit.jsp" class="box">
            <div class="text">Edit</div>
        </a>
        <a href="Display.jsp" class="box">
            <div class="text">Employee List</div>
        </a>
        <a href="DeleteEmploy.jsp" class="box">
            <div class="text">Delete Employ</div>
        </a>
       
    </div>
    
</body>
</html>
