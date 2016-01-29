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
			<h2>You have select to update:</h2>
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
				$nameFirst = $_GET['forename'];
				$_SESSION['forename'] = $nameFirst;
				$nameLast = $_GET['surname'];
				$_SESSION['surname'] = $nameLast;
				$memberID = $_GET['memberID'];
				$_SESSION['memberID'] = $memberID;
				$email = $_GET['email'];
				$_SESSION['email'] =  $email;
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
			<h2>Which detail would you like to update:</h2>
			<form action="updateInputMember.php">
				<input type="checkbox" name="surname">Surname</input>
				<br />
				<input type="checkbox" name="firstname">Forename</input>
				<br />
				<input type="checkbox" name="email">Email</input>
				<br />
				<input type="Submit" value="Submit">
			</form>
		</div>
	</div>
</body>