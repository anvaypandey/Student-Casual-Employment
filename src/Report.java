import java.io.Serializable;
import java.util.ArrayList;

public class Report implements Serializable {


    private boolean depart = false;

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
            String menu = "1. Listing of employers making offers, sorted based on the number of offers made in the specified period\n" +
                    "2. List of complaints about specific applicant or employer\n" +
                    "3. Jobs offered and accepted by a job applicant in the past\n" +
                    "4. All past offers for a particular category of job.\n" +
                    "5. Done with reports\n" +
                    "Enter your choice:";
            System.out.println(menu);

            int userChoice = Integer.parseInt(Utilities.getScanner().nextLine());

            switch(userChoice)
            {
                case 1:
                    sortEmployers();
                    break;
                case 2:
                    complaintList();
                    break;
                case 3:
                    jobOfferedAccepted();
                    break;
                case 4:
                    pastOffers();
                    break;
                case 5:
                    System.out.println("You are back in the Main Menu!\n");
                    depart = true;
                    return;
                default:
                    System.out.println("Invalid Choice. Please try again");
            }
        }
        catch( Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    private void sortEmployers() {

        for(int i=0;i<MainConsole.jobListings.size();i++)
        {
            System.out.println("No. of Offers: "+MainConsole.jobListings.get(i).getOffers().size());
            System.out.println("Employer: "+MainConsole.jobListings.get(i).getJobCreator().getUsername());
            System.out.println(MainConsole.jobListings.get(i).getDetails());
        }
    }

    private void jobOfferedAccepted() {

        boolean flag = false;
        do {
            try
            {
                System.out.println("Enter the username of the job applicant (Student)");

                String username = Utilities.getScanner().nextLine();

                if(MainConsole.userList.containsKey(username) && MainConsole.userList.get(username) instanceof Student)
                {
                    flag = true;

                    Student std = (Student) MainConsole.userList.get(username);

                    ArrayList<Offer> offers = std.getOffers();

                    if (offers.size() == 0)
                        System.out.println("No offers!");

                    boolean exists = false;

                    for(int i=0;i<offers.size();i++){
                        if(offers.get(i).isAcceptedOrRejected())
                        {
                            System.out.println(offers.get(i).getJob().getDetails()+"\n\n");
                            exists = true;
                        }

                    }
                    if(!exists)
                        System.out.println("No Such Offer Exists!");


                }
                else
                    throw new InvalidInputException("Invalid Username");

            }
            catch (InvalidInputException e)
            {
                System.err.println(e.getMessage());
                System.out.println("Enter Q to quit or anything else to try again");
                String input = Utilities.getScanner().nextLine();

                if(input.equalsIgnoreCase("q"))
                    flag = true;
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
            }

        }while(!flag);



    }

    private void pastOffers() {

        boolean flag = false;
        do {
            try
            {
                for (int i=0;i<MainConsole.jobCategories.size();i++)
                {
                    System.out.println((i+1)+": "+MainConsole.jobCategories.get(i).getName()+"\n");
                }
                boolean exists = false;
                System.out.println("Enter the Job Category");
                String jobCategory = Utilities.getScanner().nextLine();

                for(int i=0;i<MainConsole.jobListings.size();i++)
                {
                    if(MainConsole.jobListings.get(i).getJobCategory().getName().equalsIgnoreCase(jobCategory))
                    {
                        exists=true;
                        ArrayList<Offer> offers = MainConsole.jobListings.get(i).getOffers();

                        for(int j=0;j<offers.size();j++)
                        {
                            System.out.println("Student:\n"+offers.get(i).getStudent().getDetails()+"\nJob:\n"+offers.get(i).getJob().getDetails());
                        }

                    }
                }
                if(!exists)
                    throw new InvalidInputException("No Job with this job category exist");

            }
            catch(InvalidInputException e)
            {
                System.err.println(e.getMessage());
                System.out.println("Enter Q to quit or anything else to try again");
                String input = Utilities.getScanner().nextLine();

                if(input.equalsIgnoreCase("q"))
                    flag=true;
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());

            }
        }while (!flag);



    }

    private void complaintList()  {
        boolean completed = false;
        do {
            try
            {
                System.out.println("Enter the username of the User");

                String userId = Utilities.getScanner().nextLine();

                if(MainConsole.userList.containsKey(userId))
                {
                    for(int i=0;i<MainConsole.userList.size();i++)
                    {
                        if(MainConsole.userList.get(i).getUsername().equalsIgnoreCase(userId))
                        {
                            if(MainConsole.userList.get(i) instanceof Student)
                            {
                                ArrayList<Complaint> complaints= ((Student)MainConsole.userList.get(i)).getComplaints();

                                if(complaints.size() == 0)
                                    System.out.println("No Complaints!");

                                for (int j=0;j<complaints.size();j++)
                                    System.out.println(complaints.get(j).getComplaint()+"\n By: "+complaints.get(j).getComplainer()+"\n\n");

                            }
                            else if(MainConsole.userList.get(i) instanceof Employer)
                            {
                                ArrayList<Complaint> complaints= ((Employer)MainConsole.userList.get(i)).getComplaints();

                                if(complaints.size() == 0)
                                    System.out.println("No Complaints!");

                                for (int j=0;j<complaints.size();j++)
                                    System.out.println(complaints.get(j).getComplaint()+"\n By: "+complaints.get(j).getComplainer()+"\n\n");
                            }

                        }
                    }

                    completed=true;

                }
                else
                    throw new InvalidInputException("Invalid username!");

            }
            catch (InvalidInputException e)
            {
                System.err.println(e.getMessage());
                System.out.println("Enter Q to quit or anything else to try again");
                String input = Utilities.getScanner().nextLine();

                if(input.equalsIgnoreCase("q"))
                    completed = true;
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
            }

        }while(!completed);
    }
}
