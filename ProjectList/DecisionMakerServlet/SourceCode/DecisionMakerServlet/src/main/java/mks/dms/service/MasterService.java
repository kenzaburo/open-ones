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

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mks.dms.dao.controller.RequestTypeJpaController;
import mks.dms.dao.entity.RequestType;

import org.springframework.stereotype.Service;

/**
 * @author ThachLN
 *
 */
@Service
public class MasterService {
    public String getMasters() {
        String jsonData = "        ['1', '', '', '']," +
        "['2008', '10', '11', '2']";
        
        return jsonData;
    }
    
    /**
    * Get types of request.
    * <br/>
    * @return list of RequestType
    */
    public List<RequestType> getRequestTypes() {
        List<RequestType> lstRequestTypes;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DecisionMaker-DBModelPU");
        RequestTypeJpaController daoCtrl = new RequestTypeJpaController(emf);
        
        lstRequestTypes = daoCtrl.findRequestTypeEntities();
        
        return lstRequestTypes;
    }
    

}
