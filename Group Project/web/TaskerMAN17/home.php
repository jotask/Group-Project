<?php
	include 'include/sessionHead.php';
	$dbresult = mysqli_query($connection, "SELECT * FROM MEMBER");
	$dbTaskResult = mysqli_query($connection, "SELECT TASK.TASK_ID, MEMBER.MEMBER_ID, MEMBER.FORENAME, MEMBER.SURNAME, TASK.TASK_NAME, TASK.START_DATE, TASK.EXPECTED_COMPLETION_DATE, TASK.TASK_STATUS, TASK_ELEMENT.ELEMENT_ID, TASK_ELEMENT.TASK_DESCRIPTION FROM TASK_ELEMENT INNER JOIN TASK ON TASK.TASK_ID=TASK_ELEMENT.TASK_ID INNER JOIN MEMBER ON TASK.MEMBER_ID=MEMBER.MEMBER_ID ORDER BY TASK.EXPECTED_COMPLETION_DATE ASC LIMIT 15");
?>
<!DOCTYPE html> 
<html lang="en">
<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<title>Home</title>
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
			<h2>Recent Active Tasks:</h2>
			<table>
			<thead>
			<tr>
			<th>Task ID</th><th>Member ID</th><th>First Name</th><th>Last Name</th><th>Task Title</th><th>Start Date</th><th>Finish Date</th><th>Task Status</th><th>Element ID</th><th>Element Description</th>
			</tr>
			</thead>
			<tbody>
			<?php
				
				while ($b = mysqli_fetch_row($dbTaskResult)){
					echo"<tr>";
					for($c = 0; $c < 10; $c++){
						echo "<td>";
						echo $b[$c];
						echo "</td>";
					}
					echo"</tr>";
				}
			?>
			</tbody>
			</table>
			<p>The above tasks have the closest expected finish date.</p>
			<p>There are only 15 (maximum) tasks shown above, for a full list click on 'view tasks' on the side</p>
			<h2>Team Members:</h2>
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
				while ($a = mysqli_fetch_row($dbresult)){
					echo"<tr>";
					for($i = 0; $i < 4; $i++){
						echo "<td>" . $a[$i] . "</td>";
					}
					echo"</tr>";
				}
			?>
			</tbody>
			</table>
		</div>
	</div>
</body>