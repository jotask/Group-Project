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
			<h3>Task Title:</h3>
			<script>
				function clearText(i){
					if(i.value=="Enter Text Here"){
					i.value= "";
					}
				}
			</script>
			<script>
				//http://www.w3schools.com/js/js_validation.asp - used for next section of code
				function checkInput(){
					var input = document.forms["submit"]["taskTitle"].value;
					if (input == null || input == ""){
						alert("You must enter a title for the task");
						return false;
					} else if (input == "Enter Text Here"){
						alert("You must enter a title for the task");
						return false;
					}
				}
			</script>
			<form name="submit" action="addTask2.php" method="get" onsubmit="return checkInput()">
				<input name="taskTitle" id="taskTitle" type="text" value="Enter Text Here" maxlength="50" onfocus=clearText(this)></input>
				<input type="Submit" value="Submit"  />
			</form>
		</div>
	</div>
</body>