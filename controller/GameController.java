
package controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

import javax.swing.event.MenuKeyEvent;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

import controller.BuildingModeController.ObjectPlacementListener;
import domain.model.Player;
import domain.model.Sound;
import domain.model.alien.Alien;
import domain.model.buildingstuff.Building;
import domain.model.buildingstuff.CollisionChecker;
import domain.model.buildingstuff.Object;
import domain.model.powerup.HintPowerUp;
import domain.model.powerup.PlasticBottlePowerUp;
import domain.model.powerup.Power_Up;
import domain.model.powerup.ProtectionVestPowerUp;
import ui.game.GameBottomPanel;
import ui.game.GamePanelScreen;
import ui.game.GameUpperPanel;
import ui.game.Login;
import ui.game.MainFrame;
import ui.game.ObjectSelectionButtonForBuildMode;
import ui.game.PauseMenu;
import ui.game.Signup;

public class GameController implements Runnable {
	private static final int FPS = 60;

	private GamePanelScreen gamePanelScreen;
	private GameBottomPanel gamePanelBottomScreen;
	private GameUpperPanel gamePanelUpperScreen;
	public MainFrame gameFrame;
	private Tile_Object_Handler tileobjectHandler;
	Sound sound = new Sound();
	private Player player;
	public Timer timer;
	public AlienController alienController;
	public PowerUpHandler powerUpHandler;
	public PlayerKeyHandler playerKeyHandler;
	private ArrayList<Building> buildingArrayList; 
	private Building building;
	public Thread gameThread;
	public int buildingIndex = 0;
	private SaveLoadHandler saveLoad;
	public Boolean door = false;
	HintPowerUp hintPowerUp;
	PlasticBottlePowerUp plasticBottle;
	ProtectionVestPowerUp protection;
	private final static int delay = 20; // Delay for the timer object
	

	// this by default creates its own player and panel. Will be changed later
	
	
	public GameController(GamePanelScreen gamePanelScreen,MainFrame gameFrame
			,Player player , Tile_Object_Handler tileobjectHandler,
			AlienController alienController, PowerUpHandler powerUpHandler,
			PlayerKeyHandler playerKeyHandler, GameBottomPanel gamePanelBottomScreen,
			GameUpperPanel gamePanelUpperScreen,ArrayList<Building> buildingArrayList )  {
		
		this.building = tileobjectHandler.building;
		this.gamePanelScreen= gamePanelScreen;
		this.gamePanelBottomScreen=gamePanelBottomScreen;
		this.gamePanelUpperScreen=gamePanelUpperScreen;
		this.gameFrame = gameFrame;
		this.alienController = alienController;
		this.powerUpHandler = powerUpHandler;
		this.tileobjectHandler= tileobjectHandler;
		this.player = player;
		this.playerKeyHandler = playerKeyHandler;
		this.gamePanelScreen.addKeyListenertoPlayer(playerKeyHandler);
		this.buildingArrayList=buildingArrayList;

		updateAllBuildingsMap();
		generateKey();
		//testKey();
		startGameThread();

		this.building = buildingArrayList.get(buildingIndex);
		this.gamePanelScreen.addMouseListener(new PlayerMouseActionListener());
		powerUpHandler.startPowerUp();
		this.saveLoad = new SaveLoadHandler(this);
		this.playerKeyHandler.addGameController(this);
		
		this.gamePanelBottomScreen.addGameController(this);
	}


	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
		//playMusic(0);
	}


	public void updateAllBuildingsMap() {
		for (Building building : buildingArrayList ) {	 
			building.updateMapArray();	 
		}

	}
	
	public class SaveButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			saveLoad.save();
			
		}
		
	}
	
	



	@Override
	public void run() {
		// TODO Auto-generated method stub
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long lastTime_timer = System.nanoTime();

		long current_time;
		double delta_time = 0;

		while (gameThread != null) {
			if(player.gameState == player.playState) {
				player.stateUpdater();
				
				if(player.totalLives<=0) {
					
					player.isAlive=false;
					gamePanelScreen.repaint();
					gameThread.stop();
					
					
				}
				/*
				
				if((player.full_time - player.playerTime)<0) {
					player.isAlive=false;
					gamePanelScreen.repaint();
					gameThread.stop();
					
					
				}
				*/

				current_time = System.nanoTime();
				

				delta += (current_time - lastTime) / drawInterval;
				delta_time += (current_time - lastTime_timer) / drawInterval;
				lastTime = current_time;
				lastTime_timer = current_time;
				
				

				if (delta >= 1) {
					
					player.update();
					gamePanelScreen.repaint();
					delta--;
				}

				if (delta_time >= 60) {
					player.timeUpdate();
					delta_time -= 60;
				}
			
				
				
				
				
	}else if (player.gameState == player.pauseState) {
		//	gamePanelScreen.drawSubWindow();
			
			player.stateUpdater();
			
			gamePanelScreen.repaint();
	}
			
		
		

		}

	}

	public void generateKey() {

		for (Building building : buildingArrayList ) {	 			 
			int size = building.objectArrayList.size();
			Random rand = new Random();
			int randomindex = rand.nextInt(size);
			building.objectArrayList.get(randomindex).hasKey =true;
		}
	}


	public void testKey() {

		for (Building building : buildingArrayList ) {	 			 
			System.out.println(building.getName());
			for(Object obj:building.objectArrayList ) { 
				System.out.println(obj.getName());
				System.out.println(obj.hasKey); 
			}
		}
	}




	class PlayerMouseActionListener implements MouseListener{
		
		
		
		
		public void changeToNewMap() {
			
			
			
			player.full_time +=  building.objectArrayList.size()*5;
			player.coll_checker= new CollisionChecker(building); 
			tileobjectHandler = new Tile_Object_Handler(building);
			alienController.resetAlienList();
		
		
			powerUpHandler = new PowerUpHandler(building);
			playerKeyHandler.powerUpHandler=powerUpHandler;

			gamePanelScreen = new GamePanelScreen(building,player,alienController,powerUpHandler,playerKeyHandler,gamePanelBottomScreen);
			gamePanelScreen.addKeyListenertoPlayer(playerKeyHandler);
			gamePanelScreen.addMouseListener(new PlayerMouseActionListener());
			gameFrame.changeMap(gamePanelScreen);
			powerUpHandler.startPowerUp();


			updateAllBuildingsMap();		
			gameFrame.closeDoor();


			
		}
		 
		
		
		
		 
		 
		 

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

			//left click,search for key
			if(e.getButton() == MouseEvent.BUTTON1) {
				System.out.println("you clicked left mouse");


				int clickedXLocation = e.getX();
				int clickedYLocation = e.getY();
	
				tileobjectHandler.whichRowAndColumn(clickedXLocation, clickedYLocation);
				Boolean isClickedLocationHaveObject =  tileobjectHandler.checkIfObjectIsPresent(building);
				if(door) {
				if(new Rectangle(player.x,player.y,player.solidArea.width*2,player.solidArea.height*2).getBounds2D().
						intersects
						(new Rectangle(40,600,50,50).getBounds2D())) {
					buildingIndex++;
					building = buildingArrayList.get(buildingIndex);
					changeToNewMap();
					door=false;
				}}


				if(isClickedLocationHaveObject) {


					if(new Rectangle(player.x,player.y,player.solidArea.width*2,player.solidArea.height*2).getBounds2D().
							intersects
							(new Rectangle(tileobjectHandler.column*48,tileobjectHandler.row*48,50,50).getBounds2D())) {

						for (int i = 0; i < building.objectArrayList.size(); i++) {
							Object obj = building.objectArrayList.get(i);

							int obj_row = obj.row;
							int obj_col = obj.column;

							int clicked_row= tileobjectHandler.row;
							int clicked_col = tileobjectHandler.column;






							if ( obj_row == clicked_row && obj_col == clicked_col) {


								if(obj.hasKey) {
									
									if(building.nextMap!=null) {
										

										//display key 
										
										//open door
										door = true;
										gamePanelScreen.keyFound = true;
										gameFrame.openDoor();
										gamePanelScreen.repaint();
										
										
										/*changeToNewMap();
										//when it passes, load next map

										
										buildingIndex++;
										building = buildingArrayList.get(buildingIndex);*/
										
										//changeToNewMap();
									//	gameFrame.closeDoor();;

										
										System.out.println("you found the key");
										
										
										
										
										
										
										
										
									} else {
										
										
										System.out.println("You have escaped from Koç!. Congrats!!!");
										gameFrame.setVisible(false);

										
									}
									

								}

							}

						}

					}

				}
				


			}
			
			//right click, use power up
			if(e.getButton() == MouseEvent.BUTTON3) {
				
				 System.out.println("you clicked right mouse");
				 			
				
   
				   
				 int clickedXLocation = e.getX();
				 int clickedYLocation = e.getY();
				 
				 if(powerUpHandler.powerUp != null) {
					 
					 powerUpHandler.whichRowAndColumn2(clickedXLocation, clickedYLocation);
					 
					 
					 
					 if(powerUpHandler.row==powerUpHandler.powerUp.row ) {
						 
						 System.out.println("same row");

					 }
					 
					 if(powerUpHandler.col==powerUpHandler.powerUp.column ) {
						 System.out.println("same column");

						 
					 }
					
					 
					 Boolean isClickedLocationHavePowerUp =  powerUpHandler.checkIfPowerUpIsPresent(building);

					 System.out.println(isClickedLocationHavePowerUp);
					 
					 if(isClickedLocationHavePowerUp) {

						 
						 Power_Up powerUp = powerUpHandler.powerUp;
					 
						 
						 if(powerUp.type == 0) {
							 
							 //EXTRA LİFE
							 
							 System.out.println("EXTRA LİFE ALDIM");

								  player.totalLives += 1;
								 // gameFrame.addLives();
								  gamePanelBottomScreen.addLives();
								  
								 powerUpHandler.removePowerUp();
								 powerUpHandler.powerUp = null;


							 
						 }
						 
						 else if(powerUp.type == 1) {
							 
							 //EXTRA TİME
							    System.out.println("TİME ALDIM");

							     player.full_time += 5;
							     
							       
								powerUpHandler.removePowerUp();
							    powerUpHandler.powerUp = null;


						 }
						 
						 
						 else if (powerUp.type == 2) {
							 
							 
							 System.out.println("HINTI ALDIM");
							 //HINT
							  	hintPowerUp = ((HintPowerUp) powerUpHandler.powerUp);
							 	hintPowerUp.addedToInventory();
							 	
								 playerKeyHandler.getHintPowerUp(hintPowerUp);

							 	
							 	
							 	
							 	
							 	
							 	
							 	
							 	
							 	gamePanelBottomScreen.addHintToInventory(powerUp.image); 
							 
							 
							 
							 
							 
							 
							 
							 

							 powerUpHandler.removePowerUp();
							 powerUpHandler.powerUp = null;


							     
							     
							     
							  //   gamePanelScreen.extraLife.visible = false;
							 
						 }
						 
						 else if (powerUp.type == 3) {
							 	
							 //PLASTIC BOTTLE
							 
							 System.out.println("BOTTLE ALDIM");
							 plasticBottle = ((PlasticBottlePowerUp) powerUpHandler.powerUp);
							 plasticBottle.addedToInventory();
							 playerKeyHandler.getPlasticBottle(plasticBottle);
							 
							 gamePanelBottomScreen.addPlasticBottleToInventory(powerUp.image);
							 powerUpHandler.removePowerUp(); 
							 powerUpHandler.powerUp = null;

						 }
						 
						 
						 else if (powerUp.type== 4) {
							 //PROTECTION VEST
							 System.out.println("VESTI ALDIM");
							 protection = ((ProtectionVestPowerUp) powerUpHandler.powerUp);
							 protection.addedToInventory();
								System.out.println("EKLEDIM");

							System.out.println(protection.isInInventory);

							 playerKeyHandler.getProtectionPowerUp(protection);
							 gamePanelBottomScreen.addProtectionVestToInventory(powerUp.image);
							
							 protection.isInInventory = true;
							 playerKeyHandler.isInInventory = true;
							 
							 //player.vestUpdate();
							 
							 
							 
							 
							 
							 powerUpHandler.removePowerUp();
							 powerUpHandler.powerUp = null;

							  //building.removePowerUpFromPowerUpArrayList(powerUp);
							  
						
							 
							 
						 }
						  
						 
					 }
					 else {
							
							

							
						}

				 }
				 
			
				 
				
				
				
	
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}




	}


	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();

	}

	public Player getPlayer(){
		return this.player;
	}

	
	public void save() {
		saveLoad.save();
	}
	
	public void load() {
		saveLoad.load();
	}
	
	public ArrayList<Building> getBuildings() {
		return buildingArrayList;
	}
	
	public void setBuildings(ArrayList<Building> buildings) {
		this.buildingArrayList = buildings;
	}
	
	public int getBuildingIndex() {
		return buildingIndex;
	}
	
	public GameController(GamePanelScreen gamePanelScreen,MainFrame gameFrame
			,Player player , Tile_Object_Handler tileobjectHandler,
			AlienController alienController, PowerUpHandler powerUpHandler,
			PlayerKeyHandler playerKeyHandler, GameBottomPanel gamePanelBottomScreen,
			GameUpperPanel gamePanelUpperScreen,ArrayList<Building> buildingArrayList , int buildingIndex)  {
		
		this.building = tileobjectHandler.building;
		this.gamePanelScreen= gamePanelScreen;
		this.gamePanelBottomScreen=gamePanelBottomScreen;
		this.gamePanelUpperScreen=gamePanelUpperScreen;
		this.gameFrame = gameFrame;
		this.alienController = alienController;
		this.powerUpHandler = powerUpHandler;
		this.tileobjectHandler= tileobjectHandler;
		this.player = player;
		this.playerKeyHandler = playerKeyHandler;
		this.gamePanelScreen.addKeyListenertoPlayer(playerKeyHandler);
		this.buildingArrayList=buildingArrayList;
		this.buildingIndex = buildingIndex;

		updateAllBuildingsMap();
		//generateKey();
		//testKey();
		startGameThread();

		//this.building = buildingArrayList.get(buildingIndex);
		this.gamePanelScreen.addMouseListener(new PlayerMouseActionListener());
		powerUpHandler.startPowerUp();
		
		this.saveLoad = new SaveLoadHandler(this);
		this.playerKeyHandler.addGameController(this);
	}


}



/*	


	private void pauseResume() {

		if (timer.isRunning()) {
			timer.stop();

			System.out.println("paused");

			gamePanelScreen.showPauseMenu();

		} else {
			timer.start();

			System.out.println("unpaused");

			gamePanelScreen.resumeGamePanel();
		}

	}



 */






