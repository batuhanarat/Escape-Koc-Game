package controller;
import domain.*;
import domain.model.Player;
import domain.model.alien.Alien;
import domain.model.buildingstuff.BuildModeModel;
import domain.model.buildingstuff.Building;
import domain.model.buildingstuff.CollisionChecker;
import domain.model.buildingstuff.Object;
import tiles.TileHandler;
import ui.game.BuildModeBottomPanel;
import ui.game.MainFrame;
import ui.game.GamePanelScreen;
import ui.game.GameUpperPanel;
import ui.game.BuildPanelScreen;
import ui.game.GameBottomPanel;
import ui.game.ObjectSelectionButtonForBuildMode;
import ui.game.BuildModeUpperPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;




public class BuildingModeController extends JPanel {


    private MainFrame buildScreenView;

    private BuildModeUpperPanel upView;
    private BuildPanelScreen midView;
    private BuildModeBottomPanel downView;
    private BuildModeModel buildModel;
    
    private ArrayList<Building> buildingArrayList = new ArrayList<Building>();
    
    public Building building; 
    
    private Object object;
    private ObjectHandler object_Handler;

    
    private Boolean ObjectButtonPressed = false;
    private int number = 0;
    

    


    public BuildingModeController(MainFrame buildScreenView,
                                  BuildModeUpperPanel upView,
                                  BuildPanelScreen midView,
                                  BuildModeBottomPanel downView,
                                  BuildModeModel buildModel, 
                                  ObjectHandler objectHandler,
                                  Building building){
    	

        this.buildScreenView=buildScreenView;
        this.upView=upView;
        this.midView=midView;
        this.downView=downView;
        this.buildModel=buildModel;
        this.object_Handler = objectHandler;
        this.building = building;
        
        buildingArrayList.add(building);        
        

        this.downView.addObjectListener(new ObjectListener());
        this.upView.addComboBoxListener(new ComboBoxListener());         
        this.midView.addMouseListener(new ObjectPlacementListener());
        this.upView.addStartButtonListener(new StartButtonListener());
        //this.upView.addLoadButtonListener(new LoadButtonListener()); 
    }
    
    
    
  
    
   


         class ComboBoxListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				
				
				
				String newBuilding = upView.getComboBox();
				

				if(!(building.getName().equals(newBuilding)) && building.nextMap.equals(newBuilding)) {
					
					if(building.isValid()) {
						

						building.setAsFinished();
						building = new Building(newBuilding);
						
						
						
						buildingArrayList.add(building);
						
						
						
					
						 
						midView = new BuildPanelScreen(building);
						buildScreenView.addNewPanel(midView);
						
							

						
						
					} else {
						int currentConstrain = building.getConstrain();
						System.out.print(currentConstrain);
						String currentName = building.getName();
						
						upView.setComboBox(building);
						
						String message = "You need at least " + currentConstrain + " objects for " + currentName;
						upView.showErrorMessage(message);
						
					}
														
					
					
				}
					 
				
			}

            

        }
         
         
         
         
         class ObjectListener implements ActionListener{

 			@Override
 			public void actionPerformed(ActionEvent e) {
 				// TODO Auto-generated method stub
 				
 				ObjectButtonPressed= true;
 				
 				ObjectSelectionButtonForBuildMode button;
 				
 				button = (ObjectSelectionButtonForBuildMode)e.getSource();
 				

 				String name = button.getName();
 				Image im = button.getImage();
 				

 				
 				object= new Object(name,im);
 				
 	
 								
 				
 			}


         }
         
         class ObjectPlacementListener implements MouseListener {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			
				
				if(ObjectButtonPressed) {
					int XLocation = e.getX();
					int YLocation = e.getY();
					
					
					 
					
					object.setXLocation(XLocation);
					object.setYLocation(YLocation);
					object.whichRowAndColumn(XLocation, YLocation);
					
					
					building.addObject(object);
					
					midView.setDataOfImage(XLocation, YLocation, object.getImage(),building);
					midView.objectFlag = true;
					midView.repaint();
					
					
					object_Handler.setMap(building);
					object_Handler.decideType(object);
					object_Handler.addObjectsToMap(object.row, object.column, object.type);
					
					
					try {
						object_Handler.saveAddedObjectsToMap(building);
						number++;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
						
					ObjectButtonPressed=false;
					
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
		 class LoadButtonListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("load listening");
				buildScreenView.setVisible(false);
				new SaveLoadHandler().load();
			}
        	 
         }


         class StartButtonListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				buildScreenView.setVisible(false);
				
				
				building = buildingArrayList.get(0);
				

			
				CollisionChecker collisionChecker = new CollisionChecker(building);
				PowerUpHandler powerUpHandler = new PowerUpHandler(building);
				
				
				GameBottomPanel gamePanelBottomScreen = new GameBottomPanel();
				PlayerKeyHandler playerKeyHandler = new PlayerKeyHandler(powerUpHandler,gamePanelBottomScreen);
				Player player = new Player(playerKeyHandler,collisionChecker);
				player.full_time =  building.objectArrayList.size()*10;
				Tile_Object_Handler tileobject = new Tile_Object_Handler(building);
				AlienController alienController = AlienController.getAlienController(player,tileobject);
				GamePanelScreen gamePanelScreen = new GamePanelScreen(building,player, alienController,powerUpHandler,playerKeyHandler,gamePanelBottomScreen);
				GameUpperPanel gamePanelUpperScreen = new GameUpperPanel();
				MainFrame gameFrame = new MainFrame(gamePanelScreen,gamePanelBottomScreen,gamePanelUpperScreen,"Game");
				gameFrame.setVisible(true);
				

				
				GameController gamecontroller = new GameController(gamePanelScreen, gameFrame,player,tileobject,alienController,powerUpHandler,playerKeyHandler,gamePanelBottomScreen,gamePanelUpperScreen,buildingArrayList);
				
				
		
			}
        	 
        	 
        	 
        	 
         }




}

