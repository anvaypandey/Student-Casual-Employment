import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestaddReference
{

	StudentConsole stnd;
	@Before
	public void setUp() {
		
		stnd = new StudentConsole();
		MainConsole.userList.put("s123", new Student("s123", "password", "s123@gmail.com", Availability.FullTime));
	 	Student std = (Student)MainConsole.userList.get("s123");
	 	Reference result = new Reference("Michael Smith", "michsmith@yahoo.com", "044315423");


	 	//result.add(new Reference);
//		setResult(result);
		//Reference ref = new Reference("Michael Smith", "michsmith@yahoo.com","044315423" );
	 	std.setReferences(result);
		std.getReferences();
		
	
	
			
	}
	@Test (expected = java.lang.AssertionError.class)
	public void getAddRef() {
		Student std = (Student)MainConsole.userList.get("s123");
		Reference expect1 = new Reference("Michael Smith"," michsmith@yahoo.com"," 044315423");
//		//expect.add(expect1);
//		setExpect(expect1);
//		expect = getExpect();
//		result = std.getReferences();
		//expect = std.setReferences();
		//std.setReferences(expect);
		//	System.out.println(result);
//			expected = getExpected();
		//Arrays.equals(result.get(0), expect.get(0));
//			
			
		Assert.assertEquals(expect1, std.getReferences());

		//Arrays.equals(expect1, result);
}




	@Test (expected = java.lang.AssertionError.class)
	public void getDetails() {
		Student std = (Student)MainConsole.userList.get("s123");
		Reference expect = new Reference("Ellie Smith","esmith@yahoo.com","044315423");
		//result = std.getReferences();
		//	System.out.println(result);
//			expected = getExpected();
//			
			
		Assert.assertNotEquals(expect, std.getReferences());
	
}}
