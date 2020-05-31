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
	 	std.setReferences(result);
		std.getReferences();

	}
	@Test (expected = java.lang.AssertionError.class)
	public void getAddRef() {
		Student std = (Student)MainConsole.userList.get("s123");
		Reference expect1 = new Reference("Michael Smith"," michsmith@yahoo.com"," 044315423");
		Assert.assertEquals(expect1, std.getReferences());
}

	@Test (expected = java.lang.AssertionError.class)
	public void getDetails() {
		Student std = (Student)MainConsole.userList.get("s123");
		Reference expect = new Reference("Ellie Smith","esmith@yahoo.com","044315423");

		Assert.assertNotEquals(expect, std.getReferences());
	
}}
