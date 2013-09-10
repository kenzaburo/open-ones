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

import org.apache.log4j.Logger;
import org.junit.Test;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.wc.SVNInfo;

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
        SVNClient svnCln = new SVNClient("D:/Project/HCAM/svn/trunk/doc", "", "");
        svnCln.getRev();
        try {
            SVNInfo svnInfo = svnCln.getInfo();
            svnInfo.getAuthor();
            svnInfo.getRevision();
        } catch (SVNException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }
}
