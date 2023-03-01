package ui.game;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//import controller.GameController;  

public class Signup extends JFrame 
{
    //signup panel components
   private  JPanel signupPanel;
   private JLabel labelUsername, labelPassword, labelConfirmPassword;
   private  JTextField  textUsername, textPassword, textConfirmPassword;
   private  JButton confirmSignUpButton;
    

    public Signup()
    { 

        labelUsername = new JLabel();
        labelUsername.setText("Choose Username");
        textUsername = new JTextField(15);

        labelPassword = new JLabel();
        labelPassword.setText("Choose Password");
        textPassword = new JPasswordField(15);

        labelConfirmPassword = new JLabel();
        labelConfirmPassword.setText ("Confirm Password");
        textConfirmPassword = new JPasswordField(15);

        confirmSignUpButton = new JButton("Confirm Signup");

        signupPanel = new JPanel(new GridLayout(4,1));
        signupPanel.add(labelUsername);
        signupPanel.add(textUsername);
        signupPanel.add(labelPassword);
        signupPanel.add(textPassword);
        signupPanel.add(labelConfirmPassword);
        signupPanel.add(textConfirmPassword);
        signupPanel.add(confirmSignUpButton);
        
        add(signupPanel, BorderLayout.CENTER);

        setTitle("Sign Up");
        
		this.setSize(400, 100);
        this.setVisible(true);

    }
    
    
    public String getTextUsername() {
		return textUsername.getText();
	}

	

	public String getTextPassword() {
		return textPassword.getText();
	}



	public String getTextConfirmPassword() {
		return textConfirmPassword.getText();
	}

    
	 public void addConfirmSignUpButtonListener(ActionListener confirmSignUpListener) {

	        confirmSignUpButton.addActionListener(confirmSignUpListener);
	        
	    }
    
    

   


    
}
