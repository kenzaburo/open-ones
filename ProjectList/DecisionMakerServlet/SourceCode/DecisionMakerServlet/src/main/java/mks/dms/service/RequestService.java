package mks.dms.service;

import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;

import mks.dms.dao.controller.RequestJpaController;
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
	
	/* Request Jpa controller */
	private final RequestJpaController controller;
	
	/* Default constructor to create controller */
	public RequestService(EntityManagerFactory emf) {
		controller = new RequestJpaController(emf);
	}
	
	/* Save or update database */
	public void saveOrUpdate(Request request) {
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
			} catch (Exception e) {
				// Log data by throws exception inner
				LOG.error(e.getMessage());
			}
		} else {
			controller.create(request);
		}
	}
	
}
