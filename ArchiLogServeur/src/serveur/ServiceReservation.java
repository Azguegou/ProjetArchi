package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

import connect.BDD;
import mediatheque.Abonne;
import mediatheque.Document;

public class ServiceReservation extends Service {
	
	public ServiceReservation(Socket socket) {
		super(socket);
	}

	@Override
	public void run() {
		String reponse = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			PrintStream out = new PrintStream(super.getSocket().getOutputStream());
			
			for(Document doc : this.getDocuments()) {
				System.out.println(doc.toString());
			}
			
			out.println("Entrez votre numero d'abonne : ");
			int numeroAbo = Integer.parseInt(in.readLine());
			
			out.println("Entrez le numero de document voulu : ");
			int numeroDoc = Integer.parseInt(in.readLine());
			
			Abonne abo = this.getAbonne(numeroAbo);
			Document doc = this.getDocument(numeroDoc);
			
			if(abo != null) {
				if(doc != null) {
					//Appeler la methode reservation --> ajout à la liste de reservation + requete SQL pour actualiser la BDD
				}
				else {
					reponse = "Document non disponible";
				}
			}
			else {
				reponse = "Abonne non existant";
			}
		}
		catch(IOException e) {}
	}
	
	

}
