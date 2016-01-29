<?php
	include 'include/sessionHead.php';

	if (!isset($_SESSION['taskID'])){
		$_SESSION['taskID'] = array();
	}
?>

<!DOCTYPE html> 
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<link href="style.css" rel="stylesheet" type="text/css" />
		<title>Abandon Task</title>
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
			echo "<th>" . "Task Title" . "</th>";
			echo "<th>" . "Member ID" . "</th>";
			echo "<th>" . "Start Date" . "</th>";
			echo "<th>" . "Finish Date" . "</th>";
			echo "<th>" . "Status" . "</th>";
// Display the task which was selected
			$dbTaskResult = mysqli_query($connection, "SELECT * FROM TASK ORDER BY TASK_ID");
			while ($a = mysqli_fetch_array($dbTaskResult)) {
				$taskID = $a["TASK_ID"];
				if ($_SESSION['taskID'] == $taskID){
					echo "<tr>";
					echo "<td>" . $a["TASK_ID"] . "</td>";
					echo "<td>" . $a["TASK_NAME"] . "</td>";
					echo "<td>" . $a["MEMBER_ID"] . "</td>";	
					echo "<td>" . $a["START_DATE"] . "</td>";	
					echo "<td>" . $a["EXPECTED_COMPLETION_DATE"] . "</td>";
					echo "<td>" . $a["TASK_STATUS"] . "</td>";
					echo "</tr>\n";
					echo "</table>\n";
// Create the abandon task button
					$taskID = $_SESSION['taskID'];
					echo '<form action="abandonConfirm2.php" method="post">';
					echo '<td><input type="hidden" name="taskID" value="' . $taskID . '"/><input type="submit" value="Abandon Task" ></td>';
					echo "</form>";
// Create the cancel abandon task button
					echo '<form action="abandonTask.php">';
					echo '<td><input type="hidden" name="memberID" value="abandonTaskCancel"/><input type="submit" value="Cancel Abandon Task" ></td>';
					echo "</form>";
				}
			}
			
			
		}
	?>
	
    </div>
	
</html>