package ldap.test;

import ldap.dao.UserDAO;
import ldap.entry.UserEntry;
import ldap.util.ServerConfig;

import org.junit.Assert;
import org.junit.Test;

public class TestChangePwd {
    
    private ServerConfig ldapCfg = Config.getServerConfig();
    
    @Test
    public void test_N(){
        UserDAO entryDao = new UserDAO(ldapCfg);
        UserEntry entry = (UserEntry) entryDao.findByUid("user1");
        Assert.assertEquals(true, entryDao.changePassword(entry.getUid(), "user2", "542829214"));
    }
    
}
