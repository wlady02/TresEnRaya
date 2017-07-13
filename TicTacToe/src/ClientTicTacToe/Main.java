package ClientTicTacToe;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.text.Position;

public class Main {
	public static void main(String[] args) {
		
		//Game game = new Game();
		//game.setboard();
		
		Game game = new Game();	//1
		Board board = new Board("Tablero");//2				
		int casilla = board.getMove(); //3
		game.setMove(casilla,1);
		int respuesta = game.getMove();
		board.representarTirada(respuesta);
			
		
		
	}
}