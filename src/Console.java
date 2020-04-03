import java.util.HashMap;

public class Console {
	
	HashMap<String, User> UserList = new HashMap<String, User>();
	
	String user;
	
	public void run() {
		
		login();
		
		//if student show student menu, 
		
		//student 
		
	}
	
	private void login()
	{
		// ask if they want to register, login or quit
		
		register(); //if they want to register
		
		//if login ask for credentials
		
		System.out.println("Enter credentials");
		
		//verify if the username exists, if yes use validatePassword() for password
		
		//if true then return or else show Invalid Credential Exception
		
	}
	
	private void register()// check if they already have been registered or not then register
	{
		//setUsername();
		//setPassword();
	}
	
	

}
