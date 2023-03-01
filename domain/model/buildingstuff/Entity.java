package domain.model.buildingstuff;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Entity {
	public int x,y, speed;
	public BufferedImage up,down, left, right;
	public String direction;
	
	
	public Rectangle solidArea;
	public boolean collision = false;
	
	

}
