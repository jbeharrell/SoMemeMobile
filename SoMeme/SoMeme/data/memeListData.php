<?php
/*
    Page Name: memeListData.php
    Author: Jon Beharrell
    Version: 1.0
    Description: This page returns a json string of all the data needed to be displayed on the meme list page.
    Dependencies: This page is dependent on the user navigating to the home page or view of all memes.
    Change History: 2015.02.06 Original version by JB
*/

include('../database/dbConfig.php');

//Gathering the data for the list view of memes.
$query = 'SELECT
          memes.id,
          memes.title,
          memes.source_link,
          memes.user_id,
          users.username,
          memes.timestamp,
          memes.views,
          (SELECT COUNT(*) FROM meme_votes WHERE meme_id = memes.id AND is_positive = 1) AS pos,
          (SELECT COUNT(*) FROM meme_votes WHERE meme_id = memes.id AND is_positive = 0) AS neg
          FROM memes
          LEFT JOIN users ON memes.user_id = users.id;';

$result = $db -> query($query);

//If the query fails, we try and log the error
if(!$result){
  include "../database/errorLogging.php";
}else{

  $data = array();

  while ($row = $result->fetch_assoc()){
    $arr = $row;
    $arr['user'] = array("id" => $row["user_id"], 'username' => $row['username']);
    unset($arr['user_id']);
    unset($arr['username']);

    $arr['votes'] = array('pos' => $row['pos'], 'neg' => $row['neg']);
    unset($arr['pos']);
    unset($arr['neg']);

    $data[] = $arr;
  }

  $result->free();
  $db->close();

  print_r(json_encode($data));
}

/*
 json format:

[{
    "id":"",
    "title":"",
    "source_link":"",
    "timestamp":"",
    "views":"",
    "user":{
        "id":"",
        "username":""
    },
    "votes":{
        "pos":""
        "neg":""
    }
}]
*/