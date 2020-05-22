import java.util.ArrayList;
import java.util.Collections;

public class Job {

	private String JobId;
	private ArrayList<Student> shortlist;
	private Employer jobCreator;
	private String jobDescription;

	public Job(String jobId,Employer jobCreator, String jobDescription) {
		JobId = jobId;
		this.jobCreator = jobCreator;
		this.jobDescription = jobDescription;
		shortlist = new ArrayList<>();
	}
	


	public String getJobId() {
		return JobId;
	}

	public void setJobId(String jobId) {
		JobId = jobId;
	}

	public ArrayList<Student> getShortlist() {
		return shortlist;
	}

	public boolean addtoShortlist(Student student) throws Exception {
		int i;
		for(i = 0;i<shortlist.size();i++)
		{
			if(shortlist.get(i) == student)
			break;
		}
		if(i<shortlist.size())
			throw new Exception("Already Exists");
		else
			shortlist.add(student);
		
		return true;
	}

	public Employer getJobCreator() {
		return jobCreator;
	}

	public void setJobCreator(Employer jobCreator) {
		this.jobCreator = jobCreator;
	}
	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getDetails()
	{
		return null;
	}

	public void rankCandidates(String ranks)throws InvalidInputException
	{
		if(ranks.length()!= shortlist.size())// check the length of the number, which should be equal to the length of string
			throw new InvalidInputException("Incorrect Input!");

		String [] rankArray = ranks.split(" ");

		try{
			int i=0;
			while (i<ranks.length()) {
				if(Integer.parseInt(rankArray[i]) >= shortlist.size() || Integer.parseInt(rankArray[i])<= 0)
					throw new InvalidInputException("Invalid Input");
				for(int j=i+1;j<rankArray.length;j++) {
					if(Integer.parseInt(rankArray[i]) == Integer.parseInt(rankArray[j]))
						throw new InvalidInputException("Invalid Input");
				}
				i++;
			}
		}
		catch (NumberFormatException e)
		{
			throw new InvalidInputException("Invalid Input");
		}

		// sort the list according the the ranking
		ArrayList<Student> temp = new ArrayList<>();

		for(int i=0;i<rankArray.length;i++)
			temp.add(shortlist.get(Integer.parseInt(rankArray[i])-1));

		shortlist.clear();
		shortlist.addAll(temp);

	}
	
	

}
