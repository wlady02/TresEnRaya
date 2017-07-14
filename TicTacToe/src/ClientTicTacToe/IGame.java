package ClientTicTacToe;

public interface IGame {
		public int getMove();
		public void setMove(int pos, int value);
		public int victoria();
		public void reiniciar();
	
	// Hay una cosa que no entiendo. El juego es multijugador o es una persona contra el servidor (máquina)?
	// Si es lo segundo, solo necesitas tener una función de realizar movimiento en la partida (Game) en el servidor. 
	// Este método remoto te tiene que devolver si es correcto el movimiento. Si lo es lo pintas en el tablero, sino 
	// lo notificas.
	
	// No entiendo para que tienes getMove(), victoria() y reiniciar(). Cuando acabe la partida, este objeto se elimina 
	// y se crea una nueva instancia.
}
