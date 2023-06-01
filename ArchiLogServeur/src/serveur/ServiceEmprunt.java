package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

import connect.BDD;

public class ServiceEmprunt extends Service {
	
	public ServiceEmprunt(Socket socket) {
		super(socket);
	}

	@Override
	public void run() {
		
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			PrintStream out = new PrintStream(super.getSocket().getOutputStream());
			
			out.println("Entrez votre num�ro d'abonn� : ");
			int numeroAbo = Integer.parseInt(in.readLine());
			
			out.println("Entrez le num�ro du document � r�server : ");
			int numerroDoc = Integer.parseInt(in.readLine());
		}
		catch(IOException e) {}
		
	}

}
