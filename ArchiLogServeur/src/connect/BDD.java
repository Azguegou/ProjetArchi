package connect;

import java.sql.*;

public class BDD {
	private static Connection conn = null;
	private static final String url;
	private static final String user;
	private static final String passwd;
	
	static {
		url = "jdbc:mysql://localhost:3306/mediatheque";
		user = "root";
		passwd = "";
	}
	
	
	public static Connection getConnection() throws SQLException {
		if(conn != null) {
			return conn;
		}
		else {
			conn = DriverManager.getConnection(url, user, passwd);
			return conn;
		}
	}
	
	public static ResultSet openStatement(String query) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	public static void close(ResultSet rs) throws SQLException {
		rs.close();
	}
	
}
