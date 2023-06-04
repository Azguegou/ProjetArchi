package mediatheque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.BDD;
import serveur.Service;

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
		if (!disponible) {
			for(Emprunt e : Service.getEmprunts()) {
				if (e.numeroDoc == this.numero()) {
					try {
						Connection conn = BDD.getConnection();
						conn.prepareStatement("DELETE emprunt WHERE numeroDoc = " + this.numeroDoc).execute();
					}
					catch (Exception exc) {
						throw new NullPointerException();
					}
				}
			}
			Service.getEmprunts().removeIf(empr -> empr.numeroDoc == this.numero());
		}
		else {
			System.out.println("Ce DVD n'est pas emprunt�.");
		}
	}

	@Override
	public Abonne empruntePar() {
		Abonne abo = null;
		if (!disponible) {
			for(Emprunt e : Service.getEmprunts()) {
				if (e.numeroDoc == this.numero()) {
					abo = Service.getAbonne(e.numeroAbo);
				}
			}
		}
		else {
			System.out.println("Ce DVD n'est pas emprunt�.");
		}
		return abo;
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
			if (disponible) {
				try {
					Connection conn = BDD.getConnection();
					conn.prepareStatement("INSERT INTO emprunt(numeroAbo, numeroDoc) VALUES ("+ ab.getNumeroAbo() +", "+ this.numero() +")").execute();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Emprunt e = new Emprunt(ab.getNumeroAbo(), this.numero());
				Service.getEmprunts().add(e);
				this.disponible = false;
			}
			else {
				System.out.println("Ce DVD n'est pas disponible.");
			}
		}
		else {
			System.out.println("L'abonn� n'a pas l'�ge d'emprunter ce DVD");
		}
	}
	
	@Override
	public String toString() {
		// String str = "Numero de document : " + this.numeroDoc + ", titre : " + this.titre + ", cat�gorie adulte ? : ";
		// if(this.adultFilm) str += " Oui";
		// else str += " Non";
		// return str;
		return this.titre;
	}
	
	public boolean isAdult() {
		return this.adultFilm;
	}

}
