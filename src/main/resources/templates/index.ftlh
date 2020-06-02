<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="" method="post">
	
		<label>User Name</label>
		<input type="text" name="username" id="userName" />
		
		<label>Password</label>
		<input type="text" name="password" id="password" />
		
		<button type="button" value="Submit" id="login" >
			Login
		</button>
	
	</form>
	
	<table id="table" class="hidden">
	    <tr>
	        <th>Id</th>
	        <th>Username</th>
	        <th>Salary</th>
	        <th>Age</th>
	    </tr>
	</table>
	
	<!-- Scripts -->
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="js/popper.min.js"></script>
	<script type="text/javascript" src="js/ajax.js"></script>
	
</body>
</html>