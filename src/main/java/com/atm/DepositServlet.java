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
 * Servlet implementation class DepositServlet
 */
//@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer accountNumber = (Integer) session.getAttribute("accountNumber");
		if (accountNumber == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		double amount = Double.parseDouble(request.getParameter("amount"));
		try (Connection conn = DBConnection.getConnection()) {
// Update balance
			PreparedStatement ps = conn
					.prepareStatement("UPDATE users SET balance = balance + ? WHERE account_number=?");
			ps.setDouble(1, amount);
			ps.setInt(2, accountNumber);
			ps.executeUpdate();
// Log transaction
			ps = conn.prepareStatement(
					"INSERT INTO transactions (account_number, type, amount) VALUES (?, 'deposit', ?)");
			ps.setInt(1, accountNumber);
			ps.setDouble(2, amount);
			ps.executeUpdate();
// Get new balance
			ps = conn.prepareStatement("SELECT balance FROM users WHERE account_number=?");
			ps.setInt(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				request.setAttribute("message", "Deposit successful. New balance: " + rs.getDouble("balance"));
			}
			request.getRequestDispatcher("deposit.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Error occurred");
			request.getRequestDispatcher("deposit.jsp").forward(request, response);
		}
	}
}