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
package ldap.util;

import static org.junit.Assert.*;
import ldap.entry.Entry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ThachLN
 *
 */
public class LdapServiceTest {

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

    /**
     * Test method for {@link ldap.util.LdapService#checkPassword(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testCheckPassword() {
        LdapService ldapService = new LdapService("/ldap.properties");
        String newPwd="12345";
        String userDN="admin";
        
        boolean success = ldapService.checkPassword("admin", "Admin12345");
        assertTrue(success);
        
        Entry groupEntry = ldapService.findGroups();
        assertEquals("", groupEntry.getGroup());
        assertEquals("", groupEntry.getName());
    }
    
    @Test
    public void testDeleteGroup() {
        LdapService ldapService = new LdapService("/ldap.properties");
        String groupDn = "ou=abc123456";
        boolean delOK = ldapService.deleteGroup(groupDn);
        
        assertFalse(delOK);
        
        //
        groupDn = "ou=abc123456, ou=Users,dc=maxcrc,dc=com";
        delOK = ldapService.deleteGroup(groupDn);
        
        assertTrue(delOK);
    }

}
