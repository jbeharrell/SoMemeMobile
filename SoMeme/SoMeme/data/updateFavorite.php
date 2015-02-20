<?php
/*
    Page Name: updateFavorite.php
    Author: Jon Beharrell
    Version: 1.0
    Description: This page will insert or delete a favorite in the database.
    Dependencies: This page is dependent on the user clicking favorite for a meme.
    Change History: 2015.02.12 Original version by JB
*/

include('../database/dbConfig.php');

//Grabbing initial data
if(isset($_POST['current'])) {
    $userId = $_POST['user'];
    $userId = $db->real_escape_string($userId);
    $memeId = $_POST['meme'];
    $memeId = $db->real_escape_string($memeId);
    
    if($_POST['current'] == "true")
        $current = true;
    else
        $current = false;

}else{

    //Gathering the intial data and variables.
    $params = json_decode(file_get_contents('php://input'), true);
    $userId = $db -> real_escape_string($params['user']);
    $memeId = $db -> real_escape_string($params['meme']);
    $current = $params['current'];
}
//Checks if the a record will be inserted or deleted
if($current){
    $query = 'DELETE FROM favorites WHERE user_id='.$userId.' AND meme_id='.$memeId.';';
}else{
    $query = 'INSERT INTO favorites(user_id, meme_id) VALUES('.$userId.', '.$memeId.');';
}

$result = $db -> query($query);

//If the query fails, we try and log the error
if(!$result){
  include "../database/errorLogging.php";
  $user['success'] = 0;
}else{
    $user['success'] = 1;
    $db -> close();
}

print_r(json_encode($user));