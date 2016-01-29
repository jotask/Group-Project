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
			<h2>The team member you have selected:</h2>
			<table>
			<thead>
			<th>Member ID</th><th>Surnaem</th><th>Forename</th><th>Email Address</th>
			</thead>
			<tbody>
			<?php
				$memberID = $_GET['memberID'];
				$surname = $_GET['surname'];
				$forename = $_GET['forename'];
				$email = $_GET['email'];
				echo "<tr>";
				echo "<td>";
				echo $memberID;
				echo "</td><td>";
				echo $surname;
				echo "</td><td>";
				echo $forename;
				echo "</td><td>";
				echo $email;
				echo "</td>";
				echo "</tr>";
				$memberID = filter_var($memberID, FILTER_SANITIZE_STRING, FILTER_FLAG_STRIP_HIGH);
				$surname = filter_var($surname, FILTER_SANITIZE_STRING, FILTER_FLAG_STRIP_HIGH);
				$forename = filter_var($forename, FILTER_SANITIZE_STRING, FILTER_FLAG_STRIP_HIGH);
				$email = filter_var($email, FILTER_SANITIZE_STRING, FILTER_FLAG_STRIP_HIGH);
				echo "<input form=\"delete\" type=\"hidden\" name=\"memberID\" value=\"".$memberID."\">";
				echo "<input form=\"delete\" type=\"hidden\" name=\"surname\" value=\"".$surname."\">";
				echo "<input form=\"delete\" type=\"hidden\" name=\"forename\" value=\"".$forename."\">";
				echo "<input form=\"delete\" type=\"hidden\" name=\"email\" value=\"".$email."\">";
			?>
			</tbody>
			</table>
			<h2>Are you sure you want to remove this person from the team?</h2>
			<form id="delete" action="submitDeleteMember.php" method="post">
				<input type="submit" name="submit" value="Commit" />
			</form>
			<div id="cancelButton">
				<form action="myTeam.php">
					<input type="submit" value="Cancel">
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