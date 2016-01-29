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
				$numOfElements = $_GET['numOfElements'];
				echo $numOfElements;
				$_SESSION['numOfElements'] = $numOfElements;
			?>
			<p>Please enter a description for each element of the task:</p>
			<?php
				$i=0;
				echo "<form name=\"element\" action=\"addTask4.php\" method=\"get\" onsubmit=\"return checkInput()\">";
				do{
					echo "Enter element ". ($i + 1) . " description: ";
					echo "<input  type=\"text\" name=\"el". $i . "\" value=\"Enter Text Here\" onfocus=clearText(this)>";
					echo "<br />";
					echo "<br />";
					$i++;
				} while ($i < $numOfElements);
				 echo "<input type=\"Submit\" value=\"Submit\">";
				echo "</form>";
			?>
			<script>
				function clearText(i){
					if(i.value=="Enter Text Here"){
					i.value= "";
					}
				}
			</script>
			<script>
				function checkInput(){
					for(i=0;i<20;i++){
						var x = "el";
						var name = x.concat(i);
						var input = document.forms['element'][name].value;
						if(input == null || input == ""){
							alert("You have not entered a value for an element");
							return false;
						} else if(input == "Enter Text Here"){
							alert("You have not entered a value for an element");
							return false;
						}
					}
				}
			</script>
		</div>
	</div>
</body>