package sistemasdistribuidos.tresenraya.client.views;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Board extends javax.swing.JFrame {

    private final int NUMBER_OF_CELLLS = 9;
    
    private Cell[] cells;    

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Board(String name){
		super(name);
		cells = new Cell[NUMBER_OF_CELLLS];
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
		

		for(int i = 0; i < NUMBER_OF_CELLLS; i++){
			cells[i] = new Cell(i);
			panel.add(cells[i]);
		}
		
		container.add(panel);
	}
}
