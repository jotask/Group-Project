<?php
// Enter your aber user name
	$userSessionPath = "ajw21";

//DO NOT EDIT THESE LINES OF CODE
	session_save_path("/aber/". $userSessionPath ."/public_html/TaskerMAN17/tmp/");	
	session_start();

// Enter your information here
// This is the domain of the mySQL server
	$dbLocation = "db.dcs.aber.ac.uk";
// This is your username
	$dbUser = "csgpadm_17";
// This is your password
	$dbPassword = "ZsdRKXnE";
// This is the database name
	$dbName = "csgp_17_15_16";

//DO NOT EDIT THESE LINES OF CODE
	$connection=mysqli_connect($dbLocation, $dbUser, $dbPassword, $dbName);
	if (!$connection) {
		die("No connection to the database possible ".mysql_error());
	}
?>
