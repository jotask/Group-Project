<?php
	include 'include/sessionHead.php';
	echo "<form name=\"confirm\" action=\"createDatabase.php\" method=\"post\">";
	echo "<P>You are about to create the database. Performing this will reset ALL data if the database is already created. Are you sure you want to create the database?</p>";
	echo "<input type=\"submit\" name=\"submit\" value=\"Create The Database\">";
	echo "</form>";
?>	