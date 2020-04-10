import java.util.Scanner;

public class StudentConsole {

	boolean depart = false;

	Scanner scan = new Scanner(System.in);

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
					+ "8. Lodge Complaint\n"
					+ "9. Change Username\n" //Let this be for now
					+ "10. Change Password\n"
					+ "11. Logout"
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
				lodgeComplaint();
				break;
			case 9:
				changeUsername();
				break;
			case 10:
				changePassword();
				break;
			case 11:
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


	private void changePassword() {
		
		System.out.println("New Password");
		
		String newPassword = scan.nextLine();
		
		MainConsole.userList.get(MainConsole.user).setPassword(newPassword);
		// TODO Auto-generated method stub
		
	}

	private void changeUsername() {
		
		String newUsername = "abc";
		
		User a1 = new Student ((Student)MainConsole.userList.get(MainConsole.user));
		
		MainConsole.userList.put(newUsername, a1);
		
		//MainConsole.userList.remove(MainConsole.user);
		
		//MainConsole.user = newUsername; // If we want to continue from here
		
		depart =true; //to login again
		
		// TODO Auto-generated method stub
		
	}

	private void lodgeComplaint() {
		// TODO Auto-generated method stub
		
	}

	private void uploadCV() {
		// TODO Auto-generated method stub
		
	}

	private void addReference() {
		// TODO Auto-generated method stub
		
	}

	private void updateRecords() {
		
		/*
		 * Student abc= (Student)MainConsole.userList.get(MainConsole.user);
		 * 
		 * String employmentRecord = null; abc.setEmploymentRecords(employmentRecord);
		 */
		// TODO Auto-generated method stub
	}

	private void addRecord() {
		// TODO Auto-generated method stub

	}

	private void updateStatus() {
		// TODO Auto-generated method stub

	}

	private void updateAvailability() {
		// TODO Auto-generated method stub

	}


	// auto update to unknown after 2 weeks
	//

}
