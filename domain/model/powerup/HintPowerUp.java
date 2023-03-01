package domain.model.powerup;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class HintPowerUp extends Power_Up {

	
	public Boolean isInInventory = false;
	
	
	
	public HintPowerUp() {
		super();
		this.visible = true;
		this.type = 2;
        this.image = getImage();
	}

	@Override
	public void draw(Graphics2D g2) {
		if (visible) {
			g2.drawImage(image, super.XLocation, super.YLocation, size, size, null);
		}
	}

	public Image getImage() {
		try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/powerup/hint.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;

	}

	public void update() {

	}
	
	
	
	public void addedToInventory() {
		
		
		isInInventory =true;
		
	}
	
	
	public void removedFromInventory() {
		
		isInInventory = false;
		
		
	}
	
	
	

}
