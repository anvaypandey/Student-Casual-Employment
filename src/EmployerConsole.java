import java.util.ArrayList;
import java.util.Map;

public class EmployerConsole {

	boolean depart = false;

	public void run() {

		System.out.println(" Welcome Employer " + MainConsole.user);
		do {
			managemenu();
		} while (!depart);
	}

	private void managemenu()
	{
		try {
			//reference checks, interview result
			String menu ="1. Create a new Job Listing\n"
					+"2. Search candidates based on Availability\n"
					+"3. Search candidates based on Job Preference\n"
					+"4. ShortList Candidate\n"
					+"5. Rank Candidates\n"
					+"6. Address the result of the interviews\n"
					+"7. Lodge Complaint\n"
					+"8. Change Username\n"
					+"9. Change Password\n"
					+"10. Logout\n"
					+ "Enter your choice: ";
			System.out.println(menu);

				int userChoice = Integer.parseInt(Utilities.getScanner().nextLine());

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
					System.out.println("Enter the username of the student you want to shortlist");
					String studentId = Utilities.getScanner().nextLine();
					shortlistCandidate(studentId);
					break;
				case 5:
					rankCandidates();
					break;
				case 6:
					inputResultforInterviews();
					break;
				case 7:
					lodgeComplaint();
					break;
				case 8:
					System.out.println(" Enter the new Username");
					String newUsername = Utilities.getScanner().nextLine();
					changeUsername(newUsername);
					break;
				case 9:
					changePassword();
					break;
				case 10:
					System.out.println("You have successfully logged out!\n");
					depart = true;
					return;
				default:
					System.out.println("Invalid Choice. Please try again");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		
	}

	private void inputResultforInterviews() {
		for(int i=0;i<MainConsole.jobListings.size();i++)
		{
			if(MainConsole.jobListings.get(i).getJobCreator().getUsername().equalsIgnoreCase(MainConsole.user))
			{
				Job job = MainConsole.jobListings.get(i);
				ArrayList<Interview> interviewArrayList = job.getInterviews();
				for(int j=0;j<interviewArrayList.size();i++)
				{
					System.out.println(interviewArrayList.get(i).getStudent().getDetails());
				}
				System.out.println("Enter Student Username");
				String studentUsername = Utilities.getScanner().nextLine();
				//check if he exists
				//then!
				for(int j=0;j<interviewArrayList.size();i++)
				{
					if(interviewArrayList.get(j).getStudent().getUsername().equalsIgnoreCase(studentUsername))
					{
						System.out.println("Enter interview result for the student");
						String result = Utilities.getScanner().nextLine();
						interviewArrayList.get(j).setInterviewResult(result);

						System.out.println("Valid references? Y for YES, anything else for NO");
						String input = Utilities.getScanner().nextLine();
						if(input.equalsIgnoreCase("y"))
							interviewArrayList.get(j).setReferenceCheck(true);
						else
							interviewArrayList.get(j).setReferenceCheck(false);

						System.out.println("Sent the student an offer? Y for yes, anything else for no");
						input = Utilities.getScanner().nextLine();

						if(input.equalsIgnoreCase("y"))
						{
							Offer offer = new Offer(job);
							interviewArrayList.get(j).getStudent().addOffer(offer);
							interviewArrayList.get(j).getStudent().setStatus(ApplicantStatus.Pending);

						}
					}
				}
				break;
			}
		}
	}

	private void lodgeComplaint() {

		System.out.println("Enter the username of the User you want to complain about");

		String complaintUser = Utilities.getScanner().nextLine();

		System.out.println("Enter your complaint");

		String complaint = Utilities.getScanner().nextLine();

		try {
			if(MainConsole.userList.containsKey(complaintUser))
			{
				Complaint complaint1 = new Complaint(MainConsole.userList.get(MainConsole.user),complaint);
				if(MainConsole.userList.get(complaintUser) instanceof Student)
					((Student) MainConsole.userList.get(complaintUser)).addComplaint(complaint1);
				else if(MainConsole.userList.get(complaintUser) instanceof Employer)
					((Employer) MainConsole.userList.get(complaintUser)).addComplaint(complaint1);
				else
					throw new AuthorizationException("You are not authorised to complain against the Maintenance");
			}
			else
				throw new InvalidInputException("Such user does not exist");

		}
		catch (AuthorizationException e) {
			e.printStackTrace();
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}


	}

	public void changePassword() {

		System.out.println("New Password");

		String newPassword = Utilities.getScanner().nextLine();

		try {
			MainConsole.userList.get(MainConsole.user).setPassword(newPassword);
		} catch (InvalidInputException e) {
			System.err.println(e.getMessage());
		}

	}

	public boolean changeUsername(String newUsername) throws InvalidInputException{

		if (newUsername.equalsIgnoreCase(MainConsole.user))
			throw new InvalidInputException(" Your new username is the same as the old one");
		if(MainConsole.userList.containsKey(newUsername))
			throw new InvalidInputException(" This username is already taken");

		User a1 = new Employer((Employer) MainConsole.userList.get(MainConsole.user));

		MainConsole.userList.put(newUsername, a1);

		MainConsole.userList.remove(MainConsole.user);

		// MainConsole.user = newUsername; // If we want to continue from here

		depart = true;// to login again
		return true;
	}

	private void userEntry() {
		System.out.println("Enter 1 for Fulltime\n" + "Enter 2 for Part time\n" + "Enter 3 for Internship\n"
				+ "Enter your choice: ");

		int userChoice = Integer.parseInt(Utilities.getScanner().nextLine());
		if (userChoice >= 1 && userChoice <= 3)
			try {
				searchApplicantsbyAvailability(userChoice);
			} catch (Exception e) {
				e.printStackTrace();
			}
		else
		// throw invalid input
			System.out.println("Invalid input.");
	}

	private void jobPrefEntry() throws Exception
	{
		System.out.println("Select from below list:\n");
		ArrayList<JobCategory> jobCat = new ArrayList<>();
		for (int i = 0; i < jobCat.size(); i++){
			System.out.println("/n"+i);
		}
		System.out.println("Enter Job Preference of your choice: ");
		
		String prefChoice= Utilities.getScanner().nextLine();
		try {
			searchApplicantsbyJobPreference(prefChoice);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
					System.out.println(me.getValue().getDetails()+"\n\n");
					exists = true;
				}
					
				else if (i==2 && ((Student) me.getValue()).getAvailability() == Availability.PartTime)
				{
					System.out.println(me.getValue().getDetails()+"\n\n");
					exists = true;
				}
					
				else if (i==3 && ((Student) me.getValue()).getAvailability() == Availability.Internship)
				{
					System.out.println(me.getValue().getDetails()+"\n\n");
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
				ArrayList<JobCategory> jobCat = ((Student) me.getValue()).getSelectedJobCategories();
				

				for(int i=0;i<jobCat.size();i++)

					if (jobCat.get(i).getName().equalsIgnoreCase(str))
					{
						System.out.println(me.getValue().getDetails()+"\n\n");
						exists = true;
					}	
			}
		}
		if (!exists)
		throw new Exception("No student exists in this search criteria");

		return exists;
	}

	private void shortlistCandidate(String studentId) throws InvalidInputException
	{
		if(MainConsole.userList.containsKey(studentId) && MainConsole.userList.get(studentId) instanceof Student)
		{
			int i;
			for(i=0;i<MainConsole.jobListings.size();i++)
			{
				if(MainConsole.jobListings.get(i).getJobCreator().getUsername().equalsIgnoreCase(MainConsole.user))
				{
					try {
						//set time and add to notification
						MainConsole.jobListings.get(i).addtoShortlist((Student)MainConsole.userList.get(studentId), new DateTime());
						((Student) MainConsole.userList.get(studentId)).addNotification(new InterviewNotification(MainConsole.jobListings.get(i),new DateTime()));
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					break;
				}
			}
		}
		else
		{
			throw new InvalidInputException("No student with this username exists!");
		}


	}

	private void rankCandidates()
	{
		//assumption one employer just has one jobListing

		System.out.println(" The current Ranking is : \n");

		for (Job job: MainConsole.jobListings) {
			if(job.getJobCreator().getUsername().equalsIgnoreCase(MainConsole.user))
			{
				ArrayList<Student> students = job.getShortlist();
				for (int i=0;i<students.size();i++) {
					System.out.println((i+1)+". "+students.get(i).getDetails());// show the list of candidates
				}

				// ask them to give the ranking of the list Eg : 3 4 2 1 5
				System.out.println("Enter the way they have to be ranked. With spaces between ranks");
				String ranking = Utilities.getScanner().nextLine();
				try {
					job.rankCandidates(ranking);
				} catch (InvalidInputException e) {
					System.err.println(e.getMessage());
				}
				break;
			}

		}

	}

	private void setInterview() 
	{
			// TODO Auto-generated method stub
		
	}

	private void offerJob() 
	{
			// TODO Auto-generated method stub
		
	}


//FOR TESTING
	// public void addNewJob(Job job)
	// {
	// 	String id ="JOB";
	// 	String index = String.valueOf(MainConsole.jobListings.size());

	// 	for(int j=3;j>index.length();j--) // to add 0s in front of the idNumber
	// 		index+="0";
	// 	id += index;
	// 	String desc = "Sample";

	// 	job = new Job(id, ((Employer) MainConsole.userList.get(MainConsole.user)), desc);
	// 	MainConsole.jobListings.add(job);
	// }

	public void addNewJob()
	{
		System.out.println("Enter job description: ");
		String desc = Utilities.getScanner().nextLine();
		String id ="JOB";
		String index = String.valueOf(MainConsole.jobListings.size());

		for(int j=3;j>=index.length();j--) // to add 0s in front of the idNumber
			index="0"+index;
		id += index;

		Job job = new Job(id, ((Employer) MainConsole.userList.get(MainConsole.user)), desc);
		MainConsole.jobListings.add(job);

		System.out.println("Job "+id+" has been created");
	}

	/*private int jobInput()
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

	}*/

	/*private String createJobId()
	{
		int typeID = 0;
		int count = 0;
		int i;
		for (i=MainConsole.jobListings.size()-1;i>=0;i--) {
			count++;
		}
		typeID = count + 1;
		String jobID = "JOB" + String.format("%03d", typeID);
		return jobID;
	}*/

	/*private int validJobId(String jobListing)
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
	}*/

	/*private boolean showTheirJobListings()
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
	}*/

}
