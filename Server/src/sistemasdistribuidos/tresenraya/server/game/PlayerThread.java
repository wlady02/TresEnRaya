package sistemasdistribuidos.tresenraya.server.game;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import sistemasdistribuidos.tresenraya.server.enums.Player;

public class PlayerThread extends Thread {
	
	private Socket socket;
	private Player player;
	private PlayerThread opponent;
	private BufferedReader input;
	private DataOutputStream output;
	
	public PlayerThread(Socket socket, Player player) {
		this.socket = socket;
		this.player = player;
		
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new DataOutputStream(socket.getOutputStream());
		} catch (IOException exception) {
			System.out.println("Error on opening connection pipelines: " + exception);
		}
	}
	
	public void setOpponent(PlayerThread opponent) {
		this.opponent = opponent;
	}
	
	public void run() {
		String message;
		int movement;
		
		try {
			if(player == Player.PLAYER1)
				output.writeUTF("Es tu turno");
			else
				output.writeUTF("Es el turno del jugador 1");
			
			while(true) {
				message = input.readLine();
				movement = Integer.parseInt(message);
			}
		} catch(IOException exception) {
			System.out.println("Error on playing: " + exception);
		} finally {
			try {
				socket.close();
			} catch(IOException closeException) {
				System.out.println("Error on closing socket: " + closeException);
			}
		}
	}
}
