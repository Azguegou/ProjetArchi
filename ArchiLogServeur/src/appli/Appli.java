package appli;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import connect.BDD;
import serveur.ServeurEmprunt;
import serveur.ServeurReservation;
import serveur.ServeurRetour;

public class Appli {
	static int PORT_RESERVATION = 1000; 
	static int PORT_EMPRUNT = 1001;
	static int PORT_RETOUR = 1002;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = BDD.getConnection();
		new Thread(new ServeurReservation(PORT_RESERVATION)).start();
		new Thread(new ServeurEmprunt(PORT_EMPRUNT)).start();
		new Thread(new ServeurRetour(PORT_RETOUR)).start();
		System.out.println(conn.getCatalog());
		
	}

}
