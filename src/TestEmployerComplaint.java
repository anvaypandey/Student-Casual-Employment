import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestEmployerComplaint {

    @Before
    public void setUp() throws Exception {

        //MainConsole.userList.put("s123", new Student("s123", "password", "s123@gmail.com", Availability.FullTime));

        MainConsole.userList.put("e123", new Employer("e123", "password", "e123@gmail.com"));

    }

    @Test
    public void addComplaint() {
        Employer emp = (Employer) MainConsole.userList.get("e123");
        assertEquals(BlacklistStatus.NONE,emp.getBlacklistStatus());
        emp.addComplaint("Unprofessional");
        emp.addComplaint("UnCivilised");
        emp.addComplaint("Unprofessional");
        assertEquals(BlacklistStatus.PROVISIONAL,emp.getBlacklistStatus());

    }
}