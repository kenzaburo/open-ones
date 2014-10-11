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

import java.util.Date;
import java.util.List;

import mks.dms.dao.controller.DepartmentJpaController;
import mks.dms.dao.controller.RequestTypeJpaController;
import mks.dms.dao.controller.UserJpaController;
import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.RequestType;
import mks.dms.dao.entity.User;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import rocky.common.CommonUtil;

/**
 * @author ThachLN
 */
@Service
public class SystemService extends BaseService {
    /** Logger. */
    private final static Logger LOG = Logger.getLogger(SystemService.class);

    /**
    * [Give the description for method].
     * @param username 
    * @return
    * false: Data is existed | Error during create data
    */
    public boolean initData(String username) {
        // Create Types of requests
        boolean initOK = initRequestTypes(username);

        if (initOK) {
            // Create default Department
            initOK = initCreateDepartmentSystem(username);
        } else {
           return false;
        }
        
        if (initOK) {
            initUserSystem(username);
        } else {
            return false;
        }

        return initOK;
    }

    /**
    * [Give the description for method].
     * @param username 
    * @return
    * false: Data is existed or error
    */
    private boolean initRequestTypes(String username) {
        RequestTypeJpaController daoCtrl = new RequestTypeJpaController(emf);
        List<RequestType> lstRequestType = daoCtrl.findRequestTypeEntities();
        if (CommonUtil.isNNandNB(lstRequestType)) {
            return false;
        } else {

            RequestType requestType = new RequestType();
            requestType.setCd("Task");
            requestType.setName("Công việc");
            requestType.setEnabled(true);
            requestType.setCreated(new Date());
            requestType.setCreatedbyUsername(username);
            
            daoCtrl.create(requestType);

            requestType.setId(null);
            requestType.setCd("Rule");
            requestType.setName("Quy định");
            requestType.setCreated(new Date());
            requestType.setCreatedbyUsername(username);
            daoCtrl.create(requestType);

            requestType.setId(null);
            requestType.setCd("Announcement");
            requestType.setName("Thông báo");
            requestType.setCreated(new Date());
            requestType.setCreatedbyUsername(username);
            daoCtrl.create(requestType);

            requestType.setId(null);
            requestType.setCd("Leave");
            requestType.setName("Nghỉ phép");
            requestType.setCreated(new Date());
            requestType.setCreatedbyUsername(username);
            daoCtrl.create(requestType);
        }

        return true;
    }
    
    /**
    * [Give the description for method].
     * @param username 
    * @return
    * false: Data is existed or error
    */
    private boolean initCreateDepartmentSystem(String username) {
        DepartmentJpaController daoCtrl = new DepartmentJpaController(emf);
        List<Department> lstDepartment = daoCtrl.findDepartmentEntities();

        if (CommonUtil.isNNandNB(lstDepartment)) {
            return false;
        } else {
            boolean isEnable = true;
            Department department = new Department();
            department.setCd("All");
            department.setEnabled(isEnable);
            department.setName("Tất cả");
            department.setCreated(new Date());
            department.setCreatedbyUsername(username);

            daoCtrl.create(department);
        }

        return true;
    }

    public void initUserSystem(String username) {
        boolean isEnable = true;
        User user = new User();

        user.setUsername("admin");
        user.setFirstname("Admin");
        user.setLastname("Mr");
        user.setEnabled(isEnable);
        user.setEmail("lnthach@gmail.com");
        user.setCreated(new Date());
        user.setCreatedbyUsername(username);
        
        UserJpaController daoCtrl = new UserJpaController(emf);
        daoCtrl.create(user);
    }
}
