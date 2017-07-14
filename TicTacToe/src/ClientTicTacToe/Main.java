package ClientTicTacToe;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		Game play = new Game();
		Board board = new Board("Tablero");
		int casilla=-1;
		board.setSize(300, 350);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);
		
		while (true) {
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			casilla = board.getMove();
			if(casilla!=-1) {
			System.out.println(casilla + " esta es CASILLA");
			play.setMove(casilla);
			casilla=-1;
			int resp = play.getMove();
			board.getBoard(resp);
			}
		}
	}
}