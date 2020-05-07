
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class LoginTest {
	
	HashMap<String, User> userList = new HashMap<>();
	
	@Before
	public void setUp() {
		
		populateValues();
	}
	public void populateValues()
	{
		userList.put("admin", new Maintainence("admin","admin","admin@gmail.com"));
		userList.put("s1", new Student("s1","qwerty", "s1@gmail.com",Availability.PartTime));
		userList.put("e1",new Employer ("e1","qwerty","e1@gmail.com"));
		
		
	}
//	
	//throwing an InvalidCredentials exception
	@Test (expected = InvalidCredentialsException.class)
	public void testInvalidCredentialsException() throws InvalidCredentialsException {
		String tst ="qwer";
		
		userList.get("s1").validatePassword(tst);
		
	}
	
	
	

}
