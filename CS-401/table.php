<?php
require_once 'Dao.php';
?>
<?php
function renderTable($tablename){
    $dao = new Dao();
    $websites = $dao->getWebsites();
    if(count($websites)==0){
        echo "No Websites yet";
        exit;
    }
    ?>
    <table>
        <thead>
            <th>URL</th><th>Score</th>
        </thead>
        <?php
            foreach($websites as $websites){
                echo "<tr><td>". htmlspecialchars($websites['URL']) . "</td><td>" . htmlspecialchars($websites['totalS']) . "</td></tr>";
            }
        ?>
        </table>
   <?php
    }
    ?>

<?php
function renderATable($tablename){
    $dao = new Dao();
    $websites = $dao->getWebsites();
    if(count($websites)==0){
        echo "No Websites yet";
        exit;
    }
    ?>
    <table>
        <thead>
            <th>URL</th><th>Score</th>
        </thead>
        <?php
            foreach($websites as $websites){
                echo "<tr><td>" . htmlspecialchars($websites['URL']) . "</td><td>" . htmlspecialchars($websites['totalS']) . "</td><td> <a> add in the delete here </a> </td></tr>";
            }
        ?>
        </table>
   <?php
    }
    ?>


<?php
function renderWorst(){
    $dao = new Dao();
    $websites = $dao->getWorst();
    if(count($websites)==0){
        echo "No Websites yet";
        exit;
    }
    ?>
    <table>
        <thead>
            <th>URL</th><th>Score</th>
        </thead>
        <?php
            $holder;
            $bigarray = array();
            $x = 0;
            foreach ($websites as $websites) {
                $bigarray[$x] = $websites; 
                $x++;
            }
            $y = count($bigarray)-1;
            for ($holder=1;$y>=$x-4; $y--) {
                $value = $bigarray[$y];
                echo "<tr><td>" . htmlspecialchars($value['URL']) . "</td><td>" . htmlspecialchars($value['totalS']) . "</td></tr>";
                $holder++;
                if($y<=0){
                    $y=$x-4;
                }
            }
        ?>
        </table>
   <?php
    }
    ?>

<?php
function renderBest(){
    $dao = new Dao();
    $websites = $dao->getBest();
    if(count($websites)==0){
        echo "No Websites yet";
        exit;
    }
    ?>
    <table>
        <thead>
        <th>URL</th><th>Score</th>
        </thead>
        <?php
            $holder;
            $bigarray = array();
            $x = 0;
            foreach ($websites as $websites) {
                $bigarray[$x] = $websites; 
                $x++;
            }
            $y = count($bigarray)-1;
            for ($holder=1;$y>=$x-4; $y--) {
                $value = $bigarray[$y];
                echo "<tr><td>" . htmlspecialchars($value['URL']) . "</td><td>" . htmlspecialchars($value['totalS']) . "</td></tr>"; 
                $holder++;
                if($y<=0){
                    $y=$x-4;
                }
            }
        ?>
        </table>
   <?php
    }
    ?>

<?php
function renderTime(){
    $dao = new Dao();
    $websites = $dao->getTime();
    if(count($websites)==0){
        echo "No Websites yet";
        exit;
    }
    ?>
    <table>
        <thead>
        <th>URL</th><th>Score</th>
        </thead>
        <?php
            $holder=1;
            $bigarray = array();
            $x = 0;
            foreach ($websites as $websites) {
                $bigarray[$x] = $websites; 
                $x++;
            }
            $y = count($bigarray)-1;
            for ($holder=1;$y>=$x-4; $y--) {
                $value = $bigarray[$y];
                echo "<tr><td>" . htmlspecialchars($value['URL']) . "</td><td>" . htmlspecialchars($value['totalS']) . "</td></tr>";
                $holder++;
                if($y<=0){
                    $y=$x-4;
                }
            }
        ?>
        </table>
   <?php
    }
    ?>
