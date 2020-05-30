import java.io.Serializable;

public class Offer implements Serializable {

    private Job job;
    private boolean acceptedOrRejected;
    private Student student;

    public Offer(Job job,Student student) {
        this.job = job;
        this.student = student;
    }

    public boolean isAcceptedOrRejected() {
        return acceptedOrRejected;
    }

    public void setAcceptedOrRejected(boolean acceptedOrRejected) {
        this.acceptedOrRejected = acceptedOrRejected;
    }

    public Job getJob() {
        return job;
    }

    public Student getStudent() {
        return student;
    }


}
