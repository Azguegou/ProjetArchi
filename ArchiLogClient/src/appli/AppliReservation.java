package appli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AppliReservation {
	
	private final static String HOST = "localhost";
	private final static int PORT_RESERVATION = 1000;
	public static void main(String[] args) {
		BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
		
		Socket socket = null;
		
		try {
			socket = new Socket(HOST, PORT_RESERVATION);
			BufferedReader sin = new BufferedReader (new InputStreamReader(socket.getInputStream()));
			PrintWriter sout = new PrintWriter(socket.getOutputStream(), true); 
			
			System.out.println("Connecte au serveur : " + socket.getInetAddress() + " au port : " + socket.getPort());
					
			int nbTitres = Integer.parseInt(sin.readLine());
			String line;
			for (int i = 0; i < nbTitres; ++i) {
				line = sin.readLine();
				System.out.println(line);
			}
			
			//Numero d'abonné
			line = clavier.readLine();
			sout.println(line);
			
			line = sin.readLine();
			System.out.println(line);
			
			//Numéro de document
			line = clavier.readLine();
			sout.println(line);
			
			System.out.println(sin.readLine());
			socket.close();
			sin.close();
			sout.close();
		}catch(IOException e) {
			System.out.println("Fin du service");
		}
		
		try {
			if(socket != null) {
				socket.close();
			}
		}
		catch(IOException e2) {}
		
	}
}
