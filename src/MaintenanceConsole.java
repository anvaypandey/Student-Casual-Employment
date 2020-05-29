import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class MaintenanceConsole implements Serializable {

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
					+ "4. Blacklist User\n" // full only
					+ "5. Remove User from Blacklist\n" 
					+ "6. Add new job Category\n"
					+ "7. Change Username\n"
					+ "8. Change Password\n"
					+ "9. Get a report\n"
					+ "10: Logout\n";

			System.out.println(menu);

			int userChoice = Integer.parseInt(Utilities.getScanner().nextLine());

			switch(userChoice)
			{
			case 1:
				System.out.println("\n\n *** Student list *** " + "\n\n");
				accessRecords(1);
				break;
			case 2:
				System.out.println("\n\n *** Employer list *** " + "\n\n");
				accessRecords(2);
				break;
			case 3:
				System.out.println("\n\n *** Provisional and Full Blacklist *** " + "\n\n");
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
				System.out.println(" Enter new Username");
				String newUsername = Utilities.getScanner().nextLine();
				changeUsername(newUsername);
				break;
			case 8:
				changePassword();
				break;
			case 9:
				Report report = new Report();
				report.run();
				break;
			case 10:
				System.out.println("You have successfully logged out!\n");
				depart = true;
				return;
			default:
				System.out.println("Invalid Choice. Please try again");
			}
		}
		catch( Exception e)
		{
			e.printStackTrace();
			//System.err.println(e.getMessage());
		}
	

	}

	private void addJobCategory() throws InvalidInputException{
		Boolean flag=false;
		String input;
		ArrayList<JobCategory> jobCategories = MainConsole.jobCategories;
		
		do{
			System.out.println("Enter job category or 'Q' to exit");
			input = Utilities.getScanner().nextLine();
			
			try {
				if(input.compareToIgnoreCase("q") == 0 ) {
					return;
				}
				for(int i=0; i < jobCategories.size(); i++) {
		
					if( jobCategories.get(i).getName().compareTo(input) == 0 ) {
						throw new InvalidInputException ( input + " already exists");
					}
					else if ( input.contentEquals(" ") || input.contentEquals("\n")) {
						throw new InvalidInputException ("There's no input");
					}
					else if ( input.matches("\\d+")) {
						throw new InvalidInputException ("Job category cannot be a number");
					}
					else {
						flag= true;
					}
				}
			}
			catch (InvalidInputException e) {
				System.err.println(e.getMessage());
			}
			
		}while(!flag);
		
		jobCategories.add(new JobCategory(input));
		System.out.println("Success! Job category " + input + " has been added");
	}

	public void removeFromBlacklist() throws InvalidInputException, AuthorizationException {
		Boolean flag =false;
		
		do {
			System.out.println("\n\n *** Provisional and Full Blacklist *** " + "\n\n");
			accessRecords(3);
			System.out.println("Enter user ID or 'Q' to exit");
			String input = Utilities.getScanner().nextLine();
			
			try {
				if(input.compareToIgnoreCase("q") == 0 ) {
					flag=true;
				}
				else if( !MainConsole.userList.containsKey(input)) {
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
			catch (AuthorizationException | InvalidInputException e){
				System.err.println(e.getMessage());	
			}
		}
		while(!flag);
			
	}

	

	public void blackListUser() throws InvalidInputException, AuthorizationException {
		boolean flag = false;
		
		
		do {
			System.out.println("\n\n *** Provisional Blacklist *** " + "\n\n");
			accessRecords(4);
			System.out.println("Enter user ID or 'Q' to exit");
			String userID = Utilities.getScanner().nextLine();
			
			try {
		
				if(userID.equalsIgnoreCase("q")) {
					flag = true;
				}
				else if( !MainConsole.userList.containsKey(userID)) {
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
							flag = true;
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
							flag=true;
						}
						else 
							return;
					}
			
				}
			}
		catch(InvalidInputException | AuthorizationException e) {
			System.err.println(e.getMessage());	
		
		
		}
		}while(!flag);
		

	}

	private void accessRecords(int i) {
		Boolean exist= false;
		
		for(Map.Entry<String,User> me : MainConsole.userList.entrySet()) 
		{
			if(i ==1 && me.getValue() instanceof Student) {
				System.out.println(me.getValue().getDetails()+"\n\n");
				exist=true;
			}
			else if(i==2 && me.getValue() instanceof Employer) {
				System.out.println(me.getValue().getDetails()+"\n\n");
				exist=true;
			}
			else if(i == 3) {
				if (me.getValue() instanceof Student && (((Student) me.getValue()).getBlacklistStatus()) != BlacklistStatus.NONE) {
					exist =true;
					System.out.println(me.getValue().getDetails()+"\n"+((Student)me.getValue()).getBlacklistStatus()+"\n\n");
				}
				if (me.getValue() instanceof Employer &&  (((Employer) me.getValue()).getBlacklistStatus()) != BlacklistStatus.NONE) {
					exist=true;
					System.out.println(me.getValue().getDetails()+"\n"+((Employer)me.getValue()).getBlacklistStatus()+"\n\n");
				}
				
			}	
			else if (i ==4) {
				if (me.getValue() instanceof Student && (((Student) me.getValue()).getBlacklistStatus()) == BlacklistStatus.PROVISIONAL) {
					exist=true;
					System.out.println(me.getValue().getDetails()+"\n"+((Student)me.getValue()).getBlacklistStatus()+"\n\n");
				}
				if(me.getValue() instanceof Employer && (((Employer) me.getValue()).getBlacklistStatus()) == BlacklistStatus.PROVISIONAL) {
					exist=true;
					System.out.println(me.getValue().getDetails()+"\n"+((Employer)me.getValue()).getBlacklistStatus()+"\n\n");
				}
				
			}

		}
		if (!exist) {
			System.out.println("\n\n List is empty");
		}
		

	}
	
	public boolean changeUsername(String newUsername) throws InvalidInputException{
		if (newUsername.equalsIgnoreCase(MainConsole.user))
			throw new InvalidInputException(" Your new username is the same as the old one");
		if(MainConsole.userList.containsKey(newUsername))
			throw new InvalidInputException(" This username is already taken");

		User a1 = new Maintenance((Maintenance) MainConsole.userList.get(MainConsole.user));

		MainConsole.userList.put(newUsername, a1);

		MainConsole.userList.remove(MainConsole.user);

		// MainConsole.user = newUsername; // If we want to continue from here

		depart = true;// to login again
		return true;
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
	



}
