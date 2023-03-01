package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import data.DatabaseManager;
import domain.model.LoginModel;
import ui.game.Login;
import ui.game.Signup;

public class SignUpController {

	private Signup signUpScreen;
	private DatabaseManager dbManager;

	public SignUpController(Signup signUpScreen) {

		this.signUpScreen = signUpScreen;
		//this.loginController = loginController;
		
		this.dbManager = DatabaseManager.getInstance();
		
		this.signUpScreen.addConfirmSignUpButtonListener(new ConfirmSignUpButtonListener());

	}

	class ConfirmSignUpButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			//dbye baglan
			
			//ayar cek
			Boolean success = dbManager.writeUser(signUpScreen.getTextUsername(), signUpScreen.getTextPassword());
			if(!success)
				return;
			
			//logine don
			Login loginScreen = new Login();
			LoginModel loginModel = new LoginModel();
			LoginController loginController = new LoginController(loginScreen, loginModel);
			
			signUpScreen.setVisible(false);

			// openBuildMode();

		}

	}

}
