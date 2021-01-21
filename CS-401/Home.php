<?php
session_start();
require_once 'table.php';
if($_SESSION['authenticated'] != true){
    header("Location: index.php");
}
$pageName = "Home";
?>

<html>
    <header>
        <title>Home</title>
        <link href="https://fonts.googleapis.com/css2?family=Work+Sans&display=swap" rel="stylesheet"> 
        <link rel='shortcut icon' type='image/png' href='favicon.png'/>
        <link rel="stylesheet" type="text/css" href="style.css" />
    </header>
    <body>
    <div>
    <ul>
      <li><a <?php if ($pageName == "Home") { echo "class='active';"; } ?> href="Home.php">Home</a></li>
      <li><a <?php if ($pageName == "B1") { echo "class='active';"; } ?> href="B1.php">Website List A-Z</a></li>
      <li><a <?php if ($pageName == "B2") { echo "class='active';"; } ?> href="B2.php">Visit Random Website</a></li>
      <li><a <?php if ($pageName == "B3") { echo "class='active';"; } ?> href="B3.php">About Us</a></li>
      <li><a <?php if ($pageName == "B4") { echo "class='active';"; } ?> href="B4.php">Leave a Review</a></li>
      <li><a href="index.php">Logout</a></li>
    </ul>
    </div>
        <div id='page-container'>
            <div >
            
                <h2>Most Recently Reviewed</h2>
                <div class='center'>
                <?php
                renderTime();
                ?>
                </div>
            </div>
             
            <div>
                <h2>Best Sites</h2>
                <div class='center'>
                <?php
                renderBest();
                ?>
                </div>
            </div>

            <div>
                <h2>Worst Sites</h2>
                <div class='center'>
                <?php
                 renderWorst();
                 ?>
                 </div>
            </div>
        
        </div>
        <div>
        <footer>
            <p>Author: Wesley Brown</p>
            <p>Copy Right 2020</p>
          </footer>
          </div>
    </body>
</html>