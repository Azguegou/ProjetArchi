package serveur;

import java.net.Socket;
import java.sql.SQLException;

import connect.BDD;

public class ServiceReservation implements Runnable {
	private Socket client;
	
	public ServiceReservation(Socket socket) {
		this.client = socket;
	}

	@Override
	public void run() {
		
		
	}

}
