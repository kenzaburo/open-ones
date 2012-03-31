package openones.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class DomainTest {

    @Test
    public void testCheckYes() {
        boolean isRegister = new Domain().isAvailable("gogo", ".com");
        assertFalse(isRegister);
    }

    @Test
    public void testCheckNo() {
        boolean isRegister = new Domain().isAvailable("gogogfgdd", ".com");
        assertTrue(isRegister);
    }
}
