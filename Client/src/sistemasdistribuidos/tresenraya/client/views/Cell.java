package sistemasdistribuidos.tresenraya.client.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Cell extends JButton {
	
	private int number;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Cell(int number){
		this.number = number;
		setFont(new Font("Ubuntu", 1, 36));
		setBackground(Color.CYAN);
		setBorder(BorderFactory.createEmptyBorder());
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setText("X");
			}
		});
	}
	
	public int getCellNumber(){
		return number;
	}
}
