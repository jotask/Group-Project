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
				$taskTitle = $_GET['taskTitle'];
				echo $taskTitle;
				$_SESSION['taskTitle'] = $taskTitle;
			?>
			<p>How many elements are in the task?</p>
			<form name="submit" action="addTask3.php" method="get" onsubmit="return checkInput()">
				<input name="numOfElements" id="numOfElements" type="number" min="1" max="20" value="Enter Text Here" onfocus=clearText(this) onkeypress=checkKey(this) />
				<input type="Submit" value="Submit">
			</form>
			<script>
				function clearText(i){
					if(i.value=="Enter Text Here"){
					i.value= "";
					}
				}
			</script>
			<script>
				function checkInput(){
					var input = document.forms["submit"]["numOfElements"].value;
					if (input == null || input == ""){
						alert("You must enter a number of elements");
						return false;
					} 
				}
			</script>
		</div>
	</div>
</body>