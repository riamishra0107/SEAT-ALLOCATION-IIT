
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Navbar</title>
<link rel="stylesheet" href="OtLoginstyle.css">

</head>
<body>

	<Nav>
		<label class="logo" style="font-family: montserrat;">INTIMETEC</label>
		<ul>
			<li><a class="active" href="OtLogin.jsp"> HOME </a></li>
			<li><a href="ShowSeats.jsp">VIEW SEATS</a></li>
			<li><a href="Search.jsp">SEARCH</a></li>
			<li><a href="Allocate.jsp">ALLOCATE </a></li>
			<li><a href="deallocation.jsp">DEALLOCATE</a></li>
			<li><a href="/DevWorkspace/LogoutServlet">LOGOUT</a></li>
		</ul>
	</Nav>
	<div class="image">
	<img class="home-bg-img" src="images/ITTbg.png" height="100px" >  </div>
</body>
</html>