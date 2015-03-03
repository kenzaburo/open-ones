/**
 * Licensed to FPT-Software under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * FPT-Software licenses this file to you under the Apache License,
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

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author ThachLN
 *
 */
public class WCAnalyzerTest {
    String svnUrl = "https://open-ones.googlecode.com/svn/trunk/ProjectList/RockyCommon";
    String wcPath = "D:/Temp/TestSVN/RockyCommon";
    String username = null;
    String password = null;

    /**
     * Test method for {@link svn.client.WCAnalyzer#doCheckout(org.tmatesoft.svn.core.SVNURL)}.
     */
    @Test
    public void testDoCheckoutSVNURL() {
        WCAnalyzer wcAnalyzer = new WCAnalyzer(svnUrl, wcPath, username, password);
        wcAnalyzer.doCheckout(svnUrl);
    }
    
    /**
     * Test method for {@link svn.client.WCAnalyzer#WCAnalyzer(java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testWCAnalyzerStringStringString() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link svn.client.WCAnalyzer#WCAnalyzer(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testWCAnalyzerStringStringStringString() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link svn.client.WCAnalyzer#WCAnalyzer(java.lang.String)}.
     */
    @Test
    public void testWCAnalyzerString() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link svn.client.WCAnalyzer#getClientManager()}.
     */
    @Test
    public void testGetClientManager() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link svn.client.WCAnalyzer#getWcPath()}.
     */
    @Test
    public void testGetWcPath() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link svn.client.WCAnalyzer#doCheckout(java.lang.String)}.
     */
    @Test
    public void testDoCheckoutString() {

        WCAnalyzer wcAnalyzer = new WCAnalyzer(svnUrl, wcPath, username, password);
    }


    /**
     * Test method for {@link svn.client.WCAnalyzer#doUpdate()}.
     */
    @Test
    public void testDoUpdate() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link svn.client.WCAnalyzer#doUpdate(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testDoUpdateStringString() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link svn.client.WCAnalyzer#doUpdate(java.util.Date)}.
     */
    @Test
    public void testDoUpdateDate() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link svn.client.WCAnalyzer#getInfo()}.
     */
    @Test
    public void testGetInfo() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link svn.client.WCAnalyzer#getInfo(java.lang.String)}.
     */
    @Test
    public void testGetInfoString() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link svn.client.WCAnalyzer#getInfo(java.io.File)}.
     */
    @Test
    public void testGetInfoFile() {
        fail("Not yet implemented");
    }

}
