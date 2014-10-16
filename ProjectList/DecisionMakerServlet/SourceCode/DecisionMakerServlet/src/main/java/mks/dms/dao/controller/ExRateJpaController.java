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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import mks.dms.dao.entity.Rate;

/**
 * @author ThachLN
 *
 */
public class ExRateJpaController extends RateJpaController {

    public ExRateJpaController(EntityManagerFactory emf) {
        super(emf);
    }

    /**
    * Get the last Rate record by field CREATED.
    * @param id
    * @return
    */
    public Rate findLatestRateByRequestId(Integer id) {
    	
        String strQuery = "SELECT r FROM Rate r WHERE r.reqId = :reqId ORDER BY r.created desc";
        EntityManager em = getEntityManager();
        
        Query query = em.createQuery(strQuery);

        query.setParameter("reqId", id);
        query.setMaxResults(1);
        
        Rate rate = (Rate) query.getSingleResult();
        if (rate != null) 
        	return rate;
        else
        	return null;
    }

}
