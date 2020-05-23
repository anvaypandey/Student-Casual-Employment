//import static org.junit.Assert.*;
//import static org.junit.Assert.assertEquals;
//
//import java.util.ArrayList;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//
//public class TestStudentJobCat {
//
//	StudentConsole std;
//	String jobcat;
//	@Before
//	public void setUp() {
//		std = new StudentConsole();
//		MainConsole.userList.put("123", new Employer("e123", "password", "s123@gmail.com"));
//		MainConsole.jobCategories.add("cafe");
//		jobcat = "cafe";
//	}
//
//
//
//	@Test
//	public void testJobCat() throws InvalidInputException
//	{
//
//		assertEquals(true, std.jobCatego("cafe"));
//	}
//	@Test(expected = InvalidInputException.class)
//	public void testInvalidInputException() throws InvalidInputException, AuthorizationException {
//
//		std.jobCatego("clean");
//
//	}
//
//}