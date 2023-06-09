package serveur;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.List;

import mediatheque.Abonne;
import mediatheque.Document;
import mediatheque.Emprunt;
import mediatheque.Reservation;

public abstract class Service implements Runnable{
	private final Socket client;
	private static List<Document> docs;
	private static List<Abonne> abos;
	private static List<Emprunt> emprunts;
	private static List<Reservation> reservations;
	
	public Service(Socket client) {
		this.client = client;
	}
	
	public static boolean estPlusDeDeuxHeures(long date) {
        long differenceEnMillis = System.currentTimeMillis() - date;
        long differenceEnHeures = differenceEnMillis / (60 * 60 * 1000);
        return differenceEnHeures > 2;
    }
	
	public static void setData(List<Document> listeDocs, List<Abonne> listeAbos, List<Emprunt> emprunts, List<Reservation> reservations) {
		Service.docs = listeDocs;
		Service.abos = listeAbos;
		Service.emprunts = emprunts;
		Service.reservations = reservations;

	}
	
	public static Document getDocument(int numeroDoc) {
		for(Document document : docs) {
			if(document.numero() == numeroDoc) {
				return document;
			}
		}
		return null;
	}
	
	public static List<Document> getDocuments() {
		return docs;
	}
	
	public static Abonne getAbonne(int numeroAbo) {
		for(Abonne abo : abos) {
			if(abo.getNumeroAbo() == numeroAbo) {
				return abo;
			}
		}
		return null;
	}
	
	public static List<Abonne> getAbonnes() {
		return abos;
	}
	
	public static Emprunt getEmprunt(int numeroAbo, int numeroDoc) {
		for(Emprunt emprunt: emprunts) {
			if(emprunt.numeroAbo == numeroAbo && emprunt.numeroDoc == numeroDoc) {
				return emprunt;
			}
		}
		return null;
	}
	
	public static List<Emprunt> getEmprunts() {
		return emprunts;
	}
	
	public static Reservation getReservation(int numeroAbo, int numeroDoc) {
		for(Reservation resa: reservations) {
			if(resa.numeroAbo == numeroAbo && resa.numeroDoc == numeroDoc) {
				return resa;
			}
		}
		return null;
	}
	
	public static List<Reservation> getReservations() {
		return reservations;
	}
	
	public static boolean isDisponible(Document doc) {
		boolean boo = true;
		List<Emprunt> emprunts = Service.getEmprunts();
		List<Reservation> resas = Service.getReservations();
		for(Emprunt e: emprunts) {
			if(e.numeroDoc == doc.numero()) {
				boo = false;
			}
		}
		for(Reservation r: resas) {
			if (r.numeroDoc == doc.numero() && (!estPlusDeDeuxHeures(r.dateRes))) {
				boo = false;
			}
		}
		return boo;
	}
	
	@Override
	public abstract void run();
	
	public Socket getSocket() {
		return client;
	}
	
	protected void finalize() throws IOException {
		this.client.close();
	}
}
