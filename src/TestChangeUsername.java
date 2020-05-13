import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestChangeUsername {
	
	EmployerConsole emp;
	
	
	@Before
	public void setUp() {
		
		MainConsole.userList.put("s123", new Employer("s123", "password", "s123@gmail.com"));
		MainConsole.user = "s123";

		emp = new EmployerConsole();
		
	}
	
	@Test
	public void test() throws InvalidInputException {

		emp.changeUsername("qwe");
		assertFalse(MainConsole.userList.containsKey("s123"));
		assertTrue(MainConsole.userList.containsKey("qwe"));

	}

	@Test (expected = InvalidInputException.class)
	public void test2 ()throws InvalidInputException
	{
		emp.changeUsername("s123");
	}



}
