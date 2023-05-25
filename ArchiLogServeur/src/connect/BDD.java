package connect;

import java.sql.*;

public class BDD {
	private static Connection conn = null;
	
	
	public static void openConnection(String url, String user, String passwd) throws SQLException {
		try {
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
			conn.close();
		}
	}
	
	public static void closeConnection() throws SQLException {
		conn.close();
	}
	
	public static void statement(String str) throws SQLException {
		Statement stmt = conn.createStatement();
	}
}
