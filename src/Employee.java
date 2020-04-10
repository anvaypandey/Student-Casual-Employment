import java.util.ArrayList;

public class Employee extends User {
	
	String phNo;
	ArrayList<String> complaints;
	
	
	public Employee(String username, String password, String email) {
		super(username, password,email);
		complaints = new ArrayList<String>();
		
		// TODO Auto-generated constructor stub
	}
	
	public Employee(Employee that)
	{
		super(that.getUsername(),that.getPassword(),that.getEmailAddress());
		setBlacklistStatus(that.getBlacklistStatus());
		complaints.addAll(that.getComplaints());
		
		
	}

	public ArrayList<String> getComplaints() {
		return complaints;
	}

	public void addComplaint(String complaint) {
		this.complaints.add(complaint);
	}

	@Override
	public String getDetails() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
