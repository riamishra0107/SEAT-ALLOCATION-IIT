<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	if (session.getAttribute("username") == null) 
	{
		response.sendRedirect(request.getContextPath() + "/login.jsp");
		return;
	}
%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // tells browser to not store in cache
response.setHeader("Pragma", "no-cache"); // for older version of http like http 1.0, etc

if (session.getAttribute("username") == null) {
	response.sendRedirect(request.getContextPath() + "/login.jsp");
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Deallocation of employee</title>
<link rel="stylesheet" href="deallocation.css">
</head>
<body>

	<input type="hidden" id="status"
		value="<%=request.getAttribute("status")%>">
	<!-- Modal Section -->
	<div class="bg-modal">
		<div class="modal-contents">

			<%@include file="navbar.jsp"%>
			<div class="maincontainer" align="center">
				<div class="container">

					<div class="title">Deallocation</div>
					<form action="Deallocation" method="post" autocomplete="off">
						<div class="user-details">
							<div class="input-box">
								<span class="details">Full Name</span> <input type="text"
									placeholder="Enter name" required name="Full_name">

							</div>
							<div class="input-box">
								<span class="details">Employee Id</span> <input type="text"
									placeholder="Enter Employee Id" required name="emp_id">
							</div>


						</div>
						<div class="button">
							<input type="submit" value="Deallocate">
						</div>

					</form>
					<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
					<script>
						var status = document.getElementById("status").value;
						if (status == "success") {

							swal("Done", "Seat Deallocated!!", "success")
						}
						if (status == "failed") {
							swal(
									"Sorry",
									"Error in Deallocating seat!! Check the entered details",
									"error")
						}
					</script>

				</div>
			</div>
		</div>
	</div>
</body>

</html>
