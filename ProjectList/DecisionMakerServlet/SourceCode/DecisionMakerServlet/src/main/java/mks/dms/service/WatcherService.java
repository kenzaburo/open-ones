package mks.dms.service;

import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;

import mks.dms.dao.controller.WatcherJpaController;
import mks.dms.dao.entity.Watcher;

/**
 * @description create service to bound of Jpa Controller
 * @author TriLVH
 *
 */
public class WatcherService {
	/* watcher Jpa controller */
	private final WatcherJpaController controller;
	
	/* Default constructor to create controller */
	public WatcherService(EntityManagerFactory emf) {
		controller = new WatcherJpaController(emf);
	}
	
	/* Save or update database */
	public void saveOrUpdate(Watcher watcher) {
		// Init flag to check model exist
		boolean flag = false;
		// Find watcher by controller to check exist
		flag = (watcher.getId() != null) && (controller.findWatcher(watcher.getId()) != null);
		
		// If flaf is true edit watcher model
		if (!flag) {
			controller.create(watcher);
		}
	}
	
}
