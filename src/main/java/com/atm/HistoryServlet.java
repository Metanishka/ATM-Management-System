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
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class HistoryServlet
 */
//@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer accountNumber = (Integer) session.getAttribute("accountNumber");
		if (accountNumber == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		List<String> history = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT type, amount, date FROM transactions WHERE account_number=? ORDER BY date DESC");
			ps.setInt(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				history.add(rs.getString("type") + " - $" + rs.getDouble("amount") + " on " + rs.getTimestamp("date"));
			}
			request.setAttribute("history", history);
			request.getRequestDispatcher("history.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("history", new ArrayList<>());
			request.getRequestDispatcher("history.jsp").forward(request, response);
		}
	}
}