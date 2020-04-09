
public class MaintenanceConsole {
	
	boolean depart=false;
	
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
					+ "6. Add new job Category\n"
					+ "7. Change Username\n" //Let this be for now
					+ "8. Change Password\n";
			
			//switch case
		}
		catch( Exception e)
		{
			System.err.println(e.getMessage());
		}
		// TODO Auto-generated method stub
		
	}

}
