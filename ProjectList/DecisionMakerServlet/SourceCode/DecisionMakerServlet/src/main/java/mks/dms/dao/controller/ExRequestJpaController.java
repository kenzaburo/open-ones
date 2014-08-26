package mks.dms.dao.controller;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

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
	 * Get Request by createdbyCd and status and readStatus.
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
			String createdbyCd, String status, int creatorRead) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.createdbyCd = :createdbyCd AND r.status = :status AND r.creatorRead = :creatorRead");
			query.setParameter("createdbyCd", createdbyCd);
			query.setParameter("status", status);
			query.setParameter("creatorRead", creatorRead);
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
			String managerCd, String status, int managerRead) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.managerCd = :managerCd AND r.status = :status AND r.managerRead = :managerRead");
			query.setParameter("managerCd", managerCd);
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
			String assignedCd, String status, int assignerRead) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.assignedCd = :assignedCd AND r.status = :status AND r.assignerRead = :assignerRead");
			query.setParameter("assignedCd", assignedCd);
			query.setParameter("status", status);
			query.setParameter("assignerRead", assignerRead);
			List<Request> listRequest = (List<Request>) query.getResultList();

			return listRequest;
		} finally {
			em.close();
		}
	}

	/**
	 * Get Request by createdbyCd
	 * 
	 * @param username
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> getListRequestByCreatedbyCd(String createdbyCd) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.createdbyCd = :createdbyCd");
			query.setParameter("createdbyCd", createdbyCd);
			List<Request> listRequest = (List<Request>) query.getResultList();

			return listRequest;
		} finally {
			em.close();
		}
	}

	/**
	 * Get Request by createdbyCd
	 * 
	 * @param createdbyCd
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
	public List<Request> searchRequest(String createdbyCd, Date startDate,
			Date endDate, String managerCd, String assignCd,
			String requestTypeCd) {
		EntityManager em = getEntityManager();
		try {
			String scriptQuery = "select r from Request r ";
			if ((!createdbyCd.equals("")) || ((startDate != null))
					|| ((endDate != null)) || ((!managerCd.equals("")))
					|| ((!assignCd.equals("")))
					|| ((!requestTypeCd.equals("")))) {
				scriptQuery += "WHERE ";
			}
			if (!createdbyCd.equals("")) {
				if (((startDate != null)) || ((endDate != null))
						|| ((!managerCd.equals("")))
						|| ((!assignCd.equals("")))
						|| ((!requestTypeCd.equals("")))) {
					scriptQuery += "r.createdbyCd = :createdbyCd AND ";
				} else {
					scriptQuery += "r.createdbyCd = :createdbyCd ";
				}

			}
			if (startDate != null) {
				if (((endDate != null)) || ((!managerCd.equals("")))
						|| ((!assignCd.equals("")))
						|| ((!requestTypeCd.equals("")))) {
					scriptQuery += "r.startdate <= :startdate AND ";
				} else {
					scriptQuery += "r.startdate <= :startdate ";
				}
			}
			if (endDate != null) {
				if (((!managerCd.equals(""))) || ((!assignCd.equals("")))
						|| ((!requestTypeCd.equals("")))) {
					scriptQuery += "r.enddate >= :enddate AND ";
				} else {
					scriptQuery += "r.enddate >= :enddate ";
				}

			}
			if (!managerCd.equals("0")) {
				if ((!assignCd.equals("")) || ((!requestTypeCd.equals("")))) {
					scriptQuery += "r.managerCd = :managerCd AND ";
				} else {
					scriptQuery += "r.managerCd = :managerCd";
				}
			}
			if (!assignCd.equals("0")) {
				if (!requestTypeCd.equals("")) {
					scriptQuery += "r.assignedCd = :assignCd AND ";
				} else {
					scriptQuery += "r.assignedCd = :assignCd";
				}
			}
			if (!requestTypeCd.equals("")) {
				scriptQuery += "r.requesttypeCd = :requestTypeCd";
			}
			 System.out.println("Query la: " + scriptQuery);
			Query query = em.createQuery(scriptQuery);
			if (!createdbyCd.equals("")) {
				query.setParameter("createdbyCd", createdbyCd);
			}
			if (startDate != null) {
				query.setParameter("startDate", startDate);
			}
			if (endDate != null) {
				query.setParameter("endDate", endDate);
			}
			if (!managerCd.equals("0")) {
				query.setParameter("managerCd", managerCd);
			}
			if (!assignCd.equals("0")) {
				query.setParameter("assignCd", assignCd);
			}
			if (!requestTypeCd.equals("")) {
				query.setParameter("requestTypeCd", requestTypeCd);
			}
			List<Request> listRequest = (List<Request>) query.getResultList();

			return listRequest;
		} finally {
			em.close();
		}
	}

	/**
	 * Get Request by managerCd.
	 * 
	 * @param managerCd
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> getListRequestByManagerCd(String managerCd) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.managerCd = :managerCd");
			query.setParameter("managerCd", managerCd);
			List<Request> listRequest = (List<Request>) query.getResultList();

			return listRequest;
		} finally {
			em.close();
		}
	}
	
	/**
	 * Get Request by assignedCd.
	 * 
	 * @param assignedCd
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> getListRequestByAssignedCd(String assignedCd) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.assignedCd = :assignedCd");
			query.setParameter("assignedCd", assignedCd);
			List<Request> listRequest = (List<Request>) query.getResultList();

			return listRequest;
		} finally {
			em.close();
		}
	}
	
	/**
	 * Get Request by assignedCd, requestTypeCd
	 * 
	 * @param assignedCd
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> getListRequestByAssignedCdAndRequestTypeCd(String assignedCd, String requestTypeCd) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.assignedCd = :assignedCd AND r.requesttypeCd = :requesttypeCd");
			query.setParameter("assignedCd", assignedCd);
			query.setParameter("requesttypeCd", requestTypeCd);
			List<Request> listRequest = (List<Request>) query.getResultList();

			return listRequest;
		} finally {
			em.close();
		}
	}
	
	/**
	 * Get Request by managerCd, requestTypeCd.
	 * 
	 * @param assignedCd
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> getListRequestByManagerCdAndRequestTypeCd(String managerCd, String requestTypeCd) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.managerCd = :managerCd AND r.requesttypeCd = :requesttypeCd");
			query.setParameter("managerCd", managerCd);
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
}
