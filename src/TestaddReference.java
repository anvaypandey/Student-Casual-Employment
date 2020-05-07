import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestaddReference
{
	ArrayList<String> result;
	
	StudentConsole stnd;
	@Before
	public void setUp() {
		
		stnd = new StudentConsole();
		MainConsole.userList.put("s123", new Student("s123", "password", "s123@gmail.com", Availability.FullTime));
	 	Student std = (Student)MainConsole.userList.get("s123");
		std.setReferences("Michael Smith");
		std.getReferences();
		
	
	
			
	}
	@Test
	public void getAddRef() {
		Student std = (Student)MainConsole.userList.get("s123");
		String expect = "Michael Smith";
		result = std.getReferences();
			System.out.println(result);
//			expected = getExpected();
//			
			
		//Assert.assertEquals(expect, result);
		assertEquals("Michael Smith", result.get(0));
}

	
	@Test (expected = java.lang.AssertionError.class)
	public void getDetails() {
		Student std = (Student)MainConsole.userList.get("s123");
		String expect = "Michael Smith";
		result = std.getReferences();
			System.out.println(result);
//			expected = getExpected();
//			
			
		Assert.assertEquals("Michael Smith", result);
	
}}
