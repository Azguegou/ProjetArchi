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
import mediatheque.Emprunt;

public class ServiceEmprunt extends Service {
	
	public ServiceEmprunt(Socket socket) {
		super(socket);
	}

	@Override
	public void run() {
		String reponse = null;
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(this.getSocket().getInputStream()));
			PrintStream out = new PrintStream(super.getSocket().getOutputStream());
			
			out.println("Entrez votre numero d'abonne : ");
			int numeroAbo = Integer.parseInt(in.readLine());
			
			out.println("Entrez le numero du document a  reserver : ");
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
				reponse = "Abonne inexistant";
			}
			System.out.println(reponse);
			out.println(reponse);
		}
		catch(IOException e) {}
		
	}

}
