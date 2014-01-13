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
package vn.com.mks.ca.biz;

import static org.junit.Assert.*;

import java.io.FileFilter;

import org.junit.Test;

import rocky.sizecounter.ISizeCounter;
import rocky.sizecounter.SizeCounterImpl;

import vn.com.mks.ca.gui.FileFilterImpl;

/**
 * @author ThachLN
 *
 */
public class BizProcessorTest {

    @Test
    public void testGetNextVersion() {
        String wcPath = "D:/Project/HCAM/svn-check/source";
        long startRevision = 5;
        
        // Update at revision 4
        int retCd = BizProcessor.updateSVN(wcPath, startRevision);
        assertEquals(0, retCd);
        
        // Check next revision
        long nextRevision = BizProcessor.getNextRevision(wcPath);
        assertEquals(startRevision + 1, nextRevision);
    }
    
    @Test
    public void testUpdateSVN_02_OverNumber() {
        String wcPath = "D:/Project/HCAM/svn-check/source";
        
        // Update the revision 5
        
        long startRevision = 9999999;
        int retCd = BizProcessor.updateSVN(wcPath, startRevision);
        assertEquals(0, retCd);
    }
    
    /**
     * Test method for {@link vn.com.mks.ca.biz.BizProcessor#updateSVN(java.lang.String, long)}.
     */
    @Test
    public void testStatisticWC_Init() {
        String wcPath = "D:/Project/HCAM/svn-check/source";
        
        // Update the revision 5
        
        long startRevision = 5;
        int retCd = BizProcessor.updateSVN(wcPath, startRevision);
        assertEquals(0, retCd);
        
        ISizeCounter sizeCounter = new SizeCounterImpl();
        FileFilter fileFilter = new FileFilterImpl(sizeCounter );
        // Parse the working copy folder to database
        BizProcessor bizProcessor = new BizProcessor(fileFilter);
        bizProcessor.analyzeFolder(wcPath);
        
    }
    
    @Test
    public void testStatisticWC_Run() {
        String wcPath = "D:/Project/HCAM/svn-check/source";
        long maxRevision = 3014;
        
        // Update the revision 5
        long currentRevision = BizProcessor.getNextRevision(wcPath);
        long nextRevision = currentRevision;
        ISizeCounter sizeCounter = new SizeCounterImpl();
        FileFilter fileFilter = new FileFilterImpl(sizeCounter);
        // Parse the working copy folder to database
        BizProcessor bizProcessor = new BizProcessor(fileFilter);
        int retCd;
        do {
            if (nextRevision == -1) {
                fail("Could not get next revsion");
            }
            retCd = BizProcessor.updateSVN(wcPath, nextRevision);
            assertEquals(0, retCd);

            bizProcessor.analyzeFolder(wcPath);
            nextRevision++;
        } while (nextRevision <= maxRevision);
        
        assertTrue(true);
    }

}
