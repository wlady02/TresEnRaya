package ClientTicTacToe;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JFrame;


public class Game implements ClientTicTacToe.IGame{	
	
	static String host="localhost";
	static int port =9999;
	static int iid=1;
	int oid=0;
	private final int PLAYER1_VALUE = 1;
	private Player player;
   
	public Game(){
		player = Player.PLAYER1;
		System.out.println("entro al constructor Game cliente");
		try{
		Socket s = new Socket(host, port);
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        DataInputStream dis = new DataInputStream(is);
        dos.writeInt(iid);
        dos.writeInt(1);
        
        if(dis.readBoolean()){
        	//setBoard();
        	System.out.println("recibida confirmacion de server, case 1");
        	s.close();
        }             
		}
		catch(Exception e)
		{			
		}
	}
	
	public void setMove(int pos, int value) {
		try{
			Socket s = new Socket(host, port);
	        OutputStream os = s.getOutputStream();
	        DataOutputStream dos = new DataOutputStream(os);
	        dos.writeInt(iid);
	        dos.writeInt(2); //metodo remoto
	        dos.writeInt(pos);//envio pos
	        s.close();
			}catch(Exception e){e.printStackTrace();}
	}
	
	public int getMove() {
		int pos=-1;//posicion inicial con la que empezara la tabla
		try{
			Socket s = new Socket(host, port);
			InputStream is = s.getInputStream();
	        OutputStream os = s.getOutputStream();
	        DataOutputStream dos = new DataOutputStream(os);
	        DataInputStream dis = new DataInputStream(is);
	        dos.writeInt(iid);
	        dos.writeInt(3); //metodo remoto
	        pos = dis.readInt();//leo posicion
	        s.close();
			}catch(Exception e){e.printStackTrace();}
		return pos;
	}
		
	public int victoria() {
		int valor=-1;
		try{
			Socket s = new Socket(host, port);
			InputStream is = s.getInputStream();
	        OutputStream os = s.getOutputStream();
	        DataOutputStream dos = new DataOutputStream(os);
	        DataInputStream dis = new DataInputStream(is);
	        dos.writeInt(iid);
	        dos.writeInt(4);
	        valor=dis.readInt();
	        s.close();
			}catch(Exception e){e.printStackTrace();}
		return valor;
	}
	public void reiniciar() {
		try{
			Socket s = new Socket(host, port);
			InputStream is = s.getInputStream();
	        OutputStream os = s.getOutputStream();
	        DataOutputStream dos = new DataOutputStream(os);
	        DataInputStream dis = new DataInputStream(is);
	        dos.writeInt(iid);
	        dos.writeInt(5);
	        s.close();
			}catch(Exception e){e.printStackTrace();}
	}
}
