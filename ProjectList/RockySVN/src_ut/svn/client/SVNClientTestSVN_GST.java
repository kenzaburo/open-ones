/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
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
package svn.client;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author thachln
 *
 */
public class SVNClientTestSVN_GST {

    @Test
    public void testSVN_GST_CTU() {
        String svnUrl = "https://gst.fsoft.com.vn/svn/CTU_003_JSD_01_J1.2";
        String username = "student";
        String password = "123456";
        SVNClient svCln = SVNClient.newClientFromUrl(svnUrl, username, password);
        svCln.setWcPath("D:/Project/Campulink/CTU-MockProject/CTU_003_JSD_01_J1.2");
        
        // Checkout
        long lastRev = svCln.doCheckout(svnUrl);
        
        Assert.assertNotEquals(lastRev, 0);

        // Update
        long lastUpdateRev = svCln.doUpdate();
        Assert.assertNotEquals(lastUpdateRev, lastRev);
    }
    
    @Test
    public void testSVN_GSTInfra() {
        String svnUrl = "https://gst.fsoft.com.vn/svn/GSTInfra/AccountManagementLDAP/LdapManagementWeb/";
        String username = "your account";
        String password = "your password";
        SVNClient svCln = SVNClient.newClientFromUrl(svnUrl, username, password);
        svCln.setWcPath("D:/Temp/LdapManagementWeb");
        
        svCln.doCheckout(svnUrl);
    }
}
