
public class User {
	
	private String username;
	private String password;
	private String emailAddress;
	
	

	protected User(String username, String password, String emailAddress) {
		this.username = username;
		this.password = password;
		this.emailAddress = emailAddress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean validatePassword(String password) throws InvalidCredentialsException//to restrain the access of the correct password
	{
		if(!this.password.equals(password))
			throw new InvalidCredentialsException("Wrong Password.Please try again.");
		else
			return true;
		
	}
	

}
