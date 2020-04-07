import java.util.ArrayList;

public class Maintainence extends User {
	
	ArrayList<User> blacklist = new ArrayList<User>();

	protected Maintainence(String username, String password,String email) {
		super(username, password,email);
	}
	
	
	
}
