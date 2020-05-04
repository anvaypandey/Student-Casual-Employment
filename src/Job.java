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

	public void setShortlist(ArrayList<Student> shortlist) {
		this.shortlist = shortlist;
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
