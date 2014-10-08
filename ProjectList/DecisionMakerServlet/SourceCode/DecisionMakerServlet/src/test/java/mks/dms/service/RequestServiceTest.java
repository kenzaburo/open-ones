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

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import mks.dms.dao.controller.ExRequestJpaController;
import mks.dms.dao.controller.RequestJpaController;
import mks.dms.dao.entity.Request;
import mks.dms.model.SearchRequestConditionModel;
import mks.dms.util.AppCons;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ThachLe
 *
 */
public class RequestServiceTest {

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
     * Test method for {@link mks.dms.service.RequestService#saveOrUpdate(mks.dms.dao.entity.Request)}.
     */
    @Test
    public void testSaveOrUpdate() {
        // Get request
        RequestJpaController reqCtrl = new RequestJpaController(BaseService.getEmf());
        Request req = reqCtrl.findRequest(4);
        
        req.setTitle(req.getTitle() + " Updated at " + new Date());
        
        RequestService reqService = new RequestService();
        int retCd = reqService.saveOrUpdate(req);
        
        assertEquals(2, retCd);
        
        
    }

    @Test
    public void testFindByCondition() {
        ExRequestJpaController reqCtrl = new ExRequestJpaController(BaseService.getEmf());
        SearchRequestConditionModel searchCond = new SearchRequestConditionModel();
        Request requestCond = new Request();
        requestCond.setRequesttypeCd(AppCons.TASK);
        searchCond.setRequest(requestCond );
        
        List<Request> foundRequests = reqCtrl.findRequestByCondition(searchCond);
        
        assertNotNull(foundRequests);
        assertEquals(3, foundRequests.size());
    }
}
