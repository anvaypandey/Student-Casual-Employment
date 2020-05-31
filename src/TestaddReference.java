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
	@Test
	public void getAddRef() {
		Student std = (Student)MainConsole.userList.get("s123");
		Reference expect1 = new Reference("Michael Smith"," michsmith@yahoo.com"," 044315423");
		std.setReferences(expect1);
		Assert.assertEquals(std.getReferences().size(), 2);
}

	@Test
	public void getDetails() {
		Student std = (Student)MainConsole.userList.get("s123");
		//expected is Michael Smith

		Assert.assertNotEquals(std.getReferences().get(0).getName(), "Ellie Smith");
	
}}
