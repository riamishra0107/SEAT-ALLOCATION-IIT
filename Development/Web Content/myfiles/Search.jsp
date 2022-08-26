<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>


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
<title>Search Page</title>
<link rel="stylesheet" href="Search.css">
</head>
<body>
	<%@include file="navbar.jsp"%>

	<div class="maincontainer" align="center">

		<%
		String keyword = "";

		if (request.getParameter("txtKeyword") != null) {

			keyword = request.getParameter("txtKeyword");

		}
		%>
		<div class="container">
			<form name="formSearch" method="get" action="Search.jsp"
				autocomplete="off">


				<div class="user-details">
					<span class="details"> Enter Details</span> <input
						name="txtKeyword" type="text" id="txtKeyword"
						placeholder="Enter ID or Name" value="<%=keyword%>" required
						style="width: 196px; padding: 4px; margin-top: 30px; background: #fff; color: black; border-radius: 5px; text-align: center; align-items: center; margin-right: 19px;">
					<div class="button">
						<input type="submit" value="Search">

					</div>
				</div>

			</form>

			<%
			Connection connect = null;

			Statement statement = null;

			try {

				Class.forName("com.mysql.jdbc.Driver");

				connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/allocate", "root", "1234");

				statement = connect.createStatement();

				String sql = "select * from Allocation where Full_name like '%" + keyword + "%' or emp_id like '%" + keyword
				+ "%' ";
				

				ResultSet resultset = statement.executeQuery(sql);
				if (resultset.next()) {
			%>
</div>

			<div class="table" align="center">
				<table width='650'>

					<tr>

						<th width='600'>

							<div align='center'>Full Name</div>

						</th>

						<th width='300'>

							<div align='center'> Id</div>

						</th>

						<th width='300'>

							<div align='center'>Email</div>

						</th>

						<th width='300'>

							<div align='center'>Floor</div>

						</th>

						<th width='300'>

							<div align='center'>Seat</div>

						</th>

					</tr>
					<%
					do {
					%>
					<tr align="center">
						<td><div align="left"><%=resultset.getString("Full_name")%></div></td>
						<td><%=resultset.getString("emp_id")%></td>
						<td><%=resultset.getString("email")%></td>
						<td><div align="center"><%=resultset.getString("floors")%></div></td>
						<td align="center"><%=resultset.getString("seat_num")%></td>
					</tr>
					<%
					} while (resultset.next());
					%>



				</table>
				<%
				} else {
				out.print("<h1>NO DATA FOUND</h1>");
				}
				} catch (Exception e) {
				out.println(e.getMessage());
				e.printStackTrace();
				}
				%>

			</div>
		</div>
</body>

</html>







