import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

public class TestRemoveFromBlacklist {

	MaintenanceConsole main;

	@Before
	public void setUp() throws Exception {
		MainConsole.userList.put("s1", new Student("s1","qwerty", "s1@gmail.com", Availability.PartTime));
		MainConsole.userList.put("s2", new Student("s2","qwert", "s2@gmail.com", Availability.PartTime));
		MainConsole.userList.get("s1").setBlacklistStatus(BlacklistStatus.PROVISIONAL);
		MainConsole.userList.get("s2").setBlacklistStatus(BlacklistStatus.FULL);
		String simulatedUserInput = "s1";//this is to stimulate userInput
		System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
		main = new MaintenanceConsole();

	}

	//test if user can be removed from provisional blacklist
	@Test
	public void removeFromProvisionalBlacklist() throws InvalidInputException, AuthorizationException {

		main.removeFromBlacklist();

		assertEquals(BlacklistStatus.NONE, (MainConsole.userList.get("s1").getBlacklistStatus()));

	}

	//test if Authorization exception would be thrown if user that needs to be removed has been on list for fewer than 90 days
	@Test(expected = AuthorizationException.class)
	public void testInvalidInputException() throws InvalidInputException, AuthorizationException {

		String simulatedUserInput = "s2" + System.getProperty("line.separator");
		System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

			main.removeFromBlacklist();

	}

	//test if user can be removed full blacklist if been on list for more than 90 days
	@Test
	public void removeFromFullBlacklist() throws InvalidInputException, AuthorizationException {

		DateTime.setAdvance(90, 0, 0);
		String simulatedUserInput = "s2" + System.getProperty("line.separator");
		System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
		main.removeFromBlacklist();

		assertEquals(BlacklistStatus.NONE, (MainConsole.userList.get("s2").getBlacklistStatus()));

	}



}
