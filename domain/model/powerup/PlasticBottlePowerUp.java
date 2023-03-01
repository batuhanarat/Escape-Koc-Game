package domain.model.powerup;
import java.awt.Rectangle;

import java.util.Set;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicGraphicsUtils;

import ui.game.*;
import domain.model.*;

import ui.game.GamePanelScreen;
import ui.game.BuildPanelScreen;

import java.awt.Graphics2D;
import java.awt.Image;
public class PlasticBottlePowerUp extends Power_Up {
	
	
	public Boolean isInInventory = false;
	public Boolean thrown = false;
	public String direction = "";
	
    public PlasticBottlePowerUp(){
        super();
        this.type = 3;
        this.image = getImage();

    }
    @Override
    public void draw(Graphics2D g2){
        g2.drawImage(image, super.XLocation, super.YLocation,size,size,null);
       
    }
    public Image getImage(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/resources/powerup/plasticbottle.png"));
            } catch(Exception e){
                e.printStackTrace();
            }
		return image;

    }
    public void update(){
        
    }
    
    
    
public void addedToInventory() {
		
		
		isInInventory =true;
		
	}
	
	
	public void removedFromInventory() {
		
		isInInventory = false;
		
		
	}
	
}
