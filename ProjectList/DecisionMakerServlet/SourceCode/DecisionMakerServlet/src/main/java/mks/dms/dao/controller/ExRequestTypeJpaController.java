package mks.dms.dao.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import mks.dms.dao.entity.RequestType;

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
    * @param requesttypeCd
    * @return
    * @see http://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development/Querying/JPQL
    */
    public RequestType findRequestTypeByCd(String requesttypeCd) {
        EntityManager em = getEntityManager();
        try {
            
            // Query query = em.createQuery("Select e FROM RequestType e WHERE e.cd = :requestTypeCd");
            Query query = em.createNamedQuery("RequestType.findByCd");
            query.setParameter("cd", requesttypeCd);
            RequestType reqType = (RequestType) query.getSingleResult();
            
            return reqType;
        } catch (NoResultException nsEx) {
            LOG.warn("Request '" + requesttypeCd + "' not found.", nsEx);
            return null;
        } finally {
            em.close();
        }
    }
}

