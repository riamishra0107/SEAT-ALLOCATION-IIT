<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%
if (session.getAttribute("username") == null) {
	response.sendRedirect(request.getContextPath() + "/login.jsp");
	return;
}
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Allocation of employee</title>

<link rel="stylesheet" href="allocation.css">


</head>
<body>

	<input type="hidden" id="status"
		value="<%=request.getAttribute("status")%>">
	
	<div class="bg-modal">
		<div class="modal-contents">
			<%@include file="navbar.jsp"%>

			<div class="maincontainer" align="center">
				<div class="container">


					<div class="title">Allocation</div>
					<form action="AllocationServlet" method="post" autocomplete="off">
						<div class="user-details">
							<div class="input-box">
								<span class="details">Full Name</span> <input type="text"
									placeholder="Enter name" required name="Full_name">

							</div>
							<div class="input-box">
								<span class="details">Employee Id</span> <input type="text"
									placeholder="Enter id" required name="emp_id">
							</div>
							<div class="input-box">
								<span class="details">Email</span> <input type="email"
									placeholder="Enter email" required name="email">
							</div>
							<div class="input-box">
								<span class="details">Floors</span> <select name="floors">
									<option value="" disabled selected hidden>Choose Floor</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
								</select>
							</div>

							<div class="input-box">
								<span class="details">Seat number</span> <input type="number"
									min="1" placeholder="Enter seat number" required
									name="seat_num">

							</div>
						</div>

						<div class="button">
							<input type="submit" value="Allocate">
						</div>

					</form>
					<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
					<script>
						var status = document.getElementById("status").value;
						if (status == "success") {
							swal("Done", "Seat Allotment Completed!!",
									"success")
						}
						if (status == "failed") {
							swal("Sorry", "Error in Allocating seat ", "error")
						}
						if (status == "warning") {
							swal(
									"Sorry",
									"Error in Allocating seat ,Seat doesnot exist",
									"error")

						}
					</script>

				</div>
			</div>
		</div>
	</div>
</body>
</html>
