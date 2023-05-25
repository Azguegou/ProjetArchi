package appli;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import serveur.Serveur;

public class Appli {
	static int PORT_RESERVATION = 1000; 
	static int PORT_EMPRUNT = 1001;
	static int PORT_RETOUR = 1002;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		new Thread(new Serveur(PORT_RESERVATION)).start();
		new Thread(new Serveur(PORT_EMPRUNT)).start();
		new Thread(new Serveur(PORT_RETOUR)).start();
		
	}

}
