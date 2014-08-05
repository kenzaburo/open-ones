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
import org.tmatesoft.svn.core.SVNException;

/**
 * @author thachln
 *
 */
public class SVNClientTest {

    @Test
    public void testGetClientManager() {
        
    }
    /**
     * Test method for {@link svn.client.SVNClient#doCheckOut(java.util.Date)}.
     */
    @Test
    public void testDoUpdateDate() {
        SVNClient svCln = SVNClient.newClientFromConfiguration("/svn-cam.properties");
        svCln.doUpdate("2013/09/23", "yyyy/MM/dd");
    }

    /**
     * Test method for {@link svn.client.SVNClient#doCheckOut(java.util.Date)}.
     */
    @Test
    public void testDoCheckOut() {
        SVNClient svCln = SVNClient.newClientFromConfiguration("/svn-cam.properties");
        svCln.doCheckout("https://hcm-svn.fsoft.fpt.vn/svn/F15-HCAM/trunk/source");
    }
    
    @Test
    public void testDoUpdateDate2() {
        SVNClient svCln = SVNClient.newClientFromConfiguration("/svn-cam.properties");
        svCln.doUpdate2();
    }
    
    @Test
    public void testHttps() {
        String svnUrl = "https://subversion.assembla.com/svn/team6capstoneprj/trunk";
        String username = "thachln";
        String password = "assembla1qaz";
        SVNClient svCln = SVNClient.newClientFromUrl(svnUrl, username, password);
        svCln.setWcPath("D:/Project/MyProject/Salary3P");
        
        svCln.doCheckout(svnUrl);
    }
    
    @Test
    public void testgetLattestRevision() {
        String svnUrl = "http://gst.fsoft.com.vn/svn/SVNObserver/";
        String username = "manhp@gmail.com";
        String password = "abc";
       SVNClient svnClient = SVNClient.newClientFromUrl(svnUrl, username, password);
       long r;
    try {
        r = svnClient.getLattestRevision();
        System.out.println("Revision " + r);
        Assert.assertEquals(5, r);
    } catch (SVNException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
       
    }
   
    @Test
    public void testgetInfo() {
        String svnUrl = "http://gst.fsoft.com.vn/svn/SVNObserver/";
        String username = "manhp@gmail.com";
        String password = "abc";
       SVNClient svnClient = SVNClient.newClientFromUrl(svnUrl, username, password);
       svnClient.setWcPath("/home/admin/Downloads/SVNObserver/SVN/SVNObserver/trunk/source/SVNObserver/SVNLoader");
       long r;
    try {
        r = svnClient.getInfo().getRevision().getNumber();
        System.out.println("Revision " + r);
        Assert.assertEquals(5, r);
    } catch (SVNException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
       
    }
    
    @Test
    public void testgetLocalInfo() {
        String svnUrl = "http://gst.fsoft.com.vn/svn/SVNObserver/";
        String username = "manhp@gmail.com";
        String password = "abc";
       SVNClient svnClient = SVNClient.newClientFromUrl(svnUrl, username, password);
       svnClient.setWcPath("/home/admin/Downloads/SVNObserver/SVN/SVNObserver/trunk/source/SVNObserver/SVNLoader");
       long r;
    try {
        r = svnClient.getLocalInfo().getRevision().getNumber();
        System.out.println("Revision " + r);
        Assert.assertEquals(5, r);
    } catch (SVNException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
       
    }
}
