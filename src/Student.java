import java.util.ArrayList;
import java.util.Date;

public class Student extends User{
	
	Availability availability;
	ApplicantStatus status;
	
	Date lastStatusUpdateDate;
	
	String references;
	
	ArrayList<String> employmentRecords = new ArrayList<String>();
	
	String locationCV;

	protected Student(String username, String password) {
		super(username, password);
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

	public String getReferences() {
		return references;
	}

	public void setReferences(String references) {
		this.references = references;
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
	
	
	

}
