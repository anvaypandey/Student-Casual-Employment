import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestChangeUsername {
	
	StudentConsole std;
	
	
	@Before
	public void setUp() {
		
		std = new StudentConsole();
		
		MainConsole.userList.put("s123", new Student("s123", "password", "s123@gmail.com", Availability.FullTime));
		MainConsole.user = "s123";
		
	}
	
	@Test
	public void test() {
		std.changeUsername();
//		MainConsole.userList.get("s123").setUsername("abc");
//		System.out.println(MainConsole.userList.get("s123").getUsername());
		assertEquals("abc", MainConsole.userList.get("s123").getUsername());
	}

}
