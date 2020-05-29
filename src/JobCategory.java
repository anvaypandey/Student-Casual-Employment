import java.io.Serializable;

public class JobCategory implements Serializable {
    private String name;

    public JobCategory(String name)
    {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
