import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestRemoveFromBlacklist {

	MaintenanceConsole main;

	@Before
	public void setUp() throws Exception {
		MainConsole.userList.put("s1", new Student("s1","qwerty", "s1@gmail.com", Availability.PartTime));
		MainConsole.userList.put("s2", new Student("s2","qwert", "s2@gmail.com", Availability.PartTime));
		((Student) MainConsole.userList.get("s1")).setBlacklistStatus(BlacklistStatus.PROVISIONAL);
		((Student) MainConsole.userList.get("s2")).setBlacklistStatus(BlacklistStatus.FULL);
		String simulatedUserInput = "s1" + System.getProperty("line.separator") + "q" + System.getProperty("line.separator") + "s2" + System.getProperty("line.separator")+ "q";//this is to stimulate userInput
		System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
		main = new MaintenanceConsole();

	}

	//test if user can be removed from provisional blacklist
	@Test
	public void removeFromProvisionalBlacklist() throws InvalidInputException, AuthorizationException {

		main.removeFromBlacklist();

		assertEquals(BlacklistStatus.NONE, (((Student) MainConsole.userList.get("s1")).getBlacklistStatus()));

	}
	
	
	// User status shouldn't be changed if the user who needs to be removed has been on the list for fewer than 90 days and exception is expected
	@Test 
	public void cannotBeRemovedFromFullBlacklist() throws InvalidInputException, AuthorizationException {
		
		main.removeFromBlacklist();
		assertEquals(BlacklistStatus.FULL, (((Student) MainConsole.userList.get("s2")).getBlacklistStatus()));
	}

}
