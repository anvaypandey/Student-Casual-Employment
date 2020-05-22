import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Employer extends User {
	
	private String phNo;
	private ArrayList<Complaint> complaints;
	private BlacklistStatus blacklistStatus;
	private DateTime blacklistTime;
	//private ArrayList<Job> jobsCreated;
	
	
	public Employer(String username, String password, String email) {
		super(username, password,email);
		complaints = new ArrayList<Complaint>();
		blacklistStatus = BlacklistStatus.NONE;
		//jobsCreated = new ArrayList<>();
	}
	
	public Employer(Employer that)
	{
		super(that.getUsername(),that.getPassword(),that.getEmailAddress());
		setBlacklistStatus(that.getBlacklistStatus());
		if(that.getComplaints().iterator().hasNext())
			Collections.copy(complaints,that.getComplaints());
		else
			complaints = new ArrayList<>();
		/*if(that.getJobsCreated().iterator().hasNext())
			Collections.copy(jobsCreated,that.getJobsCreated());
		else
			jobsCreated = new ArrayList<>();*/
	}

	public ArrayList<Complaint> getComplaints() {
		return complaints;
	}

	public void addComplaint(Complaint complaint) {
		this.complaints.add(complaint);
		if(complaints.size()==3)
			setBlacklistStatus(BlacklistStatus.PROVISIONAL);
	}

	/*public ArrayList<Job> getJobsCreated() {
		return jobsCreated;
	}

	public void setJobsCreated(ArrayList<Job> jobsCreated) {
		this.jobsCreated = jobsCreated;
	}
*/

	public BlacklistStatus getBlacklistStatus() {
		return blacklistStatus;
	}

	public void setBlacklistStatus(BlacklistStatus blacklistStatus) {
		this.blacklistStatus = blacklistStatus;
		blacklistTime = new DateTime();
	}

	public DateTime getBlacklistTime() {
		return blacklistTime;
	}

	@Override
	public String getDetails() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
