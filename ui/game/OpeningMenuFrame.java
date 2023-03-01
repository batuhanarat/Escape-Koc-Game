package ui.game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class OpeningMenuFrame  extends JFrame {
	public JButton playNewGame;
	public JButton loadNewGame;
	public JButton help;
	public JButton exit;
	 public OpeningMenuFrame() {
	        setTitle("Game Menu");
	        setSize(400, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new GridLayout(4, 1));
	         playNewGame = new JButton("Play New Game");
	         loadNewGame = new JButton("Load New Game");
	         help = new JButton("Help");
	         exit = new JButton("Exit");
	        
	        
	        add(playNewGame);
	        add(loadNewGame);
	        add(help);
	        add(exit);
	    }
	 
	 
		
	 public void addHelpListener(ActionListener helpMenuListener) {

		 help.addActionListener(helpMenuListener);
	        
	        
	    }
	 public void addExitListener(ActionListener exitListener) {

	        exit.addActionListener(exitListener);
	        
	        
	    }
	 public void addStartNewGameListener(ActionListener startNewGameListener) {

		 playNewGame.addActionListener(startNewGameListener);
	        
	        
	    }
	 public void addLoadNewGameListener(ActionListener loadtListener) {

		 loadNewGame.addActionListener(loadtListener);
	        
	        
	    }
	 

	   
}
