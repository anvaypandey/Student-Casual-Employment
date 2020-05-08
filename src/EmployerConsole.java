import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class EmployerConsole {

	boolean depart = false;
	Scanner scan = new Scanner(System.in);

	public void run() throws Exception {

		System.out.println(" Welcome Employer " + MainConsole.user);
		do {
			managemenu();
		} while (!depart);

	}

	private void managemenu() throws Exception
	{
		String menu ="1. Create a new Job Listing\n "
				+"2. Search candidates based on Availability\n"
				+"3. Search candidates based on Availability\n"
				+"4. Change Password\n"
				+"5. Change Username\n"
				+ "Enter your choice: ";
		System.out.println(menu);

		System.out.println(menu);

			int userChoice = Integer.parseInt(scan.nextLine());

			switch(userChoice)
			{
			case 1: 
				addNewJob(); 
				break;
			case 2:
				userEntry();
				break;
			case 3:
				jobPrefEntry();
				break;
			case 4:
				changePassword();
				break;
			case 5:
				changeUsername();
				break;
			case 6:
				System.out.println("You have successfully logged out!\n");
				return;
			default:
				System.out.println("Invalid Choice. Please try again");
			}	

		
	}

	private void changePassword() {

		System.out.println("New Password");

		String newPassword = scan.nextLine();

		MainConsole.userList.get(MainConsole.user).setPassword(newPassword);

	}

	private void changeUsername() {

		System.out.println(" Enter the new Username");
		String newUsername = scan.nextLine();

		User a1 = new Employer((Employer) MainConsole.userList.get(MainConsole.user));

		MainConsole.userList.put(newUsername, a1);

		MainConsole.userList.remove(MainConsole.user);

		// MainConsole.user = newUsername; // If we want to continue from here

		depart = true; // to login again

		// TODO Auto-generated method stub

	}

	private void userEntry() {
		System.out.println("Enter 1 for Fulltime\n" + "Enter 2 for Part time\n" + "Enter 3 for Internship\n"
				+ "Enter your choice: ");

		int userChoice = Integer.parseInt(scan.nextLine());
		if (userChoice >= 1 && userChoice <= 3)
			try {
				searchApplicantsbyAvailability(userChoice);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
		// throw invalid input
			System.out.println("Invalid input.");
	}

	private void jobPrefEntry() throws Exception
	{
		System.out.println("Enter Job Preference\n"
							+"Enter your choice: ");
		
		String prefChoice= scan.nextLine();
		searchApplicantsbyJobPreference(prefChoice);
	}
	

	public boolean searchApplicantsbyAvailability(int i) throws Exception
	{
		boolean exists = false;
		for(Map.Entry<String,User> me : MainConsole.userList.entrySet()) 
		{
			if(me.getValue() instanceof Student)
			{
				if (i==1 && ((Student) me.getValue()).getAvailability() == Availability.FullTime)
				{
					me.getValue().getDetails();
					exists = true;
				}
					
				else if (i==2 && ((Student) me.getValue()).getAvailability() == Availability.PartTime)
				{
					me.getValue().getDetails();
					exists = true;
				}
					
				else if (i==3 && ((Student) me.getValue()).getAvailability() == Availability.Internship)
				{
					me.getValue().getDetails();
					exists = true;
				}
			}
		}
		if(!exists)
		throw new Exception("No student exists in this search criteria");

		return exists;
	}

	public boolean searchApplicantsbyJobPreference(String str) throws Exception
	{
		boolean exists = false;
		for(Map.Entry<String,User> me : MainConsole.userList.entrySet()) 
		{
			if(me.getValue() instanceof Student)
			{
				ArrayList<String> jobCat = new ArrayList<String>();
				jobCat = ((Student) me.getValue()).getJobCategories();
				

				for(int i=0;i<jobCat.size();i++)

					if (jobCat.get(i).equalsIgnoreCase(str))
					{
						me.getValue().getDetails();
						exists = true;
					}	
			}
		}
		if (!exists)
		throw new Exception("No student exists in this search criteria");

		return exists;
	}

	private void shortlistApplicants() 
	{
		int jobIndex = jobInput();
		if(jobIndex == -2)
			System.out.println(" You are not authorised to utilise these privileges");
		else if(jobIndex == -1)
			return;
		else
		{



		}

		// TODO Auto-generated method stub
		
	}

	private void rankApplicants() 
	{
			// TODO Auto-generated method stub
		
	}

	private void setInterview() 
	{
			// TODO Auto-generated method stub
		
	}

	private void offerJob() 
	{
			// TODO Auto-generated method stub
		
	}

	private void addNewJob()
	{
		String id = createJobId();
		String desc = "Sample";

		Job job = new Job(id, ((Employer) MainConsole.userList.get(MainConsole.user)), desc);
		MainConsole.jobListings.add(job);
	}

	private int jobInput()
	{
		String jobId =null;
		String input = null;
		if(showTheirJobListings())
		{
			if(MainConsole.userBlacklistStatus == BlacklistStatus.NONE)
			{
				do {
					System.out.println("Enter the job ID :");
					jobId = scan.nextLine();

					if(validJobId(jobId)<0)
					{
						System.out.println("Invalid Job Id.\nEnter Q to quit or any other value to try again");
						input = scan.nextLine();
					}

				}while (!input.equalsIgnoreCase("q"));

				if(input.equalsIgnoreCase("q"))
					return -2;
			}
			else
				return -1;

		}
		else
			return -2;

		return validJobId(jobId);

	}

	private String createJobId()
	{
		int typeID = 0;
		int count = 0;
		int i;
		for (i=0;i<MainConsole.jobListings.size();i++) {
			count++;
		}
		typeID = count + 1;
		String jobID = "JOB" + String.format("%03d", typeID);
		return jobID;
	}

	private int validJobId(String jobListing)
	{
		int i;
		for(i=0;i<MainConsole.jobListings.size();i++)
		{
			if(MainConsole.jobListings.get(i).getJobId().equalsIgnoreCase(jobListing))
				break;
		}
		if(i<MainConsole.jobListings.size())
			return i;
		else
			return -1;
	}

	private boolean showTheirJobListings()
	{
		boolean exists = false;
		for(int i=0;i<MainConsole.jobListings.size();i++)
		{
			 Job job = MainConsole.jobListings.get(i);
			if(job.getJobCreator().getUsername().equalsIgnoreCase(MainConsole.user))
			{
				exists = true;
				System.out.println(job.getDetails()+ "\n");

			}
		}
		if (!exists)
			System.out.println("You have zero Job Listings");

		return exists;
	}

}
