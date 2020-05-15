import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestChangePassword {

    @Before
    public void setUp() throws Exception {
        MainConsole.userList.put("s123", new Student("s123", "password", "s123@gmail.com", Availability.FullTime));
    }

    @Test(expected = InvalidInputException.class)
    public void setPassword() throws InvalidInputException {
        String newPassword = "password";
        MainConsole.userList.get("s123").setPassword(newPassword);

    }

    @Test
    public void setPassword1() throws InvalidInputException {
        String newPassword = "qwerty";

        assertTrue(MainConsole.userList.get("s123").setPassword(newPassword));
        assertEquals("qwerty",MainConsole.userList.get("s123").getPassword());
    }
}