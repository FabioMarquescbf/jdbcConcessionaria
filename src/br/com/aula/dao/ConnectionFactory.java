package br.com.aula.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/concessionaria", "administrador", "234");
		} catch(SQLException e) {
			System.out.println("SQLException Erro: "+e.getMessage());
			throw new RuntimeException(e);
		} catch(ClassNotFoundException e) {
			System.out.println("SQLException Erro: " +e.getMessage());
			throw new RuntimeException(e);
		}
		
	}
	
	
	public static void closeConnection(Connection conn) {
		close(conn, null, null); 
	}
	public static void closeConnection(Connection conn, PreparedStatement stm) {
		close(conn, stm, null);
	}
	
	public static void closeConnection(Connection conn, PreparedStatement stm, ResultSet rs) {
		close(conn, stm, rs);
	}
	
	
	
	private static void close(Connection conn, PreparedStatement stm, ResultSet rs) {
		try {
			if (conn != null) 
				conn.close();
			if (stm != null)
				stm.close();
			if (rs != null)
				rs.close();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}  finally {
			ConnectionFactory.closeConnection(conn, stm);
		}
	}
	
	
}
