<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>Transaction History</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<h1>Transaction History</h1>
	<ul>
		<% List<String> history = (List<String>) request.getAttribute("history");
           if (history != null) {
               for (String h : history) { %>
		<li><%= h %></li>
		<% }
           } %>
	</ul>
	<a href="menu.jsp">Back to Menu</a>
</body>
</html>