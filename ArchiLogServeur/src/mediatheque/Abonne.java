package mediatheque;

import java.sql.Date;
import java.time.LocalDate;

public class Abonne {

	public int id;
	
	public String nom;
	
	public Date dateNaissance;
	
	public Abonne(int numero, String nom, Date date) {
		this.id = numero;
		this.nom = nom;
		this.dateNaissance = date;
	}

	public int getNumeroAbo() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public boolean isMajeur() {
		LocalDate now = LocalDate.now();
        LocalDate dateNaissanceLocal = dateNaissance.toLocalDate();
        LocalDate dateMajeure = dateNaissanceLocal.plusYears(16);
        
        return dateMajeure.isBefore(now) || dateMajeure.isEqual(now);
	}
}
