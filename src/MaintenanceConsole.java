import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class MaintenanceConsole {

	boolean depart = false;
	Scanner scan = new Scanner(System.in);

	public void run() {

		do
		{
			manageMenu();
		}while(!depart);

		return;// TODO Auto-generated method stub

	}

	private void manageMenu() {

		try
		{
			String menu = "1. Access Student Records\n"
					+ "2. Access Employer Records\n"
					+ "3. View BlackList\n"
					+ "4. Blacklist User\n" // provisional or full
					+ "5. Remove User from Blacklist\n" 
					+ "6. Add new job Category\n";
			//+ "7. Change Username\n"
			//+ "8. Change Password\n";

			System.out.println(menu);

			int userChoice = Integer.parseInt(scan.nextLine());

			switch(userChoice)
			{
			case 1: 
				accessRecords(userChoice); 
				break;
			case 2:
				accessRecords(userChoice);
				break;
			case 3:
				accessRecords(userChoice);
				break;
			case 4:
				blackListUser();
				break;
			case 5:
				removeFromBlacklist();
				break;
			case 6:
				addJobCategory();
				break;
			case 7:
				break;
			}

			//switch case
		}
		catch( Exception e)
		{
			System.err.println(e.getMessage());
		}
		// TODO Auto-generated method stub

	}

	private void addJobCategory() {
		// TODO Auto-generated method stub

	}

	private void removeFromBlacklist() {
		// TODO Auto-generated method stub

	}

	private void blackListUser() {
		
		// check if that person has 3 or more cpmplaints
		// TODO Auto-generated method stub

	}

	private void accessRecords(int i) {
		
		for(Map.Entry<String,User> me : MainConsole.userList.entrySet()) 
		{
			if(i ==1 && me.getValue() instanceof Student)
				me.getValue().getDetails();
			else if(i==2 && me.getValue() instanceof Employee)
				me.getValue().getDetails();
			else if(i == 3 && me.getValue().getBlacklistStatus() != BlacklistStatus.NONE)
				me.getValue().getDetails();

		}

		// TODO Auto-generated method stub

	}

}
