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
import mks.dms.dao.controller.ParameterJpaController;
import mks.dms.dao.controller.RequestTypeJpaController;
import mks.dms.dao.controller.StatusFlowJpaController;
import mks.dms.dao.controller.UserJpaController;
import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.Parameter;
import mks.dms.dao.entity.RequestType;
import mks.dms.dao.entity.StatusFlow;
import mks.dms.dao.entity.User;
import mks.dms.util.AppCons;

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
            initOK = initUserSystem(username);
        } else {
            return false;
        }
        
        if (initOK) {
            initOK = initStatusFlow(username);
        } else {
            return false;
        }     

        if (initOK) {
            initOK = initParameterRank(username);
        } else {
            return false;
        }

        if (initOK) {
            initOK = initParameterEmail(username);
        } else {
            return false;
        }
        
        return initOK;
    }

    /**
    * [Give the description for method].
    * owner: Assignee or Creator
    * @param username
    * @return
    */
    private boolean initStatusFlow(String username) {
        StatusFlowJpaController daoCtrl = new StatusFlowJpaController(emf);
        StatusFlow statusFlow = new StatusFlow();
        int seqNo = 0;
        // For Task of Owner.START
        statusFlow.setRequesttypeCd(AppCons.TASK);
        statusFlow.setCreated(new Date());
        statusFlow.setCreatedbyUsername(username);
        statusFlow.setTypeUser(AppCons.TYPE_USER.Owner.toString());
        
        statusFlow.setCurrentStatus(AppCons.STATUS_CREATED);
        statusFlow.setNextStatus(AppCons.STATUS_DOING);
        statusFlow.setSeqNo(seqNo++);
        daoCtrl.create(statusFlow);
        
        statusFlow.setCurrentStatus(AppCons.STATUS_DOING);
        statusFlow.setNextStatus(AppCons.STATUS_FINISH);
        statusFlow.setSeqNo(seqNo++);
        daoCtrl.create(statusFlow);
        
        statusFlow.setCurrentStatus(AppCons.STATUS_FINISH);
        statusFlow.setNextStatus(AppCons.STATUS_REASSIGN);
        statusFlow.setSeqNo(seqNo++);
        daoCtrl.create(statusFlow);

        statusFlow.setCurrentStatus(AppCons.STATUS_REASSIGN);
        statusFlow.setNextStatus(AppCons.STATUS_DOING);
        statusFlow.setSeqNo(seqNo++);
        daoCtrl.create(statusFlow);
        // For Task of Owner.END
        
        // For Task of Manager.START
        statusFlow.setTypeUser(AppCons.TYPE_USER.Manager.toString());
        statusFlow.setCurrentStatus(AppCons.STATUS_FINISH);
        statusFlow.setNextStatus(AppCons.STATUS_DONE);
        statusFlow.setSeqNo(seqNo++);
        daoCtrl.create(statusFlow);
        
        statusFlow.setCurrentStatus(AppCons.STATUS_DONE);
        statusFlow.setNextStatus(AppCons.STATUS_REASSIGN);
        statusFlow.setSeqNo(seqNo++);
        daoCtrl.create(statusFlow);
        // For Task of Manager.END
        
        // For Leave of Manager.START
        statusFlow.setRequesttypeCd(AppCons.LEAVE);
        statusFlow.setTypeUser(AppCons.TYPE_USER.Manager.toString());
        statusFlow.setCreated(new Date());
        statusFlow.setCreatedbyUsername(username);
        
        statusFlow.setCurrentStatus(AppCons.STATUS_CREATED);
        statusFlow.setNextStatus(AppCons.STATUS_APPROVED);
        statusFlow.setSeqNo(seqNo++);
        daoCtrl.create(statusFlow);
        
        statusFlow.setCurrentStatus(AppCons.STATUS_CREATED);
        statusFlow.setNextStatus(AppCons.STATUS_REJECTED);
        statusFlow.setSeqNo(seqNo++);
        daoCtrl.create(statusFlow);
        // For Leave of Manager.END
        return true;
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
            int seqNo = 0;
            RequestType requestType = new RequestType();
            requestType.setCd("Task");
            requestType.setName("Công việc");
            requestType.setEnabled(true);
            requestType.setCreated(new Date());
            requestType.setCreatedbyUsername(username);
            requestType.setSeqNo(seqNo++);
            
            daoCtrl.create(requestType);

            requestType.setId(null);
            requestType.setCd("Rule");
            requestType.setName("Quy định");
            requestType.setCreated(new Date());
            requestType.setCreatedbyUsername(username);
            requestType.setSeqNo(seqNo++);
            
            daoCtrl.create(requestType);

            requestType.setId(null);
            requestType.setCd("Announcement");
            requestType.setName("Thông báo");
            requestType.setCreated(new Date());
            requestType.setCreatedbyUsername(username);
            requestType.setSeqNo(seqNo++);
            daoCtrl.create(requestType);

            requestType.setId(null);
            requestType.setCd("Leave");
            requestType.setName("Nghỉ phép");
            requestType.setCreated(new Date());
            requestType.setCreatedbyUsername(username);
            requestType.setSeqNo(seqNo++);
            
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

    private boolean initUserSystem(String username) {
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
        
        return true;
    }
    
    public boolean initParameterRank(String username) {
        Parameter parameter = new Parameter();
        parameter.setCreatedbyUsername(username);
        parameter.setCreated(new Date());
        parameter.setCd(AppCons.PARAM_RANK);
        parameter.setEnabled(true);
        
        
        ParameterJpaController paramDaoCtrl = new ParameterJpaController(emf);

        String name= "A";
        String value = "A";
        parameter.setId(null);
        parameter.setName(name);
        parameter.setValue(value);
        paramDaoCtrl.create(parameter);
        
        name= "B";
        value = "B";
        parameter.setId(null);
        parameter.setName(name);
        parameter.setValue(value);
        paramDaoCtrl.create(parameter);
        
        name= "C";
        value = "C";
        parameter.setId(null);
        parameter.setName(name);
        parameter.setValue(value);
        paramDaoCtrl.create(parameter);
        
        return true;
    }
    
    public boolean initParameterEmail(String username) {
        String name;
        String value;
        
        Parameter parameter = new Parameter();
        parameter.setCreatedbyUsername(username);
        parameter.setCreated(new Date());
        parameter.setCd(AppCons.PARAM_EMAIL);
        parameter.setEnabled(true);
        
        
        ParameterJpaController paramDaoCtrl = new ParameterJpaController(emf);
        
        // Subject
        name = AppCons.PARAM_RESET_PASSWORD_SUBJECT;
        value = "[DMS] Khôi phục mật khẩu";
        parameter.setId(null);
        parameter.setName(name);
        parameter.setValue(value);
        paramDaoCtrl.create(parameter);
        
        // From email address
        name = AppCons.PARAM_RESET_PASSWORD_FROM_ADDR;
        value = "service@mks.com.vn";
        parameter.setId(null);
        parameter.setName(name);
        parameter.setValue(value);
        paramDaoCtrl.create(parameter);
        
        // From email name
        name = AppCons.PARAM_RESET_PASSWORD_FROM_NAME;
        value = "MeKong Solution Service";
        parameter.setId(null);
        parameter.setName(name);
        parameter.setValue(value);
        paramDaoCtrl.create(parameter);

        // Reset link
        name = AppCons.PARAM_RESET_PASSWORD_LINK;
        value = "http://tokutokuya.mks.com.vn/decisionmaker/confirm-reset-password";
        parameter.setId(null);
        parameter.setName(name);
        parameter.setValue(value);
        paramDaoCtrl.create(parameter);
        
        return true;
    }

}
