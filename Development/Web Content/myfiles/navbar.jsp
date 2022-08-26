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
<title>navbar</title>
<link rel="stylesheet" href="Navbar.css">
</head>
<body>

	<Nav style="position: fixed; width: 100%; top: 0;">
		<label class="logo" style="font-family: montserrat;">INTIMETEC</label>
		<ul>
			<li><a href="OtLogin.jsp"> HOME </a></li>
			<li><a href="ShowSeats.jsp">VIEW SEATS</a></li>
			<li><a href="Search.jsp">SEARCH</a></li>
			<li><a href="Allocate.jsp">ALLOCATE </a></li>
			<li><a href="deallocation.jsp">DEALLOCATE</a></li>
			<li><a href="login.jsp">LOGOUT</a></li>
		</ul>
	</Nav>
</body>
</html>