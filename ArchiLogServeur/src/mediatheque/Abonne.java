package mediatheque;

import java.sql.Date;

public class Abonne {

	public int id;
	
	public String nom;
	
	public Date date;
	
	public Abonne(int numero, String nom, Date date) {
		this.id = numero;
		this.nom = nom;
		this.date = date;
	}

	public int getNumeroAbo() {
		// TODO Auto-generated method stub
		return this.id;
	}

}
