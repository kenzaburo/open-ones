package mks.dms.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import mks.dms.dao.controller.ExRequestJpaController;
import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.User;
import mks.dms.util.AppCons;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @description create service to bound of Jpa Controller
 * @author TriLVH
 *
 */
@Service
public class RequestService extends BaseService {
	
	/* Logger to log information */
	private final static Logger LOG = Logger.getLogger(RequestControllerService.class);
	
	/** Save = Create | Edit . */
	public final static int SAVE_FAIL = -1;
	public final static int CREATE_SUCCESS = 1;
	public final static int EDIT_SUCCESS = 2;
	/* Request Jpa controller */
	private final ExRequestJpaController controller;
	
	public RequestService() {
	    controller = new ExRequestJpaController(super.getEmf());
	}
	
	/* Default constructor to create controller */
	public RequestService(EntityManagerFactory emf) {
		controller = new ExRequestJpaController(emf);
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
		Date currentDate = new Date();
		// Find request by controller to check exist
		flag = (request.getId() != null) && (controller.findRequest(request.getId()) != null);
		
		String username = user.getUsername();
		// If flag is true edit request model
		if (flag) {
			try {
			    if (AppCons.TASK.equals(request.getRequesttypeCd())) {
			       //if (request.getManagerId().getId() == userLogin.getId()) {
	             if (username.equals(request.getManagerUsername())) {
	                    request.setStatus("Updated");
	                    request.setAssignerRead(1);
	                    request.setCreatorRead(1);
	                }
	             // else if (request.getCreatedbyId().getId() == userLogin.getId() && request.getAssignedId().getId() != userLogin.getId()) {
	                else if (user.getUsername().equals(request.getCreatedbyUsername()) && !username.equals(request.getAssigneeUsername())) {
	                    request.setStatus("Updated");
	                    request.setManagerRead(1);
	                    request.setAssignerRead(1);
	                }
	             // else if (request.getAssignedId().getId() == userLogin.getId() && request.getCreatedbyId().getId() != userLogin.getId()) {
	                else if (!user.getUsername().equals(request.getCreatedbyUsername()) && username.equals(request.getAssigneeUsername())) {
	                    request.setStatus("Updated1");
	                    request.setManagerRead(1);
	                    request.setCreatorRead(1);
	                }
	             // else if (request.getAssignedId().getId() == userLogin.getId() && request.getCreatedbyId().getId() == userLogin.getId()) {
	                else if (username.equals(request.getAssigneeUsername()) && user.getUsername().equals(request.getCreatedbyUsername())) {
	                    request.setStatus("Updated");
	                    request.setManagerRead(1);
	                    request.setAssignerRead(0);
	                    request.setCreatorRead(0);
	                }
	            }
			
	            
	            request.setLastmodifiedbyUsername(username);
	            request.setLastmodified(currentDate);

	            if (user.getUsername().equals(request.getAssigneeUsername())) {
	                request.setAssignerRead(1);
	            } else {
	                request.setAssignerRead(0);
	            }
	            request.setManagerRead(0);
			    
				controller.edit(request);
				return EDIT_SUCCESS;
			} catch (Exception ex) {
				// Log data by throws exception inner
				LOG.error("Could note update the request id " + request.getId(), ex);
				return SAVE_FAIL;
			}
		} else {
            request.setStatus(AppCons.STATUS_CREATED);
            // [TODO] Using meaningful constant
            request.setCreatorRead(1);
            
            if (AppCons.TASK.equals(request.getRequesttypeCd())) {
                if (username.equals(request.getAssigneeUsername())) {
                    request.setStatus(AppCons.STATUS_DOING);
                    
                    // [TODO] Using meaningful constant
                    request.setAssignerRead(1);
                }
                else {
                    request.setAssignerRead(0);
                }   
            }
            
            // [TODO] Using meaningful constant
            request.setManagerRead(0);
            
            request.setCreatedbyUsername(username);
            request.setCreated(currentDate);
		    
			controller.create(request);
			return CREATE_SUCCESS;
		}
	}

    /**
    * [Give the description for method].
    * @return
    */
    public List<Request> getListAnnouncement() {
        List<Request> lstRequest;
        lstRequest = controller.findRequestByType(AppCons.ANNOUNCEMENT);
        
        return lstRequest;
    }
    
    /**
    * [Give the description for method].
    * @return
    */
    public List<Request> getListRule() {
        List<Request> lstRequest;
        lstRequest = controller.findRequestByType(AppCons.RULE);
        
        return lstRequest;
    }
    
    /**
    * Support service layer can perform simple query.
    * @return
    */
    public ExRequestJpaController getDaoController() {
        return controller;
    }

    /**
     * Check isRead.
     * <br/>
     * @return 1 if unread, 0 if read
     */
    public int checkIsRead(Request request, User userLogin) {
        int i = 0;
        String username = userLogin.getUsername();
        
        // if (request.getCreatedbyCd() != null && request.getCreatedbyCd().equals(userLogin.getCd())) {
        if (username.equals(request.getCreatedbyUsername())) {
            if (request.getCreatorRead() == 0) {
                i = 1;
            }
        }
        
        // if (request.getAssignedCd() != null && request.getAssignedCd().equals(userLogin.getCd())) {
        if (username.equals(request.getAssigneeUsername())) {
            if (request.getAssignerRead() == 0) {
                i = 1;
            }
        }
        
        // if (request.getManagerCd() != null && request.getManagerCd().equals(userLogin.getCd())) {
        if (username.equals(request.getManagerUsername())) {
            if (request.getManagerRead() == 0) {
                i = 1;
            }
        }
        
        return i;
    }

}
