<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Withdraw</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<h1>Withdraw Money</h1>
	<form action="withdraw" method="post">
		Amount: <input type="number" name="amount" step="0.01" required><br>
		<input type="submit" value="Withdraw">
	</form>
	<% if (request.getAttribute("message") != null) { %>
	<p><%= request.getAttribute("message") %></p>
	<% } %>
	<a href="menu.jsp">Back to Menu</a>
</body>
</html>