package ClientTicTacToe;

public interface IGame {
		public int getMove();
		public void setMove(int pos, int value);
		public int victoria();
		public void reiniciar();
}
