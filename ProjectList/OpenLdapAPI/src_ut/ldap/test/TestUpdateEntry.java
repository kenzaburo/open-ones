package ldap.test;

import static org.junit.Assert.*;
import ldap.dao.UserDAO;
import ldap.entry.UserEntry;
import ldap.util.ServerConfig;

import org.junit.Test;


public class TestUpdateEntry {
    private ServerConfig ldapCfg = Config.getServerConfig();
    
	@Test
	public void test() {
		UserDAO entryDao = new UserDAO(ldapCfg);
		UserEntry entry = (UserEntry) entryDao.findByUid("adminldap");
		entry.setPassword("123456");
		//update value in AttributeSet
		entry.configAttributeSet();
		
		//update
		assertEquals(true, entryDao.update(entry));
	}

}
