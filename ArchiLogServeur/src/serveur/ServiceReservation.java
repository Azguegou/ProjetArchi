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
		Abonne abo = null;
		Document doc = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(this.getSocket().getInputStream()));
			PrintStream out = new PrintStream(super.getSocket().getOutputStream());
			
			int nb = Service.getDocuments().size() + 3;
			out.println(nb);
			
			String str = "\n";
			for(Document docu : Service.getDocuments()) {
				str = str + docu.toString() + "\n";
			}
			str = str + "\nEntrez votre numero d'abonne : ";
			out.println(str);
			int numeroAbo = Integer.parseInt(in.readLine());

			out.println("Entrez le numero de document voulu : ");
			int numeroDoc = Integer.parseInt(in.readLine());
			
			try {
				abo = Service.getAbonne(numeroAbo);
				doc = Service.getDocument(numeroDoc);	
			}
			catch(Exception e) {
				throw new NullPointerException();
			}
			
			synchronized(doc) {
				doc.reservation(abo);
			}
			
			if(doc.reservePar() == abo) {
				reponse = abo.nom + " a réservé " + doc.toString();
			}
			else {
				reponse = "Réservation impossible.";
			}
			out.println(reponse);
			
		}
		catch(IOException e) {}
	}
	
	

}
