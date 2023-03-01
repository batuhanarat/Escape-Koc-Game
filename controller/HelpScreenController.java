package controller;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import controller.LoginController.SubmitButtonListener;
import domain.model.SignUpModel;
import ui.game.HelpScreen;
import ui.game.Signup;

public class HelpScreenController {
	
	HelpScreen helpScreen;
	JTextArea helpText; 
	
	public HelpScreenController(HelpScreen helpScreen) {
		
		
		this.helpScreen = helpScreen;
		
        
		this.helpScreen.addBackButtonListener(new BackButtonListener());

         
         
         
        
	}

	
	public String setText() {

		return "Some text in here but aaa looong text";
		
		
	}
	
	
	  class BackButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			helpScreen.dispose();

			
			
			
			
		}

  

}
}
