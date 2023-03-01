import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.BuildingModeController;
import controller.GameController;
import controller.LoginController;
import ui.game.*;
import domain.model.*;
import domain.model.buildingstuff.Building;
import domain.model.buildingstuff.Object;
public class main {
	public static void main(String[] args) {
		
		 
		Login loginScreen = new Login();
		LoginModel loginModel = new LoginModel();
		LoginController loginController = new LoginController(loginScreen, loginModel);
		
	
				
	//there is a design problem in here.
	// i want to handle all of the controller in the main. But their screen is dependant to each others input. 
	//Therefore I should not initiate at the begining at the main
		
		
					 
		

	
		
		
		
		
		

	}
	
	/*
	public static void openBuildMode() {
		
		BuildModeUpperPanel buildModeUpPanelForBuildingSelection = new BuildModeUpperPanel();
		BuildPanelScreen mapPanel = new BuildPanelScreen();
		BuildModeBottomPanel buildModeBottomPanelForObjectSelection = new BuildModeBottomPanel();
		
		
		MainFrame buildModeView = new MainFrame(buildModeUpPanelForBuildingSelection,
																	mapPanel,
																	buildModeBottomPanelForObjectSelection);
		
		
		 
		BuildModeModel buildModel = new BuildModeModel();
		
		 BuildingModeController buildModeController = new BuildingModeController(buildModeView,
				 buildModeUpPanelForBuildingSelection,
				 mapPanel,
				 buildModeBottomPanelForObjectSelection,
                 buildModel);
		 
		 
		 buildModeView.setVisible(true);
		 
		 
	}
	*/
}