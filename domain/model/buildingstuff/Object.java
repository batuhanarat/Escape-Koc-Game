package domain.model.buildingstuff;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.Icon;

public class Object  {

	public int XLocation;
	public int YLocation;
	
	int size = 48;
	private static final int width = 48;
	private static final int  height = 48;
	public int row;
	public int column;
	public Boolean hasKey = false;
	
	
	
	private String name;
	public Image image;
	private Boolean collision;
	public int type;

	 
	
	 public Object() {
		 
		 
	 }
	


	public Object(String name, Image  im) {
		
		this.name = name;
		this.image=im;
		


	}
	
	


	public Object(int x, int y) {


		this.setXLocation(x);
		this.setYLocation(y);
	}
	
	
  	 
	 public void whichRowAndColumn(int x, int y) {
		 
		
		this.column = (x/size);	 
		this.row = (y/size);
		 
	
		 
		 
		 
	 }
	 
	
	
	public void draw(Graphics2D g2, int tileSize) {
		
		g2.drawImage(image, column*tileSize, (row*tileSize)-1,tileSize, tileSize, null);
	}	
	
	
	
	
	
	public Image getImage () {
		 return image; 
		
	}
	
	
	public String getName() {
		 return name; 
		
	}
	
	
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	 
	



	
	//Setters and getters for coordinates
	public int getXLocation() {
		return XLocation; 
	}

	public void setXLocation(int x) {
		
		this.XLocation = x;

	}

	public int getYLocation() {
		return YLocation; 
	}

	public void setYLocation(int y) {
		this.YLocation = y;

	}
	
	
	
	

		
		
		
	
	
	
	
	

}
