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
			<?php
				$email = $_GET['newEmail'];
				$_SESSION['newEmail'] = $email;
				$forename = $_GET['newForename'];
				$_SESSION['newForename'] = $forename;
				$lastname = $_GET['newLastname'];
				$_SESSION['newLastname'] = $lastname;
			?>
			<h1>My Team</h1>
			<table>
			<tbody>
			<tr>
			<td>Email</td>
			<?php
				echo "<td>".$email."</td>";
			?>
			</tr>
			<tr>
			<td>First Name</td>
			<?php
				echo "<td>".$forename."</td>";
			?>
			</tr>
			<td>Last Name</td>
			<?php
				echo "<td>".$lastname."</td>";
			?>
			</tr>
			</tbody>
			</table>
			<h2>Are you sure you want to create this member of the team?</h2>
			<div id="nextButton">
				<form action="submitNewTeamMember.php">
					<input type=Submit value=Next></input>
				</form>
			</div>
		</div>
		<script>
			function checkValid(i){
				if (this.value != "") {
					document.getElementById('emailValid').innerHTML = "Valid";
				} 
				if (this.value == ""){
					document.getElementById('emailValid').innerHTML = "Not Valid";
				}
			}
		</script>
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