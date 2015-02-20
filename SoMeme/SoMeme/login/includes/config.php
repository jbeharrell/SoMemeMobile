<?php
/*
    Page Name: config.php
    Author: Robert Taylor
    Version: 1.0
    Description: This file is used for connecting to the database and salting passwords.
    Dependencies: This is dependent on the login and create account functionality.
    Change History: 2015.02.07 Original version by RT
    				2015.02.15 Updated version by IM
*/

session_start();

//Whatever folder the script is in
$Folder = '/login';

// !! Local testing db!
$db = mysqli_connect('localhost','root','','someme');

//Host db
//$db = mysqli_connect('localhost', 'rtaylor_dbadmin', 'dbpass','rtaylor_someme');

//If there was an error connecting, display a warning.
if (mysqli_connect_errno()) {
	echo "Error: Could not connect to database. Please try again later.";
	exit();
}

//Salty
define('SALT1', '%&^8r8746345');
define('SALT2', '08874%%!^%$^%');

$_SESSION['error'] = "";

$Output="";
?>