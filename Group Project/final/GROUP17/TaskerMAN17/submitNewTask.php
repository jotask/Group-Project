<?php
	include 'include/sessionHead.php';
	$dbTaskResult = mysqli_query($connection, "SELECT TASK_ID, TASK_NAME, MEMBER_ID, START_DATE, EXPECTED_COMPLETION_DATE, TASK_STATUS FROM TASK");
?>
<!DOCTYPE html> 
<html lang="en">
<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<title>Commit New Task</title>
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
			<h3>Before:</h3>
			<table>
			<thead>
			<tr>
			<th>Task ID</th><th>Task Title</th><th>Member ID</th><th>Start Date</th><th>End Date</th><th>Task Status</th>
			</tr>
			</thead>
			<tbody>
			<?php
				while ($b = mysqli_fetch_row($dbTaskResult)){
					echo"<tr>";
					for($c = 0; $c < 6; $c++){
						echo "<td>";
						echo $b[$c];
						echo "</td>";
					}
					echo"</tr>";
				}
			?>
			</tbody>
			</table>
			<h3>After Submission:</h3>
			<?php
				$numOfElements = $_SESSION['numOfElements'];
				$tT = $_SESSION['taskTitle'];
				$sT = $_SESSION['start'];
				$eD = $_SESSION['end'];
				$mID = $_SESSION['memberID'];
				$tS = "allocated";
				$tID = "100";
				$query = "INSERT INTO TASK (TASK_NAME, TEAM_ID, MEMBER_ID, START_DATE, EXPECTED_COMPLETION_DATE, TASK_STATUS) VALUES ('".$tT."', ".$tID.", ".$mID.", '".$sT."', '".$eD."', '".$tS."')";
				$res = mysqli_query($connection, $query);
				$query = "SELECT * FROM TASK";
				$res2 = mysqli_query($connection, $query);
				$taskID = 0;
				while ($a = mysqli_fetch_row($res2)){
					$taskID = $a[0];
				}
				$i = 0;
				while ($i < $numOfElements){
					$pointer = "el".$i;
					$element = $_SESSION[$pointer];
					$query = "INSERT INTO TASK_ELEMENT (TASK_ID, TASK_DESCRIPTION) VALUES (".$taskID.", '".$element."')";
					$res = mysqli_query($connection, $query);
					$i++;
				}
				$dbTaskResult = mysqli_query($connection, "SELECT TASK.TASK_ID, TASK.TASK_NAME, TASK.MEMBER_ID, TASK.START_DATE, TASK.EXPECTED_COMPLETION_DATE, TASK.TASK_STATUS, TASK_ELEMENT.ELEMENT_ID, TASK_ELEMENT.TASK_DESCRIPTION FROM TASK_ELEMENT INNER JOIN TASK ON TASK.TASK_ID=TASK_ELEMENT.TASK_ID ORDER BY TASK.TASK_ID");
				
			?>
			<table>
			<thead>
			<tr>
			<th>Task ID</th><th>Task Title</th><th>Member ID</th><th>Start Date</th><th>End Date</th><th>Task Status</th><th>Element ID</th><th>Element Content</th>
			</tr>
			</thead>
			<tbody>
			<?php
				while ($b = mysqli_fetch_row($dbTaskResult)){
					echo"<tr>";
					for($c = 0; $c < 8; $c++){
						echo "<td>";
						echo $b[$c];
						echo "</td>";
					}
					echo"</tr>";
				}
			?>
		</div>
	</div>
</body>