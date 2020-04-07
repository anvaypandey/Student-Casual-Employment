import java.util.HashMap;
import java.util.Scanner;

public class MainConsole {
	
	HashMap<String, User> userList = new HashMap<String, User>();
	
	
	
	String user;
	
	Scanner scan = new Scanner(System.in);
	
	public void run() 
	{
		while (true)
		{
			login();
			if(userList.get(user) instanceof Student)
			{
				StudentConsole stdConsole = new StudentConsole();
				stdConsole.run();
				
			}
			else if(userList.get(user) instanceof Employee)
			{
				EmployeeConsole empConsole = new EmployeeConsole();
				empConsole.run();
			}
			else
			{
				MaintenanceConsole maintainConsole = new MaintenanceConsole();
				maintainConsole.run();
			}
		}
		
		//if student show student menu, 
		
		//student 
		
	}
	
	private void login()
	{
		boolean flag = false;
		
		System.out.println("Welcome To the Casual Employment System.\n");
		
		do
		{
			try
			{
				System.out.println("Please Select an option\n"
						+ "1. Login\n"
						+ "2. Register\n"
						+ "3. Quit");// ask if they want to register, login or quit
				
				int choice = Integer.parseInt(scan.nextLine());
				
				switch(choice)
				{
				case 1:
					boolean validUser = false;
					System.out.println("Enter credentials\n");//if login ask for credentials
					do
					{
						System.out.println("\nUsername:");
						user = scan.nextLine();
						
						System.out.println("Password:");
						String password = scan.nextLine();
						
						/*
						 * Console c = System.console(); char[] passwordArray =
						 * c.readPassword("Password: ");
						 * 
						 * String password = new String(passwordArray);
						 */
						try
						{
							if(userList.containsKey(user) && userList.get(user).validatePassword(password))//verify if the username exists, if yes use validatePassword() for password
							{
								validUser = true;
								flag =true;
							}
							else
							{
								System.err.println("Such User doesn't exist.");
								System.out.println("\nEnter Q to quit or anything else to try again");
								String input = scan.nextLine();
								if(input.equalsIgnoreCase("Q"))
									validUser = true;
							}
								
						}
						catch(InvalidCredentialsException e)
						{
							System.err.println(e.getMessage());
						}
						
					}while(!validUser);
					
					
					//if true then return or else show Invalid Credential Exception
					break;
				case 2:
					register();
					//if they want to register
					break;
				case 3:
					System.out.println("Thank you for using the Casual Employment System.");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid Choice. Please Try again");	
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.err.println("Please Try Again");
			}
			/*
			 * finally { System.out.print("Abc"); }
			 */
		}while(!flag);
			
		
	}
	
	private void register()// check if they already have been registered or not then register
	{
		boolean flag=false;
		do
		{
			try
			{
				System.out.println("Which type of user are you?\n"
						+ "1. Student"
						+ "2. Employee");
				int choice = Integer.parseInt(scan.nextLine());
				
				if(choice != 1 && choice != 2)
				{
					System.out.println("Invalid Choice. Please try again.");
				}
				else
				{
					System.out.println("Enter Username: ");
					String username = scan.nextLine();
					
					if(!userList.containsKey(username))
					{
						System.out.println("Enter Password: ");
						String password = scan.nextLine();
						
						System.out.println("Enter email address");
						String email = scan.nextLine();
						
						if(choice ==1)
							userList.put(username, new Student(username,password,email));
						else
							userList.put(username, new Employee(username, password,email));
						flag = true;
					}
					else
						System.out.println("This username already exists! Please try another username");
						
				}
			}
			catch(Exception e)
			{
				System.err.println(e.getMessage());
			}
			
		}while(!flag);
		
	}
	
	

}
