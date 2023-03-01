package ui.game;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.GameController;

public class GameBottomPanel extends JPanel {

	

	private static final int width = 40;
	private static final int height = 40;

	public ArrayList<ImageIcon> inventoryIcons = new ArrayList<ImageIcon>();
	public  JLabel livesLabel;
    private JPanel inventoryPanel;
    public int totalAdded= 0;
	protected static JLabel timer;
	Timer timer2;
	public int totalLives = 3;
	Image inventoryImage;
    private JButton pauseGameButton;
    
    ImageIcon hintImageIcon;
    ImageIcon plasticBottle;
    
    ImageIcon protectionImage;
    
    GameController game;

	public GameBottomPanel() {
		
		
		
		
		 setLayout(new GridBagLayout());
	     GridBagConstraints c = new GridBagConstraints();
		
		this.livesLabel = new JLabel("Total Lives: " + totalLives);
		this.setPreferredSize(new Dimension(100,90));
		c.gridx=0;
		c.gridy = 0;
	    c.insets = new Insets(5, 5, 5, 5);
	    //add(livesLabel, c);
	    
	    
	    pauseGameButton = new JButton("Save Game");
        c.gridx = 1;
        c.gridy = 0;
        add(pauseGameButton, c);
	    
	    
	   ImageIcon inv = getInventoryImage();
	   
	   
	   
	   
        inventoryPanel = new JPanel(new GridLayout(0,5));
        inventoryPanel.setVisible(false);
        c.gridx = 2;
        c.gridy = 0;
        add(inventoryPanel, c);
        
 	  // inventoryIcons.add(inv);
 	   updateInventory();
		
	}
	
	public void addGameController(GameController game) {
		this.game = game;
	}
	
	
	   public void updateInventory() {
		   
	        inventoryPanel.removeAll();
	        
	        if(!inventoryIcons.isEmpty()) {
	        	
	        	
	        	 for (ImageIcon icon : inventoryIcons) {
	 	            JLabel inventoryLabel = new JLabel();
	 	            inventoryLabel.setIcon(icon);
	 	            inventoryPanel.add(inventoryLabel);
	 	        }
	        	 inventoryPanel.revalidate();
	        	    inventoryPanel.repaint();
	 	        inventoryPanel.setVisible(true);
	        }

	       
	    }
	
	   
	   
	   public void addLives() {
	    	this.totalLives +=1;
	    	this.livesLabel.setText("Total lives :"+this.totalLives);
	    }
	   public void removeLives() {
	    	this.totalLives -=1;
	    	this.livesLabel.setText("Total lives :"+this.totalLives);
	    }
	
	   
	   public ImageIcon getInventoryImage() {
		   
		        try{
		             inventoryImage = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/inventory.png"));
		            } catch(Exception e){
		                e.printStackTrace();
		            }

		   
		   
		        inventoryImage= inventoryImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);

			    ImageIcon img = new ImageIcon(inventoryImage);

		   
			    return img;
		   
	   }
	

		public void addPlasticBottleToInventory(Image image) {
			image= image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon img = new ImageIcon(image);
			plasticBottle =img;
			inventoryIcons.add(img);
			updateInventory(); 
	
		}
		
		
		public ImageIcon getHintIconImage() {
			
			
			return hintImageIcon;
			
			
		}
		public ImageIcon getBottleImage() {
			return plasticBottle;
		}
		public ImageIcon getProtectionImage() {
			return protectionImage;
		}
		
	    public void addProtectionVestToInventory(Image image) {
			image=image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    	ImageIcon img = new ImageIcon(image);
	    	inventoryIcons.add(img);
	    	protectionImage = img;
			updateInventory(); 


			
		}
	    
	    public void addHintToInventory(Image image) {
			image=image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    	ImageIcon img = new ImageIcon(image);
	    	inventoryIcons.add(img);
	    	hintImageIcon = img;
			updateInventory(); 


      }
	    
	    
	    
	    
	    public void removeFromInventory(ImageIcon image) {
	        for (Component c : inventoryPanel.getComponents()) {
	            if (c instanceof JLabel) {
	                JLabel label = (JLabel) c;
	                if (label.getIcon() == image) {
	                    inventoryPanel.remove(label);
	                    inventoryIcons.remove(image);
	                 
	                    
	                    break;
	                }
	            }
	        }
	        inventoryPanel.revalidate();
	        inventoryPanel.repaint();
	    }
	
		public void printInventory() {
			
			  for (int i = 0; i <inventoryIcons .size(); i++)
		           
		            // Printing and display the elements in ArrayList
		            System.out.print(inventoryIcons.get(i) + " ");       
		    }
			
			
		}
	/*

	public void removeFromInventory(String name) {
		switch (name) {
		case "Hint":
			this.remove(hint1);

		}
	}
	
	*/



