/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package initdb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mks.dms.dao.controller.RequestTypeJpaController;
import mks.dms.dao.entity.RequestType;

import org.junit.Test;

/**
 *
 * @author ThachLe
 */
public class InitDataTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("DecisionMaker-DBModelPU");
    public InitDataTest() {
    }
    

    /**
     * Test of create method, of class RequestTypeJpaController.
     */
    @Test
    public void testInitRequestTypes() {
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

}
