package mks.dms.dao.controller;

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
    private final static Logger LOG = Logger.getLogger(ExDepartmentJpaController.class);
    
	public ExRequestJpaController(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}
	
	/**
	    * Get Request by createdbyName and status and readStatus.
	    * @param username
	    * @param status
	    * @param readStatus
	    * @return List<Request>
	    * @see http://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development/Querying/JPQL
	    */
	    public List<Request> getListRequestByCreatenameAndStatusAndReadstatus(String createdbyName, String status, int readstatus) {
	        EntityManager em = getEntityManager();
	        try {
	            Query query = em.createQuery("Select r FROM Request r WHERE r.createdbyName = :createdbyName AND r.status = :status AND r.readstatus = :readstatus");
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
		    * @param username
		    * @param status
		    * @param readStatus
		    * @return List<Request>
		    * @see http://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development/Querying/JPQL
		    */
		    public List<Request> getListRequestByManagernameAndStatusAndReadstatus(String managerName, String status, int readstatus) {
		        EntityManager em = getEntityManager();
		        try {
		            Query query = em.createQuery("Select r FROM Request r WHERE r.managerName = :managerName AND r.status = :status AND r.readstatus = :readstatus");
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
			    * @param username
			    * @return List<Request>
			    * @see http://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development/Querying/JPQL
			    */
			    public List<Request> getListRequestByCreatename(String createdbyName) {
			        EntityManager em = getEntityManager();
			        try {
			            Query query = em.createQuery("Select r FROM Request r WHERE r.createdbyName = :createdbyName");
			            query.setParameter("createdbyName", createdbyName);
			            List<Request> listRequest = (List<Request>) query.getResultList();
			            
			            return listRequest;
			        } finally {
			            em.close();
			        }
			    }
			    
			    /**
				    * Get Request by managerName and status and readStatus.
				    * @param username
				    * @return List<Request>
				    * @see http://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development/Querying/JPQL
				    */
				    public List<Request> getListRequestByManagername(String managerName) {
				        EntityManager em = getEntityManager();
				        try {
				            Query query = em.createQuery("Select r FROM Request r WHERE r.managerName = :managerName");
				            query.setParameter("managerName", managerName);
				            List<Request> listRequest = (List<Request>) query.getResultList();
				            
				            return listRequest;
				        } finally {
				            em.close();
				        }
				    }

    /**
    * Get all available announcements.
    * @param requestTypeCd Announcement | Rule | Task | Leave
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
