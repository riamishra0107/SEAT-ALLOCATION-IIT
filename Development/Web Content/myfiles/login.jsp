<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%
session.removeAttribute("username");
session.invalidate();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login page</title>
<link rel="stylesheet" href="Login.css">
</head>
<body>

	<input type="hidden" id="status"
		value="<%=request.getAttribute("status")%>">
	
	<div class="bg-modal">
		<div class="modal-contents">
			<div class="center">
				<h1>Login</h1>
				<form action="LoginServlet" method="post" autocomplete="off">
					<div class="txt_field">
						<input type="text" name="username" required> <span></span>
						<label>Username</label>
					</div>
					<div class="txt_field">
						<input type="password" name="password" required> <span></span>
						<label>Password</label>
					</div>

					<input type="submit" value="Login">

				</form>
				<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
				<script>
					var status = document.getElementById("status").value;

					if (status == "failed") {
						swal("Sorry", "Wrong Username or Password", "error")

					}
				</script>
			</div>
		</div>
	</div>
</body>
</html>