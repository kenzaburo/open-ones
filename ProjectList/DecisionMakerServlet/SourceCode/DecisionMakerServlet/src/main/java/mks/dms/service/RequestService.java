package mks.dms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;

import mks.dms.dao.controller.ExRequestJpaController;
import mks.dms.dao.controller.ExUserJpaController;
import mks.dms.dao.controller.RequestJpaController;
import mks.dms.dao.controller.UserJpaController;
import mks.dms.dao.controller.exceptions.IllegalOrphanException;
import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.*;

/**
 * @description create service to bound of Jpa Controller
 * @author TriLVH
 *
 */
public class RequestService {
	
	/* Logger to log information */
	private final static Logger LOG = Logger.getLogger(RequestControllerService.class);
	
	/** Save = Create | Edit . */
	public final static int SAVE_FAIL = -1;
	public final static int CREATE_SUCCESS = 1;
	public final static int EDIT_SUCCESS = 2;
	/* Request Jpa controller */
	private final RequestJpaController controller;
	
	/* Default constructor to create controller */
	public RequestService(EntityManagerFactory emf) {
		controller = new RequestJpaController(emf);
	}
	
	/**
	* Save or update database.
	* @param request
	* @return 1: Create success; 2: Update success 
	* -1: error
	*/
	public int saveOrUpdate(Request request) {
		// Init flag to check model exist
		boolean flag = false;
		// Find request by controller to check exist
		flag = (request.getId() != null) && (controller.findRequest(request.getId()) != null);
		
		// If flaf is true edit request model
		if (flag) {
			try {
				// Check default list wathcher
				if (request.getWatcherCollection() == null) {
					request.setWatcherCollection(new ArrayList<Watcher>());
				}
				controller.edit(request);
				return EDIT_SUCCESS;
			} catch (Exception e) {
				// Log data by throws exception inner
				LOG.error(e.getMessage());
				return SAVE_FAIL;
			}
		} else {
			controller.create(request);
			return CREATE_SUCCESS;
		}
	}
}
