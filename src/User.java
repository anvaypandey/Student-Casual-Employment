
public class User {
	
	private String username;
	private String password;
	
	

	protected User(String username, String password) {
		this.username = username;
		this.password = password;
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
	
	public boolean validatePassword(String password)//to restrain the access of the correct password
	{
		if(this.password.equals(password))
			return true;
		else
			return false;
		
	}
	

}
