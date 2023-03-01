package ui.game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import javax.swing.JScrollPane;
public class HelpScreen extends JFrame{
	HelpScreen helpScreen;
	JTextArea helpText; 
	
	JButton back;
	public HelpScreen() {
		
		
		this.setName("Help Screen");
		this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
        this.helpText = new JTextArea("Welcome to Escape to Koç Game. Here are some rules that we want you know."
        		+ "Press ESC to pause the game and press again to resume"
        		+ "You can click save to save the game"
        		+ "There are 3 types of aliens that you need to be careful about while trying to find the key."
        		+ "Time-wasting alien:They are changing the location of the key, Blind alien: if you touch it you will lose life,"
        		+ "Shooter alien: Do not get near him, he will shoot you. Press F5 to save, F6 to load.");
        
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        helpText.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(helpText);
        back = new JButton("Back");
        
        
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(back, BorderLayout.SOUTH);
        this.setVisible(true);

        
        
     
		
		
	}
	
	
	public void getText(String string) {
		
		
		this.helpText.setText(string);
	}
	
	 public void addBackButtonListener(ActionListener backListener) {

	        back.addActionListener(backListener);
	       

	        
	        
	    }
	

}
