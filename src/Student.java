import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Student extends User implements Serializable {
	
	private Availability availability;
	private ApplicantStatus status;
	private DateTime lastStatusUpdateDate;
	private ArrayList<Reference> references;
	private ArrayList<EmploymentRecord> employmentRecords;
	private String locationCV;
	private ArrayList<Complaint> complaints;
	private ArrayList<JobCategory> selectedJobCategories;
	private BlacklistStatus blacklistStatus;
	private DateTime blacklistTime;
	private ArrayList<Interview> interviews;
	private ArrayList<Offer> offers;

	protected Student(String username, String password, String email, Availability availability) {
		super(username, password,email); //student constructor
		
		status = ApplicantStatus.Available;
		this.availability = availability;
		references = new ArrayList<>();
		employmentRecords = new ArrayList<>();
		complaints = new ArrayList<>();
		selectedJobCategories = new ArrayList<>();
		blacklistStatus = BlacklistStatus.NONE;
		interviews = new ArrayList<>();
		offers = new ArrayList<>();
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

		if(that.getSelectedJobCategories().iterator().hasNext())
			Collections.copy(selectedJobCategories,that.getSelectedJobCategories());
		else
			selectedJobCategories = new ArrayList<>();

		if(that.getInterviewNotifications().iterator().hasNext())
			Collections.copy(interviews,that.getInterviewNotifications());
		else
			interviews = new ArrayList<>();

		if(that.getOffers().iterator().hasNext())
			Collections.copy(offers,that.getOffers());
		else
			selectedJobCategories = new ArrayList<>();

		setBlacklistStatus(that.getBlacklistStatus());
	}
//GETTERS AND SETTERS
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

	public ArrayList<Reference> getReferences() {
		return references;
	}

	public void setReferences(Reference reference) {
		references.add(reference);
	}

	public ArrayList<EmploymentRecord> getEmploymentRecords() {
		return employmentRecords;
	}

	public void setEmploymentRecords(EmploymentRecord employmentRecord) {
		employmentRecords.add(employmentRecord);
	}

	public String getLocationCV() {
		return locationCV;
	}

	public void setLocationCV(String locationCV) {
		this.locationCV = locationCV;
	}
	
	public ArrayList<JobCategory> getSelectedJobCategories() {
		return selectedJobCategories;
	}

	public void addJobCategory(JobCategory jobCategory) {
		this.selectedJobCategories.add(jobCategory);
	}

	public ArrayList<Complaint> getComplaints() {
		return complaints;
	}

	public void addComplaint(Complaint complaint) {
		this.complaints.add(complaint);
		if(complaints.size()==3)
			setBlacklistStatus(BlacklistStatus.PROVISIONAL);
	}

	public ApplicantStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicantStatus status) {
		this.status = status;
		setLastStatusUpdateDate(new DateTime());
	}

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

	public ArrayList<Offer> getOffers() {
		return offers;
	}

	public void addOffer(Offer offer) {
		this.offers.add(offer);
	}

	public ArrayList<Interview> getInterviewNotifications() {
		return interviews;
	}
//Student details to string
	@Override
	public String getDetails() {
		String s = "UserName :"+getUsername()+"\nEmail:"+getEmailAddress()+"\nAvailability: "+ getAvailability()+
				"\nStatus:"+getStatus();
		return s;
	}

	public void addInterviewNotification(Interview interview) {
		interviews.add(interview);
	}
}
