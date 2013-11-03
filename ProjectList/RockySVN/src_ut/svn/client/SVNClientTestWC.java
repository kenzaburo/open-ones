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

import java.io.File;
import java.util.Date;

import static junit.framework.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.tmatesoft.svn.cli.svn.SVNCommandEnvironment;
import org.tmatesoft.svn.cli.svn.SVNInfoCommand;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.auth.ISVNProxyManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNInfo;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 * @author thachln
 *
 */
public class SVNClientTestWC {
    private final static Logger LOG = Logger.getLogger("SVNClientTestWC");
    
    @Test
    public void testGetClientManager() {
        
    }
    /**
     * Test method for {@link svn.client.SVNClient#doCheckOut(java.util.Date)}.
     */
    @Test
    public void testGetInfo() {
        DefaultSVNOptions options = SVNWCUtil.createDefaultOptions(true);
        SVNClientManager clientManager = SVNClientManager.newInstance(options,"thachln","Fsoft@123");
        SVNWCClient wcClient = clientManager.getWCClient();
        
        SVNRevision revision = SVNRevision.HEAD;
        File path = new File("D:/Project/HCAM/svn-check");
        try {
            SVNInfo svnInfo  = wcClient.doInfo(path, revision);
            assertEquals("ThachLN", svnInfo.getAuthor());
            assertEquals(123, svnInfo.getRevision().getID());
            assertEquals(new Date(), svnInfo.getRevision().getDate());
        } catch (SVNException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        
    }
    
    @Test
    public void testGetInfo2() {
        DefaultSVNOptions options = SVNWCUtil.createDefaultOptions(true);
        SVNClientManager clientManager = SVNClientManager.newInstance(options,"","");
        SVNWCClient wcClient = clientManager.getWCClient();
        
        /*
         * For using over http:// and https://
         */
        DAVRepositoryFactory.setup();
        
        // Create configuration file in current folder
        File configFile = new File(".rockysvn");
        String username = "thachln";
        String password = "Fpt@12345";
        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(configFile, username,
                password);
        //String url = "https://open-ones.googlecode.com/svn/trunk/ProjectList/RockySVN";
        String url = "https://hcm-svn.fsoft.fpt.vn/svn/F15-HCAM/trunk";
        

        
        SVNRevision revision = SVNRevision.HEAD;
        File path = new File("D:/Project/MyProject/Open-Ones/RockySVN");
        try {
            SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(url));
            repository.setAuthenticationManager(authManager);
            repository.getDatedRevision(new Date());
            long latestRev = repository.getLatestRevision();
            
            assertEquals(634, latestRev);
            SVNInfo svnInfo  = wcClient.doInfo(path, revision);
            assertEquals("ThachLN", svnInfo.getAuthor());
            assertEquals(123, svnInfo.getRevision().getID());
            assertEquals(new Date(), svnInfo.getRevision().getDate());
        } catch (SVNException ex) {
            ex.printStackTrace();
            fail(ex.getMessage());
            
        }
        
    }

    
}
