import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class EmployerConsole implements Serializable {

	boolean depart = false;


	public void run() {
		System.out.println("Welcome " + MainConsole.user);

		if (((Employer)MainConsole.userList.get(MainConsole.user)).getBlacklistStatus() == BlacklistStatus.FULL)
		{
			do {
				fullyBlacklistedMenu();

			} while (!depart);
		}

		else
		{
			do {
				manageMenu();

			} while (!depart);

		}

		return;
	}

	private void fullyBlacklistedMenu() {

		System.out.println("As you have been fully blacklisted,you can only access limited features");
		try
		{
			String menu = "1.Change password" +
					"2.Change Username" +
					"3.LogOut";
			System.out.println(menu);

			int userChoice = Integer.parseInt(Utilities.getScanner().nextLine());

			switch (userChoice) {
				case 1:
					System.out.println(" Enter the new Username");
					String newUsername = Utilities.getScanner().nextLine();
					changeUsername(newUsername);
					break;
				case 2:
					changePassword();
					break;
				case 3:
					System.out.println("You have successfully logged out!\n");
					depart = true;
					return;
				default:
					System.out.println("Invalid Choice. Please try again");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Menu option for Employer Console
	private void manageMenu()
	{
		try {
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
			e.printStackTrace();
		}

	}

	//Stores interview status, results and offers
	private void inputResultforInterviews() {
		boolean flag = false;
		do {

			try {

				if(((Employer)MainConsole.userList.get(MainConsole.user)).getBlacklistStatus()!= BlacklistStatus.NONE)
					throw new AuthorizationException("You are not Authorised to Use this feature");

				for(int i=0;i<MainConsole.jobListings.size();i++)
				{
					if(MainConsole.jobListings.get(i).getJobCreator().getUsername().equalsIgnoreCase(MainConsole.user))
					{
						Job job = MainConsole.jobListings.get(i);
						ArrayList<Interview> interviewArrayList = job.getInterviews();
						for(int j=0;j<interviewArrayList.size();j++)
						{
							System.out.println(interviewArrayList.get(j).giveEmployeeDetails()+"\n");
						}
						System.out.println("Enter Student Username");
						String studentUsername = Utilities.getScanner().nextLine();

						if(!(MainConsole.userList.containsKey(studentUsername)) || !(MainConsole.userList.get(studentUsername) instanceof Student))
							throw new InvalidInputException("Such Student doesn't exist");

						//check if he accepted the interview
						//check if he exists
						//then!
						for(int j=0;j<interviewArrayList.size();j++)
						{
							if(interviewArrayList.get(j).getStudent().getUsername().equalsIgnoreCase(studentUsername))
							{
								if(interviewArrayList.get(j).isInterviewAccepted())
								{
									flag = true;
									System.out.println("Enter interview result for the student");
									String result = Utilities.getScanner().nextLine();
									interviewArrayList.get(j).setInterviewResult(result);

									System.out.println("Valid references? Y for YES, anything else for NO");
									String input = Utilities.getScanner().nextLine();
									if(input.equalsIgnoreCase("y"))
										interviewArrayList.get(j).setReferenceCheck(true);
									else
										interviewArrayList.get(j).setReferenceCheck(false);

									System.out.println("Send the student an offer? Y for yes, anything else for no");
									input = Utilities.getScanner().nextLine();

									if(input.equalsIgnoreCase("y"))
									{
										Offer offer = new Offer(job,interviewArrayList.get(j).getStudent());
										interviewArrayList.get(j).getStudent().addOffer(offer);
										interviewArrayList.get(j).getStudent().setStatus(ApplicantStatus.Pending);
										System.out.println("Offer sent to candidate "+studentUsername);

									}

								}
								else
									throw new InvalidInputException("The Student hasn't accepted the interview request");
								break;
							}
						}
					break;
					}
				}

			}
			catch (InvalidInputException e)
			{
				System.err.println(e.getMessage());

				System.out.println("Enter Q to quit or anything else to try again");
				String input = Utilities.getScanner().nextLine();

				if(input.equalsIgnoreCase("q"))
					flag=true;
			}
			catch (AuthorizationException e)
			{
				System.err.println(e.getMessage());
				flag=true;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}


		}while(!flag);
	}

	//Lodging complaint against student/applicant
	private void lodgeComplaint() {
		boolean flag = false;
		do {
			try {
				if(((Employer)MainConsole.userList.get(MainConsole.user)).getBlacklistStatus()!= BlacklistStatus.NONE)
					throw new AuthorizationException("You are not Authorised to Use this feature");

				System.out.println("Enter the username of the User you want to complain about");

				String complaintUser = Utilities.getScanner().nextLine();

				System.out.println("Enter your complaint");

				String complaint = Utilities.getScanner().nextLine();


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

				flag=true;

			}
			catch (AuthorizationException e) {
				System.err.println(e.getMessage());
				System.out.println("Enter Q to quit or anything else to try again");
				String input = Utilities.getScanner().nextLine();

				if(input.equalsIgnoreCase("q"))
					flag=true;
			}
			catch (InvalidInputException e) {
				System.err.println(e.getMessage());
				System.out.println("Enter Q to quit or anything else to try again");
				String input = Utilities.getScanner().nextLine();

				if(input.equalsIgnoreCase("q"))
					flag=true;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}while (!flag);
	}

	//Changing employer password
	public void changePassword() {
		boolean flag = false;
		do {
			try{
				System.out.println("New Password");

				String newPassword = Utilities.getScanner().nextLine();


				MainConsole.userList.get(MainConsole.user).setPassword(newPassword);
				flag=true;
			}
			catch (InvalidInputException e) {
				System.err.println(e.getMessage());
				System.out.println("Enter Q to quit or anything else to try again");
				String input = Utilities.getScanner().nextLine();

				if(input.equalsIgnoreCase("q"))
					flag=true;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}while(!flag);
	}

	//Changing employer username
	public boolean changeUsername(String newUsername){
		boolean flag = false;
		do {
			try {
				if (newUsername.equalsIgnoreCase(MainConsole.user))
					throw new InvalidInputException(" Your new username is the same as the old one");
				if(MainConsole.userList.containsKey(newUsername))
					throw new InvalidInputException(" This username is already taken");

				User a1 = new Employer((Employer) MainConsole.userList.get(MainConsole.user));

				MainConsole.userList.put(newUsername, a1);

				MainConsole.userList.remove(MainConsole.user);


				// MainConsole.user = newUsername; // If we want to continue from here

				depart = true;// to login again

				flag=true;
			}
			catch (InvalidInputException e){

				System.err.println(e.getMessage());
				System.out.println("Enter Q to quit or anything else to try again");
				String input = Utilities.getScanner().nextLine();

				if(input.equalsIgnoreCase("q"))
					flag=true;

			}
			catch(Exception e){
				e.printStackTrace();
			}
		}while(!flag);

		return true;
	}

	//gets user input for availability and calls  searchApplicantByAvailability()
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

	//gets user input for job preference and calls searchApplicantByJobPreference()
	private void jobPrefEntry() throws Exception
	{
		System.out.println("Select from below list:\n");
		for (int i = 0; i < MainConsole.jobCategories.size(); i++){
			System.out.println((i+1)+". "+MainConsole.jobCategories.get(i).getName());
		}
		System.out.println("Enter Job Category of your choice: ");

		String prefChoice= Utilities.getScanner().nextLine();
		try {
			searchApplicantsbyJobPreference(prefChoice);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	//searching applicants based on availability
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

	//searching applicants based on job preference
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

	//shortlisting candidates and setting up interview time
	private void shortlistCandidate(String studentId) throws InvalidInputException
	{
		boolean flag = false;
		do{
			try {
				if(((Employer)MainConsole.userList.get(MainConsole.user)).getBlacklistStatus()!= BlacklistStatus.NONE)
					throw new AuthorizationException("You are not Authorised to Use this feature");
				if(MainConsole.userList.containsKey(studentId) && MainConsole.userList.get(studentId) instanceof Student)
				{
					int i;
					for(i=0;i<MainConsole.jobListings.size();i++)
					{
						if(MainConsole.jobListings.get(i).getJobCreator().getUsername().equalsIgnoreCase(MainConsole.user))
						{
							//set time and add to notification
							MainConsole.jobListings.get(i).addtoShortlist((Student)MainConsole.userList.get(studentId), new DateTime());
							System.out.println("Candidate "+studentId+" has been successfully shortlisted\n");
							flag=true;
							break;
						}
					}
				}
				else
				{
					throw new InvalidInputException("No student with this username exists!");
				}

			}
			catch (InvalidInputException e){
				System.err.println(e.getMessage());
				System.out.println("Enter Q to quit or anything else to try again");
				String input = Utilities.getScanner().nextLine();

				if(input.equalsIgnoreCase("q"))
					flag=true;
			}
			catch (AuthorizationException e)
			{
				System.err.println(e.getMessage());
				flag=true;
			}
			catch (Exception e)
			{
				System.err.println(e.getMessage());
			}

		}while(!flag);
	}

	//ranking applicants based on employer preference
	private void rankCandidates()
	{
		//assumption one employer just has one jobListing
		boolean flag = false;
		do {
			try {
				System.out.println(" The current Ranking is : \n");

				for (Job job: MainConsole.jobListings) {
					if(job.getJobCreator().getUsername().equalsIgnoreCase(MainConsole.user))
					{
						ArrayList<Student> students = job.getShortlist();
						for (int i=0;i<students.size();i++) {
							System.out.println((i+1)+". "+students.get(i).getDetails()+"\n");// show the list of candidates
						}

						if(((Employer)MainConsole.userList.get(MainConsole.user)).getBlacklistStatus() == BlacklistStatus.PROVISIONAL)
							throw new AuthorizationException("You are not Authorised to Use this feature");

						// ask them to give the ranking of the list
						System.out.println("Enter the way they have to be ranked. One by one.");
						ArrayList<Integer> rank = new ArrayList<>();
						for(int i=0;i<students.size();i++)
						{
							rank.add(Integer.parseInt(Utilities.getScanner().nextLine()));

						}
						//String ranking = Utilities.getScanner().nextLine();
						job.rankCandidates(rank);

						System.out.println("The revised ranking of the candidates is:");

						ArrayList<Student> newRanks = job.getShortlist();
						for (int i=0;i<newRanks.size();i++) {
							System.out.println((i+1)+". "+newRanks.get(i).getDetails()+"\n");// show the list of candidates
						}
						flag = true;
						break;
					}

				}

			}
			catch (InvalidInputException e)
			{
				System.err.println(e.getMessage());
				System.out.println("Enter Q to quit or anything else to try again");
				String input = Utilities.getScanner().nextLine();

				if(input.equalsIgnoreCase("q"))
					flag=true;
			}
			catch (AuthorizationException e)
			{
				System.err.println(e.getMessage());
				flag=true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}while(!flag);
	}

	//adding new job listing
	public void addNewJob() {

		boolean flag = false;

		do{
			try {

				if(((Employer)MainConsole.userList.get(MainConsole.user)).getBlacklistStatus()!= BlacklistStatus.NONE)
				{
					throw new AuthorizationException("You are not Authorised to Use this feature");
				}
				int x;
				for(x=0;x<MainConsole.jobListings.size();x++){
					if(MainConsole.jobListings.get(x).getJobCreator().getUsername().equalsIgnoreCase(MainConsole.user));
					break;
				}

				if(x<MainConsole.jobListings.size())
					throw new AuthorizationException("You already have a job Listing. You cannot create another one.");

				System.out.println("Enter Job Category for the new Job from the following list");
				for (int i=0;i<MainConsole.jobCategories.size();i++)
				{
					System.out.println((i+1)+":"+MainConsole.jobCategories.get(i).getName());
				}

				System.out.println("Enter the Job Category:");

				String jobcategory = Utilities.getScanner().nextLine();

				int i;
				for(i=0;i<MainConsole.jobCategories.size();i++)
				{
					if(MainConsole.jobCategories.get(i).getName().equalsIgnoreCase(jobcategory))
						break;
				}
				if(i==MainConsole.jobCategories.size())
					throw new InvalidInputException("Such Job Category Doesn't Exist");

				JobCategory jobCat = new JobCategory(jobcategory);

				System.out.println("Enter job description: ");
				String desc = Utilities.getScanner().nextLine();

				String id ="JOB";
				String index = String.valueOf(MainConsole.jobListings.size());

				//job category

				for(int j=3;j>=index.length();j--) // to add 0s in front of the idNumber
					index="0"+index;
				id += index;

				//Employer is only allowed to have one job post

				Job job = new Job(id, ((Employer) MainConsole.userList.get(MainConsole.user)), desc,jobCat);
				MainConsole.jobListings.add(job);

				flag = true;

				System.out.println("Job "+id+" has been created");
			}
			catch (InvalidInputException e)
			{
				System.err.println(e.getMessage());
				System.out.println("Enter Q to quit or anything else to try again");
				String input = Utilities.getScanner().nextLine();

				if(input.equalsIgnoreCase("q"))
					flag=true;
			}
			catch (AuthorizationException e){
				System.err.println(e.getMessage());
				flag=true;
			}
			catch(Exception e){
				e.printStackTrace();
			}


		}while(!flag);
	}


}
