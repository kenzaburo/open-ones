package ldap.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import ldap.dao.UserDAO;
import ldap.entry.Entry;
import ldap.util.ServerConfig;

import org.junit.Test;

public class TestAddListEntry {
    private ServerConfig ldapCfg = Config.getServerConfig();
    
	@Test
	public void test() {
		List<Entry> entryList = new ArrayList<Entry>();
		for (int i = 993; i < 1001; i++) {
		    //UserEntry se = new UserEntry("user" + i, i + "a", "a", "a@gmail.com", i + "", "01289816416", "user");
		    //entryList.add(se);
        }
		
		UserDAO entryDAO = new UserDAO(ldapCfg);
		assertEquals(true, entryDAO.addEntryList(entryList));
	}

}
