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
// Check if there are any tasks in the database
		$dbNumOfAbandonedTask = mysqli_query ($connection, "SELECT * FROM TASK WHERE TASK_STATUS='abandoned'");
		$abandonCount = mysqli_num_rows($dbNumOfAbandonedTask);
		$dbTaskCount = mysqli_query ($connection, "SELECT * FROM TASK");
		$taskCount = mysqli_num_rows($dbTaskCount);
		if ($taskCount >= $abandonCount){
// Display table headers for tasks
			echo "<table border='1'>";
			echo "<th>" . "</th>";
			echo "<th>" . "Task ID" . "</th>";
			echo "<th>" . "Member ID" . "</th>";
			echo "<th>" . "Forename" . "</th>";
			echo "<th>" . "Surname" . "</th>";
			echo "<th>" . "Task Title" . "</th>";
			echo "<th>" . "Start Date" . "</th>";
			echo "<th>" . "Finish Date" . "</th>";
			echo "<th>" . "Status" . "</th>";
			echo "<th>" . "Element ID" . "</th>";
			echo "<th>" . "Element Description" . "</th>";
// SQL query for ordering the tasks via a user selected option
			if(isset($_POST['taskSort'])){
				if($_POST['taskSort'] == 'TaskID') {
					$sortQuery = 'SELECT TASK.TASK_ID, MEMBER.MEMBER_ID, MEMBER.FORENAME, MEMBER.SURNAME, TASK.TASK_NAME, TASK.START_DATE, TASK.EXPECTED_COMPLETION_DATE, TASK.TASK_STATUS, TASK_ELEMENT.ELEMENT_ID, TASK_ELEMENT.TASK_DESCRIPTION FROM TASK_ELEMENT INNER JOIN TASK ON TASK.TASK_ID=TASK_ELEMENT.TASK_ID INNER JOIN MEMBER ON TASK.MEMBER_ID=MEMBER.MEMBER_ID ORDER BY TASK.TASK_ID ASC';
				}
				elseif($_POST['taskSort'] == 'Task') {
					$sortQuery = 'SELECT TASK.TASK_ID, MEMBER.MEMBER_ID, MEMBER.FORENAME, MEMBER.SURNAME, TASK.TASK_NAME, TASK.START_DATE, TASK.EXPECTED_COMPLETION_DATE, TASK.TASK_STATUS, TASK_ELEMENT.ELEMENT_ID, TASK_ELEMENT.TASK_DESCRIPTION FROM TASK_ELEMENT INNER JOIN TASK ON TASK.TASK_ID=TASK_ELEMENT.TASK_ID INNER JOIN MEMBER ON TASK.MEMBER_ID=MEMBER.MEMBER_ID ORDER BY TASK.TASK_NAME ASC';
				}
				elseif($_POST['taskSort'] == 'MemberID') {
					$sortQuery = 'SELECT TASK.TASK_ID, MEMBER.MEMBER_ID, MEMBER.FORENAME, MEMBER.SURNAME, TASK.TASK_NAME, TASK.START_DATE, TASK.EXPECTED_COMPLETION_DATE, TASK.TASK_STATUS, TASK_ELEMENT.ELEMENT_ID, TASK_ELEMENT.TASK_DESCRIPTION FROM TASK_ELEMENT INNER JOIN TASK ON TASK.TASK_ID=TASK_ELEMENT.TASK_ID INNER JOIN MEMBER ON TASK.MEMBER_ID=MEMBER.MEMBER_ID ORDER BY MEMBER.MEMBER_ID ASC';
				}
				elseif($_POST['taskSort'] == 'Forename') {
					$sortQuery = 'SELECT TASK.TASK_ID, MEMBER.MEMBER_ID, MEMBER.FORENAME, MEMBER.SURNAME, TASK.TASK_NAME, TASK.START_DATE, TASK.EXPECTED_COMPLETION_DATE, TASK.TASK_STATUS, TASK_ELEMENT.ELEMENT_ID, TASK_ELEMENT.TASK_DESCRIPTION FROM TASK_ELEMENT INNER JOIN TASK ON TASK.TASK_ID=TASK_ELEMENT.TASK_ID INNER JOIN MEMBER ON TASK.MEMBER_ID=MEMBER.MEMBER_ID ORDER BY MEMBER.FORENAME ASC';
				}
				elseif($_POST['taskSort'] == 'Surname') {
					$sortQuery = 'SELECT TASK.TASK_ID, MEMBER.MEMBER_ID, MEMBER.FORENAME, MEMBER.SURNAME, TASK.TASK_NAME, TASK.START_DATE, TASK.EXPECTED_COMPLETION_DATE, TASK.TASK_STATUS, TASK_ELEMENT.ELEMENT_ID, TASK_ELEMENT.TASK_DESCRIPTION FROM TASK_ELEMENT INNER JOIN TASK ON TASK.TASK_ID=TASK_ELEMENT.TASK_ID INNER JOIN MEMBER ON TASK.MEMBER_ID=MEMBER.MEMBER_ID ORDER BY MEMBER.SURNAME ASC';
				}
				elseif($_POST['taskSort'] == 'StartDate') {
					$sortQuery = 'SELECT TASK.TASK_ID, MEMBER.MEMBER_ID, MEMBER.FORENAME, MEMBER.SURNAME, TASK.TASK_NAME, TASK.START_DATE, TASK.EXPECTED_COMPLETION_DATE, TASK.TASK_STATUS, TASK_ELEMENT.ELEMENT_ID, TASK_ELEMENT.TASK_DESCRIPTION FROM TASK_ELEMENT INNER JOIN TASK ON TASK.TASK_ID=TASK_ELEMENT.TASK_ID INNER JOIN MEMBER ON TASK.MEMBER_ID=MEMBER.MEMBER_ID ORDER BY TASK.START_DATE ASC';
				}
				elseif($_POST['taskSort'] == 'FinishDate') {
					$sortQuery = 'SELECT TASK.TASK_ID, MEMBER.MEMBER_ID, MEMBER.FORENAME, MEMBER.SURNAME, TASK.TASK_NAME, TASK.START_DATE, TASK.EXPECTED_COMPLETION_DATE, TASK.TASK_STATUS, TASK_ELEMENT.ELEMENT_ID, TASK_ELEMENT.TASK_DESCRIPTION FROM TASK_ELEMENT INNER JOIN TASK ON TASK.TASK_ID=TASK_ELEMENT.TASK_ID INNER JOIN MEMBER ON TASK.MEMBER_ID=MEMBER.MEMBER_ID ORDER BY TASK.EXPECTED_COMPLETION_DATE ASC';
				}
				elseif($_POST['taskSort'] == 'Status') {
					$sortQuery = 'SELECT TASK.TASK_ID, MEMBER.MEMBER_ID, MEMBER.FORENAME, MEMBER.SURNAME, TASK.TASK_NAME, TASK.START_DATE, TASK.EXPECTED_COMPLETION_DATE, TASK.TASK_STATUS, TASK_ELEMENT.ELEMENT_ID, TASK_ELEMENT.TASK_DESCRIPTION FROM TASK_ELEMENT INNER JOIN TASK ON TASK.TASK_ID=TASK_ELEMENT.TASK_ID INNER JOIN MEMBER ON TASK.MEMBER_ID=MEMBER.MEMBER_ID ORDER BY TASK.TASK_STATUS ASC';
				}
			} else {
				$sortQuery = 'SELECT TASK.TASK_ID, MEMBER.MEMBER_ID, MEMBER.FORENAME, MEMBER.SURNAME, TASK.TASK_NAME, TASK.START_DATE, TASK.EXPECTED_COMPLETION_DATE, TASK.TASK_STATUS, TASK_ELEMENT.ELEMENT_ID, TASK_ELEMENT.TASK_DESCRIPTION FROM TASK_ELEMENT INNER JOIN TASK ON TASK.TASK_ID=TASK_ELEMENT.TASK_ID INNER JOIN MEMBER ON TASK.MEMBER_ID=MEMBER.MEMBER_ID ORDER BY TASK.TASK_ID ASC';
			}
// Display task data after being sorted
			$dbSort = mysqli_query ($connection, $sortQuery);
			while ($a = mysqli_fetch_array($dbSort)) {
				$taskID = $a["TASK_ID"];	
				if ($a["TASK_STATUS"] <> 'abandoned'){ 
					echo "<tr>";
					echo '<form action="abandonConfirm.php" method="post">';
					echo '<td><input type="hidden" name="taskID" value="' . $taskID . '"/><input type="submit" value="Abandon" ></td>';
					echo "</form>";
					echo "<td>" . $a[0] . "</td>";
					echo "<td>" . $a[1] . "</td>";
					echo "<td>" . $a[2] . "</td>";
					echo "<td>" . $a[3] . "</td>";
					echo "<td>" . $a[4] . "</td>";
					echo "<td>" . $a[5] . "</td>";
					echo "<td>" . $a[6] . "</td>";
					echo "<td>" . $a[7] . "</td>";
					echo "<td>" . $a[8] . "</td>";
					echo "<td>" . $a[9] . "</td>";
					echo "</tr>\n";
				}
			}	
			echo "</table>\n";
// Print out a message if all tasks are set to abandoned
		}else{
			echo"All tasks have been set to abandoned";
		}
	?>
	
	</br>
		<form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="post">
		<select name="taskSort">
			<option value="TaskID">Task ID</option>
			<option value="MemberID">Member ID</option>
			<option value="Forename">Forename</option>
			<option value="Surname">Surname</option>
			<option value="Task">Task Title</option>
			<option value="StartDate">Start Date</option>
			<option value="FinishDate">Finsh Date</option>
			<option value="Status">Status</option>
		</select> 		
		<input type="submit" name="taskSortSubmit" value="Submit">
		</form>
		
	<?php
// Submit the option for sorting the task data
		if (isset($_POST['taskSort'])) {
			if (!isset($_SESSION['taskSort'])){
				$_SESSION['taskSort'] = array();
			}
			array_push($_SESSION['taskSort'], $_POST);
		}
	?>

	<?php
// Post the task ID to the next page
		if (isset($_POST['taskID'])) {
			if (!isset($_SESSION['taskID'])){
				$_SESSION['taskID'] = array();
			}
			array_push($_SESSION['taskID'], $_POST);
		}
	?>
	
		</div>
	</div>
	
</html>