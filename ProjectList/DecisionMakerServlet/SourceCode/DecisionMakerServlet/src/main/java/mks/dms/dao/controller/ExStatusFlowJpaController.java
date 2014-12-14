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
package mks.dms.dao.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import mks.dms.dao.entity.Comment;
import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.StatusFlow;
import mks.dms.dao.entity.User;
import mks.dms.util.AppCons;
import mks.dms.util.SaveBatchException;
import mks.dms.util.AppCons.RESULT;

/**
 * @author ThachLN
 *
 */
public class ExStatusFlowJpaController extends StatusFlowJpaController {

    public ExStatusFlowJpaController(EntityManagerFactory emf) {
        super(emf);
    }
    
    public List<RESULT> save(List<StatusFlow> lstStatusFlow, String username) throws SaveBatchException {
        List<RESULT> lstResult = new ArrayList<AppCons.RESULT>();
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            StatusFlow statusFlow;

            StatusFlow currentStatusFlow;
            AppCons.RESULT result;
            for (Iterator<StatusFlow> itStatusFlow = lstStatusFlow.iterator(); itStatusFlow.hasNext(); ) {
                statusFlow = itStatusFlow.next();

                currentStatusFlow = findStatusFlow(statusFlow.getId());
                
                if (currentStatusFlow == null) {
                    // Create
                    em.persist(statusFlow);
                    result = AppCons.RESULT.CREATE_OK;
                } else {
                    // Update
                    // Name
                    currentStatusFlow.setRequesttypeCd(statusFlow.getRequesttypeCd());
                    currentStatusFlow.setTypeUser(statusFlow.getTypeUser());
                    currentStatusFlow.setCurrentStatus(statusFlow.getCurrentStatus());
                    currentStatusFlow.setNextStatus(statusFlow.getNextStatus());

                    currentStatusFlow.setLastmodified(new Date());
                    currentStatusFlow.setLastmodifiedbyUsername(username);

                    em.merge(currentStatusFlow);
                    result = AppCons.RESULT.UPDATE_OK;
                }
                
                lstResult.add(result);
            }            
            
            em.getTransaction().commit();
        } catch (Throwable th) {
            em.getTransaction().rollback();
            
            throw new SaveBatchException(th);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return lstResult;
    }
}
