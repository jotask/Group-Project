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
// Assign the selected member ID to the select task
		if (isset($_POST['memberID'])) {
			$taskID = $_SESSION['taskID'];
			$memberID = $_POST['memberID'];
			$dbReassign = "UPDATE TASK SET MEMBER_ID='".$memberID."' WHERE TASK_ID = '".$taskID."'";
			if (mysqli_query($connection, $dbReassign)) {
				echo "Task has been reassigned";
				echo '<form action="reallocateTask.php">'; 
				echo '<input type="Submit" value="Continue">';
				echo "</form>";
			} else {
				echo "Error updating the task: " . mysqli_error($connection);
			}
		}
	?>
	
    </div>
	
</html>