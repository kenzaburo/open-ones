package mks.dms.dao.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.RequestType;
import mks.dms.dao.entity.User;

import org.apache.log4j.Logger;

/**
 * This class extends interfaces of RequestTypeJpaController. <br/>
 * 
 * @author truongtho88.nl
 */


public class ExRequestTypeJpaController extends RequestTypeJpaController{

	/** Logging. */
    private final static Logger LOG = Logger.getLogger(ExRequestTypeJpaController.class);
	
	public ExRequestTypeJpaController(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

	/**
	* Get RequestType by requestCd.
	* @param requestCd
	* @return RequestType
	* @see http://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development/Querying/JPQL
	*/
	public RequestType getListRequestByRequestCd(String requestCd) {
		EntityManager em = getEntityManager();
	    try {
	    	Query query = em.createQuery("Select r FROM RequestType r WHERE r.cd = :requestCd");
	        query.setParameter("requestCd", requestCd);
	        RequestType requestType = (RequestType) query.getSingleResult();
	            
	        return requestType;
	    } finally {
	        em.close();
	    }
	}

    public RequestType findRequestTypeByCd(String requesttypeCd) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("Select e FROM RequestType e WHERE e.cd = :requestTypeCd");
            query.setParameter("requestTypeCd", requesttypeCd);
            RequestType reqType = (RequestType) query.getSingleResult();
            
            return reqType;
        } finally {
            em.close();
        }
    }
}

