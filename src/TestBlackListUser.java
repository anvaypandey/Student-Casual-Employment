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
		MainConsole.userList.get("s1").setBlacklistStatus(BlacklistStatus.PROVISIONAL);
		String simulatedUserInput = "s1" + System.getProperty("line.separator")+ "Y" ;//this is to stimulate userInput


		System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
		main = new MaintenanceConsole();

	}
	// positive test - test if user on provisional blacklist can be moved to full blacklist
	@Test
	public void blacklistUser() throws InvalidInputException, AuthorizationException {

		main.blackListUser();

		assertEquals(BlacklistStatus.FULL, (MainConsole.userList.get("s1").getBlacklistStatus()));

	}

	//Negative test - Test if exception would be thrown if user is not on provisional blacklist

	@Test(expected = InvalidInputException.class)
	public void testInvalidInputException() throws InvalidInputException, AuthorizationException {
		String simulatedUserInput = "s2" + System.getProperty("line.separator");
		System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

			main.blackListUser();

	}

}




