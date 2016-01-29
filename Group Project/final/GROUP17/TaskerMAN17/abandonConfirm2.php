<?php
	include 'include/sessionHead.php';
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
// Set the selected task to abandoned
		if (isset($_POST['taskID'])) {
			$taskID = $_SESSION['taskID'];
			$dbReassign = "UPDATE TASK SET TASK_STATUS='abandoned' WHERE TASK_ID = '".$taskID."'";
			if (mysqli_query($connection, $dbReassign)) {
				echo "Task has been set to abandoned";
				echo '<form action="abandonTask.php">'; 
				echo '<input type="Submit" value="Continue">';
				echo "</form>";
			} else {
				echo "Error updating the task: " . mysqli_error($connection);
			}
		}
	?>
	
    </div>
	
</html>