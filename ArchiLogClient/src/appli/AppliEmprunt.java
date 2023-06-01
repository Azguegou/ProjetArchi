package appli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AppliEmprunt {
	
	final static int PORT_EMPRUNT = 1001;
	final static String HOST = "localhost";
	
	public static void main(String[] args) throws IOException {
		
		Socket socket = new Socket(HOST, PORT_EMPRUNT);
		
		BufferedReader sin = new BufferedReader (new InputStreamReader(socket.getInputStream()));
		PrintWriter sout = new PrintWriter(socket.getOutputStream(), true);
		
<<<<<<< HEAD
		System.out.println("Connecté au serveur : " + socket.getInetAddress() + " au port : " + socket.getPort());
		
		sout.println("");
=======
		System.out.println("Connecte au serveur : " + socket.getInetAddress() + " au port : " + socket.getPort());
>>>>>>> 9d245e3d41f85d4dd5f7eacdfaf94b5269360c59
	}
}
