<?php
	include 'include/sessionHead.php';
	$dbMemberResult = mysqli_query($connection, "SELECT MEMBER_ID, SURNAME, FORENAME, EMAIL_ADDRESS FROM MEMBER");
?>
<!DOCTYPE html> 
<html lang="en">
<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<title>Add Task</title>
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
			<h2>Add Task</h2>
			<p>Task Title: <p>
			<?php
				$taskTitle = $_SESSION['taskTitle'];
				echo $taskTitle;
			?>
			<p>Number Of Elements (Steps):</p>
			<?php
				$numOfElements = $_SESSION['numOfElements'];
				echo $numOfElements;
			?>
			<p>Task element descriptions:</p>
			<?php
				$i=0;
				do{
					$a = 'el'.$i;
					$description = $_SESSION[$a];
					echo "Element ".($i+1)." description: ". $description;
					echo "<br />";
					echo "<br />";
					$i++;
				} while ($i < $numOfElements);
			?>
			<p>Start date for the task and the deadline date for the task:</p>
			<?php
				$start = $_SESSION['start'];
				echo "Start Date: " . $start;
				echo "<br />";
				echo "<br />";
				$end = $_SESSION['end'];
				echo "Deadline Date: " . $end;
			?>
			<p>Task will be allocated to:</p>
			<?php
				$memID = $_GET['memberID'];
				$_SESSION['memberID'] = $memID;
				echo "Member ID: ".$memID;
				echo "<br />";
				echo "<br />";
				echo "Name: " . $_GET['forename'] . " " . $_GET['surname'];
			?>
			<h2>This is the task that will be submitted. Are you sure you want to submit this task to the database?</h2>
			<form action="submitNewTask.php">
			<input type="submit" name="submitTask" value="Submit">
			</form>
			<script>
				function clearText(i){
					if(i.value=="Enter Text Here"){
					i.value= "";
					}
				}
			</script>
		</div>
	</div>
</body>