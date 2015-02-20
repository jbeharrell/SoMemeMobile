<?php
/*
    Page Name: functions.php
    Author: Jon Beharrell
    Version: 1.0
    Description: This page has helpful functions relating to the database.
    Dependencies: None.
    Change History: 2015.02.12 Original version by JB
*/


/*
    Function Name: createNotification
    Author: Jon Beharrell
    Version: 1.0
    Description: This function will insert a new record for notifications in the database.
    Dependencies: This is dependent on new content for a user such as their meme being favorited.
    Change History: 2015.02.12 Original version by JB
*/
function createNotification($db, $userId, $link, $message){
    $db -> query('INSERT INTO notifications(user_id, source_link, content) VALUES('.$userId.', \''.$link.'\', \''.$message.'\');');
}

/*
    Function Name: getUsernameById
    Author: Jon Beharrell
    Version: 1.0
    Description: This function will gather a user's id
    Dependencies: This function is called throughout several pages.
    Change History: 2015.02.12 Original version by JB
*/
function getUsernameById($db, $userId){
    $result = $db -> query('SELECT id, username FROM users WHERE id='.$userId.';');
    $row = $result->fetch_assoc();
    return $row['username'];
}

/*
    Function Name: getMemeCreatorId
    Author: Jon Beharrell
    Version: 1.0
    Description: This function will gather a meme creator's id
    Dependencies: This function is called throughout several pages.
    Change History: 2015.02.12 Original version by JB
*/
function getMemeCreatorId($db, $memeId){
    $result = $db -> query('SELECT id, user_id FROM memes WHERE id='.$memeId.';');
    $row = $result->fetch_assoc();
    return $row['user_id'];
}

/*
    Function Name: getCommentCreatorId
    Author: Jon Beharrell
    Version: 1.0
    Description: This function will gather a comment creator's id
    Dependencies: This function is called throughout several pages.
    Change History: 2015.02.12 Original version by JB
*/
function getCommentCreatorId($db, $commentId){
    $result = $db -> query('SELECT id, user_id FROM comments WHERE id='.$commentId.';');
    $row = $result->fetch_assoc();
    return $row['user_id'];
}

/*
    Function Name: getCommentChildrenIds
    Author: Jon Beharrell
    Version: 1.0
    Description: This function will gather the children ids of a comment
    Dependencies: This function is called throughout several pages.
    Change History: 2015.02.12 Original version by JB
*/
function getCommentChildrenIds($db, $parentId){
    $result = $db -> query('SELECT id, parent_id FROM comments WHERE parent_id='.$parentId.';');
    $ids = array();
    while($row = $result->fetch_assoc()){
        $ids[] = $row['id'];
    }
    return $ids;
}