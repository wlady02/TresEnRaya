package sistemasdistrubuidos.tresenraya.client;

import javax.swing.JFrame;

import sistemasdistrubuidos.tresenraya.client.views.Board;

public class Main {

	public static void main(String[] args) {
		Board board = new Board("Tablero");
		board.setSize(300, 350);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);
	}

}
