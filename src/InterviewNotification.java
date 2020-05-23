public class InterviewNotification {

    private DateTime interviewTime;
    private Job job;

    public InterviewNotification(Job job, DateTime interviewTime) {
        this.interviewTime = interviewTime;
        this.job = job;
    }


    public DateTime getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(DateTime interviewTime) {
        this.interviewTime = interviewTime;
    }

    public Job getJob() {
        return job;
    }
}
