<?php
	include 'include/sessionHead.php';
?>

<!DOCTYPE html> 
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<link href="style.css" rel="stylesheet" type="text/css" />
		<title>Reallocate Task</title>
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
		</br>
		<div id="innerMain">
			</br>
	
	<?php
// Display table headers for tasks
		if (isset($_POST['taskID'])) {
			$_SESSION['taskID'] = $_POST['taskID'];
			echo "<table border='1'>";
			echo "<th>" . "Task ID" . "</th>";
			echo "<th>" . "Member ID" . "</th>";
			echo "<th>" . "Task" . "</th>";
			echo "<th>" . "Start Date" . "</th>";	
			echo "<th>" . "Finish Date" . "</th>";
			echo "</br>";
// Display the task which was selected
			$dbTaskResult = mysqli_query ($connection, "SELECT * FROM TASK ORDER BY TASK_ID");
			while ($a = mysqli_fetch_array($dbTaskResult)) {
				$taskID = $a["TASK_ID"];
				if ($_SESSION['taskID'] == $taskID){
					$selectedTask = $taskID;
					echo "<tr>";
					echo "<td>" . $a["TASK_ID"] . "</td>";
					echo "<td>" . $a["MEMBER_ID"] . "</td>";
					echo "<td>" . $a["TASK_NAME"] . "</td>";
					echo "<td>" . $a["START_DATE"] . "</td>";
					echo "<td>" . $a["EXPECTED_COMPLETION_DATE"] . "</td>";
					echo "</tr>\n";
				}
			}	
		echo "</table>\n";
		} else {
			echo "No tasks have been selected, please return to the previous page and select a task in order to continue";
		}
	?>

    <?php 
// Display table headers for members
		if (isset($_POST['taskID'])) {
			$_SESSION['taskID'] = $_POST['taskID'];
			$taskID = $_SESSION['taskID'];
			echo "<table border='1'>";
			echo "<th>" . "</th>";
			echo "<th>" . "Member ID" . "</th>";
			echo "<th>" . "Forename" . "</th>";
			echo "<th>" . "Surname" . "</th>";
			echo "<th>" . "Email Address" . "</th>";
			$dbMemberResult = mysqli_query ($connection, "SELECT * FROM MEMBER ORDER BY MEMBER_ID");
			while ($a = mysqli_fetch_array($dbMemberResult)) {
				$memberID = $a["MEMBER_ID"];
// Display all members data
					echo "<tr>";
					echo '<form action="reallocateAssign2.php" method="post">';
					echo '<td><input type="hidden" name="memberID" value="' . $memberID . '"/><input type="submit" value="Assign" ></td>';
					echo "</form>";
					echo "<td>" . $a["MEMBER_ID"] . "</td>";
					echo "<td>" . $a["FORENAME"] . "</td>";
					echo "<td>" . $a["SURNAME"] . "</td>";
					echo "<td>" . $a["EMAIL_ADDRESS"] . "</td>";
					echo "</tr>\n";
			}
			echo "</table>\n";
		} 
    ?>

    </div>
	
</html>