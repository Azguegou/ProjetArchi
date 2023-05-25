package serveur;

import java.net.Socket;
import java.sql.SQLException;

import connect.BDD;

public class ServiceReservation implements Runnable {

	public ServiceReservation(Socket socket) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try {
			BDD.openConnection(null, null, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
