package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import data.DatabaseManager;
import domain.model.LoginModel;
import domain.model.MapResetter;
import domain.model.SignUpModel;
import domain.model.buildingstuff.BuildModeModel;
import domain.model.buildingstuff.Building;
import ui.game.BuildModeBottomPanel;
import ui.game.MainFrame;
import ui.game.OpeningMenuFrame;
import ui.game.GamePanelScreen;
import ui.game.Login;
import ui.game.BuildPanelScreen;
import ui.game.Signup;
import ui.game.BuildModeUpperPanel;

public class LoginController {

	Login loginScreenView;
	Signup signUpScreenView;
	MapResetter reset;
	public ObjectHandler obj_handler;
	LoginModel loginModel;
	public Building building = new Building("Student Center");
	public Tile_Object_Handler toh = new Tile_Object_Handler(building);
	private DatabaseManager dbManager;

	public LoginController(Login loginScreenView, LoginModel loginModel) {

		reset = new MapResetter();
		reset.mapResetter();
		this.loginModel = loginModel;
		this.loginScreenView = loginScreenView;

		this.dbManager = DatabaseManager.getInstance();

		this.loginScreenView.addSubmitButtonListener(new SubmitButtonListener());
		this.loginScreenView.addSignUpButtonListener(new SignUpButtonListener());

	}

	public void openOpeningMenu() {
		
		OpeningMenuFrame menu = new OpeningMenuFrame();
		OpeningMenuController menuController = new OpeningMenuController(menu);
		menu.setVisible(true);

		
	}

	class SubmitButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			if (dbManager.checkUser(loginScreenView.getUsername(), loginScreenView.getPassword())) {

				System.out.println("Successful login");
				loginScreenView.setVisible(false);

				// open build mode frame
				openOpeningMenu();
			} else {
				System.out.println("Please enter valid username and password");
			}

		}

	}

	class SignUpButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			loginScreenView.setVisible(false);

			// SignUpModel signUpModel = new SignUpModel();
			Signup signUpScreen = new Signup();

			SignUpController signUpController = new SignUpController(signUpScreen);

		}

	}

}
