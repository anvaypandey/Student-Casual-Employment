import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TestStudentgetPassword {

	
	@Before
	public void setUp() {
		MainConsole.userList.put("s123", new Student("s123", "password", "s123@gmail.com", Availability.FullTime));
	}
	
	@Test 
	public void getDetails() {
	
		String expected = "password";
		String result = ((Student)MainConsole.userList.get("s123")).getPassword();
			System.out.print(result);	
		assertEquals(expected, result);
	}

}