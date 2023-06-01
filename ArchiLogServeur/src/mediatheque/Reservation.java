package mediatheque;

import java.sql.Date;

public class Reservation {
	
	public int numeroAbo;
	
	public int numeroDoc;
	
	public Date dateRes;
	
	public Reservation(int abo, int doc, Date date) {
		this.numeroAbo = abo;
		this.numeroDoc = doc;
		this.dateRes = date;
	}
}
