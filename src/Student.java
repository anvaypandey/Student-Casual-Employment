import java.util.ArrayList;
import java.util.Date;

public class Student extends User{
	
	Availability availability;
	ApplicantStatus status;
	
	Date lastStatusUpdateDate;
	
	ArrayList<String> references;
	
	ArrayList<String> employmentRecords;
	
	String locationCV;

	protected Student(String username, String password, String email, Availability availability) {
		super(username, password,email);
		
		status = ApplicantStatus.Unknown;
		this.availability = availability;
		references = new ArrayList<String>();
		employmentRecords = new ArrayList<String>();
		// TODO Auto-generated constructor stub
	}

	public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}
	

	public Date getLastStatusUpdateDate() {
		return lastStatusUpdateDate;
	}

	public void setLastStatusUpdateDate(Date lastStatusUpdateDate) {
		this.lastStatusUpdateDate = lastStatusUpdateDate;
	}

	public ArrayList<String> getReferences() {
		return references;
	}

	public void setReferences(String reference) {
		references.add(reference);
	}

	public ArrayList<String> getEmploymentRecords() {
		return employmentRecords;
	}

	public void setEmploymentRecords(String employmentRecord) {
		employmentRecords.add(employmentRecord);
	}

	public String getLocationCV() {
		return locationCV;
	}

	public void setLocationCV(String locationCV) {
		this.locationCV = locationCV;
	}

	@Override
	public String getDetails() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
