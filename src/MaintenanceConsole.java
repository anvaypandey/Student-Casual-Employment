import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class MaintenanceConsole {

	boolean depart = false;
	

	public void run() {

		do
		{
			manageMenu();
		}while(!depart);

		return;

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

			int userChoice = Integer.parseInt(Utilities.getScanner().nextLine());

			switch(userChoice)
			{
			case 1:
				accessRecords(1);
				break;
			case 2:
				accessRecords(2);
				break;
			case 3:
				accessRecords(3); 
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
			case 8:
				break;
			}

		}
		catch( Exception e)
		{
			System.err.println(e.getMessage());
		}
	

	}
	
	private void addJobCategory() throws InvalidInputException{

		ArrayList<String> JobCategories = MainConsole.jobCategories;

		System.out.println("Enter job category");
		String input = Utilities.getScanner().nextLine();

		for(int i=0; i < JobCategories.size(); i++) {

			if( JobCategories.get(i).compareTo(input) == 0) {
				throw new InvalidInputException ( input + "already exisits");
			}
			else if ( input.contentEquals(" ") || input.contentEquals("\n")) {
				throw new InvalidInputException ("There's no input");
			}
			else if ( input.matches("\\d+")) {
				throw new InvalidInputException ("Job category cannot be a number");
			}
			else
				System.out.println("Success ! Job category" + input + "has been added");

		}


	}

	public void removeFromBlacklist() throws InvalidInputException, AuthorizationException {

		accessRecords(3);
		System.out.println("Enter user ID or 'Y' to exit");
		String input = Utilities.getScanner().nextLine();
		
		if(input.compareToIgnoreCase("Y") == 0 ) {
			return;
		}
		if( !MainConsole.userList.containsKey(input)) {
			throw new InvalidInputException (input + " does not exist");
		}
		else if(MainConsole.userList.get(input) instanceof Student ) {
				
				if (((Student) MainConsole.userList.get(input)).getBlacklistStatus() == BlacklistStatus.PROVISIONAL) {
					
					((Student)MainConsole.userList.get(input)).setBlacklistStatus(BlacklistStatus.NONE);
					System.out.println(input + " has been removed from  provisional Blacklist");
				}
				else if (((Student) MainConsole.userList.get(input)).getBlacklistStatus() == BlacklistStatus.FULL) {
					
					DateTime d2 = new DateTime();
					DateTime d1 = ((Student) MainConsole.userList.get(input)).getBlacklistTime();
					int dateDiff = DateTime.diffDays(d2,d1);
				
				if(dateDiff >= 90) {
					((Student) MainConsole.userList.get(input)).setBlacklistStatus(BlacklistStatus.NONE);
					System.out.println(input + " has been removed from Blacklist");
				}
				else 
					throw new AuthorizationException ("Action denied. User has been on the Blacklist for less than 3 months");
				}
		}
		else if(MainConsole.userList.get(input) instanceof Employer ) {
			
			if (((Employer) MainConsole.userList.get(input)).getBlacklistStatus() == BlacklistStatus.PROVISIONAL) {
				
				((Employer)MainConsole.userList.get(input)).setBlacklistStatus(BlacklistStatus.NONE);
				System.out.println(input + " has been removed from  provisional Blacklist");
			}
			else if (((Employer) MainConsole.userList.get(input)).getBlacklistStatus() == BlacklistStatus.FULL) {
				
				DateTime d2 = new DateTime();
				DateTime d1 = ((Employer) MainConsole.userList.get(input)).getBlacklistTime();
				int dateDiff = DateTime.diffDays(d2,d1);
			
			if(dateDiff >= 90) {
				((Employer) MainConsole.userList.get(input)).setBlacklistStatus(BlacklistStatus.NONE);
				System.out.println(input + " has been removed from Blacklist");
			}
			else 
				throw new AuthorizationException ("Action denied. User has been on the Blacklist for less than 3 months");
			}
		}
		else 
			throw new InvalidInputException( input + " is not on blacklist");	
		
	}

	

	public void blackListUser() throws InvalidInputException, AuthorizationException {
		accessRecords(4);
		System.out.println("Enter user ID ");
		String userID = Utilities.getScanner().nextLine();
		
		if( !MainConsole.userList.containsKey(userID)) {
			throw new InvalidInputException (userID + " does not exist");
		}
		else if (MainConsole.userList.get(userID) instanceof Student) {
			if (((Student) MainConsole.userList.get(userID)).getBlacklistStatus() != BlacklistStatus.PROVISIONAL) {
				throw new InvalidInputException( "only user ID on the provisional blacklist can be added to the full blacklist" );
			}
			else if (((Student) MainConsole.userList.get(userID)).getBlacklistStatus() == BlacklistStatus.FULL) {
				throw new AuthorizationException (userID + " is already on the Blacklist" );
			}
			else {
				System.out.println(" are you sure you want to blacklist " + userID + "? 'Y' for yes 'N' for no");
				 char input = Utilities.getScanner().nextLine().charAt(0);
				
				if (input == 'Y') {
					((Student) MainConsole.userList.get(userID)).setBlacklistStatus(BlacklistStatus.FULL);
					System.out.println( userID + " has been blacklisted");
				}
				else 
					return;
			}
	
		}
		else if (MainConsole.userList.get(userID) instanceof Employer) {
			if (((Employer) MainConsole.userList.get(userID)).getBlacklistStatus() != BlacklistStatus.PROVISIONAL) {
				throw new InvalidInputException( "only user ID on the provisional blacklist can be added to the full blacklist" );
			}
			else if (((Employer) MainConsole.userList.get(userID)).getBlacklistStatus() == BlacklistStatus.FULL) {
				throw new AuthorizationException (userID + " is already on the Blacklist" );
			}
			else {
				System.out.println(" are you sure you want to blacklist " + userID + "? 'Y' for yes 'N' for no");
				 char input = Utilities.getScanner().nextLine().charAt(0);
				
				if (input == 'Y') {
					((Employer) MainConsole.userList.get(userID)).setBlacklistStatus(BlacklistStatus.FULL);
					System.out.println( userID + " has been blacklisted");
				}
				else 
					return;
			}
	
		}
		

	}

	private static void accessRecords(int i) {
		
		for(Map.Entry<String,User> me : MainConsole.userList.entrySet()) 
		{
			if(i ==1 && me.getValue() instanceof Student)
				me.getValue().getDetails();
			else if(i==2 && me.getValue() instanceof Employer)
				me.getValue().getDetails();
			else if(i == 3) {
				if ( me.getValue() instanceof Student && (((Student) me.getValue()).getBlacklistStatus()) != BlacklistStatus.NONE) {
					me.getValue().getDetails();
				}
				if (me.getValue() instanceof Employer &&  (((Employer) me.getValue()).getBlacklistStatus()) != BlacklistStatus.NONE) {
					me.getValue().getDetails();				
				}
			}	
			else if (i ==4) {
				if (me.getValue() instanceof Student && (((Student) me.getValue()).getBlacklistStatus()) == BlacklistStatus.PROVISIONAL) {
					me.getValue().getDetails();
				}
				if(me.getValue() instanceof Employer && (((Employer) me.getValue()).getBlacklistStatus()) == BlacklistStatus.PROVISIONAL) {
					me.getValue().getDetails();
				}
			}

		}

	}



}
