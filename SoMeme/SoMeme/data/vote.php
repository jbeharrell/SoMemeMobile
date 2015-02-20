<?php
/*
    Page Name: vote.php
    Author: Jon Beharrell
    Version: 1.0
    Description: This page will insert or update a vote for a meme in the database.
    Dependencies: This page is dependent on the user clicking vote for a meme.
    Change History: 2015.02.02 Original version by JB
*/

include('../database/dbConfig.php');

$type;
$user;
$content;
$vote;

if(isset($_POST['user'])){
    $type = $_POST['type'];
    $user = $_POST['user'];
    $content = $_POST['content'];
    $vote = $_POST['vote'];
}else {
    //Gathering json data and making intial variables.
    $params = json_decode(file_get_contents('php://input'), true);
    $type = $params['type'];
    $user = $params['user'];
    $content = $params['content'];
    $vote = $params['vote'];
}
$type = $db->real_escape_string($type);
$user = $db->real_escape_string($user);
$content = $db->real_escape_string($content);
$vote = $db->real_escape_string($vote);

//Querying the db with the select query.
$query = 'SELECT * FROM '.$type.'_votes WHERE user_id = '.$user.' AND '.$type.'_id = '.$content.';';
$result = $db -> query($query);

//If the query fails, we try and log the error
if(!$result){
    include "../database/errorLogging.php";

}else{
    $row = $result->fetch_assoc();

    //See whether or not a user is voting for the first time or changing/removing a vote
    if($row) {
        if ($vote != $row['is_positive']) {
            $query = 'UPDATE '.$type.'_votes SET is_positive = ' . $vote . ' WHERE user_id = ' . $user . ' AND '.$type.'_id = ' . $content . ';';
        }else{
            $query = 'DELETE FROM '.$type.'_votes WHERE user_id = ' . $user . ' AND '.$type.'_id = ' . $content . ';';
        }
    }else {
        $query = 'INSERT INTO '.$type.'_votes(user_id, '.$type.'_id, is_positive) VALUES(' . $user . ', ' . $content . ', ' . $vote . ');';
    }

    //Run the query from above.
    if($query) {
        $result = $db->query($query);
        
        //If the query fails, we try and log the error
        if(!$result){
            include "../database/errorLogging.php";
        }else{
            print_r(true);
        }
        
    }else{
        print_r(false);
    }

    $db->close();
}