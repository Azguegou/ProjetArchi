package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketImpl;

public class Serveur implements Runnable {
	
	private ServerSocket listen_socket_res = null;
	private ServerSocket listen_socket_emp = null;
	private ServerSocket listen_socket_ret = null;
	
	public Serveur(int port_res, int port_emp, int port_ret) throws IOException {
		this.listen_socket_res = new ServerSocket(port_res);
		this.listen_socket_emp = new ServerSocket(port_emp);
		this.listen_socket_ret = new ServerSocket(port_ret);
	}

	@Override
	public void run(){
		try {
			System.err.println("Lancement du serveur au port " + this.listen_socket_res.getLocalPort());
			System.err.println("Lancement du serveur au port " + this.listen_socket_emp.getLocalPort());
			System.err.println("Lancement du serveur au port " + this.listen_socket_ret.getLocalPort());
			while(true) {
				new Thread(new ServiceReservation(listen_socket_res.accept())).start();
				new Thread(new ServiceEmprunt(listen_socket_emp.accept())).start();
				new Thread(new ServiceRetour(listen_socket_ret.accept())).start();
			}
		}
		catch(IOException e) {
			try {
				this.listen_socket_res.close();
				this.listen_socket_emp.close();
				this.listen_socket_ret.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		System.err.println("Arret du serveur au port " + this.listen_socket_res.getLocalPort());
		System.err.println("Arret du serveur au port " + this.listen_socket_emp.getLocalPort());
		System.err.println("Arret du serveur au port " + this.listen_socket_ret.getLocalPort());
	}

	protected void finalize() throws Throwable {
		try {
			this.listen_socket_res.close();
			this.listen_socket_emp.close();
			this.listen_socket_ret.close();
		} catch (IOException e1) {
		}
	}
}
