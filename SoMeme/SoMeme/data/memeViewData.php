<?php
/*
    Page Name: memeViewData.php
    Author: Jon Beharrell
    Version: 1.0
    Description: This page returns a json string of all the data needed to be displayed on the meme view page.
    Dependencies: This page is dependent on the user navigating to browser a meme.
    Change History: 2015.02.06 Original version by JB
*/

include('../database/dbConfig.php');

$userId;
$meme;
$sqlUserId;
$isView;
if(isset($_POST['user'])){
    $userId = $_POST['user'];
    $meme = $db->real_escape_string($_POST['meme']);
    $sqlUserId = $db->real_escape_string($userId);
    $isView = $db->real_escape_string($_POST['isView']);
}else {
    $params = json_decode(file_get_contents('php://input'), true);
    $userId = $params['user'];
    $meme = $db->real_escape_string($params['meme']);
    $sqlUserId = $db->real_escape_string($userId);
    $isView = $params['isView'];
}

//Updating views
if($isView) {
  $query = 'UPDATE memes SET views = views+1';
  if ($userId > 0)
    $query .= ', user_views = user_views+1';
  $query .= ' WHERE id =' . $meme;
  $result = $db->query($query);

  //If the query fails, we try and log the error
  if(!$result){
    include "../database/errorLogging.php";
  }
}

//Getting meme data
$query = 'SELECT
memes.id,
memes.title,
memes.content,
memes.source_link,
memes.user_id,
users.username,
memes.timestamp,
memes.views,
memes.downloads,
memes.user_views,
(SELECT COUNT(*) FROM meme_votes WHERE meme_id = '.$meme.' AND is_positive = 1) AS pos,
(SELECT COUNT(*) FROM meme_votes WHERE meme_id = '.$meme.' AND is_positive = 0) AS neg
FROM memes
LEFT JOIN users ON memes.user_id = users.id
WHERE memes.id = '.$meme.';';

$result = $db -> query($query);

//If the query fails, we try and log the error
if(!$result){
  include "../database/errorLogging.php";
}else{

  $data;

  while ($row = $result->fetch_assoc()){
    $arr = $row;
    $arr['user'] = array("id" => $row["user_id"], 'username' => $row['username']);
    unset($arr['user_id']);
    unset($arr['username']);

    $arr['votes'] = array('pos' => $row['pos'], 'neg' => $row['neg']);
    unset($arr['pos']);
    unset($arr['neg']);

    $arr['user']['id'] == $userId ? $arr['isUsers'] = true : $arr['isUsers'] = false;

    $data = $arr;
  }

  $result->free();
}


//Getting current user vote
$query = 'SELECT
        *
FROM meme_votes
WHERE meme_id = '.$meme.' AND user_id ='.$sqlUserId.';';

$result = $db -> query($query);

//If the query fails, we try and log the error
if(!$result){
  include "../database/errorLogging.php";
}else{

  if($result->num_rows>0){
    $data['thisUser']['vote'] = $result -> fetch_assoc()['is_positive'];
  }else{
    $data['thisUser']['vote'] = null;
  }
  $result->free();
}

$query = 'SELECT
           *
FROM favorites
WHERE meme_id = '.$meme.' AND user_id ='.$sqlUserId.';';

$result = $db -> query($query);

//If the query fails, we try and log the error
if(!$result){
  include "../database/errorLogging.php";
}else{

  if($result->num_rows>0){
    $data['thisUser']['favorite'] = true;
  }else{
    $data['thisUser']['favorite'] = false;
  }
  $result->free();
}

//Getting comments
$query = 'SELECT
comments.id,
comments.meme_id,
comments.user_id,
users.username,
comments.timestamp,
comments.content,
comments.parent_id,
(SELECT COUNT(*) FROM comment_votes WHERE comment_id = comments.id AND is_positive = 1) AS pos,
(SELECT COUNT(*) FROM comment_votes WHERE comment_id = comments.id AND is_positive = 0) AS neg
FROM comments
LEFT JOIN users ON comments.user_id = users.id
WHERE comments.meme_id = '.$meme.';';

$result = $db -> query($query);

//If the query fails, we try and log the error
if(!$result){
  include "../database/errorLogging.php";
}else{

  $comments = array();
  $children = array();

  while ($row = $result->fetch_assoc()){
    $arr = $row;
    $arr['user'] = array("id" => $row["user_id"], 'username' => $row['username']);
    unset($arr['user_id']);
    unset($arr['username']);

    $arr['votes'] = array('pos' => $row['pos'], 'neg' => $row['neg']);
    unset($arr['pos']);
    unset($arr['neg']);

    $arr['user']['id'] == $userId ? $arr['isUsers'] = true : $arr['isUsers'] = false;

    //Getting current user vote
    $query = 'SELECT
                    *
    FROM comment_votes
    WHERE comment_id = '.$arr['id'].' AND user_id ='.$sqlUserId.';';

    $voteResult = $db -> query($query);

    //If the query fails, we try and log the error
    if(!$voteResult){
      $result = $voteResult;
      include "../database/errorLogging.php";
    }else{

      if($voteResult->num_rows>0){
        $arr['thisUser']['vote'] = $voteResult -> fetch_assoc()['is_positive'];
      }else{
        $arr['thisUser']['vote'] = null;
      }

      if($arr['parent_id'])
        $children[] = $arr;
      else
        $comments[] = $arr;

    }
  }              

  //Gathering the comments/replies
  $newComments = array();
  foreach($comments as $comment){
    $comment['children'] = array();

    foreach($children as $child){
      if($comment['id'] == $child['parent_id']){
        unset($child['parent_id']);
        $comment['children'][] = $child;
      }
    }
    unset($comment['parent_id']);
    $newComments[] = $comment;
  }

  $data['comments'] = $newComments;

  $result->free();
  $db->close();
  print_r(json_encode($data));
}

/*
 json format:

{
    "thisUser":{
        "vote":"",
        "favorite":""
    }
  "id":"",
  "title":"",
  "content":"",
  "source_link":"",
  "timestamp":"",
  "views":"",
  "downloads":"",
  "user_views":"",
  "user":{
    "id":"",
    "username":""
  },
  "votes":{
    "pos":"",
    "neg":""
  },
  "isUsers":"",
  "comments":[
    {
      "id":"",
      "meme_id":"",
      "timestamp":"",
      "content":"",
      "user":{
          "id":"",
          "username":""
            },
      "votes":{
          "pos":"",
          "neg":""
            },
      "isUsers":"",
      "children":[
        {
          "id":"",
          "meme_id":"",
          "timestamp":"",
          "content":"",
          "user":{
                        "id":"",
              "username":""
                    },
          "votes":{
                        "pos":"",
              "neg":""
                    },
          "isUsers":""
        },
      ]
    },
  ]
}
*/