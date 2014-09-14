/**
 * Licensed to Open-Ones under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package ldap.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import ldap.dao.GroupDAO;
import ldap.dao.UserDAO;
import ldap.entry.GroupEntry;
import ldap.entry.UserEntry;
import ldap.test.Config;

import org.junit.Test;

/**
 * @author ThachLN
 *
 */
public class UserDAOTest {
    private final static String rootDn = "dc=maxcrc,dc=com";
    /**
     * Test method for {@link ldap.dao.UserDAO#add(ldap.entry.Entry)}.
     */
    @Test
    public void testAddAdmin() {
        UserDAO userDao = new UserDAO(Config.getServerConfig());
        
        UserEntry userEntry = new UserEntry();
        userEntry.setFirstName("Admin");
        userEntry.setLastName("Mr");
        userEntry.setUid("admin");
        userEntry.setCommonName("Admin");
        userEntry.setPassword("admin");
        
        userEntry.setGroupOU("ou=Users, dc=oneworld, dc = com");
        userEntry.setDn("uid=admin, ou=Users, dc=oneworld, dc = com");
        userEntry.configAttributeSet();
        
        boolean result = userDao.add(userEntry);
        
        assertTrue(result);
    }
    
    /**
    * Testing add Group => Add User => Delete the user => Delete the group.
    */
    @Test
    public void testAddDemo1() {
        // Add group
        GroupDAO groupDao = new GroupDAO(Config.getServerConfig());
        String tmpGroupName = "TestGroup01";
        String groupDn = "ou=" + tmpGroupName + "," + rootDn;
        // Make sure the group does not existed
        GroupEntry groupEntry = (GroupEntry) groupDao.findGroupByDN(groupDn);
        assertNull(groupEntry);
        
        groupEntry = new GroupEntry(tmpGroupName, rootDn);
        groupDao.add(groupEntry);
        
        UserDAO userDao = new UserDAO(Config.getServerConfig());
        
        UserEntry userEntry = new UserEntry();
        userEntry.setFirstName("Demo1");
        userEntry.setLastName("Mr");
        userEntry.setUid("demo1");
        userEntry.setCommonName("Demo");
        userEntry.setPassword("changeit");
        
        
        userEntry.setGroupOU(groupDn);
        
        String userDn = "uid=demo1," + groupDn;
        userEntry.setDn(userDn);
        userEntry.configAttributeSet();
        
        boolean result = userDao.add(userEntry);
        
        assertTrue(result);
        
        // Find the user which have
        UserEntry foundUserEntry = (UserEntry) userDao.findByDN(userDn);
        assertNotNull(foundUserEntry);
        assertEquals("demo1", foundUserEntry.getUid());
        
        // Delete user
        boolean ok = userDao.deleteUser(userEntry);
        assertTrue(ok);
        
        // Delete group
        ok = groupDao.deleteGroup(groupDn);
        assertTrue(ok);
    }

    /**
     * Test method for {@link ldap.dao.UserDAO#addEntryList(java.util.List)}.
     */
    @Test
    public void testAddEntryList() {
        fail("Not yet implemented");
    }

}
