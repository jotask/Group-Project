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
				$start = $_GET['start'];
				$_SESSION['start'] = $start;
				echo "Start Date: " . $start;
				echo "<br />";
				echo "<br />";
				$end = $_GET['end'];
				$_SESSION['end'] = $end;
				echo "Deadline Date: " . $end;
			?>
			<p>Please select the team member you wish to allocate the task to:</p>
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
				$b = 0;
				while ($a = mysqli_fetch_row($dbMemberResult)){
					echo"<tr>";
					for($i = 0; $i < 4; $i++){
						echo "<td>";
						echo $a[$i];
						echo "</td>";
					}
					echo"<td>";
					echo"<form action=\"addTask6.php\" method=\"get\">";
					echo"<input type=\"hidden\" name=\"memberID\" value=\"".$a[0]."\">";
					echo"<input type=\"hidden\" name=\"surname\" value=\"".$a[1]."\">";
					echo"<input type=\"hidden\" name=\"forename\" value=\"".$a[2]."\">";
					echo"<input type=\"Submit\" value=\"Select\"></input>";
					echo"</form></td>";
					echo"</tr>";
					$b++;
				}
			?>
			</tbody>
			</table>
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