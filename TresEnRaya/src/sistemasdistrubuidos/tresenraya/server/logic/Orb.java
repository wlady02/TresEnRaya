package sistemasdistrubuidos.tresenraya.server.logic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Orb {
	private int port;

	public Orb(int port) {
		this.port = port;
	}

	public void start() throws IOException {
		ServerSocket listener = new ServerSocket(port);
		
		try{
			while(true){
				Socket playerSocket1 = listener.accept();
				Socket playerSocket2 = listener.accept();
				
				
			}
		} finally{
			listener.close();
		}
	}
}
