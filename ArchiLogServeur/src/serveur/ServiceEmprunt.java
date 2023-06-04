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
		Abonne abo = null;
		Document doc = null;
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(this.getSocket().getInputStream()));
			PrintStream out = new PrintStream(super.getSocket().getOutputStream());
			
			out.println("Entrez votre numero d'abonne : ");
			int numeroAbo = Integer.parseInt(in.readLine());
			
			out.println("Entrez le numero du document a reserver : ");
			int numeroDoc = Integer.parseInt(in.readLine());
			
			try {
				abo = this.getAbonne(numeroAbo);
				doc = this.getDocument(numeroDoc);
			}
			catch (Exception e) {
				throw new NullPointerException();
			}

			doc.emprunt(abo);
			
			if (doc.empruntePar().equals(abo)) {
				reponse = abo.nom + " a emprunté \"" + doc.toString() + "\".";
			}
			else {
				reponse = "Le DVD n'est pas disponible.";
			}
			
			out.println(reponse);
			in.close();
			out.close();
		}
		catch(IOException e) {}
		
	}

}
