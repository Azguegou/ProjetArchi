package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketImpl;

public class ServeurReservation implements Runnable {
	
	private ServerSocket listen_socket = null;
	
	public ServeurReservation(int port_res) throws IOException {
		this.listen_socket = new ServerSocket(port_res);
	}

	@Override
	public void run(){
		try {
			System.err.println("Lancement du serveur au port " + this.listen_socket.getLocalPort());
			while(true) {
				new Thread(new ServiceReservation(listen_socket.accept())).start();
			}
		}
		catch(IOException e) {
			try {
				this.listen_socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		System.err.println("Arret du serveur au port " + this.listen_socket.getLocalPort());
	}

	protected void finalize() throws Throwable {
		try {
			this.listen_socket.close();
		} catch (IOException e1) {
		}
	}
}
