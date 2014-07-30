/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mks.dms.dao.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mks.dms.dao.entity.RequestType;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author ThachLe
 */
public class RequestTypeJpaControllerTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("DecisionMaker-DBModelPU");
    
    public RequestTypeJpaControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getEntityManager method, of class RequestTypeJpaController.
     */
    @Test
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
        RequestTypeJpaController instance = null;
        EntityManager expResult = null;
        EntityManager result = instance.getEntityManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class RequestTypeJpaController.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        RequestType requestType = new RequestType();
        requestType.setCd("Task");
        requestType.setName("Công việc");
        requestType.setEnabled(true);
        

        RequestTypeJpaController daoCtrl = new RequestTypeJpaController(emf);
        daoCtrl.create(requestType);
        
        requestType.setId(null);
        requestType.setCd("Rule");
        requestType.setName("Quy định");
        daoCtrl.create(requestType);

        requestType.setId(null);
        requestType.setCd("Announcement");
        requestType.setName("Thông báo");
        daoCtrl.create(requestType);
        
        requestType.setId(null);
        requestType.setCd("Leave");
        requestType.setName("Nghỉ phép");
        daoCtrl.create(requestType);
        
        // Find created request types
        List<RequestType> requestTypes = daoCtrl.findRequestTypeEntities();
        assertNotNull(requestTypes);
        assertEquals(4, requestTypes.size());

    }

    /**
     * Test of edit method, of class RequestTypeJpaController.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        RequestType requestType = null;
        RequestTypeJpaController instance = null;
        instance.edit(requestType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class RequestTypeJpaController.
     */
    @Test
    public void testDestroy() throws Exception {
        System.out.println("destroy");
        Integer id = null;
        RequestTypeJpaController instance = null;
        instance.destroy(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findRequestTypeEntities method, of class RequestTypeJpaController.
     */
    @Test
    public void testFindRequestTypeEntities_0args() {
        System.out.println("findRequestTypeEntities");
        
        
        RequestTypeJpaController instance = new RequestTypeJpaController(emf);
        //List<RequestType> expResult = null;
        List<RequestType> result = instance.findRequestTypeEntities();
        //assertEquals(expResult, result);
        assertNotNull(result);
        assertEquals(4, result.size());

    }

    /**
     * Test of findRequestTypeEntities method, of class RequestTypeJpaController.
     */
    @Test
    public void testFindRequestTypeEntities_int_int() {
        System.out.println("findRequestTypeEntities");
        int maxResults = 0;
        int firstResult = 0;
        RequestTypeJpaController instance = null;
        List<RequestType> expResult = null;
        List<RequestType> result = instance.findRequestTypeEntities(maxResults, firstResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findRequestType method, of class RequestTypeJpaController.
     */
    @Test
    public void testFindRequestType() {
        System.out.println("findRequestType");
        Integer id = null;
        RequestTypeJpaController instance = null;
        RequestType expResult = null;
        RequestType result = instance.findRequestType(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRequestTypeCount method, of class RequestTypeJpaController.
     */
    @Test
    public void testGetRequestTypeCount() {
        System.out.println("getRequestTypeCount");
        RequestTypeJpaController instance = null;
        int expResult = 0;
        int result = instance.getRequestTypeCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
