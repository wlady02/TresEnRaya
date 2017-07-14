package ServerTicTacToe;

import java.util.Random;

public class Game implements IGame{
	int[] tablero = new int[9];
		
	Game(){
		this.tablero[0]=-1;this.tablero[1]=-1;this.tablero[2]=-1;
		this.tablero[3]=-1;this.tablero[4]=-1;this.tablero[5]=-1;
		this.tablero[6]=-1;this.tablero[7]=-1;this.tablero[8]=-1;
		
	}
	
	public int getMove() {
		Random rand = new Random();
	    int pos = rand.nextInt(9);
	    int resul = -1;
	    int count = 0; boolean exit = false;
	    while(count < 9 && !exit) {
	    if(tablero[pos]==-1){
	      tablero[pos]=0;
	      resul=pos;
	      exit=true;
	    }
	    else pos=rand.nextInt(9);
	    count++;
	    if(count>8)
	    	exit=true;
	    }
	    return resul;	    
	}
	
	//jugada cliente
	public void setMove(int pos) {
		this.tablero[pos]=1;
	}
	
	public void imprimirtabla(){
		for(int i=0;i<9;i++){
			System.out.println(tablero[i]+"  ");
		}
	}
	public int victoria() {
		int valor=-1;
		
		if( (tablero[0]==1 && tablero[1]==1 && tablero[2]==1) //0
			|| (tablero[2]==1 && tablero[4]==1 && tablero[6]==1)
			|| (tablero[1]==1 && tablero[4]==1 && tablero[7]==1)
			|| (tablero[0]==1 && tablero[4]==1 && tablero[8]==1)
			|| (tablero[3]==1 && tablero[4]==1 && tablero[5]==1)
			|| (tablero[0]==1 && tablero[3]==1 && tablero[6]==1)
			|| (tablero[6]==1 && tablero[7]==1 && tablero[8]==1)
			|| (tablero[2]==1 && tablero[5]==1 && tablero[8]==1) )
		{
			valor=1;
		}
		else if( (tablero[0]==0 && tablero[1]==0 && tablero[2]==0) //0
			|| (tablero[2]==0 && tablero[4]==0 && tablero[6]==0)
			|| (tablero[1]==0 && tablero[4]==0 && tablero[7]==0)
			|| (tablero[0]==0 && tablero[4]==0 && tablero[8]==0)
			|| (tablero[3]==0 && tablero[4]==0 && tablero[5]==0)
			|| (tablero[0]==0 && tablero[3]==0 && tablero[6]==0)
			|| (tablero[6]==0 && tablero[7]==0 && tablero[8]==0)
			|| (tablero[2]==0 && tablero[5]==0 && tablero[8]==0) )
		{
		 valor=0;
		}
		return valor;
	}
	public void reiniciar() {
		for(int i=0;i<9;i++)
			tablero[i]=-1;
	}

}

