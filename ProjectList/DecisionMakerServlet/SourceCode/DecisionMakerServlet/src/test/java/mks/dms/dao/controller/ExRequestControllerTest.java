package mks.dms.dao.controller;
import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.apache.log4j.Logger;

/**
 * @author truongtho88.nl
 *
 */
public class ExRequestControllerTest {
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
     * Test method for {@link mks.dms.dao.controller.ExUserJpaController#getListRequestByUsername(java.lang.String)}.
     */
    
    @Test
    public void testgetListRequestByCreatename() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DecisionMaker-DBModelPU");
        ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
        List<Request> listRequest = daoCtrl.getListRequestByCreatenameAndStatusAndReadstatus("user", "Created", 2);
        
        assertEquals(1, listRequest.size());
    }
    
    @Test
    public void testgetListRequestByManagername() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DecisionMaker-DBModelPU");
        ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
        List<Request> listRequest = daoCtrl.getListRequestByManagernameAndStatusAndReadstatus("admin", "Updated", 2);
        
        assertEquals(1, listRequest.size());
    }
}
