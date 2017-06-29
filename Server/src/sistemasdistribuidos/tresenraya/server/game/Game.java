package sistemasdistribuidos.tresenraya.server.game;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import sistemasdistribuidos.tresenraya.server.enums.Movement;
import sistemasdistribuidos.tresenraya.server.enums.Player;

public class Game {
public static final int NUMBER_OF_CELLLS = 9;
	
	private final int COM1 = 0x3f;						// Combinacion 1 - primera fila.
    private final int COM2 = 0xfc0;						// Combinacion 2 - segunda fila.
    private final int COM3 = 0x3f000;					// Combinacion 3 - tercera fila.
    private final int COM4 = 0x30c3;					// Combinacion 4 - primera columna.
    private final int COM5 = 0xc30c;					// Combinacion 5 - segunda columna.
    private final int COM6 = 0x30c30;					// Combinacion 6 - tercera columna.
    private final int COM7 = 0x30303;					// Combinacion 7 - diagonal de izquierda a derecha.
    private final int COM8 = 0x3330;					// Combinacion 8 - diagonal de derecha a izquierda.
    
    private final int PLAYER1_VALUE = 1;
    private final int PLAYER2_VALUE = 2;
    private final int MASK = 3;
	
	private int board;
    private int movements;
	private Player player;
	
	private PlayerThread player1;
	private PlayerThread player2;
	
	public Game(){
		board = 0;
		movements = 0;
		player = Player.PLAYER1;
	}
	
	public Game(Player player){
		board = 0;
		movements = 0;
		this.player = player;
	}
	
	public Movement move(int cell) {
		if(!isValid(cell))
			return Movement.NOT_ALLOWED;
		
		int movement;
		if(player == Player.PLAYER1)
			movement = PLAYER1_VALUE << (2 * cell);
		else
			movement = PLAYER2_VALUE << (2 * cell);
		
		board |= movement;
		movements++;
		
		if(isWinner())
			return player == Player.PLAYER1 ? Movement.WINNING_PLAYER1 : Movement.WINNING_PLAYER2;
		
		if(movements == NUMBER_OF_CELLLS)
			return Movement.FINAL;

		player = player == Player.PLAYER1 ? Player.PLAYER2 : Player.PLAYER1;
		return Movement.ALLOWED;
	}
	
	private boolean isValid(int cell) {
		int value = board & (MASK << (2 * cell));
		return value == 0;
	}
	
	private boolean isWinner(){
		if(player == Player.PLAYER1)
			return ((board & COM1) == 0x15) || ((board & COM2) == 0x540) || ((board & COM3) == 0x15000) ||
                    ((board & COM4) == 0x1041) || ((board & COM5) == 0x4104) || ((board & COM6) == 0x10410) ||
                    ((board & COM7) == 0x10101) || ((board & COM8) == 0x1110);
		else
			return ((board & COM1) == 0x2a) || ((board & COM2) == 0xa80) || ((board & COM3) == 0x2a000) ||
                    ((board & COM4) == 0x2082) || ((board & COM5) == 0x8208) || ((board & COM6) == 0x20820) ||
                    ((board & COM7) == 0x20202) || ((board & COM8) == 0x2220);
	}
	
	public String[] getBoard(){
		String[] cells = new String[NUMBER_OF_CELLLS];
		int value;
		
		for(int i = 0; i < NUMBER_OF_CELLLS; i++){
			value = board & (MASK << (2 * i));
			value >>= (2 * i);
			if(value == PLAYER1_VALUE)
				cells[i] = "X";
			else if(value == PLAYER2_VALUE)
				cells[i] = "O";
			else
				cells[i] = "";
		}
		
		return cells;
	}
	
	public void setPlayer1(PlayerThread player) {
		this.player1 = player;
	}
	
	public void setPlayer2(PlayerThread player) {
		this.player2 = player;
	}
	
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
}
