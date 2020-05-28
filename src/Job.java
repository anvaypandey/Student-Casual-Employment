import java.io.Serializable;
import java.util.ArrayList;

public class Job implements Serializable {

	private String jobId;
	private Employer jobCreator;
	private String jobDescription;
	private JobCategory jobCategory;
	private ArrayList<Interview> interviews;
	private ArrayList<Offer> offers;

	public Job(String jobId,Employer jobCreator, String jobDescription,JobCategory jobCategory) {
		this.jobId = jobId;
		this.jobCreator = jobCreator;
		this.jobDescription = jobDescription;
		interviews = new ArrayList<>();
		offers = new ArrayList<>();
		this.jobCategory = jobCategory;
	}

	public ArrayList<Interview> getInterviews() {
		return interviews;
	}

	public void setInterview(Interview interview) {
		this.interviews.add(interview);
	}
	


	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public ArrayList<Student> getShortlist() {
		ArrayList<Student> students = new ArrayList<>();
		for(int i=0;i<interviews.size();i++)
		{
			students.add(interviews.get(i).getStudent());
		}
		return students;
	}

	public boolean addtoShortlist(Student student, DateTime dateTime) throws Exception {
		int i;
		for(i = 0;i<interviews.size();i++)
		{
			if(interviews.get(i).getStudent().getUsername().equalsIgnoreCase(student.getUsername()))
			break;
		}
		if(i<interviews.size())
			throw new Exception("Already Exists");

		Interview interview = new Interview(this,student,dateTime);
		interviews.add(interview);
		student.addInterviewNotification(interview);
		
		return true;
	}

	public JobCategory getJobCategory() {
		return jobCategory;
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
		String s = "ID: "+jobId+"\nJob Category: "+jobCategory.getName()+"\nDescription: "+jobDescription+"\n";
		return s;
	}

	public void rankCandidates(String ranks)throws InvalidInputException
	{

		String [] rankArray = ranks.split(" ");

		if(rankArray.length!= interviews.size())// check the length of the string array, which should be equal to the length of string
			throw new InvalidInputException("Incorrect Input!");

		try{
			int i=0;
			while (i<ranks.length()) {
				if(Integer.parseInt(rankArray[i]) >= interviews.size() || Integer.parseInt(rankArray[i])<= 0)
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
		ArrayList<Interview> temp = new ArrayList<>();

		for(int i=0;i<rankArray.length;i++)
			temp.add(interviews.get(Integer.parseInt(rankArray[i])-1));

		interviews.clear();
		interviews.addAll(temp);

	}

	public ArrayList<Offer> getOffers() {
		return offers;
	}

}
