import com.sun.tools.javac.Main;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentConsole implements Serializable {
	boolean depart = false;
	Student std = (Student) MainConsole.userList.get(MainConsole.user);

	public void run() { //runs menu and checks if user was blacklisted, if so user is redirected
		System.out.println("Welcome " + MainConsole.user);
		if (std.getBlacklistStatus() == BlacklistStatus.FULL)
		{
			do {
				fullyBlacklistedMenu();

			} while (!depart);
		}
		else
		{
			do {
				manageMenu();

			} while (!depart);
		}
		return;
	}

	private void fullyBlacklistedMenu() { //blacklist menu, user can only change username or password

		System.out.println("As you have been fully blacklisted,you can only access limited features");
		try   //menu
		{
			String menu = "1.Change password" +
					"2.Change Username" +
					"3.LogOut";
			System.out.println(menu);

			int userChoice = Integer.parseInt(Utilities.getScanner().nextLine());

			switch (userChoice) {
				case 1:
					changeUsername();
					break;
				case 2:
					changePassword();
					break;
				case 3:
					System.out.println("You have successfully logged out!\n");
					depart = true;
					return;
				default:
					System.out.println("Invalid Choice. Please try again");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void manageMenu() { //menu that runs the student console
		try {

			String menu = "1. See Notifications\n"
					+ "2. Check Offer\n"
					+ "3. Update your Availability\n"
					+ "4. Update your Status\n"
					+ "5. Add Records\n"
					+ "6. Update Records\n"
					+ "7. Add Reference\n"
					+ "8. Update Reference\n"
					+ "9. Upload CV\n" // for later
					+ "10. Add new Job Category to your list\n"
					+ "11. Lodge Complaint\n"
					+ "12. Change Username\n" //Let this be for now
					+ "13. Change Password\n"
					+ "14. Logout\n"
					+ "Enter your choice: ";

			System.out.println(menu);

			int userChoice = Integer.parseInt(Utilities.getScanner().nextLine());

			switch (userChoice) { //switch case for menu
				case 1:
					showNotifications();
					break;
				case 2:
					checkOffer();
					break;
				case 3:
					updateAvailability();
					break;

				case 4:
					updateStatus();
					break;
				case 5:
					addRecord();
					break;
				case 6:
					updateRecords();
					break;
				case 7:
				case 8:
					addReference();
					break;
				case 9:
					uploadCV();
					break;
				case 10:
					chooseJobCategory();
					break;
				case 11:
					lodgeComplaint();
					break;
				case 12:
					changeUsername();
					break;
				case 13:
					changePassword();
					break;
				case 14:
					System.out.println("You have successfully logged out!\n");
					depart = true;
					return;
				default:
					System.out.println("Invalid Choice. Please try again");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//switch case
	}

	private void checkOffer() throws AuthorizationException { //allows user to check any job offers

		if (std.getBlacklistStatus() != BlacklistStatus.NONE) { //if student is blacklisted they cannot access this feature
			throw new AuthorizationException("You are not Authorised to Use this feature");
		} else {
			ArrayList<Offer> offers = std.getOffers(); //get offers
			if (offers.size() == 0) //check if there are any offers
				System.out.println("You have zero Job Offers");

			for (int i = 0; i < offers.size(); i++) {
				offers.get(i).getJob().getDetails();
			}

			if (std.getStatus() == ApplicantStatus.Employed) { //students cannot accept offers if employed
				System.out.println("You cant accept offers now.You're Employed!");
				return;
			}

			System.out.println("Enter Job Id"); //of job offer student wants to respond to
			String jobID = Utilities.getScanner().nextLine();

			for (int i = 0; i < offers.size(); i++) {
				if (offers.get(i).getJob().getJobId().equalsIgnoreCase(jobID)) {
					offers.get(i).getJob().getDetails();

					System.out.println("Accept or Reject? Input Y to accept, anything else to reject"); //respond to offer
					String input = Utilities.getScanner().nextLine();

					if (input.equalsIgnoreCase("Y")) {
						offers.get(i).setAcceptedOrRejected(true);
						std.setStatus(ApplicantStatus.Employed);
					} else
						offers.get(i).setAcceptedOrRejected(true);

				}
			}
		}
	}

	public void chooseJobCategory() throws AuthorizationException { //allows students to add job categories to their list of interests

		if (std.getBlacklistStatus() != BlacklistStatus.NONE) { //if student is blacklisted they cannot access this feature
			throw new AuthorizationException("You are not Authorised to Use this feature");
		} else {
			for (int i = 0; i < MainConsole.jobCategories.size(); i++) {
				System.out.println(MainConsole.jobCategories.get(i).getName() + "\n"); //displays existing job categories from employers
			}
		}
		boolean exists = false;
		do {
			System.out.println("Choose desired job category"); //selects job category desired
			String jobcat = Utilities.getScanner().nextLine();

			try {
				exists = jobCatego(jobcat);
				if (exists) {
					JobCategory jobcat1 = new JobCategory(jobcat);
					std.addJobCategory(jobcat1);
					System.out.print("Category has been added to your list");
				}

			} catch (InvalidInputException e) {

				e.printStackTrace();
			}
			if (!exists)
				System.out.print("Q to quit or try again");
			String resp = Utilities.getScanner().nextLine();
			if (resp.equalsIgnoreCase("q")) {
				exists = true;
			}
		} while (!exists);
	}
	public boolean jobCatego(String jobcat) throws InvalidInputException, AuthorizationException {

			if (std.getBlacklistStatus() != BlacklistStatus.NONE) { //if student is blacklisted they cannot access this feature
				throw new AuthorizationException("You are not Authorised to Use this feature");
			} else {
				int i;
				for (i = 0; i < MainConsole.jobCategories.size(); i++) {
					if (jobcat.equalsIgnoreCase(MainConsole.jobCategories.get(i).getName())) { //ensures job category exists

						break;
					}
				}
				if (i == MainConsole.jobCategories.size()) {
					throw new InvalidInputException("Category does not exist");
				}
				ArrayList<JobCategory> jobCat = std.getSelectedJobCategories();
				for (i = 0; i < jobCat.size(); i++) {
					if (jobcat.equalsIgnoreCase(jobCat.get(i).getName())) {
						throw new InvalidInputException("Category already exists in your list");
					}
				}

				return true;
			}

		}
	private void changePassword()  { //allows user to change password

			System.out.println("Please enter new password");

			String newPassword = Utilities.getScanner().nextLine();

			try {
				MainConsole.userList.get(MainConsole.user).setPassword(newPassword);
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}
		}

	public void changeUsername()  { //allows user to change username
			System.out.println("Please enter new username");
			String newUsername = Utilities.getScanner().nextLine();
			MainConsole.userList.get(MainConsole.user).setUsername(newUsername);
			System.out.println("Username changed");
			depart = true; //to login again

		}

	private void lodgeComplaint() throws AuthorizationException { //allows student to lodge a complaint against an employer
		if(std.getBlacklistStatus()!= BlacklistStatus.NONE) { //if student is blacklisted they cannot access this feature
			throw new AuthorizationException("You are not Authorised to Use this feature");
		}
		else {
			System.out.println("Enter the username of the User you want to complain about");
			String complaintUser = Utilities.getScanner().nextLine();
			System.out.println("Enter your complaint");
			String complaint = Utilities.getScanner().nextLine();
			try {
				if (MainConsole.userList.containsKey(complaintUser)) {
					Complaint complaint1 = new Complaint(MainConsole.userList.get(MainConsole.user), complaint);
					if (MainConsole.userList.get(complaintUser) instanceof Student)
						((Student) MainConsole.userList.get(complaintUser)).addComplaint(complaint1);
					else if (MainConsole.userList.get(complaintUser) instanceof Employer)
						((Employer) MainConsole.userList.get(complaintUser)).addComplaint(complaint1);
					else
						throw new AuthorizationException("You are not authorised to complain against the Maintenance");
				} else
					throw new InvalidInputException("Such user does not exist");

			} catch (AuthorizationException e) {
				e.printStackTrace();
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}
		}
	}
	private void uploadCV() throws AuthorizationException { //students can upload file pathway for CV
		if(std.getBlacklistStatus()!= BlacklistStatus.NONE) { //if student is blacklisted they cannot access this feature
			throw new AuthorizationException("You are not Authorised to Use this feature");
		}
		else {
			System.out.println("Please enter file path");
			String fPath = Utilities.getScanner().nextLine();
		}
	}

	public void addReference() throws AuthorizationException { //allows students to add references as reference objects
		if (std.getBlacklistStatus() != BlacklistStatus.NONE) { //if student is blacklisted they cannot access this feature
			throw new AuthorizationException("You are not Authorised to Use this feature");
		} else {
			System.out.println("Enter the name of your reference");
			String nameRef = Utilities.getScanner().nextLine();
			System.out.println("Enter the email of your reference");
			String emailRef = Utilities.getScanner().nextLine();
			System.out.println("Enter the phone of your reference");
			String phoneRef = Utilities.getScanner().nextLine();
			Reference ref1 = new Reference(nameRef, emailRef, phoneRef);
			std.setReferences(ref1);
			System.out.println("Reference successfully added!");
		}
	}
	private void updateRecords() throws AuthorizationException { //allows students to update employment details
		if (std.getBlacklistStatus() != BlacklistStatus.NONE) { //if student is blacklisted they cannot access this feature
			throw new AuthorizationException("You are not Authorised to Use this feature");
		} else {
			System.out.println("Please enter type of employment");
			String type1 = Utilities.getScanner().nextLine();
			std.getEmploymentRecords();
			System.out.println("Please enter description of employment");
			String description1 = Utilities.getScanner().nextLine();
			EmploymentRecord empRec = new EmploymentRecord(type1, description1);
			std.setEmploymentRecords(empRec);
			System.out.println("Employment Record added!");
		}
	}
	private void addRecord() throws AuthorizationException { //allows students to add records
		if (std.getBlacklistStatus() != BlacklistStatus.NONE) { //if student is blacklisted they cannot access this feature
			throw new AuthorizationException("You are not Authorised to Use this feature");
		} else {
			System.out.println("Please enter type of employment");
			String type = Utilities.getScanner().nextLine();
			System.out.println("Please enter description of employment");
			String description = Utilities.getScanner().nextLine();
			EmploymentRecord empRec = new EmploymentRecord(type, description);
			std.setEmploymentRecords(empRec);
			System.out.println("Employment Record added!");
		}
	}

	private void updateStatus() throws AuthorizationException { //allows students to update their status
		if (std.getBlacklistStatus() != BlacklistStatus.NONE) { //if student is blacklisted they cannot access this feature
			throw new AuthorizationException("You are not Authorised to Use this feature");
		} else {
			System.out.println("Please enter desired status update Available(A)/Pending(P)/Unknown(U)/Employed(E)");
			String response = Utilities.getScanner().nextLine();
			if (response.equalsIgnoreCase("A")) {
				std.setStatus(ApplicantStatus.Available);
				System.out.println("Status successfully updated");
			} else if (response.equalsIgnoreCase("P")) {
				std.setStatus(ApplicantStatus.Pending);
				System.out.println("Status successfully updated");
			} else if (response.equalsIgnoreCase("U")) {
				std.setStatus(ApplicantStatus.Unknown);
				System.out.println("Status successfully updated");
			} else if (response.equalsIgnoreCase("E")) {
				std.setStatus(ApplicantStatus.Employed);
				System.out.println("Status successfully updated");
			}
		}
	}

	public void updateAvailability() throws AuthorizationException { //allows students to update their availability

		if (std.getBlacklistStatus() != BlacklistStatus.NONE) { //if student is blacklisted they cannot access this feature
			throw new AuthorizationException("You are not Authorised to Use this feature");
		} else {
			System.out.println("Please enter new Availability: FULLTIME (F), PARTTIME (P), INTERNSHIP(I) ");
			String response = Utilities.getScanner().nextLine();
			if(response.equalsIgnoreCase("F"))
			 {
				Availability availability = Availability.FullTime;
				std.setAvailability(availability);
				System.out.println("Availability set to FullTime");
			} else if (response.equalsIgnoreCase("P")) {
				Availability availability2 = Availability.PartTime;
				std.setAvailability(availability2);
				System.out.println("Availability set to PartTime");
			} else if (response.equalsIgnoreCase("I")) {
				Availability availability3 = Availability.Internship;
				std.setAvailability(availability3);
				System.out.println("Availability set to Internship");
			}else
				System.out.println("Process could not be completed");
		}
	}

	private void showNotifications() throws AuthorizationException { //displays interview notifications to students

		if (std.getBlacklistStatus() != BlacklistStatus.NONE) { //if student is blacklisted they cannot access this feature
			throw new AuthorizationException("You are not Authorised to Use this feature");
		} else {
			ArrayList<Interview> interviews = std.getInterviewNotifications();

			if (interviews.size() == 0)
				System.out.println("No new notifications");

			for (int j = 0; j < interviews.size(); j++) {
				System.out.println(interviews.get(j).giveStudentDetails());

				System.out.println("Y to accept, anything else to reject");

				String input = Utilities.getScanner().nextLine();
				if (input.equalsIgnoreCase("Y")) {
					interviews.get(j).setInterviewAccepted(true);

				}
			}
			interviews.clear();
		}
	}
}