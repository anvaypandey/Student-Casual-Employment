import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MainConsole implements Serializable {
	
	 static HashMap<String, User> userList = new HashMap<>();
	
	static ArrayList<Job>  jobListings = new ArrayList<Job>();
	
	static ArrayList<JobCategory> jobCategories = new ArrayList<JobCategory>();
	
	
	static String user;

	
	public void run() 
	{
		getFromFiles();
		//populate();
		
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

	private void populate() {

		userList.put("admin", new Maintenance("admin","admin","admin@email.com"));
		userList.put("s123", new Student("s123","s123","s123@email.com",Availability.FullTime));
		userList.put("student1", new Student("student1","123","student1@email.com",Availability.FullTime));
		userList.put("student2", new Student("student2","123","student2@email.com",Availability.PartTime));
		userList.put("student3", new Student("student3","123","student3@email.com",Availability.PartTime));
		userList.put("emp123", new Employer("emp123","123","emp1@email.com"));
		userList.put("e123", new Employer("e123", "password", "e123@email.com"));
		userList.put("e001", new Employer("e001", "qwerty", "e001@email.com"));

		((Student)userList.get("student3")).setBlacklistStatus(BlacklistStatus.PROVISIONAL);

		jobCategories.add(new JobCategory("waiter"));
		jobCategories.add( new JobCategory("maid"));
		jobCategories.add(new JobCategory("Engineering"));
		jobCategories.add(new JobCategory("Accounting"));

		jobListings.add(new Job("JOB001", (Employer)userList.get("emp123"), "Job Description", jobCategories.get(0)));
		jobListings.add(new Job("JOB002", (Employer)userList.get("e123"), "Job Description",jobCategories.get(1)));
		jobListings.add(new Job("JOB003", (Employer)userList.get("e001"), "Job Description",jobCategories.get(2)));
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
					putToFiles();
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

	private void getFromFiles()
	{
		ObjectInputStream oisUsers;
		ObjectInputStream oisJobs;
		ObjectInputStream oisJobCategories;
		try
		{
			File fusers = new File("users.txt");
			File fjobs = new File("jobs.txt");
			File fjobcategories = new File("job_categories.txt");
			if(fusers.exists() && !fusers.isDirectory())
			{
				oisUsers = new ObjectInputStream(new FileInputStream("users.txt"));

				userList.putAll((HashMap<String,User>)oisUsers.readObject());

				oisUsers.close();
			}

			if(fjobs.exists() && !fjobs.isDirectory())
			{
				oisJobs = new ObjectInputStream(new FileInputStream("jobs.txt"));
				jobListings.addAll((ArrayList<Job>)oisJobs.readObject());
				oisJobs.close();
			}
			if(fjobcategories.exists() && !fjobcategories.isDirectory())
			{
				oisJobCategories = new ObjectInputStream(new FileInputStream("job_categories.txt"));
				jobCategories.addAll((ArrayList<JobCategory>)oisJobCategories.readObject());
				oisJobCategories.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//update the files from the ArrayList
	private void putToFiles()
	{


		try
		{
			ObjectOutputStream objUsers = new ObjectOutputStream(new FileOutputStream("users.txt"));
			objUsers.writeObject(userList);
			objUsers.close();

			ObjectOutputStream objJobs = new ObjectOutputStream(new FileOutputStream("jobs.txt"));
			objJobs.writeObject(jobListings);
			objJobs.close();


			ObjectOutputStream objCategories = new ObjectOutputStream(new FileOutputStream("job_categories.txt"));
			objCategories.writeObject(jobCategories);
			objCategories.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	

}
