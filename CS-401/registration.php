<?php
session_start();
require_once 'Dao.php';
$user=$_POST["user"];
$pass=$_POST["password"];
$dao = new Dao();

if (strlen($pass) > 256){
    echo "Password is too long";
}

if(strlen($user) > 256){
    echo "Username is too long";
}

$salt = "totalyrandomjunkyouknow";
$newpass = hash('sha256',$pass.$salt);



if ($dao->userExists($user, $newpass)) {
    header("Location: Register.php");
} else {
    $_SESSION['authenticated'] = true;
    
    $dao->addUser($user, $newpass, 0);
    header("Location: Home.php");
    exit();
}