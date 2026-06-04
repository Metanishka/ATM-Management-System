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
 * Servlet implementation class BalanceServlet
 */
//@WebServlet("/BalanceServlet")
public class BalanceServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer accountNumber = (Integer) session.getAttribute("accountNumber");
		if (accountNumber == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		try (Connection conn = DBConnection.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT balance FROM users WHERE account_number=?");
			ps.setInt(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// Log transaction
				ps = conn.prepareStatement(
						"INSERT INTO transactions (account_number, type, amount) VALUES (?, 'check_balance', 0)");
				ps.setInt(1, accountNumber);
				ps.executeUpdate();
				request.setAttribute("balance", rs.getDouble("balance"));
			}
			request.getRequestDispatcher("balance.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("balance", "Error retrieving balance");
			request.getRequestDispatcher("balance.jsp").forward(request, response);
		}
	}
}