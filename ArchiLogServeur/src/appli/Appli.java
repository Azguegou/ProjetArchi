package appli;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.BDD;
import mediatheque.Abonne;
import mediatheque.Document;
import mediatheque.Dvd;
import serveur.Serveur;
import serveur.Service;
import serveur.ServiceEmprunt;
import serveur.ServiceReservation;
import serveur.ServiceRetour;

public class Appli {
	final static int PORT_RESERVATION = 1000; 
	final static int PORT_EMPRUNT = 1001;
	final static int PORT_RETOUR = 1002;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		ArrayList<Document> docs = new ArrayList<>();
		ArrayList<Abonne> abos = new ArrayList<>();
		Connection conn = BDD.getConnection();
		Statement stmt = conn.createStatement();
		String rStr = "SELECT * FROM abonne";
		String rStr_2 = "SELECT * FROM dvd";
		PreparedStatement requ2 = conn.prepareStatement(rStr);
		PreparedStatement requ3 = conn.prepareStatement(rStr_2);
		
		ResultSet rs = requ2.executeQuery();
		ResultSet rs_1 = requ3.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDate(3));
			abos.add(new Abonne(rs.getInt(1), rs.getString(2), rs.getDate(3)));
		}
		
		while(rs_1.next()) {
			System.out.println(rs_1.getInt(1) + " " + rs_1.getString(2) + " " + rs_1.getBoolean(3));
			docs.add(new Dvd(rs_1.getInt(1), rs_1.getString(2), rs_1.getBoolean(3)));	
		}
		
		Service.setLesDocs(docs);
		Service.setLesAbos(abos);
		
		requ2.close();
		requ3.close();
		stmt.close();
		conn.close();
		
		
		new Thread(new Serveur(ServiceReservation.class, PORT_RESERVATION)).start();
		new Thread(new Serveur(ServiceEmprunt.class, PORT_EMPRUNT)).start();
		new Thread(new Serveur(ServiceRetour.class, PORT_RETOUR)).start();
		//System.out.println(conn.getCatalog());
		
		
	}

}
