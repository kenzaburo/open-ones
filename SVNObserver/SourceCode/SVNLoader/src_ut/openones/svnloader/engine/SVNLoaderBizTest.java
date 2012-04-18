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
package openones.svnloader.engine;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class SVNLoaderBizTest {

    @Test
    public void testTransfer0001_Local() {
        try {
            String username = "";
            String password = "";
            String url = "file:///I:/Projects/LunarCal/Wip/Source/LunarCal";
            //String url = "file:///I:/Projects/Open-Ones Group/GoogleCode/trunk/CodeProObserver/SourceCode/SVNLoader/testdata/svnrepo01";
            String path = "D:/Temp/News";
            SVNLoaderBiz svn2DBBiz = new SVNLoaderBiz(url, username, password, path, "Project01", -1);
            svn2DBBiz.transfer();

            assertTrue("Check the result in the database!", true);
        } catch (Throwable th) {
            th.printStackTrace();
            fail(th.getMessage());
        }
    }
    
    @Test
    public void testTransfer0002_Local() {
        try {
            String username = "";
            String password = "";
            String url = "file:///I:/Projects/Open-OnesGroup/RockySVNRepoWip/trunk/SourceCode/SVNRepoTest";
            //String url = "file:///I:/Projects/Open-Ones Group/GoogleCode/trunk/CodeProObserver/SourceCode/SVNLoader/testdata/svnrepo01";
            String path = "D:/Temp/News";
            SVNLoaderBiz svn2DBBiz = new SVNLoaderBiz(url, username, password, path, "SVNRepoTest", -1);
            svn2DBBiz.transfer();

            assertTrue("Check the result in the database!", true);
        } catch (Throwable th) {
            th.printStackTrace();
            fail(th.getMessage());
        }
    }

    @Test
    public void testTransfer0002_ProjectList() {
        try {
            String username = "";
            String password = "";
            String url = "https://open-ones.googlecode.com/svn/trunk/ProjectList";
            String path = "D:/Temp/News/ProjectList";
            SVNLoaderBiz svn2DBBiz = new SVNLoaderBiz(url, username, password, path, "ProjectList", -1);
            svn2DBBiz.transfer();

            assertTrue("Check the result in the database!", true);
        } catch (Throwable th) {
            th.printStackTrace();
            fail(th.getMessage());
        }
    }
    @Test
    public void testTransfer0002_GoogleCode() {
        try {
            String username = "";
            String password = "";
            String url = "http://open-ones.googlecode.com/svn/trunk/CoreWa";
            String path = "D:/Temp/News";
            SVNLoaderBiz svn2DBBiz = new SVNLoaderBiz(url, username, password, path, "CoreWa", -1);
            svn2DBBiz.transfer();

            assertTrue("Check the result in the database!", true);
        } catch (Throwable th) {
            th.printStackTrace();
            fail(th.getMessage());
        }
    }
    
    @Test
    public void testTransfer0002_VietCheck() {
        try {
            String username = "fsofter";
            String password = "fsofter12345";
            String url = "https://rai-server/svn/PRM/trunk/Source/VietCheck";
            String path = "D:/Temp/News";
            SVNLoaderBiz svn2DBBiz = new SVNLoaderBiz(url, username, password, path, "VietCheck", -1);
            svn2DBBiz.transfer();

            assertTrue("Check the result in the database!", true);
        } catch (Throwable th) {
            th.printStackTrace();
            fail(th.getMessage());
        }
    }
    @Test
    public void testTransfer0002_Ilunarcal() {
        try {
            String username = "fsofter";
            String password = "Test@123";
            String url = "https://raiservices/svn/ILunarCal/trunk";
            String path = "D:/Temp/News";
            SVNLoaderBiz svn2DBBiz = new SVNLoaderBiz(url, username, password, path, "Ilunarcal", 690);
            svn2DBBiz.transfer();

            assertTrue("Check the result in the database!", true);
        } catch (Throwable th) {
            th.printStackTrace();
            fail(th.getMessage());
        }
    }
//    @Test
//    public void testTransfer0002_MockProject() {
//        try {
//            String username = "fsofter";
//            String password = "Test@123";
//            String url = "https://raiservices/svn/FR-HCM05/trunk/MockProject";
//            String path = "D:/Temp/News";
//            SVNLoaderBiz svn2DBBiz = new SVNLoaderBiz(url, username, password, path, "MockProject", -1);
//            svn2DBBiz.transfer();
//
//            assertTrue("Check the result in the database!", true);
//        } catch (Throwable th) {
//            th.printStackTrace();
//            fail(th.getMessage());
//        }
//    }
//    @Test
//    public void testTransfer0002_test() {
//        try {
//            String username = "";
//            String password = "";
//            String url = "file:///D:/OJT2/Test";
//            String path = "D:/Temp/News";
//            SVNLoaderBiz svn2DBBiz = new SVNLoaderBiz(url, username, password, path, "test", -1);
//            svn2DBBiz.transfer();
//
//            assertTrue("Check the result in the database!", true);
//        } catch (Throwable th) {
//            th.printStackTrace();
//            fail(th.getMessage());
//        }
//    }
    @Test
    public void testTransfer0002_iDictionary() {
        try {
            String username = "fsoter";
            String password = "Test@123";
            String url = "https://raiservices/svn/IDictionary";
            String path = "D:/Temp/News";
            SVNLoaderBiz svn2DBBiz = new SVNLoaderBiz(url, username, password, path, "iDictionary", 895);
            svn2DBBiz.transfer();

            assertTrue("Check the result in the database!", true);
        } catch (Throwable th) {
            th.printStackTrace();
            fail(th.getMessage());
        }
    }
    @Test
    public void testTransfer0002_RsPortal() {
        try {
            String username = "fsofter";
            String password = "Test@123";
            String url = "https://raiservices/svn/RsPortal";
            String path = "D:/Temp/News";
            SVNLoaderBiz svn2DBBiz = new SVNLoaderBiz(url, username, password, path, "RsPortal", 80);
            svn2DBBiz.transfer();

            assertTrue("Check the result in the database!", true);
        } catch (Throwable th) {
            th.printStackTrace();
            fail(th.getMessage());
        }
    }
    @Test
    public void testTransfer0002_NxForum() {
        try {
            String username = "fsofter";
            String password = "Test@123";
            String url = "https://raiservices/svn/NxForum";
            String path = "D:/Temp/News";
            SVNLoaderBiz svn2DBBiz = new SVNLoaderBiz(url, username, password, path, "NxForum", 80);
            svn2DBBiz.transfer();

            assertTrue("Check the result in the database!", true);
        } catch (Throwable th) {
            th.printStackTrace();
            fail(th.getMessage());
        }
    }
    @Test
    public void testTransfer0002_iFood() {
        try {
            String username = "fsofter";
            String password = "Test@123";
            String url = "https://raiservices/svn/iFood";
            String path = "D:/Temp/News";
            SVNLoaderBiz svn2DBBiz = new SVNLoaderBiz(url, username, password, path, "iFood", 230);
            svn2DBBiz.transfer();

            assertTrue("Check the result in the database!", true);
        } catch (Throwable th) {
            th.printStackTrace();
            fail(th.getMessage());
        }
    }
    @Test
    public void testTransfer0002_PRM() {
        try {
            String username = "fsofter";
            String password = "Test@123";
            String url = "https://raiservices/svn/PRM";
            String path = "D:/Temp/News";
            SVNLoaderBiz svn2DBBiz = new SVNLoaderBiz(url, username, password, path, "PRM", 540);
            svn2DBBiz.transfer();

            assertTrue("Check the result in the database!", true);
        } catch (Throwable th) {
            th.printStackTrace();
            fail(th.getMessage());
        }
    }
    @Test
    public void testTransfer0002_iPresenter() {
        try {
            String username = "fsofter";
            String password = "Test@123";
            String url = "https://raiservices/svn/iPresenter";
            String path = "D:/Temp/News";
            SVNLoaderBiz svn2DBBiz = new SVNLoaderBiz(url, username, password, path, "iPresenter", 420);
            svn2DBBiz.transfer();

            assertTrue("Check the result in the database!", true);
        } catch (Throwable th) {
            th.printStackTrace();
            fail(th.getMessage());
        }
    }
    
}
