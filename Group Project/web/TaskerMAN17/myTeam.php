<?php
	include 'include/sessionHead.php';
	$dbMemberResult = mysqli_query($connection, "SELECT MEMBER_ID, SURNAME, FORENAME, EMAIL_ADDRESS FROM MEMBER");
	
?>
<!DOCTYPE html> 
<html lang="en">
<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<title>My Team</title>
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
			<thead>
			<tr>
			<th>ID number</th>
			<th>Surname</th>
			<th>Forename</th>
			<th>Email Address</th>
			</tr>
			</thead>
			<tbody>
			<?php
				while ($a = mysqli_fetch_row($dbMemberResult)){
					echo"<tr>";
					for($i = 0; $i < 4; $i++){
						echo "<td>";
						echo $a[$i];
						echo "</td>";
					}
					echo"</tr>";
				}
			?>
			</tbody>
			</table>
		</div>
		<div id="buttons">
			<div id="newTeamMember">
				<a href="newTeamMember1.php">New Team Member</a>
			</div>
			<div id="updateTeamMember">
				<a href="updateTeamMember.php">Update Existing Team Member</a>
			</div>
			<div id="deleteTeamMember">
				<a href="deleteTeamMember.php">Delete Team Member</a>
			</div>
		</div>
	</div>
</body>