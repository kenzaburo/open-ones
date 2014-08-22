
package mks.dms.service;

import java.util.Date;
import java.util.List;

import mks.dms.dao.controller.ExRequestJpaController;
import mks.dms.dao.controller.ExRequestTypeJpaController;
import mks.dms.dao.controller.ExUserJpaController;
import mks.dms.dao.controller.RequestJpaController;
import mks.dms.dao.controller.UserJpaController;
import mks.dms.dao.controller.exceptions.IllegalOrphanException;
import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.RequestType;
import mks.dms.dao.entity.User;
import mks.dms.dao.entity.Watcher;
import mks.dms.extentity.ExUser;
import mks.dms.model.RequestCreateModel;
import mks.dms.util.AppCons;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

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
        
        // Set default of type: Task
        if (requestCreateModel.getRequest() == null) {
            requestCreateModel.setRequest(new Request());
        }
        requestCreateModel.getRequest().setRequesttypeCd(AppCons.TASK);
        //
        
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
        // Update request type id, request type name from request type cd
        ExRequestTypeJpaController reqTypeJpaCtrl = new ExRequestTypeJpaController(BaseService.getEmf());
        RequestType reqType = reqTypeJpaCtrl.findRequestTypeByCd(request.getRequesttypeCd());
        if (reqType != null) {
            request.setRequesttypeId(reqType.getId());
            request.setRequesttypeName(reqType.getName());
        } else {
            LOG.error("Could not found request type cd = " + request.getRequesttypeCd());
        }
        
        // Get code, name of assigned member by id
    	User assignedUser = model.getRequest().getAssignedId();
    	
        if (assignedUser != null) {
            Integer userId = assignedUser.getId();
            if (userId != null) {
                UserJpaController userDaoCtrl = new UserJpaController(BaseService.getEmf());
                assignedUser = userDaoCtrl.findUser(userId);

                request.setAssignedCd(assignedUser.getUsername());
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

                if (managerUser != null) {
                    request.setManagerCd(managerUser.getUsername());
                    request.setManagerCd(ExUser.getFullname(managerUser));
                }
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
     * Get all of Request.
     * <br/>
     * @return List<Request>
     */
    public List<Request> getAllRequest() {
    	List<Request> listRequest;
    	
    	RequestJpaController daoCtrl = new RequestJpaController(emf);
    	
    	listRequest = daoCtrl.findRequestEntities();
    	
    	return listRequest;
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
     * Get List<Request> By createdbyCd.
     * <br/>
     * @return List<Request>
     */
    public List<Request> getListRequestByCreatedbyCd(String createdbyCd) {
    	List<Request> listRequest;
    	
    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
    
    	listRequest = daoCtrl.getListRequestByCreatedbyCd(createdbyCd);
    	
    	return listRequest;
    }
    
    /**
     * Get List<Request> By managerCd.
     * <br/>
     * @return List<Request>
     */
    public List<Request> getListRequestByManagerCd(String managerCd) {
    	List<Request> listRequest;
    	
    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
    
    	listRequest = daoCtrl.getListRequestByManagerCd(managerCd);
    	
    	return listRequest;
    }
    
    /**
     * Search List<Request>.
     * <br/>
     * @return List<Request>
     */
    public List<Request> searchRequest(String createdbyCd, Date startDate, Date endDate, String managerCd, String assignCd, String requestTypeCd) {
    	List<Request> listRequest;
    	
    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
    	
    	listRequest = daoCtrl.searchRequest(createdbyCd, startDate, endDate, managerCd, assignCd, requestTypeCd);
    	
    	return listRequest;
    }
    
    
    /**
     * Get List<Request> By createdbyCd, status and readstatus.
     * <br/>
     * @return List<Request>
     */
    public List<Request> getListRequestByCreatedbyCdAndStatusAndReadstatus(String createdbyCd, String status, int readstatus) {
    	List<Request> listRequest;
    	
    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
    
    	listRequest = daoCtrl.getListRequestByCreatedbyCdAndStatusAndReadstatus(createdbyCd, status, readstatus);
    	
    	return listRequest;
    }
    
    /**
     * Get List<Request> By managerCd.
     * <br/>
     * @return List<Request>
     */
    public List<Request> getListRequestByManagerCdAndStatusAndReadstatus(String managerCd, String status, int readstatus) {
    	List<Request> listRequest;
    	
    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
    
    	listRequest = daoCtrl.getListRequestByManagerCdAndStatusAndReadstatus(managerCd, status, readstatus);
    	
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
     * Get RequestType By Cd.
     * @param cd
     * @return RequestType
     */
    public RequestType getRequestTypeByCd(String requestCd) {
    	ExRequestTypeJpaController daoCtrl = new ExRequestTypeJpaController(emf);
    	return daoCtrl.getListRequestByRequestCd(requestCd);
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
