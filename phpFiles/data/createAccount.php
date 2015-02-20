<?php
/*
    Page Name: createAccount.php
    Author: Robert Taylor
    Version: 1.0
    Description: This page will take the data submitted by a user and insert it into the database if a record does not exist already.
    Dependencies: This page is dependent on the user filling in all the necessary details and clicking the create button.
    Change History: 2015.02.06 Original version by RT
                    2015.02.15 Updated version by IM
*/

include('../login/includes/functions.php');
include('../login/includes/config.php');

//We check to see if a mobile device is sending POST variables.
$IsMobileDevice = isset($_POST['mobile']);

//If it's mobile, grab the mobile POST variables.
if($IsMobileDevice){
    $Username = $_POST['username'];
    $Password = $_POST['password'];
    $Email = $_POST['email'];
    $Fname = $_POST['fname'];
    $Lname = $_POST['lname'];
    $Dob = $_POST['dob'];
    $Gender = $_POST['gender'];
    $Country = $_POST['country'];

//If it's not mobile, grab the json data
}else{
    $params = json_decode(file_get_contents('php://input'), true);

    $Username = "";
    $Password = "";
    $Email = "";
    $Fname = "";
    $Lname = "";
    $Dob = "";
    $Gender = "";
    $Country= "";
    if(isset($params['username']))
        $Username = trim($params['username']);
    if(isset($params['password']))
        $Password = trim($params['password']);
    if(isset($params['email']))
        $Email = trim($params['email']);
    if(isset($params['fname']))
        $Fname = trim($params['fname']);
    if(isset($params['lname']))
        $Lname = trim($params['lname']);
    if(isset($params['dob']))
        $Dob = trim($params['dob']);
    if(isset($params['gender']))
        $Gender = trim($params['gender']);
    if(isset($params['country']))
        $Country= trim($params['country']);
}

// Escape strings for sql
$eUsername = $db->real_escape_string($Username);
$ePassword = $db->real_escape_string($Password);
$eEmail = $db->real_escape_string($Email);
$eFname = $db->real_escape_string($Fname);
$eLname = $db->real_escape_string($Lname);
$eDob = $db->real_escape_string($Dob);
$eGender = $db->real_escape_string($Gender);
$eCountry = $db->real_escape_string($Country);

if (!empty($Username) && !empty($Password) && !empty($Email) && !empty($Fname) && !empty($Lname)  && !empty($Dob) && !empty($Gender)) {

    $uLen = strlen($Username);
    $pLen = strlen($Password);
    $eLen = strlen($Email);
    $fLen = strlen($Fname);
    $lLen = strlen($Lname);
    $dLen = strlen($Dob);
    $gLen = strlen($Gender);

    //Building a query and sending it to the database.
    $query = "SELECT username FROM users WHERE username = '" . $eUsername . "' LIMIT 1";
    $error = null;
    $result = $db->query($query);

    //If the query fails, we try and log the error
    if(!$result){
      include "../database/errorLogging.php";
    }else{

        //Doing some validation for the backend.
        if ($uLen <= 3 || $uLen >= 12) {
            $error = "Username must be between 4 and 11 characters.";
            $user['result']=false;
        } elseif ($pLen < 6) {
            $error = "Password must be longer than 6 characters.";
            $user['result']=false;
        } elseif ($result->num_rows == 1) {
            $error = "Username already exists.";
            $user['result']=false;
        } else {

            //Free the previous result and query the db again.
            $result->free();
            $query = "INSERT INTO users (`username`, `password`,`email`, `first_name`, `last_name`, `dob`, `gender`) VALUES ('" . $eUsername . "', '" . hashPassword($Password, SALT1, SALT2) . "', '" . $eEmail . "', '" . $eFname . "', '" . $eLname . "', '" . $eDob . "', '" . $eGender . "');";
            $result = $db->query($query);

            //If the query fails, we try and log the error
            if(!$result){
                $user['result']=false;
                include "../database/errorLogging.php";
            }else{
                $user = array();
                $user['result']=true;
                $user['success'] = 1;
                //print_r('true');
                $db->close();
            }
        }

        if(!empty($error)){
            echo $error;
        }   
    }
}else{
    $user['result']=false;
}

//Return used for mobile
if($IsMobileDevice)
    print_r(json_encode($user));