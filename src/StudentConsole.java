import java.util.ArrayList;
import java.util.Scanner;

public class StudentConsole {

	boolean depart = false;

	
	Student std = (Student)MainConsole.userList.get(MainConsole.user);



	public void run()
	{
		System.out.println("Welcome "+ MainConsole.user);
		do
		{
			manageMenu();

		}while(!depart);

		return;
	}

	private void manageMenu() 
	{
		try
		{

			String menu = "1. Update your Availability\n"
					+ "2. Update your Status\n"
					+ "3. Add Records\n"
					+ "4. Update Records\n"
					+ "5. Add Refernce\n"
					+ "6. Update Reference\n"
					+ "7. Upload CV\n" // for later
					+ "8. Add new Job Category to your list\n"
					+ "9. Lodge Complaint\n"
					+ "10. Change Username\n" //Let this be for now
					+ "11. Change Password\n"
					+ "12. Logout\n"
					+ "Enter your choice: ";
			System.out.println(menu);

			int userChoice = Integer.parseInt(Utilities.getScanner().nextLine());

			switch(userChoice)
			{
			case 1: 
				updateAvailability(); 
				break;

			case 2:
				updateStatus();
				break;
			case 3:
				addRecord();
				break;
			case 4:
				updateRecords();
				break;
			case 5:
				addReference();
				break;
			case 6:
				addReference();
				break;
			case 7:
				uploadCV();
				break;
			case 8:
				chooseJobCategory();
				break;
			case 9:
				lodgeComplaint();
				break;
			case 10:
				changeUsername();
				break;
			case 11:
				changePassword();
				break;
			case 12:
				System.out.println("You have successfully logged out!\n");
				depart = true;
				return;
			default:
				System.out.println("Invalid Choice. Please try again");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		//switch case
	}


	public void chooseJobCategory() {

			for(int i=0;i<MainConsole.jobCategories.size();i++)
			{
				
					System.out.println(MainConsole.jobCategories+ "\n");

			
			}
		boolean exists = false;
		do
		{
		System.out.println("Choose desired job category");
		String jobcat = Utilities.getScanner().nextLine();
		
		
		try
		{
			
			exists = jobCatego(jobcat);
		} catch (InvalidInputException e)
		{
			
			e.printStackTrace();
		}
		if(!exists)
			System.out.print("Q to quit or try again");
		String resp = Utilities.getScanner().nextLine();
		if(resp.equalsIgnoreCase("q"))
		{
			exists=true;
		}	
		}while(true);
	}

	public boolean jobCatego(String jobcat) throws InvalidInputException
	{
//		jobcat = "cafe";
		int i;
		for(i=0;i<MainConsole.jobCategories.size();i++)
		{
			if(jobcat.equalsIgnoreCase(MainConsole.jobCategories.get(i)))
			{
			MainConsole.jobCategories.add(jobcat);
				//std.addJobCategory(jobcat);
				break;	
			}
				
		}
		if(i==MainConsole.jobCategories.size())
		{
			throw new InvalidInputException("Invorrenct blah blah");
		}
		return true;
	}

	private void changePassword()  {
		
		System.out.println("New Password");
		
		String newPassword = Utilities.getScanner().nextLine();

		try {
			MainConsole.userList.get(MainConsole.user).setPassword(newPassword);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

	}

	public void changeUsername() {
		
		String newUsername = "abc";
		
		User a1 = new Student ((Student)MainConsole.userList.get(MainConsole.user));
		
		MainConsole.userList.put(newUsername, a1);
		MainConsole.userList.remove(MainConsole.user);
		
		
		//MainConsole.user = newUsername; // If we want to continue from here
		
		depart =true; //to login again
		
		
		
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



		


	private void uploadCV() {
		
		//UPLOAD CV FILE
		
	}

	public void addReference() {
		System.out.println("Enter the name of your reference");

		String nameRef = Utilities.getScanner().nextLine();

		System.out.println("Enter the email of your reference");

		String emailRef = Utilities.getScanner().nextLine();
		System.out.println("Enter the phone of your reference");

		String phoneRef = Utilities.getScanner().nextLine();


				Reference ref1 = new Reference(nameRef,emailRef, phoneRef);
				std.setReferences(ref1);

		}


	private void updateRecords() {
		
		System.out.println(std.getEmploymentRecords());
	
	}

	private void addRecord() {
		
		System.out.println("Please enter previous experience, qualifactions and certifications");
		String cv = Utilities.getScanner().nextLine();
		std.setLocationCV(cv);
		System.out.println("CV added!");
	}

	private void updateStatus() {
		
		System.out.println("Please enter desired status update Available(A)/Pending(P)/Unknown(U)/Employed(E)");
		String response = Utilities.getScanner().nextLine();
		if(response == "A")
		{
			((Student)MainConsole.userList.get(MainConsole.user)).setStatus(ApplicantStatus.Available);
			System.out.println("Status successfully updated");
		}
		else if(response == "P")
		{
			((Student)MainConsole.userList.get(MainConsole.user)).setStatus(ApplicantStatus.Pending);
			System.out.println("Status successfully updated");
		}
		else if(response == "U")
		{
			((Student)MainConsole.userList.get(MainConsole.user)).setStatus(ApplicantStatus.Unknown);
			System.out.println("Status successfully updated");
		}
		else if(response == "E")
		{
			((Student)MainConsole.userList.get(MainConsole.user)).setStatus(ApplicantStatus.Employed);
			System.out.println("Status successfully updated");
		}
	}

	public void updateAvailability() {
		
		
		if(((Student)MainConsole.userList.get(MainConsole.user)).getAvailability()==Availability.PartTime)
		{
			Availability availability = Availability.FullTime;
			((Student)MainConsole.userList.get(MainConsole.user)).setAvailability(availability);
		}
		else if((((Student)MainConsole.userList.get(MainConsole.user)).getAvailability()==Availability.FullTime))
		{
		Availability availability2 = Availability.PartTime;
		((Student)MainConsole.userList.get(MainConsole.user)).setAvailability(availability2);
		}
		else
			System.out.println("Process could not be completed");

	}


	// auto update to unknown after 2 weeks
	//

}