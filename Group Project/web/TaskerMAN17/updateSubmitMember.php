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
			<h2>The details will be changed to:</h2>
			<?php
				if (isset($_GET['forename'])){
					$chForename = $_GET['forename'];
					$_SESSION['chforename']=$chForename;
					echo "<h3>";
					echo "First Name:";
					echo "</h3>";
					echo $chForename;
					echo "<input form=\"change\" type=\"hidden\" name=\"forename\" value=\"".$chForename."\"></input>";
				}
				if (isset($_GET['lastname'])){
					$chSurname = $_GET['lastname'];
					$_SESSION['chsurname']=$chSurname;
					echo "<h3>";
					echo "Last Name:";
					echo "</h3>";
					echo $chSurname;
					echo "<input form=\"change\" type=\"hidden\" name=\"lastname\" value=\"".$chSurname."\"></input>";
				}
				if (isset($_GET['email'])){
					$chEmail = $_GET['email'];
					$_SESSION['chemail']=$chEmail;
					echo "<h3>";
					echo "Email:";
					echo "</h3>";
					echo $chEmail;
					echo "<input form=\"change\" type=\"hidden\" name=\"email\" value=\"".$chEmail."\"></input>";
				}
			?>
			<br />
			<h2>Are you sure that you want to commit the changes?</h2>
			<form id="change" action="submitUpdatedMember.php" method="get">
				<input type="Submit" value="Submit"></input>
			</form>
		</div>
	</div>
</body>