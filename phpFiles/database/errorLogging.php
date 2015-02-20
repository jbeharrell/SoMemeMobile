<?php
/*
Page Name: errorLogging.php
    Author: Ian Mori
    Version: 1.0
    Description: This page will create a record in the database for any query errors.
<<<<<<< HEAD
Dependencies: This page is dependent on a query failing and producing an error.
Change History: 2015.02.01 Original version by IM
*/

	//Gathering the $db info from the failed query
	$errorMessage = $db->error;
	$errorCode = $db->errno;
		
	//Accounting for escape characters
	if (!get_magic_quotes_gpc()) {
		$errorMessage = addslashes($errorMessage);
		$query=addslashes($query);
	}

	$errorMessage = $db->real_escape_string($errorMessage);
	$query = $db->real_escape_string($query);
	$db->close();

	//Creating another connection 	
	$db2 = new mysqli('localhost', 'root', '', 'someme');

	//If there was an error connecting, display a warning.
	if (mysqli_connect_errno()) {
		echo "Error: Could not connect to database. Please try again later.";
		exit();
	}

	//Insert into the database
	$result2 = $db2->query("INSERT INTO ERROR_LOGGING(error_code, error_desc, error_source)
     							VALUES($errorCode, '$errorMessage', '$query');");

	$db2->close();
?>