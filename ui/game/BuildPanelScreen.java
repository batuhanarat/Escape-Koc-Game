package ui.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;

import domain.model.buildingstuff.Building;
import tiles.TileHandler;

public class BuildPanelScreen extends JPanel{
	
	Image image;
	int x;
	int y;
	int row;
	int column;
	TileHandler buildingModeTileHandler;
	int width;
	int height;
	public Building building;
	public Boolean objectFlag = false;
	
	public TileHandler tileHandler;
	
	

	
	public BuildPanelScreen(Building building) {
		
		this.building = building;
		//this.building.updateMapArray();
	
	    this.tileHandler = new TileHandler(this,this.building);
	    System.out.println("Height");
	    System.out.println(this.getHeight());
	    
	    System.out.println("Width");
	    System.out.println(this.getWidth());

		
	}  
	
	
	
	  
	
	 @Override
     public void paintComponent(Graphics g) {
		 
		 super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			
			tileHandler.draw(g2);

			if(objectFlag) {

 			for (int i = 0; i < building.getArrayList().size() ; i++) {
				if (building.getArrayList().get(i) != null) {
					building.getArrayList().get(i).draw(g2, 48);
				}
			} 		 	
			
	 }
			
			 
	 
			g2.dispose();

     }
	 
 
	 
	 
	 public void setDataOfImage(int x,int y,Image im,Building building) {
		
		 this.x=x;
		 this.y=y;
		 this.image=im;
		 this.building =building;
		 
		 
		 
	 }
	
	
	
	

	 public void addMouseListener(MouseAdapter objectPlacementListener) {

	        this.addMouseListener(objectPlacementListener);
	        
	        
	    }
	 
	 
	 
	 
	 

	
	
	
		
	
	
	
	

}
