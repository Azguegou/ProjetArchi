package serveur;

import java.io.IOException;
import java.net.ServerSocket;

public class ServeurRetour implements Runnable {

	private ServerSocket listen_socket;

	public ServeurRetour(int port) throws IOException {
		this.listen_socket = new ServerSocket(port);
	}
	
	@Override
	public void run() {
		try {
			System.err.println("Lancement du serveur au port " + this.listen_socket.getLocalPort());
			
			while(true) {
				new Thread(new ServiceRetour(this.listen_socket.accept())).start();
			}
		}
		catch(IOException e) {
			try {
				this.listen_socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	protected void finalize() throws Throwable {
		try {
			this.listen_socket.close();
		} catch (IOException e1) {
		}
	}

}
