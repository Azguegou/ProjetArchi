package mediatheque;

public class Reservation {
	
	public int numeroAbo;
	
	public int numeroDoc;
	
	public long dateRes; // en millisecondes
	
	public Reservation(int abo, int doc, long date) {
		this.numeroAbo = abo;
		this.numeroDoc = doc;
		this.dateRes = date;
	}
}
