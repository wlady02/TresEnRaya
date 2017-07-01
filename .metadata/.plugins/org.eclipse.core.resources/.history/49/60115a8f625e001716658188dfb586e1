package sistemasdistribuidos.tresenraya.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerGame{
	//variables
	ServerSocket ss;
	Socket sc;
	DataInputStream canalentrada;
	DataOutputStream canalsalida;
	static int serverport = 9999;
	
//constructor
	public ServerGame(int serverport) {
	this.serverport = serverport;
	}
	
	public void arrancarServidor(){
		
		try {
			ss = new ServerSocket(serverport);
			System.out.println("Creado server del socket");
			while(true){
				sc = ss.accept();
				canalentrada = new DataInputStream(sc.getInputStream());
				canalsalida = new DataOutputStream(sc.getOutputStream());
				
				Hilos t = new Hilos(sc);
				Thread th = new Thread(t);
				th.start();
				sc.close();
			}
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
	}
	
	public static void main(String[] args){
		ServerGame servRep = new ServerGame(serverport);
		servRep.arrancarServidor();
			
	}
}
