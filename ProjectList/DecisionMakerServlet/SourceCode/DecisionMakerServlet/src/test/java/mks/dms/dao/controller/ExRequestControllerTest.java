package mks.dms.dao.controller;
import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.User;
import mks.dms.service.BaseService;
import mks.dms.util.AppCons;

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
    public void testgetListRequestByCreatedbyCd() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DecisionMaker-DBModelPU");
        ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
        List<Request> listRequest = daoCtrl.getListRequestByCreatedbyCdAndStatusAndReadstatus("001", "Created", 1);
        
        assertEquals(1, listRequest.size());
    }
    
    @Test
    public void testgetListRequestByManagerCd() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DecisionMaker-DBModelPU");
        ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
        List<Request> listRequest = daoCtrl.getListRequestByManagerCdAndStatusAndReadstatus("admin", "Updated", 2);
        
        assertEquals(1, listRequest.size());
    }
    
    @Test
    public void testFindRequestByCd() {        
        ExRequestJpaController daoCtrl = new ExRequestJpaController(BaseService.getEmf());
        List<Request> lstAnnouncement = daoCtrl.findRequestByType(AppCons.ANNOUNCEMENT);
        
        assertEquals(3, lstAnnouncement.size());
        
        List<Request> lstRule = daoCtrl.findRequestByType(AppCons.RULE);
        
        assertEquals(2, lstRule.size());
        
        List<Request> lstTask = daoCtrl.findRequestByType(AppCons.TASK);
        
        assertEquals(5, lstTask.size());
    }
}
