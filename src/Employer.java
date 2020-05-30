import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Employer extends User implements Serializable {

	private ArrayList<Complaint> complaints;
	private BlacklistStatus blacklistStatus;
	private DateTime blacklistTime;
	
	
	public Employer(String username, String password, String email) {
		super(username, password,email);
		complaints = new ArrayList<Complaint>();
		blacklistStatus = BlacklistStatus.NONE;
	}
	
	public Employer(Employer that)
	{
		super(that.getUsername(),that.getPassword(),that.getEmailAddress());
		setBlacklistStatus(that.getBlacklistStatus());
		if(that.getComplaints().iterator().hasNext())
			Collections.copy(complaints,that.getComplaints());
		else
			complaints = new ArrayList<>();
	}

	public void addComplaint(Complaint complaint) {
		this.complaints.add(complaint);
		if(complaints.size()==3)
			setBlacklistStatus(BlacklistStatus.PROVISIONAL);
	}

	//Getters and Setters
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
		String s = "UserName :"+getUsername()+"\nEmail:"+getEmailAddress();
		return s;
	}

	public ArrayList<Complaint> getComplaints() {
		return complaints;
	}
	
}
