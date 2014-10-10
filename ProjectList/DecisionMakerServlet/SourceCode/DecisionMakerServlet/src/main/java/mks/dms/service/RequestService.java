package mks.dms.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import mks.dms.dao.controller.ExRateJpaController;
import mks.dms.dao.controller.ExRequestJpaController;
import mks.dms.dao.controller.ExRequestTypeJpaController;
import mks.dms.dao.controller.ExUserJpaController;
import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.Rate;
import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.RequestType;
import mks.dms.dao.entity.User;
import mks.dms.extentity.ExUser;
import mks.dms.model.SearchRequestConditionModel;
import mks.dms.model.SearchTaskConditionModel;
import mks.dms.util.AppCons;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import rocky.common.CommonUtil;

/**
 * @description create service to bound of Jpa Controller
 * @author TriLVH
 *
 */
/**
 * @author ThachLN
 *
 */
@Service
public class RequestService extends BaseService {
	
	/* Logger to log information */
	private final static Logger LOG = Logger.getLogger(RequestService.class);
	
	/** Save = Create | Edit . */
	public final static int SAVE_FAIL = -1;
	
	public final static int MODE_CREATE = 1;
	public final static int CREATE_SUCCESS = 1;
	
	public final static int MODE_EDIT = 2;
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
		
		// Find request by controller to check exist
		flag = (request.getId() != null) && (controller.findRequest(request.getId()) != null);
		
		String username = user.getUsername();
		// If flag is true edit request model
		if (flag) {
			try {
			    if (AppCons.TASK.equals(request.getRequesttypeCd())) {
			       //if (request.getManagerId().getId() == userLogin.getId()) {
	             if (username.equals(request.getManagerUsername())) {
//	                    request.setStatus("Updated");
	                    request.setAssignerRead(1);
	                    request.setCreatorRead(1);
	                }
	             // else if (request.getCreatedbyId().getId() == userLogin.getId() && request.getAssignedId().getId() != userLogin.getId()) {
	                else if (user.getUsername().equals(request.getCreatedbyUsername()) && !username.equals(request.getAssigneeUsername())) {
//	                    request.setStatus("Updated");
	                    request.setManagerRead(1);
	                    request.setAssignerRead(1);
	                }
	             // else if (request.getAssignedId().getId() == userLogin.getId() && request.getCreatedbyId().getId() != userLogin.getId()) {
	                else if (!user.getUsername().equals(request.getCreatedbyUsername()) && username.equals(request.getAssigneeUsername())) {
//	                    request.setStatus("Updated1");
	                    request.setManagerRead(1);
	                    request.setCreatorRead(1);
	                }
	             // else if (request.getAssignedId().getId() == userLogin.getId() && request.getCreatedbyId().getId() == userLogin.getId()) {
	                else if (username.equals(request.getAssigneeUsername()) && user.getUsername().equals(request.getCreatedbyUsername())) {
//	                    request.setStatus("Updated");
	                    request.setManagerRead(1);
	                    request.setAssignerRead(0);
	                    request.setCreatorRead(0);
	                }
	            }
			
			    updateReferenceData(request, MODE_EDIT);
				controller.edit(request);
				
				return EDIT_SUCCESS;
			} catch (Exception ex) {
				// Log data by throws exception inner
				LOG.error("Could note update the request id " + request.getId(), ex);
				return SAVE_FAIL;
			}
		} else {
		    updateReferenceData(request, MODE_CREATE);
		    try {
		        controller.create(request);
		        return CREATE_SUCCESS;
		    } catch (Exception ex) {
		        LOG.error("Could note create the request id " + request.getId(), ex);
                return SAVE_FAIL;
		    }
		}
	}

	/**
	* Save Rate for the request.
	* @param requestId Identifier of the request
	* @param rate information of Rate
	* ReqId is not required. It will be set from the requestId
	* @throws ServiceException
	* If requestId does not existed
	*/
	public void saveRate(Integer requestId, Rate rate) throws ServiceException {
	    Request request = controller.findRequest(requestId);
	    
	    if (request == null) {
	        throw new ServiceException(ServiceError.DATA_NO_EXIST);
	    } else {
	        // Do next step
	    }
	    
	    // Find current Rate of the request
	    ExRateJpaController rateDaoCtrl = new ExRateJpaController(BaseService.getEmf());
	    Rate currRate = rateDaoCtrl.findLatestRateByRequestId(request.getId());
	    
	    if (currRate == null) {
	        rate.setReqId(request.getId());
	        
	        rateDaoCtrl.create(rate);
	    } else {
	        // Update rate
	        currRate.setRank(rate.getRank());
	        currRate.setContent(rate.getContent());
	        currRate.setUsername(rate.getUsername());
	        currRate.setEmail(rate.getEmail());
	        currRate.setCreated(new Date());
	        
	        try {
                rateDaoCtrl.edit(currRate);
            } catch (NonexistentEntityException ex) {
                LOG.error("Could not update the Rate of RequestId '" + requestId + "'", ex);
            } catch (Exception ex) {
                LOG.error("Could not update the Rate of RequestId '" + requestId + "'", ex);
            }
	    }
	}
	

    /**
    * [Give the description for method].
    * @param request
    * @param mode 1: Create (MODE_CREATE); 2: Edit (MODE_EDIT)
    */
    private void updateReferenceData(Request request, int mode) {
        Date currentDate = new Date();
        String username = user.getUsername();
        
        // Update request type name from request type cd
        ExRequestTypeJpaController reqTypeJpaCtrl = new ExRequestTypeJpaController(BaseService.getEmf());
        RequestType reqType = reqTypeJpaCtrl.findRequestTypeByCd(request.getRequesttypeCd());
        
        if (reqType != null) {
            request.setRequesttypeName(reqType.getName());
        } else {
            LOG.error("Could not found request type cd = " + request.getRequesttypeCd());
        }
        
        // Get Full name of assignee from account
        String assigneeAccount = request.getAssigneeUsername();
        ExUserJpaController userDaoCtrl = new ExUserJpaController(BaseService.getEmf());
        if (CommonUtil.isNNandNB(assigneeAccount)) {
            User assigneeUser = userDaoCtrl.findUserByUsername(assigneeAccount);
            request.setAssigneeName(ExUser.getFullname(assigneeUser));
        } else {
            // Do nothing
        }
        
        // Get Full name of manager from account
        String managerAccount = request.getManagerUsername();
        
        if (CommonUtil.isNNandNB(managerAccount)) {
            User managerUser = userDaoCtrl.findUserByUsername(managerAccount);
            request.setManagerName(ExUser.getFullname(managerUser));
        } else {
            // Do nothing
        }
        
        // Update Department name from Department code
        if (CommonUtil.isNNandNB(request.getDepartmentCd())) {
            Department dept = MasterService.findDepartmentByCd(request.getDepartmentCd());
            
            if (dept != null) {
                request.setDepartmentName(dept.getName());
            } else {
                // Do nothing
            }
        } else {
            // Do nothing
        }
        
//        request.setStatus(AppCons.STATUS_CREATED);
        switch (mode) {
            case MODE_CREATE:
                request.setStatus(AppCons.STATUS_CREATED);
                // [TODO] Using meaningful constant
                request.setCreatorRead(1);
                
                if (AppCons.TASK.equals(request.getRequesttypeCd())) {
                    if (username.equals(request.getAssigneeUsername())) {                        
                        // [TODO] Using meaningful constant
                        request.setAssignerRead(1);
                    }
                    else {
                        request.setAssignerRead(0);
                    }   
                }
                
                // [TODO] Using meaningful constant
                request.setManagerRead(0);
                
                // Update System information
                request.setCreatedbyUsername(username);
                request.setCreated(currentDate);
                
                // Update full name of assignee
                if (CommonUtil.isNNandNB(request.getAssigneeUsername())) {
                    request.setAssigneeName(ExUser.getFullname(user));
                }
                break;
            case MODE_EDIT:
                request.setManagerRead(0);
                
                // Update System information
                request.setLastmodifiedbyUsername(username);
                request.setLastmodified(currentDate);

                if (user.getUsername().equals(request.getAssigneeUsername())) {
                    request.setAssignerRead(1);
                } else {
                    request.setAssignerRead(0);
                }
                
                // if there is any attachment, 
                if (CommonUtil.isNNandNB(request.getFilename1()) && (request.getAttachment1().length == 0)) {
                    // Keep the current attach
                    Request currentRequest = this.getDaoController().findRequest(request.getId());
                    request.setFilename1(currentRequest.getFilename1());
                    request.setAttachment1(currentRequest.getAttachment1());
                } else {
                    // Do nothing
                }

                break;
            default:
                // Do nothing
                LOG.warn("Do not support mode = " + mode);
        }

    }
    
    /**
    * [Give the description for method].
    * @return
    */
    public List<Request> findAnnouncement() {
        List<Request> lstRequest;
        lstRequest = controller.findRequestByType(AppCons.ANNOUNCEMENT);
        
        return lstRequest;
    }
    
    /**
    * [Give the description for method].
    * @return
    */
    public List<Request> findRule() {
        List<Request> lstRequest;
        lstRequest = controller.findRequestByType(AppCons.RULE);
        
        return lstRequest;
    }
    
    /**
    * Get list of "Leave" which is relative to the given user.
    * - Owner Leave request
    * - Leave request is managed by the user
    * - Leave request is shared to the user
    * Todo
    * @param String username
    * @return
    */
    public List<Request> findLeave(String username) {
        List<Request> lstRequest;
        lstRequest = controller.findRequestByType(AppCons.LEAVE);
        
        return lstRequest;
    }
    
    /**
    * Get list of Task which is relative to the given user.
    *  - Assigned task
    *  - Task is managed by the user
    *  - Task is shared to the user
    * Todo
    * @param String username
    * @return
    */
    public List<Request> findTaskOfUser(SearchTaskConditionModel searchConditionModel) {
        List<Request> lstRequest;
        lstRequest = controller.findTaskOfUser(searchConditionModel);
        
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

    /**
    * Find all open request by username.
    * [Todo]
    * @param username
    * @return
    */
    public List<Request> findOpenRequest(String username) {
        return controller.findRequestEntities();
    }

    public List<Request> findRequestByCondition(String username, SearchRequestConditionModel searchCond) {
        return controller.findRequestByCondition(searchCond);
    }

}
