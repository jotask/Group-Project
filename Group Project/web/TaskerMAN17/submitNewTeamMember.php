<?php
	include 'include/sessionHead.php';
?>
<!DOCTYPE html> 
<html lang="en">
<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<title>Template</title>
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
				$forename = $_SESSION['newForename'];
				$lastname = $_SESSION['newLastname'];
				$email = $_SESSION['newEmail'];
				$query = "INSERT INTO MEMBER (SURNAME, FORENAME, EMAIL_ADDRESS) VALUES ('".$lastname."', '".$forename."', '".$email."')";
				$res = mysqli_query($connection, $query);
				$query = "SELECT * FROM MEMBER";
				$search =  mysqli_query($connection, $query);
				$point = 0;
				while ($a = mysqli_fetch_row($search)){
					$point = $a[0];
				}
				$query = "INSERT INTO TEAM_MEMBER (TEAM_ID, MEMBER_ID) VALUES (100, ".$point.")";
				$res = mysqli_query($connection, $query);
				$dbMemberResult = mysqli_query($connection, "SELECT * FROM MEMBER");
			?>
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
	</div>
</body>