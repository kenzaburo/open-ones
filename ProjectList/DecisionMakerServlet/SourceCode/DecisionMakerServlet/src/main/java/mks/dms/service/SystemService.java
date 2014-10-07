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

import java.util.List;

import mks.dms.dao.controller.DepartmentJpaController;
import mks.dms.dao.controller.RequestTypeJpaController;
import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.RequestType;

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
    * @return
    * false: Data is existed | Error during create data
    */
    public boolean initData() {
        // Create Types of requests
        boolean initOK = initRequestTypes();
        
        if (initOK) {
        // Create default Department
         initOK = initCreateDepartmentSystem();
        } else {
            // Do nothing
        }
        
        return initOK;
    }
    
    /**
    * [Give the description for method].
    * @return
    * false: Data is existed or error
    */
    private boolean initRequestTypes() {
        RequestTypeJpaController daoCtrl = new RequestTypeJpaController(emf);
        List<RequestType> lstRequestType = daoCtrl.findRequestTypeEntities();
        if (CommonUtil.isNNandNB(lstRequestType)) {
            return false;
        } else {

            RequestType requestType = new RequestType();
            requestType.setCd("Task");
            requestType.setName("Công việc");
            requestType.setEnabled(true);

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
        }

        return true;
    }
    
    /**
    * [Give the description for method].
    * @return
    * false: Data is existed or error
    */
    private boolean initCreateDepartmentSystem() {
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

            daoCtrl.create(department);
        }

        return true;
    }

}
