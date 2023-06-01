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

public class ServiceEmprunt extends Service {
	
	public ServiceEmprunt(Socket socket) {
		super(socket);
	}

	@Override
	public void run() {
		String reponse = null;
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			PrintStream out = new PrintStream(super.getSocket().getOutputStream());
			
			out.println("Entrez votre num�ro d'abonn� : ");
			int numeroAbo = Integer.parseInt(in.readLine());
			
			out.println("Entrez le numéro du document à réserver : ");
			int numeroDoc = Integer.parseInt(in.readLine());
			
			Abonne abo = this.getAbonne(numeroAbo);
			Document doc = this.getDocument(numeroDoc);
			
			if(abo != null) {
				if(doc != null) {
					doc.emprunt(abo);
				}
				else {
					reponse = "Document non existant";
				}
			}
			else {
				reponse = "Cours non existant";
			}
		}
		catch(IOException e) {}
		
	}

}
