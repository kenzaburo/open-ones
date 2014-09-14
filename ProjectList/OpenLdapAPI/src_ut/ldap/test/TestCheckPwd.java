package ldap.test;

import ldap.dao.UserDAO;
import ldap.util.ServerConfig;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class TestCheckPwd {

    private ServerConfig ldapCfg = Config.getServerConfig();
    private final static Logger LOG = Logger.getLogger(TestChangePwd.class);
    @Test
    public void testCheckPwd(){
        
        UserDAO user = new UserDAO(ldapCfg);
            boolean result = user.checkPass("user1", "111111");
            Assert.assertEquals(true, result);

        

    }
    
}
