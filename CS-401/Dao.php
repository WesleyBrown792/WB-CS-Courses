<?php
    class Dao{
        private $host = "us-cdbr-east-02.cleardb.com";
        private $db = "heroku_4723b1ca872b752";
        private $user = "beb4004d3c922d";
        private $pass = "f2c8495a";


        public function getConnection() {
            try{
                $conn = new PDO ("mysql:host={$this->host};dbname={$this->db}", $this->user,$this->pass);
                return $conn;
            }catch (Exception $e){
                echo print_r($e,1);
                exit;
            }
        }

        public function addUser($user, $pass, $acc){
            $conn = $this->getConnection();
            $saveQ = "insert into user (username,password,access) values (:user,:pass,:acc);";
            $q = $conn->prepare($saveQ);
            $q->bindParam(":user",$user);
            $q->bindParam(":pass",$pass);
            $q->bindParam(":acc",$acc);
            $q->execute();
        }
        
        public function userExists($user, $pass){
            $conn = $this->getConnection();
            $stmt =  $conn->prepare("select * from user where username = :user and password = :pass;");
            $stmt->bindParam(":user",$user,PDO::PARAM_STR);
            $stmt->bindParam(":pass",$pass,PDO::PARAM_STR);
            $stmt->execute();
            $result = $stmt->fetchAll(PDO::FETCH_ASSOC);

            if(is_null($user)||$user==""){
                return false;
            }else{
                if(count($result)==1){
                    return true;
                }else{
                    return false;
                }
            }
        
        }

        public function addwebsite($name, $URL, $totalS, $P1, $P2, $P3, $P4){
            $conn = $this->getConnection();
            $saveQ = "insert into Websitelist (Name, URL, totalS, P1S, P2S, P3S, P4S, reviewed) values (:name, :URL, :totalS, :P1, :P2, :P3, :P4, now());";
            $q = $conn->prepare($saveQ);
            $q->bindParam(":name",$name);
            $q->bindParam(":URL",$URL);
            $q->bindParam(":totalS",$totalS);
            $q->bindParam(":P1",$P1);
            $q->bindParam(":P2",$P2);
            $q->bindParam(":P3",$P3);
            $q->bindParam(":P4",$P4);
            $q->execute();
        }

        public function deleteWebsite($URL){
            $conn = $this->getConnection();
            $deleteQuery = "delete from WebsiteList where URL = :URL;";
            $q = $conn->prepare($deleteQuery);
            $q->bindParam(":URL", $URL);
            $q->execute();
        }
        
        public function getWebsites(){
            $conn = $this->getConnection();
            $stmt = $conn->query("select * from WebsiteList;");
            $stmt->execute();
            $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
            return $result;
        }

        public function getWorst(){
            $conn = $this->getConnection();
            $stmt =  $conn->query("select * from WebsiteList ORDER BY totalS DESC;");
            $stmt->execute();
            $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
            return $result;
        }

        public function getBest(){
            $conn = $this->getConnection();
            $stmt = $conn->query("select * from WebsiteList ORDER BY totalS ASC;");
            $stmt->execute();
            $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
            return $result;
        }

        public function getTime(){
            $conn = $this->getConnection();
            $stmt = $conn->query("select * from WebsiteList ORDER BY reviewed;");
            $stmt->execute();
            $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
            return $result;
        }
    }
?>