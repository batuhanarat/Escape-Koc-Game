package domain.model.powerup;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

public class Power_Up {
	public int XLocation;
	public int YLocation;
	public Random rand = new Random();
	public int type;
	public boolean visible;
	public boolean isInInventory = false;
	public Image image= null;
	public int row;
	public int column;
	public int mapIndex;
	
	public Boolean isDeleted = false;
	public int powerUpRemovalTimerStart=0;
	public Boolean thrown = false;
	public int direction;
	
	public int size = 48;

	public Power_Up() {
		this.XLocation = randomX();
		this.YLocation = randomY();

	}

	
	
	public int getPowerUpType() {
		
		return type;
		
		
		
	}
	
	private int randomX() {

		int upperBound = 500;
		int randomX = rand.nextInt(upperBound);

		return randomX;

	}

	private int randomY() {
		int upperBound = 500;
		int randomY = rand.nextInt(upperBound);

		return randomY;

	}

	public void draw(Graphics2D g2) {

	}
}
