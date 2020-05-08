import java.util.ArrayList;
import java.util.Date;

public class Student extends User{
	
	Availability availability;
	ApplicantStatus status;
	
	Date lastStatusUpdateDate;
	
	ArrayList<String> references;
	
	ArrayList<String> employmentRecords;
	
	String locationCV;
	
	ArrayList<String> complaints;
	
//	ArrayList<String> jobCategories = new ArrayList<String>();
	
	

	protected Student(String username, String password, String email, Availability availability) {
		super(username, password,email);
		
		status = ApplicantStatus.Unknown;
		this.availability = availability;
		references = new ArrayList<String>();
		employmentRecords = new ArrayList<String>();
		complaints = new ArrayList<String>();
		
		// TODO Auto-generated constructor stub
	}

	public Student(Student that) {
		
		super(that.getUsername(),that.getPassword(),that.getEmailAddress());
		status = that.getStatus();
		this.availability = that.getAvailability();

		//collections will throw null pointer exception
		references.addAll(that.getReferences());
		employmentRecords.addAll(that.getEmploymentRecords());
		complaints.addAll(that.getComplaints());
		//jobCategories.addAll(that.getJobCategories());
		setBlacklistStatus(that.getBlacklistStatus());
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
	
//	public ArrayList<String> getJobCategories() {
//		return jobCategories;
//	}
//
//	public void addJobCategory(String jobCategory) {
//		this.jobCategories.add(jobCategory);
//	}

	public ArrayList<String> getComplaints() {
		return complaints;
	}

	public void addComplaint(String complaint) {
		this.complaints.add(complaint);
		//if complaints are 3 then blacklisted
	}

	public ApplicantStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicantStatus status) {
		this.status = status;
	}

	@Override
	public String getDetails() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
