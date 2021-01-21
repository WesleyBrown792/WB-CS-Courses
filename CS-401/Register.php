<?php
session_start();
$taken = false
?>

<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src = "register.js"></script>
    <title>Register</title>
    <link href="https://fonts.googleapis.com/css2?family=Work+Sans&display=swap" rel="stylesheet"> 
    <link rel="stylesheet" teype="text/css" href="login.css"/>
</head>

<body>
    <h1>Register Here</h1>

    <form method="POST" action="registration.php">
        <div>User: <input type="text" name="user" id="user"/></div>
        <div>Password: <input type="password" name="password" id="password"/></div>
        <input type="submit" value="Submit" name="submit" id="js_button">
    </form>
    <a href=index.php> Click Here If You Already Have An Account</a>
</body>
</html>