package seatInServer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {
	
	// canali di comunicazione
	ServerSocket ss;
	Socket s;
	ObjectInputStream in;
	ObjectOutputStream out;
	
	public ServerThread() {
		
	}
	
	@Override
	public void run() {
		
	}

}
