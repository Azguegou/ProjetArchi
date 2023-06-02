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
			//note : ne pas oublier le getInputStream
			BufferedReader in = new BufferedReader(new InputStreamReader(this.getSocket().getInputStream()));
			PrintStream out = new PrintStream(super.getSocket().getOutputStream());
			
			StringBuilder sb = new StringBuilder();
			for(Document doc : this.getDocuments()) {
				sb.append(doc.toString());
			}
			out.println(sb.toString());
			
			out.println("Entrez votre numero d'abonne : ");
			int numeroAbo = Integer.parseInt(in.readLine());
			Abonne abo = this.getAbonne(numeroAbo);
			
			out.println("Entrez le numero de document voulu : ");
			int numeroDoc = Integer.parseInt(in.readLine());
			
			Document doc = this.getDocument(numeroDoc);
			
			if(abo != null) {
				if(doc != null) {
					//Appeler la methode reservation --> ajout à la liste de reservation + requete SQL pour actualiser la BDD
					reponse = "reservation reussie";
				}
				else {
					reponse = "Document non disponible";
				}
			}
			else {
				reponse = "Abonne non existant";
			}
			out.println(reponse);
			
		}
		catch(IOException e) {}
	}
	
	

}
