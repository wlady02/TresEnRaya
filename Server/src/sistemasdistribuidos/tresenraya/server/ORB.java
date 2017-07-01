package sistemasdistribuidos.tresenraya.server;

import java.io.IOException;
import java.net.ServerSocket;

public class ORB {

	public ORB(int port){
		try {
			ServerSocket serverSocket = new ServerSocket(2889);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
