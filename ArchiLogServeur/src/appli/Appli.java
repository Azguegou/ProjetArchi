package appli;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import connect.BDD;
import serveur.Serveur;

public class Appli {
	static int PORT_RESERVATION = 1000; 
	static int PORT_EMPRUNT = 1001;
	static int PORT_RETOUR = 1002;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		new Thread(new Serveur(PORT_RESERVATION, PORT_EMPRUNT, PORT_RETOUR)).start();
		
		
		BDD.openConnection("jdbc:mysql://localhost:3306/mediatheque", "root", "");
		
		BDD.closeConnection();
		
	}

}
