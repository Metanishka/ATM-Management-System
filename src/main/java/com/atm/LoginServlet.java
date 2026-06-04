package com.atm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		int pin = Integer.parseInt(request.getParameter("pin"));
		try (Connection conn = DBConnection.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE account_number=? AND pin=?");
			ps.setInt(1, accountNumber);
			ps.setInt(2, pin);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("accountNumber", accountNumber);
				response.sendRedirect("menu.jsp");
			} else {
				request.setAttribute("error", "Invalid credentials");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Database error");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
