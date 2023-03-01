package ui.game;
import java.awt.event.*;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


import ui.game.*;

public class MainFrame extends JFrame  {

   
    JPanel baseContainer;

	BuildModeUpperPanel up;
    BuildPanelScreen mid;
    BuildModeBottomPanel down;
    GamePanelScreen gamePanel;
    GameBottomPanel gameBottomPanel;
    GameUpperPanel gameUpperPanel;
    //static int screen_width=1024;
    static int screen_width=1152;

    static int screen_height=791;

    //static int screen_height=768;
	JLayeredPane layeredPane; 

    public MainFrame(BuildModeUpperPanel up,BuildPanelScreen mid, BuildModeBottomPanel down, String mode) {
        if(mode.equals("Build")) {
	        this.up = up;
	        this.mid = mid;
	        this.down = down;
	   
	        this.setSize(screen_width,screen_height);
	        this.setTitle("Building Mode");
	        this.setResizable(true);
	       // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	        
	        
	
	      this.add(up, BorderLayout.NORTH);
	      this.add(mid, BorderLayout.CENTER);
	      this.add(down, BorderLayout.SOUTH);
	        
	        
	        this.setVisible(true);
	       }
        System.out.println("print build frame");

        

        System.out.println(this.getHeight());
        System.out.println(this.getWidth());

        
        
        
        System.out.println(up.getHeight());
        System.out.println(up.getWidth());

        
        System.out.println(mid.getHeight());
        System.out.println(mid.getWidth());
        
        System.out.println(down.getHeight());
        System.out.println(down.getWidth());

        
}
        
        
        
        
        

    
        


       

    
    public MainFrame(GamePanelScreen gamePanel, GameBottomPanel bottomPanel,GameUpperPanel upperPanel ,String mode) { 
    	this.gamePanel = gamePanel;
    	this.gameBottomPanel= bottomPanel;
    	this.gameUpperPanel = upperPanel;
    	this.setSize(screen_width,screen_height);
    	this.setTitle("Escape from Koc Game");
    	this.setResizable(true);


    	baseContainer = new JPanel();
    	baseContainer.setLayout(new BorderLayout());
    	
    	baseContainer.add(gamePanel);
    	gameBottomPanel.setVisible(true);
    	baseContainer.add(gameBottomPanel, BorderLayout.SOUTH);
    	
    	//gameUpperPanel.setVisible(true);
    	//baseContainer.add(gameUpperPanel, BorderLayout.NORTH);
     	
    	this.add(baseContainer);
    	
    	// layeredPane = new JLayeredPane();
    	//layeredPane.add(gamePanel, new Integer(1));
    	//layeredPane.add(upperPanel, new Integer(0));
    	//layeredPane.add(bottomPanel, new Integer(2));
    	
    	//this.add(layeredPane);
       	this.setVisible(true);
       	
       	
       	
       	
    	baseContainer.revalidate();
        baseContainer.repaint();
        
        System.out.println("print game frame");


        System.out.println(this.getHeight());
        System.out.println(this.getWidth());

        
        
        
        System.out.println(gamePanel.getHeight());
        System.out.println(gamePanel.getWidth());

        
        System.out.println(bottomPanel.getHeight());
        System.out.println(bottomPanel.getWidth());
        

    	
    	
    }
    
    public void changeMap(JPanel newMap) {
        baseContainer.add(newMap, BorderLayout.CENTER);
        baseContainer.revalidate();
        baseContainer.repaint();
    	
    	
    	//layeredPane.remove(gamePanel);
        //gamePanel = newMap;
        //layeredPane.add(gamePanel, new Integer(1));
        //layeredPane.revalidate();
        //layeredPane.repaint();
    	
    }
    
    
    	

    public void addNewPanel(BuildPanelScreen panel) {
    	this.invalidate();
    	  this.add(up, BorderLayout.NORTH);
          this.add(panel, BorderLayout.CENTER);
          this.add(down, BorderLayout.SOUTH);
          this.validate();
    	
    }
    public void loseLives() {
    	this.gameBottomPanel.totalLives -=1;
    	this.gameBottomPanel.livesLabel.setText("Total lives :"+gameBottomPanel.totalLives);
    }
    
    
    
    public void addLives() {
    	this.gameBottomPanel.totalLives +=1;
    	this.gameBottomPanel.livesLabel.setText("Total lives :"+gameBottomPanel.totalLives);
    }
    
   
    public void openDoor() {
    	
    	this.gamePanel.door.openDoorImage();
    }
    
    public void closeDoor() {
    	
    	
    	this.gamePanel.door.closeTheDoorForNextMapPrep();
    }




}