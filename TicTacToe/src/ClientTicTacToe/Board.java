package ClientTicTacToe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Board extends javax.swing.JFrame {   
	private static final long serialVersionUID = 1L;
	private static final int NUMBER_OF_CELLLS = 9;    
	private JLabel label;
    private Cell[] cells;
    private Game game;
    private int celda;
    private int board;
    private final int MASK = 3;	
    private final int PLAYER1_VALUE = 1;
    private final int PLAYER2_VALUE = 2;
    private int movements;
 	private Player player;	
    
	public Board(String name){
		super(name);
		board = 0;
		movements = 0;
		player = Player.PLAYER1;
		label = new JLabel();
		cells = new Cell[NUMBER_OF_CELLLS];
		board =0;
		//initBoard();
	}
	public void representarTirada(int celda){
		// No es necesario que hagas esto. Esto se utiliza para iniciar el tablero. Para representar la tirada lo unico que deberías hacer 
		// es pintar sobre la celda. Nada más.
		GridLayout layout = new GridLayout(3, 3);
		JPanel headerPanel = new JPanel();
		JPanel boardPanel = new JPanel();
		

		headerPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		headerPanel.add(label, CENTER_ALIGNMENT);
		layout.setHgap(10);
		layout.setVgap(10);
		boardPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
		boardPanel.setLayout(layout);
		boardPanel.setBackground(Color.WHITE);
		/*for(int i = 0; i < Game.NUMBER_OF_CELLLS; i++){
			cells[i] = new Cell(i);
			
		}*/
		boardPanel.add(cells[celda]);				
		setLayout(new BorderLayout());
		add(headerPanel, BorderLayout.NORTH);
		add(boardPanel, BorderLayout.CENTER);
		
		setSize(300, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public int onClick(ActionEvent event) {
		Cell cell = (Cell) event.getSource();
		celda = cell.getCellNumber();
		Movement movement = move(cell.getCellNumber());
		if(movement == Movement.NOT_ALLOWED){
			label.setText("Movimiento no válido.");
		}
		else{
			String[] cellValues = getBoard();
			for(int i = 0; i < NUMBER_OF_CELLLS; i++)
				cells[i].setText(cellValues[i]);
			
			if(movement != Movement.ALLOWED)
				finishGame();
			if(movement == Movement.WINNING_PLAYER1)
				label.setText("Ha ganado el jugador 1.");
			else if(movement == Movement.WINNING_PLAYER2)
				label.setText("Ha ganado el jugador 2.");
			else if(movement == Movement.FINAL)
				label.setText("Se ha terminado la partida. El juego ha quedado en tablas.");
		}
		return celda;
	}
	
	public void finishGame(){
		for(int i = 0; i < NUMBER_OF_CELLLS; i++)
			cells[i].setEnabled(false);
		game.reiniciar();
	}
	
	public int getMove(){
		
		for(int i = 0; i < NUMBER_OF_CELLLS; i++){
			cells[i] = new Cell(i);
			cells[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					onClick(event);					
				}
			});
			//boardPanel.add(cells[i]);
		}
		return celda;
	}
	
	public String[] getBoard(){
		String[] cells = new String[NUMBER_OF_CELLLS];
		int value;
		
		for(int i = 0; i < NUMBER_OF_CELLLS; i++){
			value = board & (MASK << (2 * i));
			value >>= (2 * i);
			if(value == PLAYER1_VALUE)
				cells[i] = "X";
			else if(value == PLAYER2_VALUE)
				cells[i] = "O";
			else
				cells[i] = "";
		}
		
		return cells;
	}
	
	public Movement move(int cell) {				
		if(!isValid(cell))
			return Movement.NOT_ALLOWED;
		
		int movement;
		movement = PLAYER1_VALUE << (2 * cell);
		board |= movement;
		movements++;		
		if(victoria()==1)
			return player == Player.PLAYER2 ? Movement.WINNING_PLAYER1 : Movement.WINNING_PLAYER2;
		if(victoria()==0)
			return player == Player.PLAYER1 ? Movement.WINNING_PLAYER2 : Movement.WINNING_PLAYER1;
		
		if(movements == NUMBER_OF_CELLLS)
			return Movement.FINAL;
		System.out.println("movimientos: "+movements);
		return Movement.ALLOWED;
	}
	
	private boolean isValid(int cell) {
		int value = board & (MASK << (2 * cell));
		return value == 0;
	}
	
	public void movePlayer2(int cell){
		player = player == Player.PLAYER1 ? Player.PLAYER2 : Player.PLAYER1;
		int movement = PLAYER2_VALUE << (2 * cell);
		board |= movement;
		movements++;
	}
		
}
