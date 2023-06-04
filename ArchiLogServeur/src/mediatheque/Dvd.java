package mediatheque;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import connect.BDD;
import serveur.Service;

public class Dvd implements Document {

	private int numeroDoc;
	
	private String titre;
		
	private boolean adultFilm;

	public Dvd(int numero) {
		this.numeroDoc = numero;
		this.titre = "";
		this.adultFilm = false;
	}
	
	public Dvd(int numero, String titre, boolean adulte) {
		this.numeroDoc = numero;
		this.titre = titre;
		this.adultFilm = adulte;
	}

	@Override
	public int numero() {
		return numeroDoc;
	}

	@Override
	public void retour() {
		if (!Service.isDisponible(this)) {
			for(Emprunt e : Service.getEmprunts()) {
				if (e.numeroDoc == this.numero()) {
					try {
						Connection conn = BDD.getConnection();
						conn.prepareStatement("DELETE FROM emprunt WHERE numeroDoc = " + this.numeroDoc).execute();
						Service.getEmprunts().remove(e);
					}
					catch (Exception exc) {
						throw new NullPointerException();
					}
				}
			}
		}
		else {
			System.out.println("Ce DVD n'est pas emprunté.");
		}
	}

	@Override
	public Abonne empruntePar() {
		Abonne abo = null;
		if (!Service.isDisponible(this)) {
			for(Emprunt e : Service.getEmprunts()) {
				if (e.numeroDoc == this.numero()) {
					abo = Service.getAbonne(e.numeroAbo);
				}
			}
		}
		else {
			System.out.println("Ce DVD n'est pas emprunté.");
		}
		return abo;
	}

	@Override
	public Abonne reservePar() {
		Abonne abo = null;
		if (!Service.isDisponible(this)) {
			for(Reservation e : Service.getReservations()) {
				if (e.numeroDoc == this.numero()) {
					abo = Service.getAbonne(e.numeroAbo);
				}
			}
		}
		else {
			System.out.println("Ce DVD n'est pas réservé.");
		}
		return abo;	}

	@Override
	public void reservation(Abonne ab) {	
		long today = System.currentTimeMillis();
		if (!adultFilm || (adultFilm && ab.isMajeur())) {
			if (Service.isDisponible(this)) {
				try {
					Connection conn = BDD.getConnection();
					conn.prepareStatement("INSERT INTO reservation(numeroAbo, numeroDoc, dateRes) VALUES ("+ ab.getNumeroAbo() +", "+ this.numero() + ", " + today + ")").execute();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Reservation r = new Reservation(ab.getNumeroAbo(), this.numero(), today);
				Service.getReservations().add(r);
			}
			else {
				System.out.println("Ce DVD n'est pas disponible.");
			}
		}
		else {
			System.out.println("L'abonné n'a pas l'âge de réserver ce DVD");
		}
	}

	@Override
	public void emprunt(Abonne ab) {
		Connection conn = null;
		if (!adultFilm || (adultFilm && ab.isMajeur())) {
			if (Service.isDisponible(this) || this.reservePar() == ab) {
				try {
					conn = BDD.getConnection();
					conn.prepareStatement("INSERT INTO emprunt(numeroAbo, numeroDoc) VALUES ("+ ab.getNumeroAbo() +", "+ this.numero() +")").execute();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Emprunt e = new Emprunt(ab.getNumeroAbo(), this.numero());
				Service.getEmprunts().add(e);
				if (this.reservePar() == ab) {
					try {
						conn.prepareStatement("DELETE FROM reservation WHERE numeroDoc = " + this.numeroDoc).execute();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			else {
				System.out.println("Ce DVD n'est pas disponible.");
			}
		}
		else {
			System.out.println("L'abonné n'a pas l'âge d'emprunter ce DVD");
		}
	}
	
	@Override
	public String toString() {
		// String str = "Numero de document : " + this.numeroDoc + ", titre : " + this.titre + ", catégorie adulte ? : ";
		// if(this.adultFilm) str += " Oui";
		// else str += " Non";
		// return str;
		return this.titre;
	}
	
	public boolean isAdult() {
		return this.adultFilm;
	}

}
