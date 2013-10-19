package rocky.common;

import java.io.IOException;
import java.util.Properties;

import junit.framework.TestCase;

public class BeanUtilTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testPropsToEntity() {
        try {
            Properties props = PropertiesManager.newInstanceFromProps("/testPropsToEntity.properties");
            BeanEntity ent = (BeanEntity) BeanUtil.propsToEntity(props, BeanEntity.class);
            
            assertEquals("Rocky", ent.getName());
        } catch (IOException ex) {
            fail(ex.getMessage());
            ex.printStackTrace();
        }
    }

}
