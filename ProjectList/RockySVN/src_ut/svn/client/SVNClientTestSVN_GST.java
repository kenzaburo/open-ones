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

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author thachln
 *
 */
public class SVNClientTestSVN_GST {

    @Test
    public void testSVN_GST_HCMUT() {
        String svnUrl = "https://gst.fsoft.com.vn/svn/HCMUT_012_JSD_01_13/trunk";
        String username = "campuslink.reviewer@fsoft.com.vn";
        String password = "123456";
        String wcPath = "D:/Projects/Campulink/svn/trunk/wip/hcm_hcmut/MockProject/svn/HCMUT_012_JSD_01_13/trunk";
        
        WCAnalyzer wcAnalyzer = new WCAnalyzer(svnUrl, wcPath , username, password);
        
        wcAnalyzer.start();
        
    }
    
}
