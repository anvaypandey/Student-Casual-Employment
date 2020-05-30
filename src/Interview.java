import java.io.Serializable;

public class Interview implements Serializable {

    private Student student;
    private DateTime time;
    private String interviewResult;
    private boolean referenceCheck;



    private boolean interviewAccepted;

    private boolean finalOutcome;

    private Job job;

    public Interview(Job job,Student student, DateTime time) {
        this.student = student;
        this.time = time;
        this.job = job;
        interviewAccepted=false;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public String getInterviewResult() {
        return interviewResult;
    }

    public void setInterviewResult(String interviewResult) {
        this.interviewResult = interviewResult;
    }

    public boolean isReferenceCheck() {
        return referenceCheck;
    }

    public void setReferenceCheck(boolean referenceCheck) {
        this.referenceCheck = referenceCheck;
    }

    public void setInterviewAccepted(boolean interviewAccepted) {
        this.interviewAccepted = interviewAccepted;
    }
    public void setFinalOutcome(boolean finalOutcome) {
        this.finalOutcome = finalOutcome;
    }

    public String giveStudentDetails()
    {
        String s = job.getDetails()+"\n Interview Time: "+time.toString()+"\n\n";

        return s;
    }

    public String giveEmployerDetails()
    {
        String s = job.getDetails()+"Student Username: "+student.getUsername()+"\nInterview Time:"+time.toString();
        return s;
    }
    public boolean isInterviewAccepted() {
        return interviewAccepted;
    }



}
