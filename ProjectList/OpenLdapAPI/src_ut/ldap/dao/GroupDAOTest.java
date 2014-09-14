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

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import ldap.dao.GroupDAO;
import ldap.entry.Entry;
import ldap.entry.GroupEntry;
import ldap.test.Config;
import ldap.util.ServerConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ThachLe
 *
 */
public class GroupDAOTest {
    private ServerConfig ldapCfg = Config.getServerConfig();
    private String userOU = "ou=Users,dc=maxcrc,dc=com";
    
    /**
     * [Give the description for method].
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * [Give the description for method].
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddGroupByDN() {
        GroupDAO dao = new GroupDAO(ldapCfg);
        
        String groupDn = "ou=Test01,dc=maxcrc,dc=com";
        GroupEntry groupEntry = new GroupEntry("Test01", "dc=maxcrc,dc=com");
        
        // Find in the first time
        // Make sure the group does not existed.
        GroupEntry foundGroup = (GroupEntry) dao.findGroupByDN(groupDn);
        assertNull(foundGroup);
        
        boolean ok = dao.add(groupEntry);
        assertTrue(ok);
        
        // Find group
        foundGroup = (GroupEntry) dao.findGroupByDN(groupDn);
        assertNotNull(foundGroup);
        assertEquals(groupDn, foundGroup.getDn());
        
        // Delete group
        ok = dao.deleteGroup(groupDn);
        assertTrue(ok);
        
        // Find again
        foundGroup = (GroupEntry) dao.findGroupByDN(groupDn);
        assertNull(foundGroup);
    }
    
    @Test
    public void testDeleteGroupByDN() {
        GroupDAO dao = new GroupDAO(ldapCfg);
        
        String groupDn = "ou=Test01,dc=maxcrc,dc=com";
        // Delete group
        boolean ok = dao.deleteGroup(groupDn);
        assertTrue(ok);
        
        // Find again
        GroupEntry foundGroup = (GroupEntry) dao.findGroupByDN(groupDn);
        assertNull(foundGroup);
    }
    
    
    /**
     * Test method for {@link ldap.dao.GroupDAO#findGroupByDN(java.lang.String)}.
     */
    @Test
    public void testFindGroupByDN() {
        GroupDAO dao = new GroupDAO(ldapCfg);
        GroupEntry entry = (GroupEntry) dao.findGroupByDN(userOU);
        
        List<Entry> lstEntry = entry.getEntryList();
        Iterator<Entry> itEntry = lstEntry.iterator();
        
        System.out.println("List group entries...");
        Entry aGroupEntry;
        while (itEntry.hasNext()) {
            aGroupEntry = itEntry.next();
            System.out.println("Group name=" + aGroupEntry.getName());
        }
    }

}
