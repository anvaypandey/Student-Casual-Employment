import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TestGetSetStudentDetails {

//	static HashMap<String, User> userList = new HashMap<>();
	
	@Before
	public void setUp() {
		MainConsole.userList.put("s123", new Student("s123", "password", "s123@gmail.com", Availability.FullTime));
	}
	
	@Test 
	public void getDetails() {
		Availability expected = Availability.FullTime;
		Availability result = ((Student)MainConsole.userList.get("s123")).getAvailability();
			System.out.print(result);	
		assertEquals(expected, result);
	}

}
