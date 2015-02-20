<?php
/*
    Page Name: notificationData.php
    Author: Jon Beharrell
    Version: 1.0
    Description: This page returns a json string of all notifications for a user.
    Dependencies: This page is dependent on the user navigating to the notifications page.
    Change History: 2015.02.12 Original version by JB
*/

include('../database/dbConfig.php');

$params = json_decode(file_get_contents('php://input'), true);
$userId = $db -> real_escape_string($params['user']);

$query = 'SELECT * FROM notifications WHERE user_id='.$userId.';';
$result = $db -> query($query);

//If the query fails, we try and log the error
if(!$result){
  include "../database/errorLogging.php";
}else{

    $data = array();

    while ($row = $result->fetch_assoc()){
        $data[] = $row;
    }

    $result->free();
    $db->close();

    print_r(json_encode($data));
}

/*
 json format:

[{
    "id":"",
    "user_id":"",
    "timestamp":"",
    "source_link":"",
    "content":""
}]
*/