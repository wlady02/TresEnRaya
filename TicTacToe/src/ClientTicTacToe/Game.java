package ClientTicTacToe;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


//import javax.swing.JFrame;



import ServerTicTacToe.IGame;

public class Game implements IGame{	
	
	static String host="localhost";
	static int port =9999;
	static int iid=1;
	int oid=0;
    public static final int NUMBER_OF_CELLLS = 9;
	
	
	private int board;
	
    private int movements;
	private Player player;
	String[] cells = new String[NUMBER_OF_CELLLS];
	
	public void initCells() {
		for(int i = 0; i < NUMBER_OF_CELLLS; i++){
			cells[i] = "";
		}
	}
	public Game(){
		board = 0;
		movements = 0;
		player = Player.PLAYER1;
		System.out.println("entro al constructor Game cliente");
		initCells();
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
	
	
	public Game(Player player){
		System.out.println("entro constructor Game cliente player");
		board = 0;
		movements = 0;
		this.player = player;
	}
	
	public Movement move(int cell) {
		if(!isValid(cell))
			return Movement.NOT_ALLOWED;
		
//		if(player == Player.PLAYER2)
//			movements++;
//			if(!isValid(cell))
//				return Movement.NOT_ALLOWED;
		
//		int movement;
//		movement = PLAYER1_VALUE << (2 * cell);
//		board |= movement;
		movements++;
		
		if(victoria()==1)
			return player == Player.PLAYER2 ? Movement.WINNING_PLAYER1 : Movement.WINNING_PLAYER2;
//		if(victoria()==0)
//			return player == Player.PLAYER1 ? Movement.WINNING_PLAYER2 : Movement.WINNING_PLAYER1;
		
		if(movements == NUMBER_OF_CELLLS)
			return Movement.FINAL;

//		player = player == Player.PLAYER1 ? Player.PLAYER2 : Player.PLAYER1; //se cambia el valor de player de player1 a player2
		System.out.println("movimientos: "+movements);
		return Movement.ALLOWED;
	}
	
	private boolean isValid(int cell) {			
		return cells[cell].compareTo("")==0;
	}
	
	
	public String[] getBoard(int resp){
		
		for(int i = 0; i < NUMBER_OF_CELLLS; i++){
			if(player == Player.PLAYER1) {
				if(resp == i)
				cells[i] = "X";
			}
			else if(player == Player.PLAYER2) {
				if(resp == i)
				cells[i] = "O";
			}
		}
		
		return cells;
	}


	public void setMove(int pos) {
		try{
			Socket s = new Socket(host, port);
			//InputStream is = s.getInputStream();
	        OutputStream os = s.getOutputStream();
	        DataOutputStream dos = new DataOutputStream(os);
	        //DataInputStream dis = new DataInputStream(is);
	        dos.writeInt(iid);
	        dos.writeInt(2); //metodo remoto
	        dos.writeInt(pos);//envio pos
	        s.close();
			}catch(Exception e){e.printStackTrace();}
	}
	
	public int getMove() {
		int pos=-1;
		try{
			Socket s = new Socket(host, port);
			InputStream is = s.getInputStream();
	        OutputStream os = s.getOutputStream();
	        DataOutputStream dos = new DataOutputStream(os);
	        DataInputStream dis = new DataInputStream(is);

	        dos.writeInt(iid);
	        dos.writeInt(3); //metodo remoto
	        pos = dis.readInt();//recibo pos
	        s.close();
			}catch(Exception e){e.printStackTrace();}
		return pos;
	}
	
	public Movement movePlayer2(int cell){
		player = player == Player.PLAYER1 ? Player.PLAYER2 : Player.PLAYER1;
		if(!isValid(cell))
			return Movement.NOT_ALLOWED;
		movements++;
		return Movement.ALLOWED;
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
	        dos.writeInt(4); //metodo remoto
	        valor=dis.readInt();//envio pos
	        s.close();
			}catch(Exception e){e.printStackTrace();}
		return valor;
	}
	public void reiniciar() {
		try{
			Socket s = new Socket(host, port);
			//InputStream is = s.getInputStream();
	        OutputStream os = s.getOutputStream();
	        DataOutputStream dos = new DataOutputStream(os);
	        //DataInputStream dis = new DataInputStream(is);
	        dos.writeInt(iid);
	        dos.writeInt(5); //metodo remoto
	        s.close();
			}catch(Exception e){e.printStackTrace();}
	}
}
