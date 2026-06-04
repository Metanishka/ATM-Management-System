<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ATM Login</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>ATM Login</h1>
	<form action="login" method="post">
		Account Number: <input type="text" name="accountNumber" required><br>
		PIN: <input type="password" name="pin" required><br> <input
			type="submit" value="Login">
	</form>
	
	<%
	if (request.getAttribute("error") != null) {
	%>
	<p style="color: red;"><%=request.getAttribute("error")%></p>
	<%
	}
	%>
</body>
</html>