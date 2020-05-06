import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class TestBlackListUser {
	
//	HashMap<String, User> userList = new HashMap<>();
//	static HashMap<String, User> userList= new HashMap<>();
	Scanner scan = new Scanner(System.in);

	@Before
	public void setUp() {
		MainConsole.userList.put("s1", new Student("s1","qwerty", "s1@gmail.com", Availability.PartTime));
		MainConsole.userList.put("s2", new Student("s2","qwert", "s2@gmail.com", Availability.PartTime));
		MainConsole.userList.get("s1").setBlacklistStatus(BlacklistStatus.PROVISIONAL);
		String simulatedUserInput = "s1" + System.getProperty("line.separator")+ "Y" + System.getProperty("line.separator") + "s2";
		InputStream savedStandardInputStream = System.in;
		System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
		
	}
	// positive test - test if user on provisional blacklist can be moved to full blacklist 
	@Test
	public void blacklistUser() throws InvalidInputException, AuthorizationException {

		MaintenanceConsole.blackListUser();
		
		assertEquals(BlacklistStatus.FULL, (MainConsole.userList.get("s1").getBlacklistStatus()));
	}

	//Negative test - Test if exception would be thrown if user is not on provisional blacklist 
	
	@Test(expected = InvalidInputException.class)
	public void testInvalidInputException() throws InvalidInputException, AuthorizationException {
			MaintenanceConsole.blackListUser();
			
	}

}




