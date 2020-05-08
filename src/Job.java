import java.util.ArrayList;

public class Job {

	private String JobId;
	private ArrayList<Student> shortlist;
	private Employer jobCreator;
	private String jobDescription;

	public Job(String jobId, Employer jobCreator, String jobDescription) {
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
	
	

}
