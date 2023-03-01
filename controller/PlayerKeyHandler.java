package controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import domain.model.Player;
import domain.model.powerup.HintPowerUp;
import domain.model.powerup.PlasticBottlePowerUp;
import domain.model.powerup.ProtectionVestPowerUp;
import ui.game.GameBottomPanel;
import ui.game.HintRectangle;

public class PlayerKeyHandler implements KeyListener {
	PowerUpHandler powerUpHandler;
	
	//public Boolean hint = false;
	HintPowerUp hintPowerUp;
	PlasticBottlePowerUp plasticBottle;
	ProtectionVestPowerUp protection;
	int rectangle_X;
	int rectangle_Y;
	Boolean draw; 
	Boolean pressed = false;
	GameBottomPanel bottomPanel;
	public boolean gameChanger = true;
	public boolean isInInventory = false;
	private Player player;
	private Timer timer = new Timer();
	GameController game;
	private TimerTask taskForVest = new TimerTask() {
		@Override
		public void run()
		{

			removeProtectionFromInventory();
		}
	};
	
	public PlayerKeyHandler(PowerUpHandler powerUpHandler,GameBottomPanel bottomPanel) {
		
		
		this.powerUpHandler=powerUpHandler;
		this.bottomPanel = bottomPanel;
		
		
	}
	public void getPlasticBottle(PlasticBottlePowerUp plastic) {
		this.plasticBottle = plastic;
		
	}
	
	
	
	public void getHintPowerUp(HintPowerUp hintPowerUp) {
		

		this.hintPowerUp = hintPowerUp;

		System.out.println("Inventory ");

		bottomPanel.printInventory();

		
	}
	
	public void addGameController(GameController game) {
		this.game = game;
	}
	
	public void getProtectionPowerUp(ProtectionVestPowerUp protection) {
		

		this.protection = protection;
		protection.isInInventory=true;
		
		System.out.println("Inventory ");
		bottomPanel.printInventory();

		
	}

	public void executeVest()
	{
		timer.schedule(taskForVest, 20000);
		
	}
	
	public void executeHint() {
		
		
		if(hintPowerUp != null) {
			
			if (hintPowerUp.isInInventory) {
				
				
				 hint=true;
				 
			} 
			else {
				
				hint=false;
				
			}
			
		}
		
			
		
	}
	
	
	public void removeHintFromInventory() {
		
		this.hintPowerUp.isInInventory= false;
		
	
		bottomPanel.removeFromInventory(bottomPanel.getHintIconImage());
		
		
		System.out.println("Inventory after removing");
		bottomPanel.printInventory();
		
		
	}
	
	public void removeBottleFromInventory() {
		
		this.plasticBottle.isInInventory= false;
		
	
		bottomPanel.removeFromInventory(bottomPanel.getBottleImage());
		
		
		System.out.println("Inventory after removing");
		bottomPanel.printInventory();
		
		
	}
	public void removeProtectionFromInventory() {
		this.protection.isInInventory = false;
		bottomPanel.removeFromInventory(bottomPanel.getProtectionImage());


	}
	
	
	
	

	
	
	public boolean upMove, downMove, leftMove, rightMove;
	public boolean hint = false;
	public boolean deActivate = false;
	public boolean thrown = false;
	public boolean released = false;
	public boolean vestChanger = false;
 	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	

	@Override
	public void keyPressed(KeyEvent e) {
	
            
                
               
               
                    	
		// TODO Auto-generated method stub

		int code = e.getKeyCode();
		if(code == KeyEvent.VK_B) {
			pressed = true;
			System.out.println(plasticBottle.isInInventory);
		}
		if(pressed ) {
			code = e.getKeyCode();
			removeBottleFromInventory();
			if(code == KeyEvent.VK_X && plasticBottle.isInInventory) {		
				powerUpHandler.powerUps[3].thrown = true;
				powerUpHandler.powerUps[3].direction = 1;
				
				System.out.println("Thrown to SOUTH");
				powerUpHandler.powerUps[3].isInInventory = false;
				pressed = false;
				//plasticBottle.isInInventory = false;
			
			}if(code == KeyEvent.VK_W && plasticBottle.isInInventory) {
				powerUpHandler.powerUps[3].thrown = true;
				powerUpHandler.powerUps[3].direction = 2;
				pressed = false;
				//plasticBottle.isInInventory = false;
			}if(code == KeyEvent.VK_D && plasticBottle.isInInventory) {
				powerUpHandler.powerUps[3].thrown = true;
				powerUpHandler.powerUps[3].direction = 3;
				pressed = false;
				System.out.println("Thrown to EAST");
				//plasticBottle.isInInventory = false;
			}if(code == KeyEvent.VK_A && plasticBottle.isInInventory) {
				powerUpHandler.powerUps[3].thrown = true;
				powerUpHandler.powerUps[3].direction = 4;
				pressed = false;
				System.out.println("Thrown to WEST");
				//plasticBottle.isInInventory = false;
			}
			}

		if (code == KeyEvent.VK_UP) {
			upMove = true;
		}
		if (code == KeyEvent.VK_F5) {
			System.out.println("f5e bastin brokoli");
			game.save();
		}
		if (code == KeyEvent.VK_F6) {
			System.out.println("f6 bastin brokoli");
			game.gameFrame.setVisible(false);
			game.load();
		}
		if (code == KeyEvent.VK_DOWN) {
			downMove = true;
		}

		if (code == KeyEvent.VK_LEFT) {
			leftMove = true;
		}

		if (code == KeyEvent.VK_RIGHT) {
			rightMove = true;
		}

	
		
		if (code == KeyEvent.VK_H) {
			
			
			executeHint();

			removeHintFromInventory();
				
			
		}
		if (code == KeyEvent.VK_ESCAPE) {
			gameChanger = !(gameChanger);
			
		}
		if(code == KeyEvent.VK_P) {
			Boolean check = protection.isInInventory;
			
			if(check) {
				removeProtectionFromInventory();
			//vestChanger = true;
			//getProtectionPowerUp(protection);
			Player.getPlayer().protectionVest=true;
			System.out.println("VEST ACTIVATED");
			protection.isInInventory =false;
		}}
		
		
		
			
		}
	
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_UP) {
			upMove = false;
			//thrown = false;
		}

		if (code == KeyEvent.VK_DOWN) {
			downMove = false;
			//thrown = false;
		}

		if (code == KeyEvent.VK_LEFT) {
			leftMove = false;
			//thrown = false;
		}

		if (code == KeyEvent.VK_RIGHT) {
			rightMove = false;
			//thrown = false;
		}
		
		if (code == KeyEvent.VK_H) {
			hint=false;
			deActivate = true;
			removeHintFromInventory();

		}
		if(code == KeyEvent.VK_B) {
			//removeBottleFromInventory();
		}
		if(code == KeyEvent.VK_P) {
			if(!protection.isInInventory) {
				
				
				
				
				executeVest();
				taskForVest =	new TimerTask() {
					@Override
					public void run()
					{

						removeProtectionFromInventory();
					}
				};

			}
			
		}
			

	}


}
