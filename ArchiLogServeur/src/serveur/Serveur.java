package serveur;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;

public class Serveur implements Runnable {
	
	private ServerSocket listen_socket = null;
	private Class<? extends Runnable> classeService;
	
	public Serveur(Class<? extends Runnable> classe, int port) throws IOException {
		this.listen_socket = new ServerSocket(port);
		this.classeService = classe;
	}

	@Override
	public void run(){
		try {
			System.err.println("Lancement du serveur au port " + this.listen_socket.getLocalPort());
			while(true) {
				try {
					new Thread(classeService.getConstructor(Socket.class).newInstance(listen_socket.accept())).start();
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
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
