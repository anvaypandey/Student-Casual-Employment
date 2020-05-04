import java.util.ArrayList;

public class Employer extends User {
	
	String phNo;
	ArrayList<String> complaints;
	
	
	public Employer(String username, String password, String email) {
		super(username, password,email);
		complaints = new ArrayList<String>();
		
		// TODO Auto-generated constructor stub
	}
	
	public Employer(Employer that)
	{
		super(that.getUsername(),that.getPassword(),that.getEmailAddress());
		setBlacklistStatus(that.getBlacklistStatus());
		complaints.addAll(that.getComplaints());//will throw null pointer exception
		
		
	}

	public ArrayList<String> getComplaints() {
		return complaints;
	}

	public void addComplaint(String complaint) {
		this.complaints.add(complaint);
		if(complaints.size()==3)
			setBlacklistStatus(BlacklistStatus.PROVISIONAL);
	}

	@Override
	public String getDetails() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
