package ui.game;


import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BuildModeBottomPanel extends JPanel{
	
	
	private BufferedImage statueIm;
	private BufferedImage treeIm;
	private BufferedImage chestIm;
	private BufferedImage shelveIm;
	private BufferedImage stoneIm;
	private BufferedImage vehbiIm;

 
	
	private Image statue;
	private Image tree; 
	private Image chest;
	private Image shelve;
	private Image stone;
	private Image vehbi;

	

	private static final int width = 30;
	private static final int height = 30;
	


	
	public ObjectSelectionButtonForBuildMode statueButton;
	public ObjectSelectionButtonForBuildMode treeButton;
	public ObjectSelectionButtonForBuildMode chestButton;
	public ObjectSelectionButtonForBuildMode shelveButton;
	public ObjectSelectionButtonForBuildMode stoneButton;
	public ObjectSelectionButtonForBuildMode vehbiButton;

	public JButton currentButton;
	
	//In this bottom panel, there should be code that iterate over resources/objects, to have array of all images.
	//In this code I implemented every object by manuel, but afterwards it wont be the case.
	//maybe this array should be implemented at buildmodemodel.
	
	/*Pair<Integer, String> myPair = new Pair<>(7, "Seven");
	Integer key = myPair.getKey();
	String value = myPair.getValue();
	*/
	
	public BuildModeBottomPanel() {
		 
		  loadImages();
		 
	 
		 
		   statueButton = new ObjectSelectionButtonForBuildMode("statue",statue);
		   treeButton = new ObjectSelectionButtonForBuildMode("tree",tree);
		   chestButton = new ObjectSelectionButtonForBuildMode("chest",chest);
		   shelveButton = new ObjectSelectionButtonForBuildMode("shelve",shelve);
		   stoneButton = new ObjectSelectionButtonForBuildMode("stone",stone);
		   vehbiButton = new ObjectSelectionButtonForBuildMode("vehbi",vehbi);

		   
		  
		   
  		   add(statueButton);
		   add(treeButton);
		   add(chestButton);
		   add(shelveButton);
		   add(stoneButton);
		   add(vehbiButton);
		   
		
	
		
	}

	
	
	

		
	public void loadImages() {
		
			
		

			try {
				statueIm = ImageIO.read(getClass().getResourceAsStream("/resources/objects/statue.png"));
			    statue = statueIm.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				
				treeIm = ImageIO.read(getClass().getResourceAsStream("/resources/objects/Trees.png"));
				tree = treeIm.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				
				chestIm = ImageIO.read(getClass().getResourceAsStream("/resources/objects/chest.png"));
				chest = chestIm.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				
				shelveIm = ImageIO.read(getClass().getResourceAsStream("/resources/objects/shelve.png"));
				shelve = shelveIm.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				
				stoneIm = ImageIO.read(getClass().getResourceAsStream("/resources/objects/stone.png"));
				stone = stoneIm.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				
				vehbiIm = ImageIO.read(getClass().getResourceAsStream("/resources/objects/vehbi.png"));
				vehbi = vehbiIm.getScaledInstance(width, height, Image.SCALE_SMOOTH);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	
	
	
	
	
	 public void addObjectListener(ActionListener objectListener) {

	        statueButton.addActionListener(objectListener);
	        treeButton.addActionListener(objectListener);
	        chestButton.addActionListener(objectListener);
	        shelveButton.addActionListener(objectListener);
	        stoneButton.addActionListener(objectListener);
	        vehbiButton.addActionListener(objectListener);

	        
	        
	    }
	
	
	
	
		
	
	
	
	
	
	
	
	
	
}
