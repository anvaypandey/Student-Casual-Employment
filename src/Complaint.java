import java.io.Serializable;

public class Complaint implements Serializable {

    private User complainer;
    private String complaint;


    public Complaint(User complainer, String complaint)
    {
        this.complainer=complainer;
        this.complaint=complaint;
    }
    public User getComplainer() {
        return complainer;
    }

    public void setComplainer(User complainer) {
        this.complainer = complainer;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
}
