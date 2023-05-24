package appli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppliRetour {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque", "root", "");
		System.out.println(conn.getCatalog());

	}

}
