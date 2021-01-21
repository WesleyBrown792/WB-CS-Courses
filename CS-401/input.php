<?php
session_start();
require_once 'Dao.php';

$dao = new Dao();
$name = $_POST['name'];
$url = $_POST['url'];
$func = $_POST['func'];
$layout = $_POST['layout'];
$info = $_POST['info'];
$total = ($info + $func + $layout)/3;
$valid=0;

$_SESSION['name'] = $name;
$_SESSION['url'] = $url;
$_SESSION['func'] = $func;
$_SESSION['layout'] = $layout;
$_SESSION['info'] = $info;


$dao->addwebsite($name, $url, $total, $func, $layout, $info, 0);


/* if(strlen($name) > 256 ){
    echo "Your website name was too long";
    $valid++;
}
if(strlen($url) > 256 ){
    echo "Your website URL was too long, most general page urls should not be too long";
    $valid++;
}
if($info <= 10 || $info >= 0 || is_Numeric($info)){
    echo "Your Functionality score is too high";
    $valid++;
}
if($info <= 10 || $info >= 0 || is_Numeric($info)){
    echo "Your Information score is too high";
    $valid++;
}
if($info <= 10 || $info >= 0 || is_Numeric($info)){
    echo "Your Layout score is too high";
    $valid++;
}

if(!is_link($url)){
    echo "Your URL is not a link";
    $valid++;
}


if($valid > 0){
    echo"Please fix any errors with your Scores, Name, or URL";
}else{
    $dao->addwebsite($name, $url, $total, $func, $layout, $info, 0);
    $_SESSION['name']='';
    $_SESSION['url']='';
    $_SESSION['func']='';
    $_SESSION['info']='';
    $_SESSION['layout']='';  
} */
exit();