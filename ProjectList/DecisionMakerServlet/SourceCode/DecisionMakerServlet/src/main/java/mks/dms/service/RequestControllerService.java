
package mks.dms.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.RequestType;
import mks.dms.dao.entity.Watcher;
import mks.dms.dao.entity.User;
import mks.dms.model.RequestCreateModel;

/**
 * @description Get data from database and map to model controller
 * @author TriLVH, ThachLe
 */
@Service
public class RequestControllerService {
    // Logger to log information to console.
    private final static Logger LOG = Logger.getLogger(RequestControllerService.class);
    
    /* Create mapping model of RequestCreate action */
    public RequestCreateModel getRequestCreateModel(MasterService masterService) {
        // Initialize new model
        RequestCreateModel requestCreateModel = new RequestCreateModel();
        // Get list RequestType from database
        List<RequestType> listRequestType = masterService.getRequestTypes();
        // Get list user from database
        List<User> listUser = masterService.getUsers();
        // Get list department from database
        List<Department> listDepartment = masterService.getDepartments();
        // Set data to model
        requestCreateModel.setListRequestType(listRequestType);
        requestCreateModel.setListUser(listUser);
        requestCreateModel.setListDepartment(listDepartment);
        
        return requestCreateModel;
    }

    /* Save request to database */
    public void saveRequest(RequestCreateModel model, MasterService masterService) {
        // Get request from model
    	Request request = model.getRequest();
    	// Create request with all default value.
    	this.initialize(request);
    	// Save request with empty list watcher to get request id
    	masterService.getRequestService().saveOrUpdate(request);
    	
    	// LOG request id created by database
    	LOG.debug("RequestControllerService created id: " + request);
    	
    	// Create list watcher
    	this.createRequestWatchers(request, model, masterService);
        // Save list watcher
    	masterService.getRequestService().saveOrUpdate(request);
    	
    	// LOG save watchers list to database success
    	LOG.debug("RequestControllerService create success: " + request);
    }
    
    /* Initialize model if it null*/
    private void initialize(Request model) {
    	// check valid and return valid data to model
    	// create defautl list watcher
    	model.setWatcherCollection(new ArrayList<Watcher>());
    }
    
    /* Create list watcher from request and user ids */
    private void createRequestWatchers(Request request, RequestCreateModel model, MasterService service) {
    	// LOG start watcher creating
    	LOG.debug("Creating watcher, size of watcher: " + 
    	        ((model.getListWatcher() != null) ? model.getListWatcher().length : -1));
    	
    	// Init watcher model to save to database
    	Watcher watcher;
    	// Create watcher class from request id and userid
        if (model.getListWatcher() != null) {
            for (Integer id : model.getListWatcher()) {
                // Create watcher from request id and userid
                watcher = new Watcher();
                watcher.setUserId(new User(id));
                watcher.setReqId(new Request(request.getId()));

                // LOG created watcher id by database
                LOG.debug("Created watcher id: " + watcher.getId());

                // save watcher to database
                service.getWatcherService().saveOrUpdate(watcher);
                // add watcher to request
                request.getWatcherCollection().add(watcher);
            }
        }
    }
}
