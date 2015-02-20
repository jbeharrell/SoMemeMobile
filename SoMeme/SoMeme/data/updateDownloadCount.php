<?php
/*
    Page Name: updateDownloadCount.php
    Author: Ian Mori
    Version: 1.0
    Description: This page will update the total download count for a meme.
    Dependencies: This page is dependent on the user downloading a meme.
    Change History: 2015.02.12 Original version by IM
*/

include('../database/dbConfig.php');

//Gathering variables
$params = json_decode(file_get_contents('php://input'), true);
$meme = $db -> real_escape_string($params['meme']);

//Creating an insert query and running it against the db.
$query = "UPDATE MEMES SET DOWNLOADS = DOWNLOADS + 1 WHERE id = '$meme';";
$result = $db -> query($query);

//If the query fails, we try and log the error
if(!$result){
  include "../database/errorLogging.php";
}else{
    $db -> close();
}