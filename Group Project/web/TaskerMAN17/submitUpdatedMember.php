<?php
	include 'include/sessionHead.php';
	
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
			<h2>Previous Data:</h2>
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
				
				$memberID = $_SESSION['memberID'];
				$nameLast = $_SESSION['surname'];
				$nameFirst = $_SESSION['forename'];
				$email  = $_SESSION['email'];
				echo"<td>";
				echo $memberID;
				echo"</td>";
				echo"<td>";
				echo $nameLast;
				echo"</td>";
				echo"<td>";
				echo $nameFirst;
				echo"</td>";
				echo"<td>";
				echo $email;
				echo"</td>";
			?>
			</tbody>
			</table>
			<h2>New Data:</h2>
			<?php
				if (isset($_GET['forename'])){
					$chForename = $_GET['forename'];
					$_SESSION['chforename']=$chForename;
					echo "Forename = ".$chForename;
					echo "<br />";
					$query = "UPDATE MEMBER SET FORENAME='".$chForename."' WHERE MEMBER_ID=".$memberID;
					$res = mysqli_query($connection, $query);
				}
				if (isset($_GET['lastname'])){
					$chSurname = $_GET['lastname'];
					$_SESSION['chsurname']=$chSurname;
					echo "Surname = ".$chSurname;
					echo "<br />";
					$query = "UPDATE MEMBER SET SURNAME='".$chSurname."' WHERE MEMBER_ID=".$memberID;
					$res = mysqli_query($connection, $query);
				}
				if (isset($_GET['email'])){
					$chEmail = $_GET['email'];
					$_SESSION['chemail']=$chEmail;
					echo "Email = ".$chEmail;
					echo "<br />";
					$query = "UPDATE MEMBER SET EMAIL_ADDRESS='".$chEmail."' WHERE MEMBER_ID=".$memberID;
					$res = mysqli_query($connection, $query);
				}
			?>
			<br />
			<h2>New Team Data:</h2>
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
				$dbMemberResult = mysqli_query($connection, "SELECT MEMBER_ID, SURNAME, FORENAME, EMAIL_ADDRESS FROM MEMBER");
				while ($a = mysqli_fetch_row($dbMemberResult)){
					echo"<tr>";
					for($i = 0; $i < 4; $i++){
						echo "<td>";
						echo $a[$i];
						echo "</td>";
					}
					echo"<tr>";
				}
			?>
			</tbody>
			</table>
			<form action="myTeam.php">
				<input type="submit" value="Home">
			</form>
		</div>
	</div>
</body>