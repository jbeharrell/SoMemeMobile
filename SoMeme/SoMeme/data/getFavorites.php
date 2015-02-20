<?php
/*
    Page Name: getFavorites.php
    Author: Jon Beharrell
    Version: 1.0
    Description: This page returns a json string of all the data for the memes that a user has favorited.
    Dependencies: This page is dependent on the user navigating to the favorite page.
    Change History: 2015.02.12 Original version by JB
*/

include('../database/dbConfig.php');

$userId;

//Grabbing initial data
if(isset($_POST['user_id'])) {
    $userId = $_POST['user_id'];
    $userId = $db->real_escape_string($userId);
}else{
  $params = json_decode(file_get_contents('php://input'), true);
  $userId = $db -> real_escape_string($params['user']);
}
//Gathering the favorites for a user.
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
          LEFT JOIN favorites ON memes.id = favorites.meme_id
          LEFT JOIN users ON users.id = favorites.user_id
          WHERE favorites.user_id = '.$userId.';';

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
    "user":[
           "id":"",
        "username":""
    ],
    "votes":[
        "pos":""
        "neg":""
    ]
}]
*/