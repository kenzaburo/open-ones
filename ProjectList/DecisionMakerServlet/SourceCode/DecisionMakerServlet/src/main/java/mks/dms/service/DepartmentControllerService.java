package mks.dms.service;

import java.util.List;

import mks.dms.dao.controller.DepartmentJpaController;
import mks.dms.dao.controller.ExDepartmentJpaController;
import mks.dms.dao.controller.UserJpaController;
import mks.dms.dao.controller.exceptions.IllegalOrphanException;
import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.User;
import mks.dms.extentity.ExUser;
import mks.dms.model.RequestCreateModel;
import mks.dms.util.AppCons;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @description Get data from database and map to model controller
 * @author truongtho88.nl
 */
@Service
public class DepartmentControllerService extends BaseService{
	// Logger to log information to console.
    private final static Logger LOG = Logger.getLogger(UserControllerService.class);
    
    /**
     * Get all of Department.
     * <br/>
     * @return list of Department
     */
    public List<Department> getAllDepartment() {
    	List<Department> listDepartment;
    	
    	DepartmentJpaController departmentDaoCtrl = new DepartmentJpaController(emf);
    	
    	listDepartment = departmentDaoCtrl.findDepartmentEntities();
    	
    	return listDepartment;
    }
}
