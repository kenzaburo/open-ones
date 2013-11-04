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
package org.tmatesoft.svn.cli.svn;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tmatesoft.svn.cli.svn.SVN;

/**
 * @author thachle
 *
 */
public class SVNLauncherTest {

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
     * Test method for {@link org.tmatesoft.svn.cli.svn.SVN#main(java.lang.String[])}.
     */
    @Test
    public void testInfo() {
        String cmd = "RockyInfo";
        String path = "/media/Thach/HCAM/svn/source/PanoramaSystem/CamSysViewer/";

        String[] args = {cmd, path};
        SVNLauncher.main(args);
    }

}
