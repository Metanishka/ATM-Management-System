package com.atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDB {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/atm_db?useSSL=false&serverTimezone=UTC", "root", "password");
			System.out.println("Connection successful!");
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}