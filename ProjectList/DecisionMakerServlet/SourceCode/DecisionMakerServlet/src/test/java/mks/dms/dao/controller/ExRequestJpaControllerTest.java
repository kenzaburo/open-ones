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
package mks.dms.dao.controller;

import static org.junit.Assert.*;

import java.util.Date;

import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.Request;
import mks.dms.service.BaseService;
import mks.dms.service.RequestService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ThachLN
 *
 */
public class ExRequestJpaControllerTest {

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
     * Test method for {@link mks.dms.dao.controller.RequestJpaController#create(mks.dms.dao.entity.Request)}.
     */
    @Test
    public void testCreate() {
        fail("Not yet implemented");
    }

    @Test
    public void testCreate_001() {
        Request req = new Request();
        
        req.setTitle("UT 01");
        req.setCreated(new Date());
        req.setLikes(new String[] {"user1", "user2@test"});
        
        
        ExRequestJpaController daoCtrl = new ExRequestJpaController(BaseService.getEmf());
        daoCtrl.create(req);
        
        assertEquals(Integer.valueOf(1), req.getId());
        
        req = daoCtrl.findRequest(1);
        
        String[] likes = req.getListLikes();
        assertEquals(2, likes.length);
        assertEquals("user1", likes[0]);
        assertEquals("user2@test", likes[1]);
        
    }
    
    @Test
    public void testDestroy() {
        ExRequestJpaController daoCtrl = new ExRequestJpaController(BaseService.getEmf());
        try {
            daoCtrl.destroy(1);
        } catch (NonexistentEntityException ex) {
            fail(ex.getMessage());
        }
        
    }
    
}
