package ui.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.AlienController;
import controller.PlayerKeyHandler;
import controller.PowerUpHandler;
import controller.Tile_Object_Handler;
import domain.model.Player;
import domain.model.buildingstuff.Building;
import domain.model.buildingstuff.Door;
import domain.model.powerup.ExtraLifePowerUp;
import domain.model.powerup.ExtraTimePowerUp;
import domain.model.powerup.HintPowerUp;
import domain.model.powerup.PlasticBottlePowerUp;
import domain.model.powerup.Power_Up;
import domain.model.powerup.ProtectionVestPowerUp;

public class GamePanelScreen extends JPanel {

	private static final int FPS = 60;
	final int originalTileSize = 16;
	final int scale = 3;
	JFrame frame;
	HintRectangle hintR;
	final int tileSize = originalTileSize * scale; // 48x48
	final int maxScreenCol = 24;
	final int maxScreenRow = 14;

	final int screenWidth = tileSize * maxScreenCol;
	final int screenHeight = tileSize * maxScreenRow;

	Thread gameThread;
	public Building building;
	public Player player;
	public AlienController alienController;
	public PowerUpHandler powerUpHandler;
	public PlayerKeyHandler keyHandler;
	public Boolean keyFound = false;
	public JPanel pausePanel;
	Font arial_40 = new Font("Arial",Font.PLAIN,40);

	public Tile_Object_Handler fullmap;
	public Door door = new Door();
	public GameBottomPanel bottomPanel;

	public GamePanelScreen(Building building, Player player, AlienController alienController,PowerUpHandler powerUpHandler,PlayerKeyHandler keyHandler,GameBottomPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
		this.powerUpHandler = powerUpHandler;
		this.player = player;
		this.building = building;
		this.keyHandler=keyHandler;
		this.alienController = AlienController.getAlienController(player,fullmap);
		this.fullmap = new Tile_Object_Handler(building);
		//this.setPreferredSize(new Dimension(screenHeight * tileSize, screenHeight * tileSize));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.setFocusable(true);

		
		
		
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//if (player.gameState == player.playState) {
			fullmap.set_map(g2);
			player.draw(g2);
			alienController.draw(g2);
			powerUpHandler.draw(g2);
			door.draw(g2);
			player.drawTime(g2);
			player.drawLives(g2);
			
			
				if(keyHandler.hint) {
					hintR = new HintRectangle(building.keyReturner());

					hintR.draw(g2);	
					
				}if(powerUpHandler.powerUps[3].thrown ) {
					
				
					powerUpHandler.draw(g2);
					//sure koy
					//powerUpHandler.powerUps[3].thrown = false;
				}if(alienController.shot) {
					player.drawLives(g2);
					

				}if(keyFound) {
					g2.setFont(arial_40);
					g2.setColor(Color.white);
					String text;
					int textLength;
					int x;
					int y;
					text = "You found the key !";
					textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
					x = this.screenWidth/2 - textLength/2;
					y= this.screenHeight/2 -(tileSize*3);
					g2.drawString(text,x,y);
					
				}
				if(!player.isAlive) {
					
					
					g2.setFont(arial_40);
					g2.setColor(Color.white);
					String text;
					int textLength;
					int x;
					int y;
					text = "You died!!!!";
					textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
					x = this.screenWidth/2 - textLength/2;
					y= this.screenHeight/2 -(tileSize*3);
					g2.drawString(text,x,y);
					
					
				}
				
				
				
				
		//}
		else if (player.gameState == player.pauseState) {
			drawSubWindow();
			System.out.println("Paused");
		}

				g2.dispose();

	}

	
	

	/*
	 * public void showPauseMenu() { frame.add(pauseMenu); frame.revalidate();
	 * this.repaint(); pauseMenu.repaint();
	 * 
	 * 
	 * }
	 * 
	 * public void resumeGamePanel() { frame.remove(pauseMenu); frame.revalidate();
	 * alienController.moveAlien(alien); this.repaint(); //pauseMenu.repaint(); }
	 */
	public void drawSubWindow() {
		this.pausePanel = new JPanel();
		pausePanel.setPreferredSize(new Dimension(tileSize*6,tileSize*6));
		pausePanel.setLocation(tileSize*6, tileSize);
		JButton button = new JButton("AA");
		
		this.add(pausePanel);
		this.revalidate();
		
		//pausePanel.validate();
	}
	public void update() {
		player.update();
	}
	
	
	/*
	 * public void init() { frame = new JFrame(); frame.setBounds(10,10,908,496);
	 * frame.setTitle("Escape From Koç Game"); frame.setResizable(false);
	 * frame.setVisible(true); frame.setFocusable(true);
	 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 * 
	 * 
	 * frame.add(this,BorderLayout.CENTER);
	 * 
	 * frame.setVisible(true); requestFocus(); }
	 * 
	 */

	public void addKeyListenertoPlayer(KeyListener playerMovementListener) {

		this.addKeyListener(playerMovementListener);

	}

	public void addMouseListener(MouseAdapter searchListener) {

		this.addMouseListener(searchListener);

	}

}
