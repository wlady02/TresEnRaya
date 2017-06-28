package sistemasdistribuidos.tresenraya.client.controllers;

import sistemasdistribuidos.tresenraya.client.enums.Movement;
import sistemasdistribuidos.tresenraya.client.enums.Player;

public class Game {
	
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
	private Player player;
	
	public Game(){
		board = 0;
		player = Player.PLAYER1;
	}
	
	public Game(Player player){
		board = 0;
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
		
		if(isWinner())
			return player == Player.PLAYER1 ? Movement.WINNING_PLAYER1 : Movement.WINNING_PLAYER2;

		player = player == Player.PLAYER1 ? Player.PLAYER2 : Player.PLAYER1;
		return Movement.ALLOWED;
	}
	
	private boolean isValid(int cell) {
		int value = MASK << (2 * cell);
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
	
	public int getBoard(){
		return board;
	}
}
