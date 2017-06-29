package sistemasdistribuidos.tresenraya.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import sistemasdistribuidos.tresenraya.server.enums.Player;
import sistemasdistribuidos.tresenraya.server.game.Game;
import sistemasdistribuidos.tresenraya.server.game.Game.PlayerThread;

public class Main {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(2889);
		System.out.println("Server running...");
		
		try {
			ArrayList<Game> games = new ArrayList<>();
			
			while(true) {
				Game game = new Game();
				PlayerThread player1 = game.new PlayerThread(serverSocket.accept(), Player.PLAYER1);
				PlayerThread player2 = game.new PlayerThread(serverSocket.accept(), Player.PLAYER2);
				
				player1.setOpponent(player2);
				player2.setOpponent(player1);
				
				game.setPlayer1(player1);
				game.setPlayer2(player2);
				
				games.add(game);
				player1.start();
				player2.start();
			}
		} finally {
			serverSocket.close();
		}
	}
}
