
package mks.dms.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import mks.dms.dao.controller.ExRequestJpaController;
import mks.dms.dao.controller.ExUserJpaController;
import mks.dms.dao.controller.RequestJpaController;
import mks.dms.dao.controller.UserJpaController;
import mks.dms.dao.controller.exceptions.IllegalOrphanException;
import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.RequestType;
import mks.dms.dao.entity.Watcher;
import mks.dms.dao.entity.User;
import mks.dms.extentity.ExUser;
import mks.dms.model.RequestCreateModel;

/**
 * @description Get data from database and map to model controller
 * @author TriLVH, ThachLe
 */
@Service
public class RequestControllerService extends BaseService{
    // Logger to log information to console.
    private final static Logger LOG = Logger.getLogger(RequestControllerService.class);
    
    /* Create mapping model of RequestCreate action */
    public RequestCreateModel getRequestCreateModel(MasterService masterService) {
        // Initialize new model
        RequestCreateModel requestCreateModel = new RequestCreateModel();
        // Get list RequestType from database
//        List<RequestType> listRequestType = masterService.getRequestTypes();
        // Get list user from database
//        List<User> listUser = masterService.getUsers();
        // Get list department from database
        List<Department> listDepartment = masterService.getDepartments();
        // Set data to model
//        requestCreateModel.setListRequestType(listRequestType);
//        requestCreateModel.setListUser(listUser);
        requestCreateModel.setListDepartment(listDepartment);
        
        return requestCreateModel;
    }

    /* Save request to database */
    public boolean saveRequest(RequestCreateModel model, MasterService masterService) {
        // Get request from model
    	Request request = model.getRequest();
    	// Create request with all default value.
//    	this.initialize(request);
    	
    	updateReferenceData(model, request);

    	// Save request with empty list watcher to get request id
    	RequestService requestService = masterService.getRequestService();
        requestService.saveOrUpdate(request);
    	
    	// LOG request id created by database
    	LOG.debug("RequestControllerService created id: " + request);
    	
    	// Create list watcher
    	this.createRequestWatchers(request, model, masterService);
        // Save list watcher
    	int retCd = requestService.saveOrUpdate(request);
    	
    	// LOG save watchers list to database success
    	LOG.debug("RequestControllerService create success: " + request);
    	
    	return (retCd != RequestService.SAVE_FAIL); 
    }

    /**
    * [Give the description for method].
    * @param model
    * @param request output
    */
    private void updateReferenceData(RequestCreateModel model, Request request) {
        // Get code, name of assigned member by id
    	User assignedUser = model.getRequest().getAssignedId();
    	
        if (assignedUser != null) {
            Integer userId = assignedUser.getId();
            if (userId != null) {
                UserJpaController userDaoCtrl = new UserJpaController(BaseService.getEmf());
                assignedUser = userDaoCtrl.findUser(userId);

                request.setAssignedAccount(assignedUser.getUsername());
                request.setAssignedName(ExUser.getFullname(assignedUser));
            }
        } else {
            // Do nothing
        }
    	
    	// Get code, name of manager by id
    	User managerUser = model.getRequest().getManagerId();
    	
        if (managerUser != null) {
            Integer managerUserId = managerUser.getId();
            if (managerUserId != null) {
                UserJpaController userDaoCtrl = new UserJpaController(BaseService.getEmf());
                managerUser = userDaoCtrl.findUser(managerUserId);

                request.setManagerAccount(managerUser.getUsername());
                request.setManagerName(ExUser.getFullname(managerUser));
            }
        } else {
            // Do nothing
        }
    }
    
//    /* Initialize model if it null*/
//    private void initialize(Request model) {
//    	// check valid and return valid data to model
//    	// create defautl list watcher
//    	model.setWatcherCollection(new ArrayList<Watcher>());
//    }
    
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
    
    /**
     * Get Request By Cd.
     * <br/>
     * @return Request
     */
    public Request getRequestById(int id) {
    	Request request;
    	
    	RequestJpaController daoCtrl = new RequestJpaController(emf);
    
    	request = daoCtrl.findRequest(id);
    	
    	return request;
    }
    
    /**
     * Get List<Request> By createdbyName.
     * <br/>
     * @return List<Request>
     */
    public List<Request> getListRequestByCreatedbyName(String createdbyName) {
    	List<Request> listRequest;
    	
    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
    
    	listRequest = daoCtrl.getListRequestByCreatename(createdbyName);
    	
    	return listRequest;
    }
    
    /**
     * Get List<Request> By managerName.
     * <br/>
     * @return List<Request>
     */
    public List<Request> getListRequestByManagerName(String managerName) {
    	List<Request> listRequest;
    	
    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
    
    	listRequest = daoCtrl.getListRequestByManagername(managerName);
    	
    	return listRequest;
    }
    
    /**
     * Get List<Request> By createdbyName, status and readstatus.
     * <br/>
     * @return List<Request>
     */
    public List<Request> getListRequestByCreatedbyNameAndStatusAndReadstatus(String createdbyName, String status, int readstatus) {
    	List<Request> listRequest;
    	
    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
    
    	listRequest = daoCtrl.getListRequestByCreatenameAndStatusAndReadstatus(createdbyName, status, readstatus);
    	
    	return listRequest;
    }
    
    /**
     * Get List<Request> By managerName.
     * <br/>
     * @return List<Request>
     */
    public List<Request> getListRequestByManagerNameAndStatusAndReadstatus(String managerName, String status, int readstatus) {
    	List<Request> listRequest;
    	
    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
    
    	listRequest = daoCtrl.getListRequestByManagernameAndStatusAndReadstatus(managerName, status, readstatus);
    	
    	return listRequest;
    }
    
    /**
     * Get Update Request.
     * @Param request
     * @return 
     * @throws Exception 
     * @throws NonexistentEntityException 
     * @throws IllegalOrphanException 
     */
    public void updateRequest(Request request) throws IllegalOrphanException, NonexistentEntityException, Exception {
    	
    	RequestJpaController daoCtrl = new RequestJpaController(emf);
    
    	daoCtrl.edit(request);
    }
    /**
     * Get User By Cd.
     * @param cd
     * @return User
     */
    public User getUserById(int id) {
    	User user;
    	
    	UserJpaController daoCtrl = new UserJpaController(emf);
    	
    	user = daoCtrl.findUser(id);
    	
    	return user;
    }
    
    /**
     * Get User By Cd.
     * @param username
     * @return User
     */
    public User getUserByUsername(String username) {
    	User user;
    	
    	ExUserJpaController daoCtrl = new ExUserJpaController(emf);
    	
    	user = daoCtrl.findUserByUsername(username);
    	
    	return user;
    }
    
    
    /**
     * Get all of User.
     * <br/>
     * @return list of User
     */
    public List<User> getAllUser() {
    	List<User> listUsers;
    	
    	UserJpaController userDaoCtrl = new UserJpaController(emf);
    	
    	listUsers = userDaoCtrl.findUserEntities();
    	
    	return listUsers;
    }
    
    /**
     * Create Request.
     * @param parentDepartment
     * @param data
     * @return
     */
    public boolean createRequest(Request request) {
    	RequestJpaController daoCtrl = new RequestJpaController(emf);
    	daoCtrl.create(request);
    	return true;
    }
}
