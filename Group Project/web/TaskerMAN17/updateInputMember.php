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
			<h2>You selected to change:</h2>
			<?php
				if (isset($_GET['firstname'])){
					$chForename = $_GET['firstname'];
					echo "<h3>";
					echo "First Name:";
					echo "</h3>";
					echo "<input form=\"change\" type=\"text\" name=\"forename\"></input>";
				}
				if (isset($_GET['surname'])){
					$chSurname = $_GET['surname'];
					echo "<h3>";
					echo "Last Name:";
					echo "</h3>";
					echo "<input form=\"change\" type=\"text\" name=\"lastname\"></input>";
				}
				if (isset($_GET['email'])){
					$chEmail = $_GET['email'];
					echo "<h3>";
					echo "Email:";
					echo "</h3>";
					echo "<input form=\"change\" type=\"email\" name=\"email\"></input>";
				}
			?>
			<br />
			<form id="change" name="change" action="updateSubmitMember.php" method="get" onsubmit="return checkInput()">
				<input type="Submit" value="Submit">
			</form>
			<script>
				function checkInput(){
					<?php
						if (isset($_GET['firstname'])){
							echo "var input = document.forms['change']['forename'].value;";
							echo "if(input == null || input == \"\"){";
							echo "alert(\"You have not enter a forename\");";
							echo "return false;";
							echo "}";
						}
					?>
					<?php
						if (isset($_GET['surname'])){
							echo "var input = document.forms['change']['lastname'].value;";
							echo "if(input == null || input == \"\"){";
							echo "alert(\"You have not enter a surname\");";
							echo "return false;";
							echo "}";
						}
					?>
					<?php
						if (isset($_GET['email'])){
							echo "var input = document.forms['change']['email'].value;";
							echo "if(input == null || input == \"\"){";
							echo "alert(\"You have not enter a email\");";
							echo "return false;";
							echo "}";
						}
					?>
				}
			</script>
		</div>
	</div>
</body>