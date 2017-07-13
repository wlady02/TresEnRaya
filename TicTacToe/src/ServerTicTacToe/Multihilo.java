package ServerTicTacToe;

import java.net.Socket;

public class Multihilo implements Runnable {
	Socket sc;
	public Multihilo(Socket sc){
		this.sc=sc;
	}
	
	public void run(){
	System.out.println("Empieza varias partidas");
	}
}
