package ui.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PauseMenu extends JPanel{
	
	public PauseMenu() {
		super();
		this.setVisible(true);

	}
	
	public void paint(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.darkGray);
		g.fillRect(0,0,2000,2000); //have to generalize dimensions
	}
	
}
