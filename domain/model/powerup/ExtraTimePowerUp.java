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

public class ExtraTimePowerUp extends Power_Up {
    public ExtraTimePowerUp(){
        super();
        this.type = 1;
        this.visible = true;
        this.image = getImage();
    }
    @Override
    public void draw(Graphics2D g2){
    	if(visible) {
        g2.drawImage(image, super.XLocation, super.YLocation,size,size,null);
    	}
    }
    public Image getImage(){
        try{
             image = ImageIO.read(getClass().getResourceAsStream("/resources/powerup/extra_time_power_up.png"));
            } catch(Exception e){
                e.printStackTrace();
            }
		return image;

    }
    public void update(){
    	
        
    }

}
