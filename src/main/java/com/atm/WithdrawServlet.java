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
 * Servlet implementation class WithdrawServlet
 */
//@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
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
			// Check balance
			PreparedStatement ps = conn.prepareStatement("SELECT balance FROM users WHERE account_number=?");
			ps.setInt(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				double balance = rs.getDouble("balance");
				if (balance >= amount) {
					// Update balance
					ps = conn.prepareStatement("UPDATE users SET balance = balance - ? WHERE account_number=?");
					ps.setDouble(1, amount);
					ps.setInt(2, accountNumber);
					ps.executeUpdate();
					// Log transaction
					ps = conn.prepareStatement(
							"INSERT INTO transactions (account_number, type, amount) VALUES (?, 'withdraw', ?)");
					ps.setInt(1, accountNumber);
					ps.setDouble(2, amount);
					ps.executeUpdate();
					request.setAttribute("message", "Withdrawal successful. New balance: " + (balance - amount));
				} else {
					request.setAttribute("message", "Insufficient funds");
				}
			}
			request.getRequestDispatcher("withdraw.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Error occurred");
			request.getRequestDispatcher("withdraw.jsp").forward(request, response);
		}
	}
}