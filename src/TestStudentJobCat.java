import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestStudentJobCat {
	ArrayList<String> result;
	ArrayList<String> expected;
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
	public void getDetails() {
		Student std = (Student)MainConsole.userList.get("s123");
		String expect = "Michael Smith";
		result = std.getReferences();
			System.out.println(result);
//			expected = getExpected();
//			
			
		Assert.assertEquals(expect, result);
	
}}