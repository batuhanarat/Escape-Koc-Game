package domain.model;

public class LoginModel {
	
	
	private String username = "alp";
	
	private String password = "alp";
	
	private Boolean loggedIn = false;
	
	
	
	
	public LoginModel() {
		
			
	}
	
	
	
	public void checkLogin(String inputUsername, String inputPassword) {
		
		
		if(inputUsername.equals(username) && inputPassword.equals(password)) {
			loggedIn = true;
		}
		
	
	}
	
	
	
	public Boolean IsLoginSuccesfull() {
		return loggedIn;
		
		
	}
	
	public void loggedIn()
	{
		
		loggedIn = false;
		
	}


	
	
}
	
	
	
	
	
	

    
  
