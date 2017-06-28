package sistemasdistribuidos.tresenraya.client.views;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sistemasdistribuidos.tresenraya.client.controllers.Game;
import sistemasdistribuidos.tresenraya.client.enums.Movement;

public class Board extends javax.swing.JFrame {   

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    private Cell[] cells; 
    private Game game;
	
	public Board(String name){
		super(name);
		cells = new Cell[Game.NUMBER_OF_CELLLS];
		game = new Game();
		initBoard();
	}

	public void initBoard(){		
		Container container = getContentPane();
		GridLayout layout = new GridLayout(3, 3);
		JPanel panel = new JPanel();

		layout.setHgap(10);
		layout.setVgap(10);
		panel.setBorder(new EmptyBorder(30, 30, 30, 30));
		panel.setLayout(layout);
		panel.setBackground(Color.WHITE);

		for(int i = 0; i < Game.NUMBER_OF_CELLLS; i++){
			cells[i] = new Cell(i);
			cells[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					onClick(event);					
				}
			});
			panel.add(cells[i]);
		}
		
		container.add(panel);
	}
	
	public void onClick(ActionEvent event) {
		Cell cell = (Cell) event.getSource();		
		Movement movement = game.move(cell.getCellNumber());
		if(movement == Movement.NOT_ALLOWED){
			
		}
		else{
			String[] cellValues = game.getBoard();
			for(int i = 0; i < Game.NUMBER_OF_CELLLS; i++)
				cells[i].setText(cellValues[i]);
		}
	}
}
