import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestEmployerConsole extends EmployerConsole {

	EmployerConsole emp;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		

		MainConsole.userList.put("student1", new Student("student1","123","student1@email.com",Availability.FullTime));
		MainConsole.userList.put("student2", new Student("student2","123","student2@email.com",Availability.PartTime));
		//MainConsole.userList.put("student3", new Student("student3","123","student3",Availability.PartTime));
		//MainConsole.userList.put("student4", new Student("student4","123","student4",Availability.FullTime));
		//MainConsole.userList.put("student5", new Student("student5","123","student5",Availability.Internship));
		//MainConsole.userList.put("student6", new Student("student6","123","student6",null));
		
		MainConsole.jobCategories.add("Engineering");
		MainConsole.jobCategories.add("Accounting");
		
		((Student)MainConsole.userList.get("student1")).addJobCategory("Engineering");
		((Student)MainConsole.userList.get("student2")).addJobCategory("Accounting");

		MainConsole.userList.put("emp123", new Employer("emp123","123","student1@email.com"));

		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test (expected = Exception.class)
	public void testSearchApplicantsbyAvailability1() throws Exception {
		searchApplicantsbyAvailability(3);
	}

	@Test
	public void testSearchApplicantsbyAvailability2() throws Exception{
		assertTrue(searchApplicantsbyAvailability(1));
	}

	@Test (expected = Exception.class)
	public void testSearchApplicantsbyJobPreference() throws Exception{
		searchApplicantsbyJobPreference("sdfgsf");
	}

	@Test
	public void testSearchApplicantsbyJobPreference1() throws Exception{
		assertTrue(searchApplicantsbyJobPreference("Engineering"));
	}

	@Test
	public void testAddNewJob() {
		Job job1 = new Job("JOB001", ((Employer) MainConsole.userList.get("emp123")), "Sample Job1");
		Job job2 = new Job("JOB002", ((Employer) MainConsole.userList.get("emp123")), "Sample Job2");
		Job job3 = new Job("JOB003", ((Employer) MainConsole.userList.get("emp123")), "Sample Job3");

		MainConsole.jobListings.add(job1);
		MainConsole.jobListings.add(job2);
		MainConsole.jobListings.add(job3);

		Job job = new Job("JOB000", ((Employer) MainConsole.userList.get("emp123")), "Sample Job");
		addNewJob(job);
		
		assertEquals(4, MainConsole.jobListings.size());
	}



}
