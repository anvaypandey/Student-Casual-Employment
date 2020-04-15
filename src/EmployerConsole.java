import java.util.Scanner;

public class EmployerConsole {
	
	boolean depart = false;
	Scanner  scan = new Scanner(System.in);

	public void run() {
		do
		{
			managemenu();
		}while(!depart);
		// TODO Auto-generated method stub
		
	}

	private void managemenu() 
	{
		String menu = "Welcome Employee"; 
		// TODO Auto-generated method stub
		
	}
	
	private void changePassword() {
		
		System.out.println("New Password");
		
		String newPassword = scan .nextLine();
		
		MainConsole.userList.get(MainConsole.user).setPassword(newPassword);
		// TODO Auto-generated method stub
		
	}

	private void changeUsername() {
		
		String newUsername = "abc";
		
		User a1 = new Employer((Employer)MainConsole.userList.get(MainConsole.user));
		
		MainConsole.userList.put(newUsername, a1);
		
		MainConsole.userList.remove(MainConsole.user);
		
		//MainConsole.user = newUsername; // If we want to continue from here
		
		depart =true; //to login again
		
		// TODO Auto-generated method stub
		
	}

	private void searchApplicants() 
	{
			// TODO Auto-generated method stub
		
	}

	private void shortlistApplicants() 
	{
			// TODO Auto-generated method stub
		
	}

	private void rankApplicants() 
	{
			// TODO Auto-generated method stub
		
	}

	private void setInterview() 
	{
			// TODO Auto-generated method stub
		
	}

	private void offerJob() 
	{
			// TODO Auto-generated method stub
		
	}

}
