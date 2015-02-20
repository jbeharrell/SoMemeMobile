<?php
/*
    Page Name: commiteMeme.php
    Author: Jon Beharrell
    Version: 1.0
    Description: This page will insert a meme into the database.
    Dependencies: This page is dependent on a user submitting a meme.
    Change History: 2015.02.07 Original version by JB
*/

include('../database/dbConfig.php');

//Gather initial variables.
$params = json_decode(file_get_contents('php://input'), true);
$user = $params['user'];
$title = $params['title'];
$content = $params['content'];
$sourceLink = $params['source'];

//Check if the content is valid
if($title == ""){
    $error = "Must use a title to post meme";
    print_r($error);

}else{
    //Insert the meme into the database.
    $query = 'INSERT INTO memes(
          title,
          content,
          source_link,
          user_id';

    $query .= ') VALUES(\''.$title.'\', \''.$content.'\', \''.$sourceLink.'\', \''.$user.'\' );';
    $result = $db->query($query);

    //If the query fails, we try and log the error
    if(!$result){
      include "../database/errorLogging.php";
    }else{
      $db -> close();
    }
}