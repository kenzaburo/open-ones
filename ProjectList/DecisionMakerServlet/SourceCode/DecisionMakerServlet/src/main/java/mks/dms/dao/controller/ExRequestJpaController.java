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
	 * Get Request by createdbyName and status and readStatus.
	 * 
	 * @param username
	 * @param status
	 * @param readStatus
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> getListRequestByCreatenameAndStatusAndReadstatus(
			String createdbyName, String status, int readstatus) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.createdbyName = :createdbyName AND r.status = :status AND r.readstatus = :readstatus");
			query.setParameter("createdbyName", createdbyName);
			query.setParameter("status", status);
			query.setParameter("readstatus", readstatus);
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
	public List<Request> getListRequestByManagernameAndStatusAndReadstatus(
			String managerName, String status, int readstatus) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.managerName = :managerName AND r.status = :status AND r.readstatus = :readstatus");
			query.setParameter("managerName", managerName);
			query.setParameter("status", status);
			query.setParameter("readstatus", readstatus);
			List<Request> listRequest = (List<Request>) query.getResultList();

			return listRequest;
		} finally {
			em.close();
		}
	}

	/**
	 * Get Request by createdbyName
	 * 
	 * @param username
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> getListRequestByCreatename(String createdbyName) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.createdbyName = :createdbyName");
			query.setParameter("createdbyName", createdbyName);
			List<Request> listRequest = (List<Request>) query.getResultList();

			return listRequest;
		} finally {
			em.close();
		}
	}

	/**
	 * Get Request by createdbyName
	 * 
	 * @param createdbyName
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
	public List<Request> searchRequest(String createdbyName, Date startDate,
			Date endDate, String managerId, String assignId,
			String requestTypeCd) {
		EntityManager em = getEntityManager();
		try {
			String scriptQuery = "select r from Request r ";
			if ((!createdbyName.equals("")) || ((startDate != null))
					|| ((endDate != null)) || ((!managerId.equals("")))
					|| ((!assignId.equals("")))
					|| ((!requestTypeCd.equals("")))) {
				scriptQuery += "WHERE ";
			}
			if (!createdbyName.equals("")) {
				if (((startDate != null)) || ((endDate != null))
						|| ((!managerId.equals("")))
						|| ((!assignId.equals("")))
						|| ((!requestTypeCd.equals("")))) {
					scriptQuery += "r.createdbyname = :createdbyname AND ";
				} else {
					scriptQuery += "r.createdbyname = :createdbyname ";
				}

			}
			if (startDate != null) {
				if (((endDate != null)) || ((!managerId.equals("")))
						|| ((!assignId.equals("")))
						|| ((!requestTypeCd.equals("")))) {
					scriptQuery += "r.startdate <= :startdate AND ";
				} else {
					scriptQuery += "r.startdate <= :startdate ";
				}
			}
			if (endDate != null) {
				if (((!managerId.equals(""))) || ((!assignId.equals("")))
						|| ((!requestTypeCd.equals("")))) {
					scriptQuery += "r.enddate >= :enddate AND ";
				} else {
					scriptQuery += "r.enddate >= :enddate ";
				}

			}
			if (!managerId.equals("")) {
				if ((!assignId.equals("")) || ((!requestTypeCd.equals("")))) {
					scriptQuery += "r.managerAccount = :managerId AND ";
				} else {
					scriptQuery += "r.managerAccount = :managerId";
				}
			}
			if (!assignId.equals("")) {
				if (!requestTypeCd.equals("")) {
					scriptQuery += "r.assignedAccount = :assignId AND ";
				} else {
					scriptQuery += "r.assignedAccount = :assignId";
				}
			}
			if (!requestTypeCd.equals("")) {
				scriptQuery += "r.requesttypeCd = :requestTypeCd";
			}
			// System.out.println("Query la: " + scriptQuery);
			Query query = em.createQuery(scriptQuery);
			if (!createdbyName.equals("")) {
				query.setParameter("createdbyName", createdbyName);
			}
			if (startDate != null) {
				query.setParameter("startDate", startDate);
			}
			if (endDate != null) {
				query.setParameter("endDate", endDate);
			}
			if (!managerId.equals("")) {
				query.setParameter("managerId", managerId);
			}
			if (!assignId.equals("")) {
				query.setParameter("assignId", assignId);
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
	 * Get Request by managerName and status and readStatus.
	 * 
	 * @param username
	 * @return List<Request>
	 * @see http
	 *      ://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development
	 *      /Querying/JPQL
	 */
	public List<Request> getListRequestByManagername(String managerName) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("Select r FROM Request r WHERE r.managerName = :managerName");
			query.setParameter("managerName", managerName);
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
