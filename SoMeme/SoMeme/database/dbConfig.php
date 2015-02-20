<?php
/*
    Page Name: dbConfig.php
    Author: Jon Beharrell
    Version: 1.0
    Description: This page creates a connection to the database.
    Dependencies: None.
    Change History: 2015.02.12 Original version by JB
*/
//Local db
//$db = mysqli_connect('localhost','root','','someme');

//Host db

$db = mysqli_connect('localhost', 'root', '','someme');

//$db = mysqli_connect('localhost', 'rtaylor_dbadmin', 'dbpass','rtaylor_someme');


//If there was an error connecting, display a warning.
if (mysqli_connect_errno()) {
	echo "Error: Could not connect to database. Please try again later.";
	exit();
}