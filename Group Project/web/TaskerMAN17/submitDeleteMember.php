<?php
	include 'include/sessionHead.php';
	$dbMemberResult = mysqli_query($connection, "SELECT MEMBER_ID, SURNAME, FORENAME, EMAIL_ADDRESS FROM MEMBER");
	
?>
<!DOCTYPE html> 
<html lang="en">
<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<title>My Team - New Team Member</title>
</head>
<body>
	<div id="header">
		<span id="largeHeading">TaskerMAN</span>
	</div>
	<div id="nav">
		<?php
			include 'include/nav.php';
		?>
	</div>
	<div id="main">
		<div id="innerMain">
		<h2>Deletion has been:</h2>
		<br />
			<?php
				$memberID = $_POST['memberID'];
				$surname = $_POST['surname'];
				$forename = $_POST['forename'];
				$email = $_POST['email'];
				$query = "DELETE FROM MEMBER WHERE MEMBER_ID = ".$memberID;
				if ($res = mysqli_query($connection, $query)){
					echo "successful";
				} else {
					echo "unsuccessful";
				}
			?>
			<div id="cancelButton">
				<form action="myTeam.php">
					<input type="submit" value="Home">
				</form>
			</div>
		</div>
		<div id="buttons">
			<div id="newTeamMember">
				<a>New Team Member</a>
			</div>
			<div id="updateTeamMember">
				<a>Update Existing Team Member</a>
			</div>
			<div id="deleteTeamMember">
				<a>Delete Team Member</a>
			</div>
		</div>
	</div>
</body>