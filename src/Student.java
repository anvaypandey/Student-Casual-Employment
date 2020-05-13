import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Student extends User{
	
	private Availability availability;
	private ApplicantStatus status;
	
	private DateTime lastStatusUpdateDate;
	
	private ArrayList<String> references;
	
	private ArrayList<String> employmentRecords;
	
	private String locationCV;
	
	private ArrayList<String> complaints;
	
	private ArrayList<String> jobCategories;
	

	protected Student(String username, String password, String email, Availability availability) {
		super(username, password,email);
		
		status = ApplicantStatus.Unknown;
		this.availability = availability;
		references = new ArrayList<String>();
		employmentRecords = new ArrayList<String>();
		complaints = new ArrayList<String>();
		jobCategories = new ArrayList<String>();
		
		// TODO Auto-generated constructor stub
	}

	public Student(Student that) {
		
		super(that.getUsername(),that.getPassword(),that.getEmailAddress());
		status = that.getStatus();
		this.availability = that.getAvailability();

		if(that.getReferences().iterator().hasNext())
			Collections.copy(references,that.getReferences());
		else
			references = new ArrayList<>();

		if(that.getComplaints().iterator().hasNext())
			Collections.copy(complaints,that.getComplaints());
		else
			complaints = new ArrayList<>();

		if(that.getEmploymentRecords().iterator().hasNext())
			Collections.copy(employmentRecords,that.getEmploymentRecords());
		else
			employmentRecords = new ArrayList<>();

		if(that.getJobCategories().iterator().hasNext())
			Collections.copy(jobCategories,that.getJobCategories());
		else
			jobCategories = new ArrayList<>();
		setBlacklistStatus(that.getBlacklistStatus());
	}

	public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}
	//need to look into this date time
	public DateTime getLastStatusUpdateDate() {
		return lastStatusUpdateDate;
	}

	public void setLastStatusUpdateDate(DateTime lastStatusUpdateDate) {
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
	
	public ArrayList<String> getJobCategories() {
		return jobCategories;
	}

	public void addJobCategory(String jobCategory) {
		this.jobCategories.add(jobCategory);
	}

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
