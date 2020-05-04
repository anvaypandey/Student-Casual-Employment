
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class LoginTest {
	
	HashMap<String, User> userList = new HashMap<>();
	
	@Before
	public void setUp() {
		userList.put("s123", new Student("s123", "password", "s123@gmail.com", Availability.FullTime));
		
	}
//	
	//throwing an InvalidCredentials exception
	@Test (expected = InvalidCredentialsException.class)
	public void testInvalidCredentialsException() throws InvalidCredentialsException {
		String tst ="pasord";
		
		userList.get("s123").validatePassword(tst);
		
	}
	
	
	

}
