import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class TestBlackListUser {

	MaintenanceConsole main;

	@Before
	public void setUp() {
		MainConsole.userList.put("s1", new Student("s1","qwerty", "s1@gmail.com", Availability.PartTime));
		MainConsole.userList.put("s2", new Student("s2","qwert", "s2@gmail.com", Availability.PartTime));
		((Student) MainConsole.userList.get("s1")).setBlacklistStatus(BlacklistStatus.PROVISIONAL);
		
		//this is to stimulate user inputs in the loop
		String simulatedUserInput = "s2" + System.getProperty("line.separator") + "s1" + System.getProperty("line.separator") +"Y" + System.getProperty("line.separator");


		System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
		main = new MaintenanceConsole();

	}
	
	
	@Test
	public void testIfUserCanBeBlacklisted() throws InvalidInputException, AuthorizationException {

		main.blackListUser();
		
		//Negative test - user shouldn't be blacklisted 
		assertEquals(BlacklistStatus.NONE, (((Student) MainConsole.userList.get("s2")).getBlacklistStatus()));
		
		// positive test - test if user on provisional blacklist can be moved to full blacklist
		assertEquals(BlacklistStatus.FULL, (((Student) MainConsole.userList.get("s1")).getBlacklistStatus()));

		
	}

}
	





