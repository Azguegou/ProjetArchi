package serveur;

import java.io.IOException;
import java.net.Socket;
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
	
	public static void setData(List<Document> listeDocs, List<Abonne> listeAbos, List<Emprunt> emprunts, List<Reservation> reservations) {
		Service.docs = listeDocs;
		Service.abos = listeAbos;
		Service.emprunts = emprunts;
		Service.reservations = reservations;

	}
	
	public Document getDocument(int numeroDoc) {
		for(Document document : docs) {
			if(document.numero() == numeroDoc) {
				return document;
			}
		}
		return null;
	}
	
	public List<Document> getDocuments() {
		return docs;
	}
	
	public Abonne getAbonne(int numeroAbo) {
		for(Abonne abo : abos) {
			if(abo.getNumeroAbo() == numeroAbo) {
				return abo;
			}
		}
		return null;
	}
	
	public List<Abonne> getAbonnes() {
		return abos;
	}
	
	public Emprunt getEmprunt(int numeroAbo, int numeroDoc) {
		for(Emprunt emprunt: emprunts) {
			if(emprunt.numeroAbo == numeroAbo && emprunt.numeroDoc == numeroDoc) {
				return emprunt;
			}
		}
		return null;
	}
	
	public List<Emprunt> getEmprunts() {
		return emprunts;
	}
	
	public Reservation getReservation(int numeroAbo, int numeroDoc) {
		for(Reservation resa: reservations) {
			if(resa.numeroAbo == numeroAbo && resa.numeroDoc == numeroDoc) {
				return resa;
			}
		}
		return null;
	}
	
	public List<Reservation> getReservations() {
		return reservations;
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
