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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mks.dms.dao.controller.DepartmentJpaController;
import mks.dms.dao.controller.RequestTypeJpaController;
import mks.dms.dao.controller.RoleJpaController;
import mks.dms.dao.controller.TemplateJpaController;
import mks.dms.dao.controller.UserJpaController;
//import mks.dms.dao.controller.UserRoleJpaController;
import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.RequestType;
import mks.dms.dao.entity.Template;
import mks.dms.dao.entity.User;
//import mks.dms.dao.entity.UserRole;

import org.junit.Test;

import rocky.common.Constant;
import rocky.common.FileUtil;

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

    @Test
    public void testInitCreateUser() {
        System.out.println("create");
        String username = "user";
        boolean isEnable = true;
        User user = new User();
        user.setCd("001");
        
        user.setDepartmentCd("System");
        user.setDepartmentName("System");
        user.setDepartmentId(1);
        
        user.setUsername(username);
        user.setFirstname("User");
        user.setLastname("Mr");
        user.setEnabled(isEnable);
        user.setEmail("user@gmail.com");
        
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
    public void testInitCreateUser01() {
        System.out.println("create");
        String username = "user01";
        boolean isEnable = true;
        User user = new User();
        user.setDepartmentCd("System");
        user.setDepartmentName("System");
        user.setDepartmentId(1);
        user.setCd("002");
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
        user.setCd("000");
        user.setUsername("admin");
        user.setFirstname("Admin");
        user.setLastname("Mr");
        user.setEnabled(isEnable);
        user.setEmail("admin@gmail.com");
        user.setDepartmentId(0);
        user.setDepartmentCd("System");
        user.setDepartmentName("System");
        
        UserJpaController daoCtrl = new UserJpaController(emf);
        RoleJpaController daoRoleCtrl = new RoleJpaController(emf);
        daoCtrl.create(user);

        user = new User();
        user.setCd("010");
        user.setUsername("manager");
        user.setFirstname("Manager");
        user.setLastname("Mr");
        user.setEnabled(isEnable);
        user.setEmail("manager@gmail.com");
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
    
    @Test
    public void testInitCreateDepartmentSystem() {
        boolean isEnable = true;
        Department department = new Department();
        department.setCd("System");
        department.setEnabled(isEnable);
        department.setName("System");
        
        DepartmentJpaController daoCtrl = new DepartmentJpaController(emf);
        
        daoCtrl.create(department);
    }

    @Test
    public void testInitCreateTemplate() {
        Template template = new Template();
        template.setCd("tokutokuya-rule");
        
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
