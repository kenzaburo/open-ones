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
package mks.dms.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import mks.dms.dao.entity.Request;
import mks.dms.util.AppCons;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ThachLe
 *
 */
public class MasterServiceTest {

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
     * Test method for {@link mks.dms.service.MasterService#getAllStatus()}.
     */
    @Test
    public void testGetAllStatus() {
        MasterService masterService = new MasterService();
        List<String> lstStatus = masterService.getAllStatus();
        
        assertNotNull(lstStatus);
        assertEquals(5, lstStatus.size());
        assertEquals(AppCons.STATUS_CREATED, lstStatus.get(0));
    }

    /**
     * Test method for {@link mks.dms.service.MasterService#getNextStatus(mks.dms.dao.entity.Request)}.
     */
    @Test
    public void testGetNextStatus() {
        MasterService masterService = new MasterService();

        String username = null;
        Request request = new Request();
        request.setStatus(AppCons.STATUS_CREATED);
        request.setRequesttypeCd(AppCons.TASK);
        
        List<String> lstOnwerNextStatus = masterService.getNextStatus(request , AppCons.TYPE_USER.Owner);
        

        assertEquals(AppCons.STATUS_DOING, lstOnwerNextStatus.get(0));
    }

}
