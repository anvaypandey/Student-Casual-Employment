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
		System.out.println("Enter job category");
		// get input
		// search if input already exists
		//if exist -> invalid input exception -> show message 
		// if it doesnt -> " job cat has been successfully added "
		

	}

	private void removeFromBlacklist() {
		// TODO Auto-generated method stub
		accessRecords(3);
		System.out.println("Enter user ID");
		//check id matches id on blacklist, status == provisional/ full 
		// if provisional 
		// if yes -> message out put " userID + "has been removed from blacklist"
		
		//if full blacklist, check time if it has been more than 3 months 
		// if not more than 3 months -> out put (" not allowed ") AuthorizationException
		
		
		//else "userID + has been removed from blacklist 
		MainConsole.userList.get(userID).setBlackListStatus(BlacklistStatus.NONE);
		
		
		// if not -> 
	}

	private void blackListUser() {
		
		//accessRecords(4)
		// out - get input 
		//check if valid id 
		// check status provisional , if not , AuthorizationException 
		if(method(userID))
		// confirm message - > are you sure you want to blacklist + userID
		// if yes, blacklist MainConsole.userList.get(userID).SetBlackListStatus(BlacklistStatus.FUll);

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
			else if (i ==4 && me.getValue().getBlacklistStatus() == BlacklistStatus.PROVISIONAL) 
				me.getValue().getDetails();
			

		}

		// TODO Auto-generated method stub

	}
	
	//check if are authorized to move someone to a blacklist 
	
	private boolean method(String userId) throws AuthorizationException
	{
		if(MainConsole.userList.get(userId).getBlacklistStatus() == BlacklistStatus.PROVISIONAL)
			return true;
		else
			throw new AuthorizationException("You are not authori...");
	}
	
	// check

}
