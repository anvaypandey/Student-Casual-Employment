import java.util.ArrayList;
import java.util.Scanner;

public class StudentConsole {

	boolean depart = false;

	Scanner scan = new Scanner(System.in);
	
	Student std = (Student)MainConsole.userList.get(MainConsole.user);
	
	static ArrayList<String> CV = new ArrayList<String>();
	static ArrayList<String> Reference = new ArrayList<String>();

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

			int userChoice = Integer.parseInt(scan.nextLine());

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
		String jobcat = scan.nextLine();
		
		
		try
		{
			
			exists = jobCatego(jobcat);
		} catch (InvalidInputException e)
		{
			
			e.printStackTrace();
		}
		if(!exists)
			System.out.print("Q to quit or try again");
		String resp = scan.nextLine();
		if(resp.equalsIgnoreCase("q"))
		{
			exists=true;
		}	
		}while(true);
	}

	public boolean jobCatego(String jobcat) throws InvalidInputException
	{
		jobcat = "cafe";
		int i;
		for(i=0;i<MainConsole.jobCategories.size();i++)
		{
			if(jobcat.equalsIgnoreCase(MainConsole.jobCategories.get(i)))
			{
				std.addJobCategory(jobcat);
				break;	
			}
				
		}
		if(i==MainConsole.jobCategories.size())
		{
			throw new InvalidInputException("Invorrenct blah blah");
		}
		return true;
	}

	private void changePassword() {
		
		System.out.println("New Password");
		
		String newPassword = scan.nextLine();
		
		MainConsole.userList.get(MainConsole.user).setPassword(newPassword);
		// TODO Auto-generated method stub
		
	}

	public void changeUsername() {
		
		String newUsername = "abc";
		
		User a1 = new Student ((Student)MainConsole.userList.get(MainConsole.user));
		
		MainConsole.userList.put(newUsername, a1);
		MainConsole.userList.remove(MainConsole.user);
		
		
		//MainConsole.user = newUsername; // If we want to continue from here
		
		depart =true; //to login again
		
		// TODO Auto-generated method stub
		
	}

	private void lodgeComplaint() {
		
		String complaint = null;
		
		//Student std = (Student)MainConsole.userList.get(MainConsole.user);
		
		std.addComplaint(complaint);
		// TODO Auto-generated method stub
		
	}

	private void uploadCV() {
		
		
		System.out.println("Please enter previous experience, qualifactions and certifications");
		String cv = scan.nextLine();
		std.setLocationCV(cv);
	}

	public void addReference() {
//		System.out.println("Please enter your reference information");
//		String reference = scan.nextLine();
		String reference ="Michael Smith";
		std.setReferences(reference);
		System.out.println(reference);
;		
	}

	private void updateRecords() {
		
		System.out.println(std.getEmploymentRecords());
		
		
		
		
		
	}

	private void addRecord() {
		System.out.println("Please enter previous experience, qualifactions and certifications");
		String cv = scan.nextLine();
		std.setEmploymentRecords(cv);
	}

	private void updateStatus() {
		
		//update the lastUpdateDate
		// TODO Auto-generated method stub

	}

	public void updateAvailability() {
		
		// check if the student is intntl or not
		//give options of type of availability
		Availability availability = Availability.PartTime;
		((Student)MainConsole.userList.get(MainConsole.user)).setAvailability(availability);
		// TODO Auto-generated method stub

	}


	// auto update to unknown after 2 weeks
	//

}