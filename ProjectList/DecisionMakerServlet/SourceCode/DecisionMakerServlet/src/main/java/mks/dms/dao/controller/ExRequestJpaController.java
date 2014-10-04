package mks.dms.dao.controller;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.User;

import org.apache.log4j.Logger;

/**
 * This class extends interfaces of RequestJpaController. <br/>
 * 
 * @author truongtho88.nl
 */

public class ExRequestJpaController extends RequestJpaController {

	/** Logging. */
	private final static Logger LOG = Logger
			.getLogger(ExDepartmentJpaController.class);

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
	public List<Request> getListRequestByManagerCdAndStatusAndManagerRead(
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
	public List<Request> getListRequestByCreatedbyCd(String createdbyUsername) {
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
	public List<Request> getListRequestByManagerCd(String managerUsername) {
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
	public List<Request> getListRequestByAssignedCd(String assigneeUsername) {
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
	public List<Request> getListRequestByAssignedCdAndRequestTypeCd(String assigneeUsername, String requestTypeCd) {
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
	public List<Request> getListRequestByManagerCdAndRequestTypeCd(String managerUsername, String requestTypeCd) {
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
}
