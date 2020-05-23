import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestEmployerComplaint {

    @Before
    public void setUp() throws Exception {

        //MainConsole.userList.put("s123", new Student("s123", "password", "s123@gmail.com", Availability.FullTime));

        MainConsole.userList.put("e123", new Employer("e123", "password", "e123@gmail.com"));
        MainConsole.userList.put("student1", new Student("student1","123","student1@email.com",Availability.FullTime));
        MainConsole.userList.put("student2", new Student("student2","123","student2@email.com",Availability.PartTime));
        MainConsole.userList.put("student3", new Student("student3","123","student3@gmail.com",Availability.PartTime));

    }

    @Test
    public void addComplaint() {
        Employer emp = (Employer) MainConsole.userList.get("e123");
        assertEquals(BlacklistStatus.NONE,emp.getBlacklistStatus());
        emp.addComplaint(new Complaint(MainConsole.userList.get("student1"),"Unprofessional"));
        emp.addComplaint(new Complaint(MainConsole.userList.get("student2"),"Uncivilised"));
        emp.addComplaint(new Complaint(MainConsole.userList.get("student3"),"Unprofessional"));
        assertEquals(BlacklistStatus.PROVISIONAL,emp.getBlacklistStatus());

    }
}