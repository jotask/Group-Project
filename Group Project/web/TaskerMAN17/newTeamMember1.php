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
			<h1>My Team</h1>
			<table>
			<tbody>
			<tr>
			<td>Email</td><td><input form="next" name="newEmail" type="email" onkeyup=checkValid(this) onblur=checkValid(this) /></td><td id="emailValid">Not Valid</td>
			</tr>
			<tr>
			<td>First Name</td><td><input form="next" name="newForename" type="text" onkeyup=nameValid(this) /></td><td id="nameValid">Not Valid</td>
			</tr>
			<tr>
			<td>Last Name</td><td><input form="next" name="newLastname" type="text" onkeyup=lnameValid(this) /></td><td id="lnameValid">Not Valid</td>
			</tr>
			</tbody>
			</table>
			<div id="nextButton">
				<form name="next" id="next" action="newTeamMember2.php" method="get" onsubmit="return checkInput()">
					<input type=Submit value=Next></input>
				</form>
			</div>
			<div id="cancelButton">
				<form action="myTeam.php">
					<input type="submit" value="Cancel">
				</form>
			</div>
		</div>
		<script>
			function checkValid(i){
				if (i.value != "" || i.value != null) {
					document.getElementById('emailValid').innerHTML = "Valid";
				}
				if (i.value == "" || i.value == null){
					document.getElementById('emailValid').innerHTML = "Not Valid";
				}
			}
			function checkInput(){
				var input = document.forms['next']['newEmail'].value;
				if(input == null || input == ""){
					alert("You have not entered an email");
					return false;
				}
				var input = document.forms['next']['newForename'].value;
				if (input == null || input == ""){
					alert("You have not added a forename");
					return false;
				}
				var input = document.forms['next']['newLastname'].value;
				if(input == null || input == ""){
					alert("You have not entered a surname");
					return false;
				}
			}
			function nameValid(i){
				if (i.value != "" || i.value != null) {
					document.getElementById('nameValid').innerHTML = "Valid";
				} 
				if (i.value == "" || i.value == null){
					document.getElementById('nameValid').innerHTML = "Not Valid";
				}
			}
			function lnameValid(i){
				if (i.value != "" || i.value != null) {
					document.getElementById('lnameValid').innerHTML = "Valid";
				} 
				if (i.value == "" || i.value == null){
					document.getElementById('lnameValid').innerHTML = "Not Valid";
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