package sistemasdistrubuidos.tresenraya.server.logic;

import java.net.Socket;

public class Game implements Runnable {
	
	private Socket player1;
	private Socket player2;
	
	private int board;
	
	public Game(Socket player1, Socket player2) {
		this.player1 = player1;
		this.player2 = player2;
		
		board = 0;
	}

	@Override
	public void run() {
		
	}

}
