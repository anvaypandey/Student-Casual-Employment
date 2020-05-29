import java.io.Serializable;

public abstract class User implements Serializable {
	
	private String username;
	private String password;
	private String emailAddress;
	

	protected User(String username, String password, String emailAddress) {
		this.username = username;
		this.password = password;
		this.setEmailAddress(emailAddress);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean setPassword(String password) throws InvalidInputException{

		if(password.equalsIgnoreCase(this.password))
			throw new InvalidInputException("Your new password cannot be same as the new password");
		this.password = password;
		return true;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public boolean validatePassword(String password) throws InvalidCredentialsException//to restrain the access of the correct password
	{
		if(!this.password.equals(password))
			throw new InvalidCredentialsException("Wrong Password.Please try again.");
		else
			return true;
		
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public abstract String getDetails();
	

}
