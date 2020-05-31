import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestStudentJobCat { //tests the student add job category method
	ArrayList<JobCategory> jobCat;
	Student std;
	String jobcat;
	StudentConsole stu;
	@Before
	public void setUp() { //adds information

		MainConsole.userList.put("s123", new Student("s123", "password", "s123@gmail.com", Availability.FullTime));
        jobcat = "waiter";
        JobCategory jobcat1 = new JobCategory(jobcat);
        MainConsole.jobCategories.add(jobcat1);
		stu = new StudentConsole();
		MainConsole.user = "s123";

	}
	@Test
	public void testJobCat() throws InvalidInputException, AuthorizationException {

		assertEquals(true, stu.jobCatego("waiter"));
	}
	@Test(expected = InvalidInputException.class)
	public void testInvalidInputException() throws InvalidInputException,  AuthorizationException {

		stu.jobCatego("clean");

	}

}