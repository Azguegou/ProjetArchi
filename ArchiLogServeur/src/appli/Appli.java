package appli;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.BDD;
import mediatheque.Abonne;
import mediatheque.Document;
import mediatheque.Dvd;
import mediatheque.Emprunt;
import mediatheque.Reservation;
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

		List<Document> docs = new ArrayList<Document>();
		List<Abonne> abos = new ArrayList<Abonne>();
		List<Emprunt> emprunts = new ArrayList<Emprunt>();
		List<Reservation> resas = new ArrayList<Reservation>();
		Connection conn = BDD.getConnection();
		String rStr_1 = "SELECT * FROM abonne";
		String rStr_2 = "SELECT * FROM dvd";
		String rStr_3 = "SELECT * FROM emprunt";
		String rStr_4 = "SELECT * FROM reservation";
		PreparedStatement requ1 = conn.prepareStatement(rStr_1);
		PreparedStatement requ2 = conn.prepareStatement(rStr_2);
		PreparedStatement requ3 = conn.prepareStatement(rStr_3);
		PreparedStatement requ4 = conn.prepareStatement(rStr_4);

		ResultSet rs_1 = requ1.executeQuery();
		ResultSet rs_2 = requ2.executeQuery();
		ResultSet rs_3 = requ3.executeQuery();
		ResultSet rs_4 = requ4.executeQuery();

		while (rs_1.next()) {
			System.out.println(rs_1.getInt(1) + " " + rs_1.getString(2) + " " + rs_1.getDate(3));
			abos.add(new Abonne(rs_1.getInt(1), rs_1.getString(2), rs_1.getDate(3)));
		}

		while (rs_2.next()) {
			System.out.println(rs_2.getInt(1) + " " + rs_2.getString(2) + " " + rs_2.getBoolean(3));
			docs.add(new Dvd(rs_2.getInt(1), rs_2.getString(2), rs_2.getBoolean(3)));
		}
		
		while (rs_3.next()) {
			System.out.println(rs_3.getInt(1) + " " + rs_3.getInt(2));
			emprunts.add(new Emprunt(rs_3.getInt(1), rs_3.getInt(2)));
		}
		
		while (rs_4.next()) {
			System.out.println(rs_4.getInt(1) + " " + rs_4.getInt(2) + " " + rs_4.getDate(3));
			resas.add(new Reservation(rs_4.getInt(1), rs_4.getInt(2), rs_4.getDate(3)));
		}
		
		requ1.close();
		requ2.close();
		requ3.close();
		requ4.close();
		conn.close();

		Service.setData(docs, abos, emprunts, resas);

		new Thread(new Serveur(ServiceReservation.class, PORT_RESERVATION)).start();
		new Thread(new Serveur(ServiceEmprunt.class, PORT_EMPRUNT)).start();
		new Thread(new Serveur(ServiceRetour.class, PORT_RETOUR)).start();
	}

}
