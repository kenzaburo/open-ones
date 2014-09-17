
package mks.dms.service;

import java.io.IOException;
import java.util.List;

import mks.dms.dao.controller.ExRequestJpaController;
import mks.dms.dao.controller.ExRequestTypeJpaController;
import mks.dms.dao.controller.ExUserJpaController;
import mks.dms.dao.controller.RequestJpaController;
import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.RequestType;
import mks.dms.dao.entity.User;
import mks.dms.extentity.ExUser;
import mks.dms.model.RequestModel;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description Get data from database and map to model controller
 * @author TriLVH, ThachLe
 */
@Service
public class RequestControllerService extends RequestService {
    // Logger to log information to console.
    private final static Logger LOG = Logger.getLogger(RequestControllerService.class);
    
//    /* Save request to database */
//    public boolean saveRequest(RequestModel model, MasterService masterService) {
//        // Get request from model
//    	Request request = AppUtil.parseRequestModel2Entity(model);
//    	// Create request with all default value.
////    	this.initialize(request);
//    	
//    	updateReferenceData(model, request);
//
//    	// Save request with empty list watcher to get request id
//    	RequestService requestService = masterService.getRequestService();
////        requestService.saveOrUpdate(request);
//    	
//    	// LOG request id created by database
//    	LOG.debug("RequestControllerService created id: " + request);
//    	
//    	// Create list watcher
//    	this.createRequestWatchers(request, model, masterService);
//        // Save list watcher
//    	int retCd = requestService.saveOrUpdate(request);
//    	
//    	// LOG save watchers list to database success
//    	LOG.debug("RequestControllerService create success: " + request);
//    	
//    	return (retCd != RequestService.SAVE_FAIL); 
//    }
//    
//    public int saveOrUpdate(Request request, MasterService masterService) {
//    	RequestService requestService = masterService.getRequestService();
//    	return requestService.saveOrUpdate(request);
//    }

    /**
    * [Give the description for method].
    * @param model
    * @param request output
    */
//    private void updateReferenceData(RequestModel model, Request request) {
//        // Update request type id, request type name from request type cd
//        ExRequestTypeJpaController reqTypeJpaCtrl = new ExRequestTypeJpaController(BaseService.getEmf());
//        RequestType reqType = reqTypeJpaCtrl.findRequestTypeByCd(request.getRequesttypeCd());
//        
//        if (reqType != null) {
//            request.setRequesttypeName(reqType.getName());
//        } else {
//            LOG.error("Could not found request type cd = " + request.getRequesttypeCd());
//        }
//        
//        // Get code, name of assigned member by cd
//    	String assigneeCd = model.getAssigneeAccount();
//    	ExUserJpaController userDaoCtrl = new ExUserJpaController(BaseService.getEmf());
//        if (assigneeCd != null) {
//            
//            User assigneeUser = userDaoCtrl.findUserByUsername(assigneeCd);
//            request.setAssigneeName(ExUser.getFullname(assigneeUser));
//        } else {
//            // Do nothing
//        }
//    	
//    	// Get code, name of manager by id
//        String managerAccount = model.getManagerAccount();
//    	
//        if (managerAccount != null) {
//            User managerUser = userDaoCtrl.findUserByUsername(managerAccount);
//            request.setManagerName(ExUser.getFullname(managerUser));
//        } else {
//            // Do nothing
//        }
//        
//        // Update attachment
//        List<MultipartFile> lstAttachment = model.getAttachments();
//        if (lstAttachment != null) {
//            MultipartFile attachFile = lstAttachment.get(0);
//            request.setFilename1(attachFile.getOriginalFilename());
//            try {
//                request.setAttachment1(attachFile.getBytes());
//            } catch (IOException ex) {
//                LOG.warn("Could not get the content of attached file", ex);
//            }
//        }
//    }
    
//    /* Initialize model if it null*/
//    private void initialize(Request model) {
//    	// check valid and return valid data to model
//    	// create defautl list watcher
//    	model.setWatcherCollection(new ArrayList<Watcher>());
//    }
    
    /* Create list watcher from request and user ids */
//    private void createRequestWatchers(Request request, RequestCreateModel model, MasterService service) {
//    	// LOG start watcher creating
//    	LOG.debug("Creating watcher, size of watcher: " + 
//    	        ((model.getListWatcher() != null) ? model.getListWatcher().length : -1));
//    	
//    	// Init watcher model to save to database
//    	Watcher watcher;
//    	// Create watcher class from request id and userid
//        if (model.getListWatcher() != null) {
//            for (Integer id : model.getListWatcher()) {
//                // Create watcher from request id and userid
//                watcher = new Watcher();
//                watcher.setUserId(new User(id));
//                watcher.setReqId(new Request(request.getId()));
//
//                // LOG created watcher id by database
//                LOG.debug("Created watcher id: " + watcher.getId());
//
//                // save watcher to database
//                service.getWatcherService().saveOrUpdate(watcher);
//                // add watcher to request
//                request.getWatcherCollection().add(watcher);
//            }
//        }
//    }
    
    /**
     * Get all of Request.
     * <br/>
     * @return List<Request>
     */
//    public List<Request> getAllRequest() {
//    	List<Request> listRequest;
//    	
//    	RequestJpaController daoCtrl = new RequestJpaController(emf);
//    	
//    	listRequest = daoCtrl.findRequestEntities();
//    	
//    	return listRequest;
//    }
    
    /**
     * Get Request By Id.
     * <br/>
     * @return Request
     */
//    public Request getRequestById(int id) {
//    	Request request;
//    	
//    	RequestJpaController daoCtrl = new RequestJpaController(emf);
//    
//    	request = daoCtrl.findRequest(id);
//    	
//    	return request;
//    }

    /**
     * Get List<Request> By assignedCd, requestTypeCd.
     * <br/>
     * @return List<Request>
     */
//    public List<Request> getListRequestByAssignedCdAndRequestTypeCd(String assignedCd, String requestTypeCd) {
//    	List<Request> listRequest;
//    	
//    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
//    
//    	listRequest = daoCtrl.getListRequestByAssignedCdAndRequestTypeCd(assignedCd, requestTypeCd);
//    	
//    	return listRequest;
//    }
    
    /**
     * Get List<Request> By managerCd, requestTypeCd.
     * <br/>
     * @return List<Request>
     */
//    public List<Request> getListRequestByManagerCdAndRequestTypeCd(String managerCd, String requestTypeCd) {
//    	List<Request> listRequest;
//    	
//    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
//    
//    	listRequest = daoCtrl.getListRequestByManagerCdAndRequestTypeCd(managerCd, requestTypeCd);
//    	
//    	return listRequest;
//    }
    
    /**
     * Get List<Request> By createdbyCd.
     * <br/>
     * @return List<Request>
     */
//    public List<Request> getListRequestByCreatedbyCd(String createdbyCd) {
//    	List<Request> listRequest;
//    	
//    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
//    
//    	listRequest = daoCtrl.getListRequestByCreatedbyCd(createdbyCd);
//    	
//    	return listRequest;
//    }
//    
    /**
     * Get List<Request> By managerCd.
     * <br/>
     * @return List<Request>
     */
//    public List<Request> getListRequestByManagerCd(String managerCd) {
//    	List<Request> listRequest;
//    	
//    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
//    
//    	listRequest = daoCtrl.getListRequestByManagerCd(managerCd);
//    	
//    	return listRequest;
//    }
    
    /**
     * Get List<Request> By assignedCd.
     * <br/>
     * @return List<Request>
     */
//    public List<Request> getListRequestByAssignedCd(String assignedCd) {
//    	List<Request> listRequest;
//    	
//    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
//    
//    	listRequest = daoCtrl.getListRequestByAssignedCd(assignedCd);
//    	
//    	return listRequest;
//    }
//    
//    /**
//     * Check isRead.
//     * <br/>
//     * @return 1 if unread, 0 if read
//     */
//    public int checkIsRead(Request request, User userLogin) {
//    	int i = 0;
//    	if (request.getCreatedbyCd() != null && request.getCreatedbyCd().equals(userLogin.getCd())) {
//    		if (request.getCreatorRead() == 0) {
//    			i = 1;
//    		}
//    	}
//    	
//    	if (request.getAssignedCd() != null && request.getAssignedCd().equals(userLogin.getCd())) {
//    		if (request.getAssignerRead() == 0) {
//    			i = 1;
//    		}
//    	}
//    	
//    	if (request.getManagerCd() != null && request.getManagerCd().equals(userLogin.getCd())) {
//    		if (request.getManagerRead() == 0) {
//    			i = 1;
//    		}
//    	}
//    	
//    	return i;
//    }

    /**
     * Search List<Request>.
     * <br/>
     * @return List<Request>
     */
//    public List<Request> searchRequest(String createdbyCd, Date startDate, Date endDate, String managerCd, String assignCd, String requestTypeCd) {
//    	List<Request> listRequest;
//    	
//    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
//    	
//    	listRequest = daoCtrl.searchRequest(createdbyCd, startDate, endDate, managerCd, assignCd, requestTypeCd);
//    	
//    	return listRequest;
//    }
//    
    
    /**
     * Get List<Request> By createdbyCd, status and readstatus.
     * <br/>
     * @return List<Request>
     */
//    public List<Request> getListRequestByCreatorCdAndStatusAndCreatorRead(String createdbyCd, String status, int creatorRead) {
//    	List<Request> listRequest;
//    	
//    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
//    
//    	listRequest = daoCtrl.getListRequestByCreatorCdAndStatusAndCreatorRead(createdbyCd, status, creatorRead);
//    	
//    	return listRequest;
//    }
    
    /**
     * Get List<Request> By requestTypeCd.
     * <br/>
     * @return List<Request>
     */
//    public List<Request> getListRequestByRequestTypeCd(String requestTypeCd) {
//    	List<Request> listRequest;
//    	
//    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
//    	
//    	listRequest = daoCtrl.getListRequestByRequestTypeCd(requestTypeCd);
//    	
//    	return listRequest;
//    }
    
    /**
     * Get List<Request> By requestTypeCd.
     * <br/>
     * @return List<Request>
     */
//    public List<Request> getListRequestByRequestTypeCdAndOrderByCreate(String requestTypeCd, String order) {
//    	List<Request> listRequest;
//    	
//    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
//    	
//    	listRequest = daoCtrl.getListRequestByRequestTypeCdAndOrderByCreate(requestTypeCd, order);
//    	
//    	return listRequest;
//    }
    
    /**
     * Get List<Request> By managerCd.
     * <br/>
     * @return List<Request>
     */
//    public List<Request> getListRequestByManagerCdAndStatusAndReadstatus(String managedCd, String status, int managerRead) {
//    	List<Request> listRequest;
//    	
//    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
//    
//    	listRequest = daoCtrl.getListRequestByManagerCdAndStatusAndManagerRead(managedCd, status, managerRead);
//    	
//    	return listRequest;
//    }
    
    /**
     * Get List<Request> By assignerCd.
     * <br/>
     * @return List<Request>
     */
//    public List<Request> getListRequestByAssignerCdAndStatusAndAssignerRead(String assignerCd, String status, int assignerRead) {
//    	List<Request> listRequest;
//    	
//    	ExRequestJpaController daoCtrl = new ExRequestJpaController(emf);
//    
//    	listRequest = daoCtrl.getListRequestByAssignerCdAndStatusAndAssignerRead(assignerCd, status, assignerRead);
//    	
//    	return listRequest;
//    }
    
    /**
     * Get RequestType By Cd.
     * @param cd
     * @return RequestType
     */
//    public RequestType getRequestTypeByCd(String requestCd) {
//    	ExRequestTypeJpaController daoCtrl = new ExRequestTypeJpaController(emf);
//    	return daoCtrl.getListRequestByRequestCd(requestCd);
//    }
//    
    /**
     * Create Request.
     * @param parentDepartment
     * @param data
     * @return
     */
//    public boolean createRequest(Request request) {
//    	RequestJpaController daoCtrl = new RequestJpaController(emf);
//    	daoCtrl.create(request);
//    	return true;
//    }
}
