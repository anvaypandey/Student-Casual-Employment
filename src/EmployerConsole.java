import java.util.Scanner;

public class EmployerConsole {
	
	boolean depart = false;
	Scanner  scan = new Scanner(System.in);

	public void run() {

		System.out.println(" Welcome Employer "+ MainConsole.user);
		do
		{
			managemenu();
		}while(!depart);
		
	}

	private void managemenu()
	{
		String menu ="1. Create a new Job Listing\n "
				+"2. Search candidates based on Availability\n"
				+ "Enter your choice: ";
		System.out.println(menu);
		// TODO Auto-generated method stub
		
	}
	
	private void changePassword() {
		
		System.out.println("New Password");
		
		String newPassword = scan.nextLine();
		
		MainConsole.userList.get(MainConsole.user).setPassword(newPassword);
		// TODO Auto-generated method stub
		
	}

	private void changeUsername() {

		System.out.println(" Enter the new Username");
		String newUsername = scan.nextLine();
		
		User a1 = new Employer((Employer)MainConsole.userList.get(MainConsole.user));
		
		MainConsole.userList.put(newUsername, a1);
		
		MainConsole.userList.remove(MainConsole.user);
		
		//MainConsole.user = newUsername; // If we want to continue from here
		
		depart =true; //to login again
		
		// TODO Auto-generated method stub
		
	}

	private void searchApplicants() 
	{


		// TODO Auto-generated method stub
		
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
