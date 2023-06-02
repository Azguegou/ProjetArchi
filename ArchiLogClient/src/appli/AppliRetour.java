package appli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AppliRetour {
	private final static int PORT_RETOUR = 1002;
	private final static String HOST = "localhost";
	public static void main(String[] args) {
		BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
		
		Socket socket = null;
		
		try {
			socket = new Socket(HOST, PORT_RETOUR);
			BufferedReader sin = new BufferedReader (new InputStreamReader(socket.getInputStream()));
			PrintWriter sout = new PrintWriter(socket.getOutputStream(), true); 
			
			System.out.println("Connecte au serveur : " + socket.getInetAddress() + " au port : " + socket.getPort());
		
			String line;
			line = sin.readLine();
			System.out.println(line);
			
			//Numero de document
			line = clavier.readLine();
			sout.println(line);
			
			line = sin.readLine();
			System.out.println(line);
			
			System.out.println(sin.readLine());
			socket.close();
			
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
