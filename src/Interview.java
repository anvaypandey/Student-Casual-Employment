public class Interview {

    private Student student;
    private DateTime time;
    private String interviewResult;
    private boolean referenceCheck;
    private boolean finalOutcome;

    public Interview(Student student, DateTime time) {
        this.student = student;
        this.time = time;
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

    public boolean isFinalOutcome() {
        return finalOutcome;
    }

    public void setFinalOutcome(boolean finalOutcome) {
        this.finalOutcome = finalOutcome;
    }


}
