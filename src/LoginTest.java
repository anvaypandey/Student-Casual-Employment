
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class LoginTest {
	
	static HashMap<String, User> userList = new HashMap<>();
	
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
	
	
	
	
//	@Test(expected = InvalidCredentialsException.class)
//	public void testInvalidCredentialsException() {
//		MainConsole main = new MainConsole();
//		main.login();
//		
//		userList.put("s123", new Student("s123", "password", "s123@gmail.com", Availability.FullTime));
//		String choice = "1";
//		InputStream in = new ByteArrayInputStream(choice.getBytes());
//		System.setIn(in);
//		
//		String user = "s123";
//		InputStream username = new ByteArrayInputStream(user.getBytes());
//		System.setIn(username);
//		
//		String password = "pord";
//		InputStream pass = new ByteArrayInputStream(password.getBytes());
//		System.setIn(pass);  
//		
//		
//		
//	}
	
	

}
