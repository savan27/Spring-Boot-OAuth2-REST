$(document).ready(function() {
	
	// Login
	$("#login").click(function() {
		
		var body = {
		    grant_type: 'password',
		    username: $("#userName").val(),
		    password: $("#password").val()
		};
		
		$.ajax({
			type : "POST",
			url : "http://localhost:8080/oauth/token",
			beforeSend: function (xhr) {
			    xhr.setRequestHeader ("Authorization", "Basic " + btoa("client" + ":" + "secret"));
			},
			data : body,
			dataType: 'json',
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			success : function(data) {
				alert(data.access_token);
				localStorage.setItem("access_token", data.access_token);
				alert((data.access_token != null));
				alert("from local storage : "+localStorage.getItem('access_token'));
				alert("authorities " + data.authorities.authority)
				alert("authorities " + data.authorities[0].authority)
				
				if (data.authorities[0].authority == "ROLE_ADMIN") {
					AdminLogin();
				}else {
					UserLogin($("#userName").val());
				}
			},
			error : function(data) {
				alert(data);
			}
		});
		return false;
	});	
	
	function UserLogin(username) {
		
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/users/user/"+username,
			headers: {
				"Authorization": 'Bearer ' + localStorage.getItem('access_token')
			},
			dataType:"json",
			success : function(data) {
				alert("inside success");
				if(data){
					alert(data.username);
					var txt = "";
					txt += "<tr><td>"+data.id+"</td>" +
		            		"<td>"+data.username+"</td>" +
		            		"<td>"+data.salary+"</td>" +
		            		"<td>"+data.age+"</td></tr>";
	                if(txt != ""){
	                	$("#table").append(txt)
	                }
	            }
			},
			error : function(data) {
				alert(data.message);
			}
	});
		
	}
	



			function AdminLogin() {

						$.ajax({
							type : "GET",
							url : "http://localhost:8080/users/user",
							headers : {
								"Authorization" : 'Bearer '
										+ localStorage.getItem('access_token')
							},
							dataType : "json",
							success : function(data) {
								if (data) {
									var len = data.length;
									var txt = "";
									if (len > 0) {
										for (var i = 0; i < len; i++) {
											if (data[i].id && data[i].username
													&& data[i].salary
													&& data[i].age) {
												txt += "<tr><td>" + data[i].id
														+ "</td>" + "<td>"
														+ data[i].username
														+ "</td>" + "<td>"
														+ data[i].salary
														+ "</td>" + "<td>"
														+ data[i].age
														+ "</td></tr>";
											}
										}
										if (txt != "") {
											$("#table").append(txt)
										}
									}
								}
							},
							error : function(data) {
								alert(data.message);
							}
						});

					}
	
	
});