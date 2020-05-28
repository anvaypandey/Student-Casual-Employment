import java.io.Serializable;
import java.util.ArrayList;

public class Maintenance extends User implements Serializable {
	

	protected Maintenance(String username, String password,String email) {
		super(username, password,email);
	}
	
	
	public Maintenance(Maintenance that) {
		super(that.getUsername(), that.getPassword(), that.getEmailAddress());
		
	}


	@Override
	public String getDetails() {
		return null;
	}
	
	
	
}
