package serveur;

import java.net.Socket;

public abstract class Service implements Runnable{
	private Socket client;
	
	public Service(Socket client) {
		this.client = client;
	}
	
	@Override
	public abstract void run();
	
	public Socket getSocket() {
		return client;
	}
}
