<?php
	include 'include/sessionHead.php';
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
					$description = $_GET[$a];
					$_SESSION[$a] = $description;
					echo "Element ".($i+1)." description: ". $description;
					echo "<br />";
					echo "<br />";
					$i++;
				} while ($i < $numOfElements);
			?>
			<p>Please set the start date for the task and the deadline date for the task: (YYYY-MM-DD in firefox)</p>
			<form name="date" action="addTask5.php" method="get" onsubmit="return checkInput()">
				<p>Start Date:</p><input name="start" id="start" type="date" min="1999-01-01" value="Enter Text Here" onfocus=clearText(this) />
				<p>End Date:</p><input name="end" id="end" type="date" value="Enter Text Here" onfocus=clearText(this) />
				<br /><input type="Submit" value="Submit" />
			</form>
			<script>
				function clearText(i){
					if(i.value=="Enter Text Here"){
					i.value= "";
					}
				}
				function checkInput(){
					var input = document.forms['date']['start'].value;
					if(input == null || input == ""){
						alert("You have not entered a date for the start date");
						return false;
					} else if (input == "Enter Text Here"){
						alert("You have not entered a date for the start date");
						return false;
					}
					var input = document.forms['date']['end'].value;
					if(input == null || input == ""){
						alert("You have not entered a date for the end date");
						return false;
					} else if (input == "Enter Text Here"){
						alert("You have not entered a date for the end date");
						return false;
					}
				}
			</script>
		</div>
	</div>
</body>