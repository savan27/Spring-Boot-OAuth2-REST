<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome Page</title>
</head>
<body>
	
	<h1>Welcome to profile page</h1>
	
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
	<script>
		window.onload = function() {

				$.ajax({
					type : "GET",
					url : "http://localhost:8080/users/user",
					headers: {
						"Authorization": 'Bearer ' + localStorage.getItem('access_token')
					},
					dataType:"json",
					success : function(data) {
						if(data){
			                var len = data.length;
			                var txt = "";
			                if(len > 0){
			                    for(var i=0;i<len;i++){
			                        if(data[i].id && data[i].username && data[i].salary && data[i].age){
			                            txt += "<tr><td>"+data[i].id+"</td>" +
			                            		"<td>"+data[i].username+"</td>" +
			                            		"<td>"+data[i].salary+"</td>" +
			                            		"<td>"+data[i].age+"</td></tr>";
			                        }
			                    }
			                    if(txt != ""){
			                        $("#table").append(txt)
			                    }
			                }
			            }
					},
					error : function(data) {
						alert(data);
					}
			});
		};
	</script>
	
</body>
</html>
        