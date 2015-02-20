<?php
/*
    Page Name: userProfileData.php
    Author: Jon Beharrell
    Version: 1.0
    Description: This page returns a json string of all the data needed to be displayed on the user profile page.
    Dependencies: This page is dependent on a user navigating to a user profile page.
    Change History: 2015.02.06 Original version by JB
*/

include('../database/dbConfig.php');

$userId;
$sqlUserId;
$currentUser = 0;

//Grabbing initial data
if(isset($_POST['user_id'])) {
    $userId = $_POST['user_id'];
    $sqlUserId = $db->real_escape_string($userId);
    $currentUser = $_POST['currentUser'];
}else{
    $params = json_decode(file_get_contents('php://input'), true);
    $userId = $params['user_id'];
    $sqlUserId = $db->real_escape_string($userId);

    //Checking if a user if a member and logged in, building columns for the user
    if (isset($params['current_user']))
        $currentUser = $db->real_escape_string($params['current_user']);

}

$columns;
if($currentUser == $userId)
    $columns =
        'id,
        username,
        date_joined,
        email,
        first_name,
        last_name,
        dob,
        gender,
        country,
        default_sort';
else
    $columns =
        'id,
        username,
        date_joined';

//Building the select query
$query = 'SELECT '.$columns.' FROM users WHERE id = '.$sqlUserId.';';
$result = $db -> query($query);

//If the query fails, we try and log the error
if(!$result){
  include "../database/errorLogging.php";
}else{

  while ($row = $result->fetch_assoc()){
      $data = $row;
  }

  if($userId == $currentUser)
      $data['isUsers'] = true;
  else
      $data['isUsers'] = false;

  $result->free();

  //Building next select query
  $query = 'SELECT
            memes.id,
            memes.title,
            memes.user_id,
            memes.source_link,
            memes.timestamp,
            memes.views,
            (SELECT COUNT(*) FROM meme_votes WHERE meme_id = memes.id AND is_positive = 1) AS pos,
            (SELECT COUNT(*) FROM meme_votes WHERE meme_id = memes.id AND is_positive = 0) AS neg
            FROM memes WHERE memes.user_id = '.$sqlUserId.';';

  $result = $db -> query($query);

  //If the query fails, we try and log the error
  if(!$result){
    include "../database/errorLogging.php";
  }else{

    $memes = array();

    //Getting the voters for a user
    while ($row = $result->fetch_assoc()){
        $arr = $row;

        unset($arr['user_id']);

        $arr['votes'] = array('pos' => $row['pos'], 'neg' => $row['neg']);
        unset($arr['pos']);
        unset($arr['neg']);
        $arr['user'] = array('id' => $userId, 'username' => $data['username']);
        $memes[] = $arr;
    }

    $data['memes'] = $memes;

    $result->free();
    $db->close();

    print_r(json_encode($data));
  }
}

/*
 json format:

{
    "id":"",
    "username":"",
    "date_joined":"",
    "email":"", #~*
    "first_name":"", #~*
    "last_name":"", #~*
    "dob":"", #~*
    "gender":"", #~*
    "country":"", #~*
    "default_sort":"", #~*
    "isUsers":"",
    "memes":[{
        "id":"",
        "user":{
            "id":"",
            "username":""
        },
        "title":"",
        "source_link":"",
        "timestamp":"",
        "views":"",
        "votes":{
            "pos":"",
            "neg":"",
        }
    }]
}

#~* Only exits if user is on their own page
*/