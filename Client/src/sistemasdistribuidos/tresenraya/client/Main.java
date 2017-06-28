package sistemasdistribuidos.tresenraya.client;

import javax.swing.JFrame;

import sistemasdistribuidos.tresenraya.client.views.Board;

public class Main {

	public static void main(String[] args) {
		Board board = new Board("Tablero");
		board.setSize(300, 300);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);
	}

}
