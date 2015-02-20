<?php
/*
    Page Name: postComment.php
    Author: Jon Beharrell
    Version: 1.0
    Description: This page will insert a comment into the database.
    Dependencies: This page is dependent on a user submitting a comment.
    Change History: 2015.02.07 Original version by JB
*/

include('functions.php');
include('../database/dbConfig.php');

$user;
$meme;
$content;
$parent = null;

if(isset($_POST['content'])) {
    $user = $_POST['user'];
    $meme = $_POST['meme'];
    $content = $_POST['content'];
}else{

$params = json_decode(file_get_contents('php://input'), true);
//Gather variables
$user = $params['user'];
$meme = $params['meme'];
$content = $params['content'];
if(isset($params['parent']))
    $parent = $db -> real_escape_string($params['parent']);
}
$user = $db -> real_escape_string($user);
$meme = $db -> real_escape_string($meme);
$content = $db -> real_escape_string($content);

//Check if the user has submitted a blank comment/reply
if($content == "")
    exit;

//Inserting comment in to the database.
$query = 'INSERT INTO comments(
          user_id,
          meme_id,
          content';
if($parent)
    $query .= ', parent_id';
$query .= ') VALUES('.$user.', '.$meme.', \''.$content.'\'';
if($parent)
    $query .= ', '.$parent;
$query .= ');';

// Create notifications
createNotification($db, getMemeCreatorId($db, $meme), '#/meme/'.$meme, getUsernameById($db, $user).' has commented on your meme');
if($parent){
    createNotification($db, getCommentCreatorId($db, $parent), '#/meme/'.$meme, getUsernameById($db, $user).' has replied to your comment');
    $children = getCommentChildrenIds($db, $parent);
    foreach($children as $child){
        createNotification($db, getCommentCreatorId($db, $child), '#/meme/'.$meme, getUsernameById($db, $user).' has replied to a comment you replied to');
    }
}

$db->query($query);

//If the query fails, we try and log the error
if(!$result){
  include "../database/errorLogging.php";
}else{
    $db -> close();
}