package domain.model;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import controller.PlayerKeyHandler;
import domain.model.buildingstuff.CollisionChecker;
import domain.model.buildingstuff.Entity;
public class Player extends Entity implements Saveable, Loadable {
	private static final int FPS = 60;
	public  double full_time = 60;
	public double playerTime = 0;
	public Boolean isAlive = true;
	DecimalFormat dFormat = new DecimalFormat("#0.00");	
	public PlayerKeyHandler key_Handler;
	public int totalLives = 30;
	public CollisionChecker coll_checker;
	public int gameState = 1;
	public final int playState = 1 ;
	public final int pauseState = 2;
	public boolean protectionVest = false;
	public double time ;
	public boolean upD = false;
	
	
	private static Player player;
	
	public Player(PlayerKeyHandler playerKeyHandler, CollisionChecker coll_checker) {
		this.coll_checker = coll_checker;
		this.key_Handler = playerKeyHandler;
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
		
	}
	
	
	
	public void setDefaultValues() {
		x = 150;
		y = 150;
		speed = 4;
		direction = "down";
		player=this;
		
	}
	
	public void timeUpdate() {
		playerTime++;
	}
	
	
	
	public void getPlayerImage() {
		try {
			up = ImageIO.read(getClass().getResourceAsStream("/resources/objects/player_up.png"));
			down = ImageIO.read(getClass().getResourceAsStream("/resources/objects/player_down.png"));
			left = ImageIO.read(getClass().getResourceAsStream("/resources/objects/player_left.png"));
			right = ImageIO.read(getClass().getResourceAsStream("/resources/objects/player_right.png"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void moveRight() {
		x += speed;
		if(x > 1005){
			x = 1005;
		}
		if(x< 0){
			x = 0;
		}
 
	}
	
	public void moveLeft() {
	
		x -= speed;
		if(x< 0){
			x = 0;
		}
		if(x > 1024){
			x = 1024;
		}
	
	}
	
	public void moveUp() {
		y -= speed;
		if(y < 5){
			y = 5;
		}
		
	}
	
	public void moveDown() {
		y += speed;
		
		if(y >720){
			y = 720;
		}
		
	}
	
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		if (direction.equals("up")) {
			image = up;
		}
		
		else if (direction.equals("down")) {
			image = down;
		}
		
		else if (direction.equals("left")) {
			image = left;
		}
		
		else if (direction.equals("right")) {
			image = right;
		}
		
		g2.drawImage(image, x,y,48,48,null);
		if(key_Handler.vestChanger) {
			vestUpdate();
			key_Handler.vestChanger = false;
			//System.out.println(this.playerTime-time);
		
}
		
		}
	
	public void drawTime(Graphics2D g2) {
		g2.setFont(new Font("Arial", Font.PLAIN, 20));
		g2.setColor(Color.white);
		g2.drawString("Time: " + dFormat.format(full_time - playerTime), 912,65);
	}
	
	public void drawLives(Graphics2D g2) {
		g2.setFont(new Font("Arial", Font.PLAIN,20));
		g2.setColor(Color.white);
		g2.drawString("Total Lives:  " + totalLives, 0,65);
	}
		
	
public void update() {
		
		if (key_Handler.upMove == true || key_Handler.downMove == true || key_Handler.leftMove == true || key_Handler.rightMove == true ) {
			if (key_Handler.upMove == true) {
				direction = "up";	
			}
	
			else if (key_Handler.downMove == true) {
				direction = "down";
			}
			
			else if (key_Handler.leftMove == true) {
				direction = "left";
			}
			
			else if (key_Handler.rightMove == true) {
				direction = "right";
			}
			
			this.collision = false;
			coll_checker.checkTile(this);
			
			if (this.collision == false) {
				switch(direction) {
				case "up":
					if (y-speed >0) {
					y -= speed;}
					
					else {
						y = 0;
					}
					break;
				case "down":
					if (y+speed < 608) {
						y += speed;}
						
					else {
						y = 608;
					}
					break;
				case "left":
					if (x-speed > 0) {
						x -= speed;}
						
					else {
						x = 0;
					}
			
					break;
				case "right":
					if (x+speed < 1103) {
						x += speed;
					}
					else {
						x = 1103;
					}
					break;
				}
				
			}
		}
		
		
	}





	public void stateUpdater() {
		if (key_Handler.gameChanger) {
			this.gameState = playState;
			
			
		}
		
		else {
			this.gameState = pauseState;
		
			
		}
	}
	public void vestUpdate() {
		
		if(key_Handler.vestChanger) {
			time = this.playerTime;
			upD = true;
			this.protectionVest = true;
			System.out.println("ProtectionVest AACTIVATED");
			 
			
			
		}
	}
	public int getx() {
		return x;
	}

	public void setx(int x) {
		this.x = x;
	}

	public int gety() {
		return y;
	}

	public void sety(int y) {
		this.y = y;
	}

	
	public static Player getPlayer() {
		return player;
	}

	public double getPlayerTime() {
		return playerTime;
	}



	public void setPlayerTime(double playerTime) {
		this.playerTime = playerTime;
	}



	public int getTotalLives() {
		return totalLives;
	}



	public void setTotalLives(int totalLives) {
		this.totalLives = totalLives;
	}

	public void save(SaveFile save) {
		save.totalLives = getTotalLives();
		save.posX = getx();
		save.posY = gety();
		save.fulltime = getPlayerTime();
	}
	
	public void load(SaveFile save) {
		player.setTotalLives(save.totalLives);
		player.setx(save.posX);
		player.sety(save.posY);
		player.setPlayerTime(save.fulltime);
	}
	
}