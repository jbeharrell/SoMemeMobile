<?php
/*
    Page Name: functions.php
    Author: Robert Taylor
    Version: 1.0
    Description: This page holds various functions for account creation and user login
    Dependencies: This page is dependent on a user creating an account or a user logging in
    Change History: 2015.02.07 Original version by RT
*/

//Create account function
function createAccount($Username, $Password, $Email, $Fname, $Lname, $Dob, $Gender) {

	if (!empty($Username) && !empty($Password) && !empty($Email) && !empty($Fname) && !empty($Lname)  && !empty($Dob) && !empty($Gender)) {
		$uLen = strlen($Username);
		$pLen = strlen($Password);
        $eLen = strlen($Email);
        $fLen = strlen($Fname);
        $lLen = strlen($Lname);
        $dLen = strlen($Dob);
        $gLen = strlen($Gender);
		
		//escape username
		$eUsername = mysql_real_escape_string($Username);
		$sql = "SELECT username FROM users WHERE username = '" . $eUsername . "' LIMIT 1";

		$query = mysql_query($sql) or trigger_error("Query Failed: " . mysql_error());
		if ($uLen <= 4 || $uLen >= 11) {
			$_SESSION['error'] = "Username must be between 4 and 11 characters.";
		}elseif ($pLen < 6) {
			$_SESSION['error'] = "Password must be longer then 6 characters.";
        }elseif ($fLen < 1) {
            $_SESSION['error'] = "You must enter a first name";
        }elseif ($lLen < 1) {
            $_SESSION['error'] = "You must enter a last name";
        }elseif ($eLen < 5) {
            $_SESSION['error'] = "Email must be longer";
        }elseif ($dLen < 1) {
            $_SESSION['error'] = "You must enter a date of birth";
        }elseif ($gLen < 1) {
            $_SESSION['error'] = "You must enter a gender";
		}elseif (mysql_num_rows($query) == 1) {
			$_SESSION['error'] = "Username already exists.";
		}else {

			$sql = "INSERT INTO users (`username`, `password`,`email`, `first_name`, `last_name`, `dob`, `gender`) VALUES ('" . $eUsername . "', '" . hashPassword($Password, SALT1, SALT2) . "', '" . $Email . "', '" . $Fname . "', '" . $Lname . "', '" . $Dob . "', '" . $Gender . "');";
			
			$query = mysql_query($sql) or trigger_error("Query Failed: " . mysql_error());
			
			if ($query) {
				return true;
			}	
		}
	}
	
	return false;
}


function hashPassword($Password, $Salt1="2345#$%@3e", $Salt2="taesa%#@2%^#") {
	return sha1(md5($Salt2 . $Password . $Salt1));
}

//Checks session data to see if user is logged in
function loggedIn() {
	if (isset($_SESSION['loggedin']) && isset($_SESSION['username'])) {
		return true;
	}
	
	return false;
}

//Logout unset session
function logoutUser() {
    unset($_SESSION['username']);
	unset($_SESSION['loggedin']);

	return true;


}

//verifies if username/password combination is corrent
function validateUser($Username, $Password) {
	// See if the username and password are valid.
	$sql = "SELECT username FROM users 
		WHERE username = '" . mysql_real_escape_string($Username) . "' AND password = '" . hashPassword($Password, SALT1, SALT2) . "' LIMIT 1";
	$query = mysql_query($sql) or trigger_error("Query Failed: " . mysql_error());

	if (mysql_num_rows($query) == 1) {
		$row = mysql_fetch_assoc($query);
		$_SESSION['username'] = $row['username'];
		$_SESSION['loggedin'] = true;
			
		return true;
	}
	
	
	return false;
}
?>