import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestStudentJobCat {
	ArrayList<JobCategory> jobCat;
	StudentConsole std;
	String jobcat;
	@Before
	public void setUp() {
		std = new StudentConsole();
		MainConsole.userList.put("123", new Employer("e123", "password", "s123@gmail.com"));
        jobcat = "waiter";
        JobCategory jobcat1 = new JobCategory(jobcat);
        MainConsole.jobCategories.add(jobcat1);


	}



	@Test (expected = NullPointerException.class)
	public void testJobCat() throws InvalidInputException, NullPointerException
	{

		assertEquals(true, std.jobCatego("waiter"));
	}
	@Test(expected = InvalidInputException.class)
	public void testInvalidInputException() throws InvalidInputException, AuthorizationException {

		std.jobCatego("clean");

	}

}