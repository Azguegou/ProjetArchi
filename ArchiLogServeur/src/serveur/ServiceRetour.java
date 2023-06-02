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
			
			out.println("Selectionnez un DVD a  rendre : ");
			int numeroDoc = Integer.parseInt(in.readLine());
			
			Document doc = this.getDocument(numeroDoc);
			
			if(doc != null) {
				//renvoyer ce document dans la liste --> methode a coder dans DVD : retour()
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
