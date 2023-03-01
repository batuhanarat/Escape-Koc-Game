package ui.game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameUpperPanel extends JPanel{

    private JButton pauseGameButton;
    
    
    public GameUpperPanel() {
    	
    	
    	
        pauseGameButton = new JButton("Pause");
        add(pauseGameButton);

    	
    	
    }

    
    
    
	 public void addPauseButtonListener(ActionListener objectListener) {

	       pauseGameButton.addActionListener(objectListener);

	        
	        
	    }
    
	
	
}
