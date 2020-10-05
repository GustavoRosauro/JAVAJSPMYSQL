package br.com.tutorial.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
	public static Connection getConn() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/java","root","");
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}
	}
}
