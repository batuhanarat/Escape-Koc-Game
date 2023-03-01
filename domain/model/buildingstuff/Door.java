package domain.model.buildingstuff;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Door extends Entity{
	
	
	public BufferedImage open,closed;
	public BufferedImage state;
	public Boolean isItOpen = false;
	public Door() {

		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		
	
		getDoorImage();
		
	}
	
	
	public void getDoorImage() {
		try {
			open = ImageIO.read(getClass().getResourceAsStream("/resources/objects/opendoor.png"));
			
			closed = ImageIO.read(getClass().getResourceAsStream("/resources/objects/closeddoor.png"));
			state= closed;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 public void draw(Graphics2D g2){
		 
		 
		 
		 if(state == open) {
	        g2.drawImage(open, 0, 600,40,60,null);
		 }
		 else {
			 g2.drawImage(closed, 0, 600,40,60,null);
		 }
	    }

	
	public void openDoorImage() {
		
		state = open;
			  	
			
		}
	
	public void closeTheDoorForNextMapPrep() {
		
		state = closed;
	}
		
	}
	



