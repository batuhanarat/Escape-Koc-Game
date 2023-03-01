package domain.model.powerup;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.imageio.ImageIO;
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

public class ExtraLifePowerUp extends Power_Up {
	public ExtraLifePowerUp() {
		super();
		this.type = 0;
		this.visible = true;
        this.image = getImage();
	}
	public void draw(Graphics2D g2){
    	if(visible) {

        g2.drawImage(image, super.XLocation, super.YLocation,size,size,null);
    	}
    }
    public Image getImage(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/resources/powerup/life.png"));
            } catch(Exception e){
                e.printStackTrace();
            }
		return image;

    }
    public void update(){
        
    }
}
