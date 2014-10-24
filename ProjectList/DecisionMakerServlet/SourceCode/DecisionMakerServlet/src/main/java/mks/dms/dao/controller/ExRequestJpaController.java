package mks.dms.dao.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.Comment;
import mks.dms.dao.entity.Rate;
import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.Watcher;
import mks.dms.model.SearchLeaveConditionModel;
import mks.dms.model.SearchRequestConditionModel;
import mks.dms.model.SearchTaskConditionModel;
import mks.dms.util.AppCons;

import org.apache.log4j.Logger;

import rocky.common.CHARA;
import rocky.common.CommonUtil;

/**
 * This class extends interfaces of RequestJpaController. <br/>
 * 
 * @author truongtho88.nl
 */

public class ExRequestJpaController extends RequestJpaController {

    /** Logging. */
    private final static Logger LOG = Logger.getLogger(ExDepartmentJpaController.class);

	public ExRequestJpaController(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Get Request by createdbyUsername and status and readStatus.
	 * 
	 * @param username
	 * @param status
	 * @param readStatus
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> getListRequestByCreatorCdAndStatusAndCreatorRead(
			String createdbyUsername, String status, int creatorRead) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.createdbyUsername = :createdbyUsername AND r.status = :status AND r.creatorRead = :creatorRead");
			query.setParameter("createdbyUsername", createdbyUsername);
			query.setParameter("status", status);
			query.setParameter("creatorRead", creatorRead);
			List<Request> listRequest = (List<Request>) query.getResultList();

			return listRequest;
		} finally {
			em.close();
		}
	}

	/**
	 * Get Request by requestType.
	 * 
	 * @param requestType
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> getListRequestByRequestTypeCd(String requestTypeCd) {
		EntityManager em = getEntityManager();
		try {
			Query query = em.createQuery("Select r FROM Request r WHERE r.requesttypeCd = :requestTypeCd");
			query.setParameter("requestTypeCd", requestTypeCd);
			List<Request> listRequest = (List<Request>) query.getResultList();

			return listRequest;
		} finally {
			em.close();
		}
	}
	
	/**
	 * Get Request by requestType and Order By created
	 * 
	 * @param requestType
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> getListRequestByRequestTypeCdAndOrderByCreate(String requestTypeCd, String order) {
		EntityManager em = getEntityManager();
		try {
			String script = "Select r FROM Request r WHERE r.requesttypeCd = :requestTypeCd ORDER BY r.created " + order.toUpperCase(); 
			Query query = em.createQuery(script);
			query.setParameter("requestTypeCd", requestTypeCd);
			List<Request> listRequest = (List<Request>) query.getResultList();

			return listRequest;
		} finally {
			em.close();
		}
	}
	
	
	/**
	 * Get Request by managerName and status and readStatus.
	 * 
	 * @param username
	 * @param status
	 * @param readStatus
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> getListRequestByManagerUsernameAndStatusAndManagerRead(
			String managerUsername, String status, int managerRead) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.managerUsername = :managerUsername AND r.status = :status AND r.managerRead = :managerRead");
			query.setParameter("managerUsername", managerUsername);
			query.setParameter("status", status);
			query.setParameter("managerRead", managerRead);
			List<Request> listRequest = (List<Request>) query.getResultList();

			return listRequest;
		} finally {
			em.close();
		}
	}
	
	/**
	 * Get Request by managerName and status and readStatus.
	 * 
	 * @param username
	 * @param status
	 * @param readStatus
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> getListRequestByAssignerCdAndStatusAndAssignerRead(
			String assigneeUsername, String status, int assignerRead) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.assigneeUsername = :assigneeUsername AND r.status = :status AND r.assignerRead = :assignerRead");
			query.setParameter("assigneeUsername", assigneeUsername);
			query.setParameter("status", status);
			query.setParameter("assignerRead", assignerRead);
			List<Request> listRequest = (List<Request>) query.getResultList();

			return listRequest;
		} finally {
			em.close();
		}
	}

	/**
	 * Get Request by createdbyUsername
	 * 
	 * @param username
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> getListRequestByCreatedbyUsername(String createdbyUsername) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.createdbyUsername = :createdbyUsername");
			query.setParameter("createdbyUsername", createdbyUsername);
			List<Request> listRequest = (List<Request>) query.getResultList();

			return listRequest;
		} finally {
			em.close();
		}
	}

	/**
	 * Get Request by createdbyUsername
	 * 
	 * @param createdbyUsername
	 * @param startDate
	 * @param endDate
	 * @param managerId
	 * @param assignId
	 * @param requestType
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> searchRequest(String createdbyUsername, Date startDate,
			Date endDate, String managerUsername, String assignUserName,
			String requestTypeCd) {
		EntityManager em = getEntityManager();
//		System.out.println("managerUsername: " + managerUsername);
//    	System.out.println("createdbyUsername: " + createdbyUsername);
//    	System.out.println("assignUsername: " +  assignUserName);
//    	System.out.println("requestTypeCd: " + requestTypeCd);
		try {
			String scriptQuery = "select r from Request r ";
			if ((!createdbyUsername.equals("0")) || ((startDate != null))
					|| ((endDate != null)) || ((!managerUsername.equals("0")))
					|| ((!assignUserName.equals("0")))
					|| ((!requestTypeCd.equals("0")))) {
				scriptQuery += "WHERE ";
			}
			if (!createdbyUsername.equals("0")) {
				if (((startDate != null)) || ((endDate != null))
						|| ((!managerUsername.equals("0")))
						|| ((!assignUserName.equals("0")))
						|| ((!requestTypeCd.equals("0")))) {
					scriptQuery += "r.createdbyUsername = :createdbyUsername AND ";
				} else {
					scriptQuery += "r.createdbyUsername = :createdbyUsername ";
				}

			}
			if (startDate != null) {
				if (((endDate != null)) || ((!managerUsername.equals("0")))
						|| ((!assignUserName.equals("0")))
						|| ((!requestTypeCd.equals("0")))) {
					scriptQuery += "r.startdate >= :startdate AND ";
				} else {
					scriptQuery += "r.startdate >= :startdate ";
				}
			}
			if (endDate != null) {
				if (((!managerUsername.equals("0"))) || ((!assignUserName.equals("0")))
						|| ((!requestTypeCd.equals("")))) {
					scriptQuery += "r.enddate <= :enddate AND ";
				} else {
					scriptQuery += "r.enddate <= :enddate ";
				}

			}
			if (!managerUsername.equals("0")) {
				if ((!assignUserName.equals("0")) || ((!requestTypeCd.equals("0")))) {
					scriptQuery += "r.managerUsername = :managerUsername AND ";
				} else {
					scriptQuery += "r.managerUsername = :managerUsername";
				}
			}
			if (!assignUserName.equals("0")) {
				if ((!requestTypeCd.equals("0"))) {
					scriptQuery += "r.assigneeUsername = :assignUserName AND ";
				} else {
					scriptQuery += "r.assigneeUsername = :assignUserName";
				}
			}
			if (!requestTypeCd.equals("0")) {
				scriptQuery += "r.requesttypeCd = :requestTypeCd";
			}
			Query query = em.createQuery(scriptQuery);
			if (!createdbyUsername.equals("0")) {
				query.setParameter("createdbyUsername", createdbyUsername);
			}
			if (startDate != null) {
				query.setParameter("startdate", startDate);
			}
			if (endDate != null) {
				query.setParameter("enddate", endDate);
			}
			if (!managerUsername.equals("0")) {
				query.setParameter("managerUsername", managerUsername);
			}
			if (!assignUserName.equals("0")) {
				query.setParameter("assignUserName", assignUserName);
			}
			if (!requestTypeCd.equals("0")) {
				query.setParameter("requestTypeCd", requestTypeCd);
			}
			System.out.println("Script Query: " + scriptQuery);
			System.out.println(requestTypeCd);
			List<Request> listRequest = (List<Request>) query.getResultList();
				
			return listRequest;
		} finally {
			em.close();
		}
	}

	/**
	 * Get Request by managerUsername.
	 * 
	 * @param managerUsername
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> getListRequestByManagerUsername(String managerUsername) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.managerUsername = :managerUsername");
			query.setParameter("managerUsername", managerUsername);
			List<Request> listRequest = (List<Request>) query.getResultList();

			return listRequest;
		} finally {
			em.close();
		}
	}
	
	/**
	 * Get Request by assigneeUsername.
	 * 
	 * @param assigneeUsername
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> getListRequestByAssigneeUsername(String assigneeUsername) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.assigneeUsername = :assigneeUsername");
			query.setParameter("assigneeUsername", assigneeUsername);
			List<Request> listRequest = (List<Request>) query.getResultList();

			return listRequest;
		} finally {
			em.close();
		}
	}
	
	/**
	 * Get Request by assigneeUsername, requestTypeCd
	 * 
	 * @param assigneeUsername
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> getListRequestByAssigneeUsernameAndRequestTypeCd(String assigneeUsername, String requestTypeCd) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.assigneeUsername = :assigneeUsername AND r.requesttypeCd = :requesttypeCd");
			query.setParameter("assigneeUsername", assigneeUsername);
			query.setParameter("requesttypeCd", requestTypeCd);
			List<Request> listRequest = (List<Request>) query.getResultList();

			return listRequest;
		} finally {
			em.close();
		}
	}
	
	/**
	 * Get Request by managerUsername, requestTypeCd.
	 * 
	 * @param assigneeUsername
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> getListRequestByManagerUsernameAndRequestTypeCd(String managerUsername, String requestTypeCd) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.managerUsername = :managerUsername AND r.requesttypeCd = :requesttypeCd");
			query.setParameter("managerUsername", managerUsername);
			query.setParameter("requesttypeCd", requestTypeCd);
			List<Request> listRequest = (List<Request>) query.getResultList();

			return listRequest;
		} finally {
			em.close();
		}
	}
	
	/**
	 * Get all available announcements.
	 * 
	 * @param requestTypeCd
	 *            Announcement | Rule | Task | Leave
	 * @see mks.dms.util.AppCons
	 * @author ThachLe
	 */
	public List<Request> findRequestByType(String requestTypeCd) {
		List<Request> lstRequest;
		EntityManager em = getEntityManager();
		try {
			lstRequest = em.createNamedQuery("Request.findByRequesttypeCd")
					.setParameter("requesttypeCd", requestTypeCd)
					.getResultList();

			return lstRequest;
		} finally {
			em.close();
		}

	}

    /**
    * [Give the description for method].
    * @param requestId
    * @param fileId start from 1
     * @throws Exception 
     * @throws NonexistentEntityException 
    */
    public void deleteAttachment(Integer requestId, Integer fileId) throws NonexistentEntityException, Exception {
        LOG.debug("requestId=" + requestId + ";fileId=" + fileId);
        Request request = findRequest(requestId);

        if (fileId == 1) {
            request.setFilename1(null);
            request.setAttachment1(null);
        } else if (fileId == 2) {
            request.setFilename2(null);
            request.setAttachment2(null);
        } else if (fileId == 3) {
            request.setFilename3(null);
            request.setAttachment3(null);
        }

        this.edit(request);
    }

    public void merge(Request request) {
        EntityManager em = getEntityManager();
        
        try {
            em.merge(request);
        } finally {
            em.close();
        }
    }
    
    /**
    * [Give the description for method].
    * @param searchCond
    * @return
    * @see http://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development/Querying/Criteria#Where
    */
    public List<Request> findRequestByCondition(SearchRequestConditionModel searchCond) {
        List<Request> lstRequest;
        EntityManager em = getEntityManager();

        // Searching condition
        Request cond = (searchCond != null) ? searchCond.getRequest() : null;

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();

            Root<Request> rootReq = cq.from(Request.class);
            Predicate predicate = null;
            // Condition: Request Type
            if ((cond != null) && (cond.getRequesttypeCd() != null) && (!AppCons.ALL.equals(cond.getRequesttypeCd()))) {
                Predicate nextPredicate = buildPredicate(cb, rootReq, "requesttypeCd", cond.getRequesttypeCd());
                predicate = (predicate == null) ? nextPredicate : cb.and(predicate, nextPredicate);
            } else {
                // Do nothing
            }

            // Condition: Assignee
            if ((cond != null) && (cond.getAssigneeUsername() != null)
                    && (!AppCons.ALL.equals(cond.getAssigneeUsername()))) {
                // cq.where(cb.equal(rootReq.get("assigneeUsername"), cond.getAssigneeUsername()));
                Predicate predicateAssigee = buildPredicate(cb, rootReq, "assigneeUsername", cond.getAssigneeUsername());
                predicate = (predicate == null) ? predicateAssigee : cb.and(predicate, predicateAssigee);
            } else {
                // Do nothing
            }

            // Condition: Status
            if ((cond != null) && (cond.getStatus() != null) && (!AppCons.ALL.equals(cond.getStatus()))) {
                // cq.where(cb.equal(rootReq.get("status"), cond.getStatus()));
                Predicate predicateStatus = buildPredicate(cb, rootReq, "status", cond.getStatus());
                predicate = (predicate == null) ? predicateStatus : cb.and(predicate, predicateStatus);
            } else {
                // Do nothing
            }

            if (predicate != null) {
                cq.where(predicate);
            }

            Query query = em.createQuery(cq);

            lstRequest = query.getResultList();

        } finally {
            em.close();
        }

        return lstRequest;
    }

    /**
    * [Give the description for method].
    * @param username
    * @param searchCond
    * @return
    * @see findRequestByCondition
    */
    public List<Request> findRequestOfUserByCondition(String username, SearchRequestConditionModel searchCond) {
        List<Request> lstRequest;
        EntityManager em = getEntityManager();

        // Searching condition
        Request cond = (searchCond != null) ? searchCond.getRequest() : null;

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();

            // Sub query.START
            Subquery sq = cq.subquery(Request.class);
            Root<Request> rootSub = cq.from(Request.class);
            // 3 groups of request: assigned to user; managed by user; created by user
            Predicate predicateOfUser = cb.or(cb.equal(rootSub.get("assigneeUsername"), username)
                                            , cb.equal(rootSub.get("managerUsername"), username)
                                            , cb.equal(rootSub.get("createdbyUsername"), username));
            sq.where(predicateOfUser);

            // Sub query.END
            
            Root<Request> rootReq = cq.from(Request.class);
            Predicate predicate = null;
            // Condition: Request Type
            if ((cond != null) && (cond.getRequesttypeCd() != null) && (!AppCons.ALL.equals(cond.getRequesttypeCd()))) {
                Predicate nextPredicate = buildPredicate(cb, rootReq, "requesttypeCd", cond.getRequesttypeCd());
                predicate = (predicate == null) ? nextPredicate : cb.and(predicate, nextPredicate);
            } else {
                // Do nothing
            }

            // Condition: Assignee
            if ((cond != null) && (cond.getAssigneeUsername() != null)
                    && (!AppCons.ALL.equals(cond.getAssigneeUsername()))) {
                // cq.where(cb.equal(rootReq.get("assigneeUsername"), cond.getAssigneeUsername()));
                Predicate predicateAssigee = buildPredicate(cb, rootReq, "assigneeUsername", cond.getAssigneeUsername());
                predicate = (predicate == null) ? predicateAssigee : cb.and(predicate, predicateAssigee);
            } else {
                // Do nothing
            }

            // Condition: Status
            if ((cond != null) && (cond.getStatus() != null) && (!AppCons.ALL.equals(cond.getStatus()))) {
                // cq.where(cb.equal(rootReq.get("status"), cond.getStatus()));
                Predicate predicateStatus = buildPredicate(cb, rootReq, "status", cond.getStatus());
                predicate = (predicate == null) ? predicateStatus : cb.and(predicate, predicateStatus);
            } else {
                // Do nothing
            }

            if (predicate != null) {
                cq.where(cb.and(predicate, cb.in(sq)));
            }

            Query query = em.createQuery(cq);

            lstRequest = query.getResultList();

        } finally {
            em.close();
        }

        return lstRequest;
    }
    
    /**
    * [Give the description for method].
    * @param cb
    * @param rootReq
    * @param field
    * @param value list of item separated by a common. Ex "Task,Rule"
    * @return
    */
    private Predicate buildPredicate(CriteriaBuilder cb, Root<Request> rootReq, String field, String values) {
        Predicate predicate = null;

        if (CommonUtil.isNNandNB(values)) {
            String[] arrValues = values.split(CHARA.COMMA);
            
            List<String> lstValues = Arrays.asList(arrValues);
            Iterator<String> it = lstValues.iterator();
            String val;
            while (it.hasNext()) {
                val = it.next();
                
                if (predicate == null) {
                    predicate = cb.equal(rootReq.get(field), val);
                } else {
                    predicate = cb.or(predicate, cb.equal(rootReq.get(field), val));
                }
            }
        }

        return predicate;
    }

   /**
    * Find task of given user
    *  - Assigned task
    *  - Task is managed by the user
    *  - Task is shared to the user
    * @param searchConditionModel
    * Members to be used:
    * - assigneeUsername
    * @return
    */
    public List<Request> findTaskOfUser(SearchTaskConditionModel searchConditionModel) {
        List<Request> lstRequest;
        EntityManager em = getEntityManager();

        // Searching condition
        Request cond = (searchConditionModel != null) ? searchConditionModel.getRequest() : null;

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();

            Root<Request> rootReq = cq.from(Request.class);
            
            // Build condition with Task
            Predicate predicate = cb.equal(rootReq.get("requesttypeCd"), AppCons.TASK);

            // Build condition assigned || manage 
            // Condition: Assignee
            if (cond != null) {
                Predicate predicateOfUser = null;
                String assignee = (cond.getAssigneeUsername() == null) ? CHARA.BLANK : cond.getAssigneeUsername();
                String manager =  (cond.getManagerUsername() == null) ? CHARA.BLANK : cond.getManagerUsername();
                
                if ((assignee.isEmpty() && manager.isEmpty()) ||
                     ((!assignee.isEmpty()) && (!manager.isEmpty()))   
                    ) {
                    predicateOfUser = cb.or(cb.equal(rootReq.get("assigneeUsername"), assignee)
                                          , cb.equal(rootReq.get("managerUsername"), manager)
                                          , cb.equal(rootReq.get("createdbyUsername"), assignee)
                                          );
                    
                } else if (!assignee.isEmpty()) {
                    predicateOfUser = cb.or(cb.equal(rootReq.get("assigneeUsername"), assignee)
                                          , cb.equal(rootReq.get("createdbyUsername"), assignee)
                                           );
                } else { // !manager.isEmpty()
                    predicateOfUser = cb.or(cb.equal(rootReq.get("managerUsername"), manager)
                                          , cb.equal(rootReq.get("createdbyUsername"), assignee)
                                           );
                }
                
                predicate = cb.and(predicate, predicateOfUser);
            }

            // Condition: Status
            if ((cond != null) && (cond.getStatus() != null) && (!AppCons.ALL.equals(cond.getStatus()))) {
                // cq.where(cb.equal(rootReq.get("status"), cond.getStatus()));
                Predicate predicateStatus = buildPredicate(cb, rootReq, "status", cond.getStatus());
                predicate = (predicate == null) ? predicateStatus : cb.and(predicate, predicateStatus);
            } else {
                // Do nothing
            }

            if (predicate != null) {
                cq.where(predicate);
            }

            Query query = em.createQuery(cq);

            lstRequest = query.getResultList();

        } finally {
            em.close();
        }

        return lstRequest;
    }
    
    public List<Request> findLeaveOfUser(SearchLeaveConditionModel searchConditionModel) {
        List<Request> lstRequest;
        EntityManager em = getEntityManager();

        // Searching condition
        Request cond = (searchConditionModel != null) ? searchConditionModel.getRequest() : null;

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();

            Root<Request> rootReq = cq.from(Request.class);
            
            // Build condition with Task
            Predicate predicate = cb.equal(rootReq.get("requesttypeCd"), AppCons.LEAVE);

            // Build condition assigned || manage 
            // Condition: Assignee
            if (cond != null) {
                Predicate predicateOfUser = null;
                String assignee = (cond.getAssigneeUsername() == null) ? CHARA.BLANK : cond.getAssigneeUsername();
                String manager =  (cond.getManagerUsername() == null) ? CHARA.BLANK : cond.getManagerUsername();
                
                if ((assignee.isEmpty() && manager.isEmpty()) ||
                     ((!assignee.isEmpty()) && (!manager.isEmpty()))   
                    ) {
                    predicateOfUser = cb.or(cb.equal(rootReq.get("assigneeUsername"), assignee)
                                          , cb.equal(rootReq.get("managerUsername"), manager));
                    
                } else if (!assignee.isEmpty()) {
                    predicateOfUser = cb.equal(rootReq.get("assigneeUsername"), assignee);
                } else { // !manager.isEmpty()
                    predicateOfUser = cb.equal(rootReq.get("managerUsername"), manager);
                }
                
                predicate = cb.and(predicate, predicateOfUser);
            }

            // Condition: Status
            if ((cond != null) && (cond.getStatus() != null) && (!AppCons.ALL.equals(cond.getStatus()))) {
                // cq.where(cb.equal(rootReq.get("status"), cond.getStatus()));
                Predicate predicateStatus = buildPredicate(cb, rootReq, "status", cond.getStatus());
                predicate = (predicate == null) ? predicateStatus : cb.and(predicate, predicateStatus);
            } else {
                // Do nothing
            }

            if (predicate != null) {
                cq.where(predicate);
            }

            Query query = em.createQuery(cq);

            lstRequest = query.getResultList();

        } finally {
            em.close();
        }

        return lstRequest;
    }
    
    /* 
    * [Explain the description for this method here].
    * Delete:
    *  + RATE
    *  + COMMENT
    *  + WATCHER
    *  + REQUEST_DEPARTMENT
    * @param id given request id
    * @see mks.dms.dao.controller.RequestJpaController#destroy(java.lang.Integer)
    */
    @Override
    public void destroy(Integer reqId) throws NonexistentEntityException {
        EntityManager em = null;
        Class[] arrReferClass = {Rate.class, Comment.class, Watcher.class};
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            // Remove referenced item
            CriteriaBuilder cb = em.getCriteriaBuilder();
            
            // Delete referenced class of request
            CriteriaDelete cd;
            Root rootRate;
            Query queryDelete;
            for (int i = 0; i < arrReferClass.length; i++) {
                cd = cb.createCriteriaDelete(arrReferClass[i]);
                
                rootRate = cd.getRoot();
                
                cd.where(cb.equal(rootRate.get("reqId"), reqId));
               
                queryDelete = em.createQuery(cd);
                queryDelete.executeUpdate();
            }
                        
            // Delete request
            Request request;
            try {
                request = em.getReference(Request.class, reqId);
                request.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The request with id " + reqId + " no longer exists.", enfe);
            }
            em.remove(request);
            em.getTransaction().commit();
        } catch (Exception ex) {
            LOG.error("Could not delete request '" + reqId, ex);
            em.getTransaction().rollback();
        } finally {
            if (em != null) { em.close(); }
        }
    }

    public void updateStatus(Integer requestId, String status, String username, Comment comment) {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            // Remove referenced item
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaUpdate<Request> cu = cb.createCriteriaUpdate(Request.class);
            
            Root<Request> rootRequest = cu.from(Request.class);
            
            cu.set(rootRequest.get("status"), status);
            cu.set(rootRequest.get("lastmodified"), new Date());
            cu.set(rootRequest.get("lastmodifiedbyUsername"), username);
            
            cu.where(cb.equal(rootRequest.get("id"), requestId));
            Query updateQuery = em.createQuery(cu);
            
            int affected = updateQuery.executeUpdate();
            
            if (affected == 1) {
                // Add comment
                if (comment != null) {
                    em.persist(comment);
                }
            }
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            LOG.error("Could not update status for request '" + requestId + "';status=" + status + ";username="
                    + username + ";comment=" + comment, ex);
        } finally {
            if (em != null) { em.close(); }
        }
    }
    
    /**
    * Update status of request to done and create Rate (level, note)
    * @param requestId
    * @param status
    * @param rate
    * @param username
    */
    public boolean confirmDone(Integer requestId, String status, Rate rate, String username) {
        EntityManager em = null;
        
        try {
            // Step 1: Update status of request
            em = getEntityManager();
            em.getTransaction().begin();
            // Remove referenced item
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaUpdate<Request> cu = cb.createCriteriaUpdate(Request.class);
            
            Root<Request> rootRequest = cu.from(Request.class);

            cu.set(rootRequest.get("status"), status);
            
            cu.set(rootRequest.get("lastmodified"), new Date());
            cu.set(rootRequest.get("lastmodifiedbyUsername"), username);
            
            cu.where(cb.equal(rootRequest.get("id"), requestId));
            Query updateQuery = em.createQuery(cu);
            
            int affected = updateQuery.executeUpdate();
            LOG.info("Affected record: " + affected);
            
            // Step 2: Add Rate
            em.persist(rate);
            
            // Commit
            em.getTransaction().commit();
            
            return true;
        } catch (Exception ex) {
            LOG.error("Could not update request '" + requestId + "'; status=" + status, ex);
            em.getTransaction().rollback();
        } finally {
            if (em != null) { em.close(); }
        }
        
        return false;
    }
        
    
    @Override
    public void edit(Request request) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            // Remove referenced item
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaUpdate<Request> cu = cb.createCriteriaUpdate(Request.class);
            
            Root<Request> rootRequest = cu.from(Request.class);

            cu.set(rootRequest.get("title"), request.getTitle());
            cu.set(rootRequest.get("content"), request.getContent());
            cu.set(rootRequest.get("assigneeUsername"), request.getAssigneeUsername());
            cu.set(rootRequest.get("assigneeName"), request.getAssigneeName());
            cu.set(rootRequest.get("managerUsername"), request.getManagerUsername());
            cu.set(rootRequest.get("managerName"), request.getManagerName());
            cu.set(rootRequest.get("startdate"), request.getStartdate());
            cu.set(rootRequest.get("enddate"), request.getEnddate());
            cu.set(rootRequest.get("label1"), request.getLabel1());
            cu.set(rootRequest.get("duration"), request.getDuration());
            cu.set(rootRequest.get("durationunit"), request.getDurationunit());            
            cu.set(rootRequest.get("filename1"), request.getFilename1());
            cu.set(rootRequest.get("attachment1"), request.getAttachment1());
            // cu.set(rootRequest.get("status"), request.getStatus());
            
            cu.set(rootRequest.get("lastmodified"), new Date());
            cu.set(rootRequest.get("lastmodifiedbyUsername"), request.getLastmodifiedbyUsername());
            
            cu.where(cb.equal(rootRequest.get("id"), request.getId()));
            Query updateQuery = em.createQuery(cu);
            
            int affected = updateQuery.executeUpdate();
            LOG.info("Affected record: " + affected);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            LOG.error("Could not edit request '" + request.getId() + "'", ex);
        } finally {
            if (em != null) { em.close(); }
        }
    }


}
