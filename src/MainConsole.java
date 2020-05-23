import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MainConsole {
	
	static HashMap<String, User> userList = new HashMap<>();
	
	static ArrayList<Job>  jobListings = new ArrayList<Job>();
	
	static ArrayList<JobCategory> jobCategories = new ArrayList<JobCategory>();
	
	
	static String user;

	
	public void run() 
	{
		userList.put("admin", new Maintenance("admin","admin","admin@gmail.com"));
		userList.put("s123", new Student("s123","s123","s123@gmail.com",Availability.FullTime));
		jobCategories.add(new JobCategory("waiter"));
		jobCategories.add( new JobCategory("maid"));
		
		while (true)
		{
			login();

			if(userList.get(user) instanceof Student)
			{
				StudentConsole stdConsole = new StudentConsole();
				stdConsole.run();
				
			}
			else if(userList.get(user) instanceof Employer)
			{
				EmployerConsole empConsole = new EmployerConsole();
				empConsole.run();
			}
			else
			{
				MaintenanceConsole maintainConsole = new MaintenanceConsole();
				maintainConsole.run();
			}
		}

		
	}
	
	public void login()
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
				
				int choice = Integer.parseInt(Utilities.getScanner().nextLine());
				
				switch(choice)
				{
				case 1:
					boolean validUser = false;
					System.out.println("Enter credentials\n");//if login ask for credentials
					do
					{
						System.out.println("\nUsername:");
						user = Utilities.getScanner().nextLine();
						
						System.out.println("Password:");
						String password = Utilities.getScanner().nextLine();
						
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
								String input = Utilities.getScanner().nextLine();
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
						+ "1. Student\n"
						+ "2. Employee");
				int choice = Integer.parseInt(Utilities.getScanner().nextLine());
				
				if(choice != 1 && choice != 2)
				{
					System.out.println("Invalid Choice. Please try again.");
				}
				else
				{
					System.out.println("Enter Username: ");
					String username = Utilities.getScanner().nextLine();
					
					if(!userList.containsKey(username))
					{
						System.out.println("Enter Password: ");
						String password = Utilities.getScanner().nextLine();
						
						System.out.println("Enter email address");
						String email = Utilities.getScanner().nextLine();
						
						if(choice ==1)
						{
							System.out.println("Enter \'I\' if you are an International Student, anything else if you are a local student ");
							String option = Utilities.getScanner().nextLine();
							if(option.equalsIgnoreCase("I"))
								userList.put(username, new Student(username,password,email,Availability.PartTime));
							else
								userList.put(username, new Student(username,password,email,Availability.FullTime));	
						}
							
						else
							userList.put(username, new Employer(username, password,email));
						
						System.out.println("Registration complete! Please Login to access your account");
						flag = true;
					}
					else
					{
						System.err.println("This username already exists! Please try another username");
						System.out.println("\nEnter Q to quit or anything else to try again");
						String input = Utilities.getScanner().nextLine();
						if(input.equalsIgnoreCase("Q"))
							flag = true;
					}
						
						
				}
			}
			catch(Exception e)
			{
				System.err.println(e.getMessage());
			}
			
		}while(!flag);
		
		
	}
	
	

}
