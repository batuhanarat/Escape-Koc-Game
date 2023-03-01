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

//create CreateLoginForm class to create login form  
//class extends JFrame to create a window where our component add  
//class implements ActionListener to perform an action on button click  
public class Login extends JFrame 
{  
    //initialize button, panel, label, and text field  
	private  JButton b1;
    private JButton signupButton;  
    private JPanel newPanel;  
    private JLabel userLabel, passLabel;  
    private final JTextField  textField1, textField2;
      
    
    //calling constructor  
    public Login()  
    {     
        //create label for username   
        userLabel = new JLabel();  
        userLabel.setText("Username");      //set label value for textField1  
          
        //create text field to get username from the user  
        textField1 = new JTextField(15);    //set length of the text  
  
        //create label for password  
        passLabel = new JLabel();  
        passLabel.setText("Password");      //set label value for textField2  
          
        //create text field to get password from the user  
        textField2 = new JPasswordField(15);    //set length for the password  
          
        //create submit button  
        b1 = new JButton("SUBMIT"); //set label to button
        signupButton = new JButton("Sign Up"); //set button label to Sign Up  
          
        //create panel to put form elements  
        newPanel = new JPanel(new GridLayout(3, 1));  
        newPanel.add(userLabel);    //set username label to panel  
        newPanel.add(textField1);   //set text field to panel  
        newPanel.add(passLabel);    //set password label to panel  
        newPanel.add(textField2);   //set text field to panel  
        newPanel.add(b1);           //set button to panel  
        newPanel.add(signupButton); //add signup button to the panel

        //set border to panel   
        add(newPanel, BorderLayout.CENTER);  
          
        setTitle("Welcome To Escape From Koç Game");  //set title to the login form  
        
        this.setSize(500, 300);
        this.setVisible(true);
        
        
    }
    
    
    public String getUsername() {
    	return textField1.getText();
    	
    	
    }
    
    public String getPassword() {
    	return textField2.getText();
    	
    	
    }
    
    public JButton getSubmitButton() {
    	
    	return b1;
    	
    }
    
    
    public JButton getSignUpButton() {
    	
    	return signupButton;
    	
    }
    

	 public void addSubmitButtonListener(ActionListener submitButtonListener) {

	        b1.addActionListener(submitButtonListener);
	        
	    }

	 
	 public void addSignUpButtonListener(ActionListener signUpButtonListener) {

		    signupButton.addActionListener(signUpButtonListener);
	        
	    }


       
   

     
    }
    
