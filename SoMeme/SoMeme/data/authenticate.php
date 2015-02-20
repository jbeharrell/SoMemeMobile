<?php
/*
    Page Name: authenticate.php
    Author: Robert Taylor
    Version: 1.0
    Description: This page validates the user login.
    Dependencies: This page is dependent on the the username and password being filled out and submitted by a user.
    Change History: 2015.02.06 Original version by RT
                    2015.02.15 Updated version by IM
*/

include('../login/includes/functions.php');
include('../login/includes/config.php');

//Checking if a mobile device is submitting data
if(isset($_POST['mobile'])){
    $Username = trim($_POST['username']);
    $Password = trim($_POST['password']);
}else{
    $params = json_decode(file_get_contents('php://input'), true);
    $Username = trim($params['username']);
    $Password = trim($params['password']);
}    

// See if the username and password are valid.
$query = "SELECT id, username FROM users
		WHERE username = '" . $db->real_escape_string($Username) . "' AND password = '" . hashPassword($Password, SALT1, SALT2) . "' LIMIT 1";
$result = $db->query($query);

//If there is an error, try and log the error on the query
if(!$result){
    include "../database/errorLogging.php";

}else{
    $user = array();

    //If we find a match, we can log the user in
    if ($result->num_rows == 1) {
        $user = $result->fetch_assoc();
        $user['result'] = true;
        $user['success'] = 1;

    }else {
        $user['result'] = false;
    }
    
    $db->close();
    print_r(json_encode($user));
}
