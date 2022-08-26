<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page import="java.sql.ResultSet"%>
<%
if (session.getAttribute("username") == null) {
	response.sendRedirect(request.getContextPath() + "/login.jsp");
	return;
}
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // tells browser to not store in cache
response.setHeader("Pragma", "no-cache"); // for older version of http like http 1.0, etc
%>

<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>


<link rel="stylesheet" href="ShowSeats.css">
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	%>
	<%@include file="navbar.jsp"%>
	<form action="SeatCount" method="Post" autocomplete="off">
		<div class="maincontainer" align="center">

			<div class="container">
				<div class="title">VIEW SEATS</div>
				<div class="user-details">
					<div class="input-box">
						<div class="input-box">

							<span class="details"> Select Floor</span> <select name="floors"
								required>
								<option value="" disabled selected hidden>Choose Floor</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
						</div>
						<div class="button">
							<input type="submit" value="View Seats">

						</div>
					</div>
				</div>
			</div>
	</form>
	<%
	if (request.getAttribute("Available") == null) {
		request.setAttribute("Available", " Select floor ");
		request.setAttribute("Allocated", " Select floor ");
	}
	%>


	<div class=seats>
		<table style="width: 50%">
			<tr align="center">
				<th scope="col">Available</th>
				<th scope="col">Allocated</th>
			</tr>
			<tr align="center">
				<td><%=request.getAttribute("Available")%></td>
				<td><%=request.getAttribute("Allocated")%></td>
			</tr>

		</table>
	</div>

	<%
	Class.forName("com.mysql.cj.jdbc.Driver");
	String floors = (String) request.getAttribute("floors");
	try {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allocate", "root", "1234");
		PreparedStatement preparedStatement = connection
		.prepareStatement("SELECT * from  Allocation where floors=? order by seat_num");
		preparedStatement.setString(1, floors);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
	%>

	<div class="table" align="center">
		<table style="width: 90%">
			<thead>
				<tr align="center">
					<th scope="col">Full Name</th>
					<th scope="col">Employee Id</th>
					<th scope="col">Email</th>
					<th scope="col">Floor</th>
					<th scope="col">Seat number</th>
				</tr>
			</thead>
			<%
			do {
			%>
			<tbody>
				<tr align="center">
					<td><%=resultSet.getString("Full_name")%></td>
					<td><%=resultSet.getString("emp_id")%></td>
					<td><%=resultSet.getString("email")%></td>
					<td><%=resultSet.getString("floors")%></td>
					<td><%=resultSet.getString("seat_num")%></td>
				</tr>
			</tbody>
			<%
			} while (resultSet.next());
			%>
		</table>

		<%
		}
		connection.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
		%>
	</div>

</body>
</html>