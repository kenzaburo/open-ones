package openones.dc;

import static org.junit.Assert.*;

import org.junit.Test;

public class VinaHostCheckerTest {

    @Test
    public void testIsAvailable01() {
        IDomainChecker checker = new VinaHostCheckerImpl();
        boolean isAvail;
        isAvail = checker.isAvailable("open-ones", ".com");
        assertEquals(false, isAvail);

    }

    @Test
    public void testIsAvailable02() {
        IDomainChecker checker = new VinaHostCheckerImpl();
        boolean isAvail;

        isAvail = checker.isAvailable("openone", ".co");
        assertEquals(true, isAvail);

    }

    @Test
    public void testIsAvailableMultiExts01() {
        IDomainChecker checker = new VinaHostCheckerImpl();
        boolean[] checkResult = checker.checkAvailable("openone", new String[]{".com", ".co", "vn"});

        Boolean[] expectResult = new Boolean[checkResult.length];
        for (int i = 0; i < checkResult.length; i++) {
            expectResult[i] = checkResult[i];
        }
        assertArrayEquals(new Boolean[]{false, true, true}, expectResult);
    }

}
