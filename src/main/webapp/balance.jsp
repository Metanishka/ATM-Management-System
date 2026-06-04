<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Balance</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<h1>Your Balance</h1>
	<p>
		Balance: $<%= request.getAttribute("balance") %></p>
	<a href="menu.jsp">Back to Menu</a>
</body>
</html>