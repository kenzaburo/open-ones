/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package initdb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mks.dms.dao.controller.DepartmentJpaController;
import mks.dms.dao.controller.ParameterJpaController;
import mks.dms.dao.controller.RequestTypeJpaController;
import mks.dms.dao.controller.RoleJpaController;
import mks.dms.dao.controller.TemplateJpaController;
import mks.dms.dao.controller.UserJpaController;
import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.Parameter;
import mks.dms.dao.entity.RequestType;
import mks.dms.dao.entity.Template;
import mks.dms.dao.entity.User;
//import mks.dms.dao.entity.UserRole;

import mks.dms.service.SystemService;

import org.junit.Test;

import rocky.common.Constant;
import rocky.common.FileUtil;
//import mks.dms.dao.controller.UserRoleJpaController;

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
    public void testInitSystemData() {
        SystemService systemService = new SystemService();
        systemService.initData("admin");
    }
    
    @Test 
    public void testInitCreateRank() {
    	ParameterJpaController daoCtrl = new ParameterJpaController(emf);
    	
    	Parameter rankBad = new Parameter();
    	rankBad.setCd("Rank");
    	rankBad.setName("Tệ");
    	rankBad.setValue("Bad");
    	daoCtrl.create(rankBad);
    	
    	Parameter rankNormal = new Parameter();
    	rankNormal.setCd("Rank");
    	rankNormal.setName("Bình thường");
    	rankNormal.setValue("Normal");
    	daoCtrl.create(rankNormal);
    	
    	Parameter rankGood = new Parameter();
    	rankGood.setCd("Rank");
    	rankGood.setName("Khá");
    	rankGood.setValue("Good");
    	daoCtrl.create(rankGood);
    	
    	Parameter rankPerfect = new Parameter();
    	rankPerfect.setCd("Rank");
    	rankPerfect.setName("Hoàn hảo");
    	rankPerfect.setValue("Perfect");
    	daoCtrl.create(rankPerfect);
    	
    	Parameter rankExcellent = new Parameter();
    	rankExcellent.setCd("Rank");
    	rankExcellent.setName("Xuất sắc, vượt trội");
    	rankExcellent.setValue("Excellent");
    	daoCtrl.create(rankExcellent);
    	
    	assertEquals(5, daoCtrl.getParameterCount());
    	
    }

    @Test
    public void testInitCreateUser() {
        System.out.println("create");
        String username = "user";
        boolean isEnable = true;
        User user = new User();
        
        user.setDepartmentCd("All");
        user.setDepartmentName("Tất cả");
        
        user.setUsername(username);
        user.setFirstname("User");
        user.setLastname("Mr");
        user.setEnabled(isEnable);
        user.setEmail("user@gmail.com");
        user.setCreated(new Date());
        user.setCreatedbyUsername("admin");
        
        UserJpaController daoCtrl = new UserJpaController(emf);
        daoCtrl.create(user);

        System.out.println("Created user id:" + user.getId());
        assertNotNull(user.getId());
        
        // Find created user
        User createdUser = daoCtrl.findUser(user.getId());
        assertEquals(username, createdUser.getUsername());
        assertEquals(isEnable, createdUser.getEnabled());

    }

    @Test
    public void testInitCreateUserForTokutokuya() {
        String username = "thang@test";
        boolean isEnable = true;
        User user = new User();
        
        user.setDepartmentCd("All");
        user.setDepartmentName("Tất cả");
        user.setCreated(new Date());
        user.setCreatedbyUsername("admin");
        
        user.setUsername(username);
        user.setFirstname("Thang");
        user.setLastname("Chau Duc");
        user.setEnabled(isEnable);
        user.setEmail("thang@testemail");
        
        UserJpaController daoCtrl = new UserJpaController(emf);
        daoCtrl.create(user);

        
        user.setDepartmentCd("All");
        user.setDepartmentName("Tất cả");
        
        user.setUsername("ngan@test");
        user.setFirstname("Ngan");
        user.setLastname("Ms");
        user.setEnabled(isEnable);
        user.setEmail("ngan@testemail");
        
        daoCtrl.create(user);

    }

    @Test
    public void testInitCreateUser01() {
        System.out.println("create");
        String username = "user01";
        boolean isEnable = true;
        User user = new User();
        user.setDepartmentCd("All");
        user.setDepartmentName("Tất cả");
        user.setCreated(new Date());
        user.setCreatedbyUsername("admin");

        user.setUsername(username);
        user.setEnabled(isEnable);
        user.setEmail("user01@gmail.com");
        
        UserJpaController daoCtrl = new UserJpaController(emf);
        daoCtrl.create(user);

        System.out.println("Created user id:" + user.getId());
        assertNotNull(user.getId());
        
        // Find created user
        User createdUser = daoCtrl.findUser(user.getId());
        assertEquals(username, createdUser.getUsername());
        assertEquals(isEnable, createdUser.getEnabled());

    }
    
    @Test
    public void testInitCreateUserSystem() {
        boolean isEnable = true;
        User user = new User();
        
        UserJpaController daoCtrl = new UserJpaController(emf);

        user.setUsername("manager");
        user.setFirstname("Manager");
        user.setLastname("Mr");
        user.setEnabled(isEnable);
        user.setEmail("manager@gmail.com");
        user.setCreated(new Date());
        user.setCreatedbyUsername("admin");
        daoCtrl.create(user);
    }
    
//    @Test
//    public void testInitCreateUserRoleSystem() {
//        boolean isEnable = true;
//        UserRole userRole = new UserRole();
//        userRole.setUsername("admin");
//        userRole.setEnabled(isEnable);
//        userRole.setRole("Admin");
//
//        
//        UserRoleJpaController daoCtrl = new UserRoleJpaController(emf);
//        daoCtrl.create(userRole);
//
//        userRole = new UserRole();
//        userRole.setUsername("manager");
//        userRole.setEnabled(isEnable);
//        userRole.setRole("Manager");
//        
//        daoCtrl.create(userRole);
//    }
    
//    @Test
//    public void testInitCreateDepartmentSystem() {
//        boolean isEnable = true;
//        Department department = new Department();
//        department.setCd("All");
//        department.setEnabled(isEnable);
//        department.setName("Tất cả");
//        department.setCreated(new Date());
//        department.setCreatedbyUsername("admin");
//        
//        DepartmentJpaController daoCtrl = new DepartmentJpaController(emf);
//        
//        daoCtrl.create(department);
//    }

    @Test
    public void testInitCreateTemplate() {
        Template template = new Template();
        template.setCd("tokutokuya-rule");
        template.setName("Thong bao");
        template.setCreated(new Date());
        template.setCreatedbyUsername("admin");
        
        String content;
        try {
            content = FileUtil.getContent("/testdata/html-template/template-rule.html", Constant.DEF_ENCODE);
            template.setContent(content);
            
            TemplateJpaController daoCtrl = new TemplateJpaController(emf);
            daoCtrl.create(template);
            
            // Find created user
            Template createdTemplate = daoCtrl.findTemplate(template.getId());
            assertEquals(content, createdTemplate.getContent());
        } catch (IOException ex) {
            fail(ex.getMessage());
        }


    }
}
