<?php
	include 'include/sessionHead.php';
	$dbMemberResult = mysqli_query($connection, "SELECT MEMBER_ID, SURNAME, FORENAME, EMAIL_ADDRESS FROM MEMBER");
?>
<!DOCTYPE html> 
<html lang="en">
<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<title>Update Team Member</title>
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
			<h2>Please select a team member that you wish to update:</h2>
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
					echo"<td>";
					echo"<form action=\"updateTeamMember2.php\" method=\"get\">";
					echo"<input type=\"hidden\" name=memberID value=\"".$a[0]."\">";
					echo"<input type=\"hidden\" name=surname value=\"".$a[1]."\">";
					echo"<input type=\"hidden\" name=forename value=\"".$a[2]."\">";
					echo"<input type=\"hidden\" name=email value=\"".$a[3]."\">";
					echo"<input type=\"Submit\" value=\"Select\"></input>";
					echo"</form></td>";
					echo"</tr>";
				}
			?>
			</tbody>
			</table>
		</div>
	</div>
</body>