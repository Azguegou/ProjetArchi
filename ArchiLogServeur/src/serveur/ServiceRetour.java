package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import mediatheque.Document;

public class ServiceRetour extends Service {


	public ServiceRetour(Socket socket) {
		super(socket);
	}

	@Override
	public void run() {
		String reponse = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			PrintStream out = new PrintStream(super.getSocket().getOutputStream());
			
			out.println("Sélectionnez un DVD à rendre : ");
			int numeroDoc = Integer.parseInt(in.readLine());
			
			Document doc = this.getDocument(numeroDoc);
			
			if(doc != null) {
				//renvoyer ce document dans la liste --> méthode à coder dans DVD
				doc.retour();
			}
			else {
				reponse = "Document inexistant";
			}
			System.out.println(reponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
