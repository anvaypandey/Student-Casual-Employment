public class Offer {

    private Job job;
    private boolean acceptedOrRejected;

    public Offer(Job job) {
        this.job = job;
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


}
