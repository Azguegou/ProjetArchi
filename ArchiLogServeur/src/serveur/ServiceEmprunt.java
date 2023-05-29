package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

import connect.BDD;

public class ServiceEmprunt implements Runnable {

	private Socket client;
	
	public ServiceEmprunt(Socket socket) {
		this.client = socket;
	}

	@Override
	public void run() {
		
		try {
			
			try {
				Connection conn = BDD.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			PrintStream out = new PrintStream(client.getOutputStream());
		}
		catch(IOException e) {}
		
	}

}
