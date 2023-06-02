package serveur;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import mediatheque.Abonne;
import mediatheque.Document;

public abstract class Service implements Runnable{
	private final Socket client;
	private static List<Document> docs;
	private static List<Abonne> abos;
	
	public Service(Socket client) {
		this.client = client;
	}
	
	public static void setLesDocs(List<Document> listeDocs) {
		Service.docs = listeDocs;
	}
	
	public static void setLesAbos(List<Abonne> listeAbos) {
		Service.abos = listeAbos;
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
	
	
	@Override
	public abstract void run();
	
	public Socket getSocket() {
		return client;
	}
	
	protected void finalize() throws IOException {
		this.client.close();
	}
}
