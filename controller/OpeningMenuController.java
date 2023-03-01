package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.model.buildingstuff.BuildModeModel;
import domain.model.buildingstuff.Building;
import ui.game.BuildModeBottomPanel;
import ui.game.BuildModeUpperPanel;
import ui.game.BuildPanelScreen;
import ui.game.HelpScreen;
import ui.game.MainFrame;
import ui.game.OpeningMenuFrame;

public class OpeningMenuController {

	OpeningMenuFrame menu;
	
	
	public OpeningMenuController(OpeningMenuFrame frame) {
		
		
		this.menu = frame;
		
		this.menu.addStartNewGameListener(new StartNewGameListener());
		this.menu.addLoadNewGameListener(new LoadNewGameListener());
		this.menu.addHelpListener(new HelpListener());
		this.menu.addExitListener(new ExitListener());
	}
	
	public void openBuildMode() {
		 
		 
		 
		BuildModeBottomPanel buildModeBottomPanelForObjectSelection = new BuildModeBottomPanel();
		BuildModeUpperPanel buildModeUpPanelForBuildingSelection = new BuildModeUpperPanel();
        Building  building = new Building("Student Center");
		BuildPanelScreen mapPanel = new BuildPanelScreen(building);
		
		ObjectHandler handler = new ObjectHandler(mapPanel,building);

		
		MainFrame buildModeView = new MainFrame(buildModeUpPanelForBuildingSelection,
																	mapPanel,
																	buildModeBottomPanelForObjectSelection,"Build");
		
		
		 
		BuildModeModel buildModel = new BuildModeModel();
		
		 BuildingModeController buildModeController = new BuildingModeController(buildModeView,
				 buildModeUpPanelForBuildingSelection,
				 mapPanel,
				 buildModeBottomPanelForObjectSelection,
                 buildModel,
                 handler,
                 building);  
		        
		 
		 buildModeView.setVisible(true);
		
	} 
	
	public void openHelpScreen() {
		
		HelpScreen helpScreen = new HelpScreen();
		HelpScreenController helpController = new HelpScreenController(helpScreen);
		
		
		 helpScreen.setVisible(true);

		
		
	}

	
	
	class  StartNewGameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			menu.setVisible(false);
			openBuildMode();
			
		}

     

 }
	
	class  LoadNewGameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			SaveLoadHandler save = new SaveLoadHandler();
			save.load();
			
			
		}

     

 }
	
	
	class  HelpListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			openHelpScreen();
		     

     

 }
	}
	
	class  ExitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
            System.exit(0);

			
			
		}

     

 }
	
	
	
	
	
	
}
