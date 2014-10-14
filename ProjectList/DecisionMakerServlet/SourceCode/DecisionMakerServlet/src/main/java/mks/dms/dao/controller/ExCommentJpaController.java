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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import mks.dms.dao.entity.Comment;
import mks.dms.dao.entity.Request;

/**
 * @author ThachLN
 *
 */
public class ExCommentJpaController extends CommentJpaController {

    public ExCommentJpaController(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Comment> findCommentByRequestId(Integer reqId) {
        List<Comment> lstComment;
        EntityManager em = getEntityManager();
        try {
            lstComment = em.createNamedQuery("Comment.findByReqId")
                    .setParameter("reqId", reqId)
                    .getResultList();

            return lstComment;
        } finally {
            em.close();
        }
    }
}
