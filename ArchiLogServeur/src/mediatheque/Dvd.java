package mediatheque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.BDD;

public class Dvd implements Document {

	private int numeroDoc;
	
	private String titre;
	
	private boolean disponible;
	
	private boolean adultFilm;

	public Dvd(int numero) {
		this.numeroDoc = numero;
		this.titre = "";
		this.disponible = true;
		this.adultFilm = false;
	}
	
	public Dvd(int numero, String titre, boolean adulte) {
		this.numeroDoc = numero;
		this.titre = titre;
		this.disponible = true;
		this.adultFilm = adulte;
	}

	@Override
	public int numero() {
		return numeroDoc;
	}

	@Override
	public void retour() {

	}

	@Override
	public Abonne empruntePar() {
		return null;
	}

	@Override
	public Abonne reservePar() {
		return null;
	}

	@Override
	public void reservation(Abonne ab) {		
	}

	@Override
	public void emprunt(Abonne ab) {
		if (!adultFilm || (adultFilm && ab.isMajeur())) {
		try {
			Connection conn = BDD.getConnection();
			PreparedStatement request = conn.prepareStatement("INSERT INTO emprunt(numeroAbo, numeroDoc) VALUES ("+ ab.getNumeroAbo() +", "+ this.numero() +")");
			ResultSet rs = request.executeQuery();
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		Emprunt e = new Emprunt(ab.getNumeroAbo(), this.numero());
		}
		else {
			System.out.println("L'abonné n'a pas l'âge d'emprunter ce DVD");
		}
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public String toString() {
		String str = "Numero de document : " + this.numeroDoc + ", titre : " + this.titre + ", catégorie adulte ? : ";
		if(this.adultFilm) str += " Oui";
		else str += " Non";
		return str;
	}
	
	public boolean isAdult() {
		return this.adultFilm;
	}

}
