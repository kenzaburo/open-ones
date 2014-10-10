package mks.dms.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mks.dms.dao.controller.ExRequestJpaController;
import mks.dms.dao.controller.exceptions.IllegalOrphanException;
import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.User;
import mks.dms.extentity.ExUser;
import mks.dms.info.Result;
import mks.dms.model.RequestCreateModel;
import mks.dms.model.RequestModel;
import mks.dms.model.SearchRequestConditionModel;
import mks.dms.service.MasterService;
import mks.dms.service.RequestService;
import mks.dms.service.UserControllerService;
import mks.dms.util.AppCons;
import mks.dms.util.AppUtil;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ThachLe, TruongTho
 */
@Controller
@SessionAttributes({"listRequestType","listUser", "listDurationUnit", "listDepartment"})
public class RequestController {

    /**  */
	private static final Logger LOG = Logger.getLogger(RequestController.class);

	private final MasterService masterService;
	
	//private final RequestControllerService requestControllerService;
	
	private final RequestService requestService;
	
	private final UserControllerService userService;
	
	@Autowired
    @Qualifier("requestValidator")
    private Validator validator;
	
    @Autowired
    public RequestController(MasterService masterService, RequestService requestService, UserControllerService userService) {
        this.masterService = masterService;
        this.requestService = requestService;
        this.userService = userService;
    }
	
    /**
    * This method is called when binding the HTTP parameter to bean (or model).
    * @param binder
    */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(AppCons.DATE_FORMAT);
        dateFormat.setLenient(false);

        // true passed to CustomDateEditor constructor means convert empty String to null
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));     

        // Refer: http://www.coderanch.com/t/524168/Spring/SessionAttributes-Validator-set-InitBinder
        
        if ((validator != null) && (binder.getTarget() != null) && (validator.supports(binder.getTarget().getClass()))) {
            binder.setValidator(this.validator);
        } else {
            LOG.warn("Could not set validator");
        }
    }
    
	private static String username = "softeksolutionreport@gmail.com";
	private static String password = "softeksolutionreport1";
	
	public void sendMail(String address, String subject, String content){
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
 
		Session sessionMail = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(sessionMail);
//			message.setHeader("Content-Type", encodingOptions);
			message.setFrom(new InternetAddress("no-reply@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
			message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "Q"));
			message.setContent(content, "text/html; charset=UTF-8");
			Transport.send(message);
			

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//    @Autowired
//    public RequestController(MasterService masterService) {
//        this.masterService = masterService;
//    }
    
    /**
    * Prepare to display the screen of "Create a request".
    * @param model
    * @return
    */
    @RequestMapping(value="createRequest" , method = RequestMethod.GET)
    public ModelAndView createRequest(Model model, Principal principal){
        ModelAndView mav = new ModelAndView("createRequest");
        
        // Initial the request model
        RequestModel requestCreateModel = new RequestModel();
        requestCreateModel.getRequest().setRequesttypeCd(AppCons.TASK);

        // Add object to modelandview
        mav.addObject(AppCons.MODEL, requestCreateModel);
        mav.addObject("current", "commonManagement");
        
    	return mav;
    }
    
    /**
    * This method process saving (create or edit) a request from client.
    * @param model contains data are submitted from client
    * @param bindingResult
    * @return the current view with result of saving
    * @author ThachLe
    * @see /DecisionMakerServlet/src/main/webapp/WEB-INF/views/Request/_createTask.jsp
    */
    @RequestMapping(value = "saveRequest", method = RequestMethod.POST)
    public ModelAndView saveRequest(@ModelAttribute(AppCons.MODEL) @Validated RequestModel model, BindingResult bindingResult, Principal principal) {
        ModelAndView mav = new ModelAndView("createRequest");
        if (bindingResult.hasErrors()) {
            LOG.debug("Binding result; hasError=" + bindingResult.hasErrors());
            return mav;
        }
    	
        User userLogin = userService.getUserByUsername(principal.getName());
        // Update login user for services
        requestService.setUser(userLogin);
        
        // Debug data of model
        Request request = model.getRequest();
        request = AppUtil.updateModelToEntity(model, request);
        
        //LOG.debug("type id=" + request.getRequesttypeId());
        LOG.debug("type cd=" + request.getRequesttypeCd());                       // have value from client
        //LOG.debug("type name=" + request.getRequesttypeName());
        LOG.debug("title=" + request.getTitle());                                 // For all requests 
        LOG.debug("content=" + request.getContent()); 
        LOG.debug("Creator id=" + userLogin.getId());   							// For all requests

        
        LOG.debug("Start date=" + request.getStartdate());                         // Task | Leave
        LOG.debug("End date=" + request.getEnddate());                             // Task | Leave
        
        int saveOrUpdate = requestService.saveOrUpdate(request);

        
        // Send email if the request is "Leave"
        if (saveOrUpdate > 0) {
            if (AppCons.LEAVE.equals(request.getRequesttypeCd())) {
                sendEmailLeave(request);
            }
            
            // Update request entity to model
            // AppUtil.parseRequestEntity2Model(request, model);

            // Enable flag save.success
            mav.addObject(AppCons.SAVE_STATUS, AppCons.SUCCESS);
        } else {
            // save.fail
            mav.addObject(AppCons.SAVE_STATUS, AppCons.FAIL);
        }

        // Refresh model
        return mav;
    }
    
    /**
    * Sending email to request a leave.
    * @param request contain information data from client
    */
    private void sendEmailLeave(Request request) {
        // TODO Auto-generated method stub
        
    }

    /**
    * Show the screen Edit request.
    * <br/>
    * Edit screen is sample as the Create screen.
    * So the .jsp is reused 100%.
    * @param id identifier of the request
    * @return
    */
    @RequestMapping(value="editRequest")
    public ModelAndView editRequest(@RequestParam("id") int id, Principal principal) {
        ModelAndView mav = new ModelAndView("editRequest");
        LOG.debug("id=" + id);
        
        User userLogin = userService.getUserByUsername(principal.getName());
        String username = userLogin.getUsername();
        RequestCreateModel requestCreateModel = new RequestCreateModel();
        
        Request request = requestService.getDaoController().findRequest(id);
        
        // if (request.getManagerUsername().equals(userLogin.getCd())) {
        if (userLogin.getUsername().equals(request.getManagerUsername())) {
        	mav.addObject("isManager", Boolean.TRUE);
        }
        
        // if (request.getRequesttypeCd().equals("Task") && request.getCreatedbyUsername().equals(userLogin.getCd()) && request.getCreatedbyUsername().equals(request.getAssigneeUsername())) {
        if (AppCons.TASK.equals(request.getRequesttypeCd())) {
            if ((username.equals(request.getCreatedbyUsername())) && (request.getCreatedbyUsername().equals(request.getAssigneeUsername()))) {
                mav.addObject("isCreatorAssigner", Boolean.TRUE);
            }
            
            // if (request.getRequesttypeCd().equals("Task") && request.getCreatedbyUsername().equals(userLogin.getCd()) && !request.getCreatedbyUsername().equals(request.getAssigneeUsername())) {
            if ((username.equals(request.getCreatedbyUsername())) && (!request.getCreatedbyUsername().equals(request.getAssigneeUsername()))) {
                mav.addObject("isCreator", Boolean.TRUE);
            }
            
            // if (request.getRequesttypeCd().equals("Task") && request.getAssigneeUsername().equals(userLogin.getCd())) {
            if (username.equals(request.getAssigneeUsername())) {
                mav.addObject("isAssigner", Boolean.TRUE);
            }
        }
        
        requestCreateModel.setRequest(request);;
        
        // Add object to modelandview
        mav.addObject(AppCons.MODEL, requestCreateModel);

        return mav;
    }

    @RequestMapping(value="downloadFile")
    public void downloadFile(@RequestParam("id") int id, HttpServletResponse response) {
        LOG.debug("id=" + id);
        Request request = requestService.getDaoController().findRequest(id);
        String mimeType = "application/octet-stream";
        
        if (request.getAttachment1() != null) {
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) request.getAttachment1().length);
        }
        
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                request.getFilename1());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream;
        try {
            outStream = response.getOutputStream();
            outStream.write(request.getAttachment1());
        } catch (IOException ex) {
            LOG.error("Could not read the attachment content.", ex);
        }
         
    }
    
    @RequestMapping(method = RequestMethod.GET, value="deleteRequest")
    @ResponseBody
    public String deleteRequest(@RequestParam("id") Integer requestId) {
        String jsonResult;

        LOG.debug("id=" + requestId);

        ExRequestJpaController daoCtrl = requestService.getDaoController();
        try {
            daoCtrl.destroy(requestId);
            jsonResult = "{result: 'SUCCESS'}";
        } catch (NonexistentEntityException ex) {
            jsonResult = "{result: 'FAIL'}";
            LOG.error("Could not delete the request id " + requestId, ex);
        }
        
        return jsonResult;
    }

    @RequestMapping(method = RequestMethod.GET, value="deleteAttachment")
    @ResponseBody
    public Result deleteAttachment(@RequestParam("id") Integer requestId, @RequestParam("fileId") Integer fileId) {
        Result result = new Result();

        LOG.debug("id=" + requestId + ";fileId=" + fileId );

        ExRequestJpaController daoCtrl = requestService.getDaoController();
        try {
            daoCtrl.deleteAttachment(requestId, fileId);
            result.setStatus("SUCCESS");
            //jsonResult = "{\"result\": SUCCESS}";
        } catch (Exception ex) {
            // jsonResult = "{\"result\": FAIL}";
            //LOG.error("Could not delete the request id " + requestId, ex);
            result.setStatus("FAIL");
        }
        
        return result;
    } 

    @RequestMapping(method = RequestMethod.GET, value="updateAssignee")
    @ResponseBody
    public Result updateAssignee(@RequestParam("id") Integer requestId, 
                                 @RequestParam("assigneeUsername") String assigneeUsername, @RequestParam("assigneeNote") String assigneeNote) {
        Result result = new Result();

        LOG.debug("id=" + requestId + ";assigneeUsername=" + assigneeUsername + ";assigneeNote=" + assigneeNote);

        ExRequestJpaController daoCtrl = requestService.getDaoController();
        try {
            Request request = daoCtrl.findRequest(requestId);
            request.setAssigneeUsername(assigneeUsername);
            request.setAssigneeNote(assigneeNote);
            User assignee = MasterService.findUserByUsername(assigneeUsername);
            request.setAssigneeName(ExUser.getFullname(assignee));
            
            daoCtrl.edit(request);
            result.setStatus("SUCCESS");
            //jsonResult = "{\"result\": SUCCESS}";
        } catch (Exception ex) {
            // jsonResult = "{\"result\": FAIL}";
            //LOG.error("Could not delete the request id " + requestId, ex);
            result.setStatus("FAIL");
        }
        
        return result;
    }
    
    /**
    * [Give the description for method].
    * This method will replace the method "showDetailRequestPage"
    * @param id
    * @param principal
    * @return
    */
    @RequestMapping(value="browseRequest")
    public ModelAndView browseRequest(@RequestParam("id") int id, Principal principal) {
        ModelAndView mav = new ModelAndView("browseRequest");
        
        Request request = requestService.getDaoController().findRequest(id);
        
        RequestModel requestModel = new RequestModel();
        requestModel.setRequest(request);
        
        // Update durationUnitName from request to Model
        if (request.getDurationunit() != null) {
            String duName = MasterService.getDurationUnitName(request.getDurationunit());
            requestModel.setDurationUnitName(duName);
        }
        
        mav.addObject(AppCons.MODEL, requestModel);

        return mav;
    }
    
    /**
    * [Give the description for method].
    * @param id
    * @param principal
    * @return
    * @throws IllegalOrphanException
    * @throws NonexistentEntityException
    * @throws Exception
    * @{@link Deprecated}
    */
    @RequestMapping(value="detailRequest")
    public ModelAndView showDetailRequestPage(@RequestParam("id") int id, Principal principal) throws IllegalOrphanException, NonexistentEntityException, Exception {
    	Request request = requestService.getDaoController().findRequest(id);
    	ModelAndView mav = new ModelAndView("detailRequest");
    	mav.addObject("request", request);
//    	kiem tra tai khoan dang nhap phai tai khoan duoc nhan request ko
//    	neu phai
    	
    	User userLogin = userService.getUserByUsername(principal.getName());
    	String username = userLogin.getUsername();
    	
    	if (AppCons.LEAVE.equals(request.getRequesttypeCd())) {
    		if (username.equals(request.getManagerUsername())) {
        		if (request.getManagerRead() == 0) {
            		request.setManagerRead(1);
            		// requestService.saveOrUpdate(request);
            		requestService.getDaoController().edit(request);
            	}
        		mav.addObject("isManager", Boolean.TRUE);
        	}
        	
        	if (username.equals(request.getCreatedbyUsername())) {
    	    	if (request.getCreatorRead() == 0) {
    	    		request.setCreatorRead(1);
    	    		requestService.getDaoController().edit(request);
    	    		
    	    	}
    	    	mav.addObject("isCreator", Boolean.TRUE);
        	}
    	}

    	if (AppCons.TASK.equals(request.getRequesttypeCd())) {
    	    
    		if (request.getAssigneeUsername().equals(request.getCreatedbyUsername())) {
    			if (username.equals(request.getManagerUsername())) {
            		if (request.getManagerRead() == 0) {
                		request.setManagerRead(1);
                		requestService.getDaoController().edit(request);
                	}
            		mav.addObject("isManager", Boolean.TRUE);
            	}
            	
            	if (username.equals(request.getCreatedbyUsername())) {
        	    	if (request.getCreatorRead() == 0) {
        	    		request.setCreatorRead(1);
        	    		request.setAssignerRead(1);
        	    		requestService.getDaoController().edit(request);
        	    	}
        	    	mav.addObject("isCreatorAssigner", Boolean.TRUE);
            	}
            	
            	
    		}
    		else {
    			if (username.equals(request.getAssigneeUsername())) {
    				if (request.getAssignerRead() == 0) {
    					request.setAssignerRead(1);
    					requestService.getDaoController().edit(request);
    				}
    				mav.addObject("isAssigner", Boolean.TRUE);
    			}
    			if (username.equals(request.getManagerUsername())) {
            		if (request.getManagerRead() == 0) {
                		request.setManagerRead(1);
                		requestService.getDaoController().edit(request);
                	}
            		mav.addObject("isManager", Boolean.TRUE);
            	}
    			if (username.equals(request.getCreatedbyUsername())) {
        	    	if (request.getCreatorRead() == 0) {
        	    		request.setCreatorRead(1);
        	    		requestService.getDaoController().edit(request);
        	    	}
        	    	mav.addObject("isCreator", Boolean.TRUE);
            	}
    		}
    	}
    	if (AppCons.RULE.equals(request.getRequesttypeCd()) || AppCons.ANNOUNCEMENT.equals(request.getRequesttypeCd())) {
    		if (username.equals(request.getCreatedbyUsername())) {
    			mav.addObject("isRead", Boolean.TRUE);
    		}
//    		kiem tra user dang nhap co thuoc cac phong ban trong pham vi ko
//    		neu phai set isRead True
    	}
    	return mav;
    }
    
    /**
     * Process when approveRequest
     **/
    @RequestMapping(value="approveRequest")
    public String approveRequest(@RequestParam("id") int id, Principal principal) throws IllegalOrphanException, NonexistentEntityException, Exception {
    	Date today = new Date();
    	
    	User userLogin = userService.getUserByUsername(principal.getName());
    	
    	Request request = requestService.getDaoController().findRequest(id);
    	// if (request.getRequesttypeCd().equals("Leave")) {
    	if (AppCons.LEAVE.equals(request.getRequesttypeCd())) {
    		// request.setStatus("Approved");
    	    request.setStatus(AppCons.STATUS_APPROVED);
    	}
//    	if (request.getRequesttypeCd().equals("Task") && (request.getStatus().equals("Created") || request.getStatus().equals("Updated"))) {
//    		request.setStatus("In-progress");
//    		request.setManagerRead(0);
//    	}
        if (AppCons.TASK.equals(request.getRequesttypeCd()) && (AppCons.STATUS_FINISH.equals(request.getStatus()))) {
            request.setStatus(AppCons.STATUS_DONE);
            request.setManagerRead(0);
        }    	
    	request.setLastmodified(today);
    	request.setCreatorRead(0);
    	
    	
//    	request.setLastmodified(today);
//    	request.setLastmodifiedbyUsername(userLogin.getUsername());
//    	request.setLastmodifiedbyName(userLogin.getLastname() + userLogin.getFirstname());
//    	request.setLastmodifiedbyId(userLogin.getId());
//    	
//    	requestService.saveOrUpdate(request);
    	
    	requestService.saveOrUpdate(request);
    	
    	return "redirect:detailRequest?id=" + id;
    }
    
    /**
     * Process when rejecte Request
     **/
    @RequestMapping(value="rejectRequest")
    public String rejectRequest(HttpServletRequest req, Principal pricipal) throws IllegalOrphanException, NonexistentEntityException, Exception {
    	SimpleDateFormat formater = new SimpleDateFormat(AppCons.DATE_FORMAT);
    	Date today = new Date();
    	int id = Integer.parseInt(req.getParameter("requestId"));
    	String reasonReject = req.getParameter("rejectContent");
    	
//    	Lay thong tin tai khoan dang nhap
//    	Kiem tra tai khoan dang nhap phai tai khoan duoc yeu cau khong
//    	Neu khong phai -> quay lai trang home -> hien thong bao "Ban khong co quyen nay"
    	
//    	Neu phai
    	Request request = requestService.getDaoController().findRequest(id);
    	
    	//if (request.getRequesttypeCd().equals("Leave")) {
    	if (AppCons.LEAVE.equals(request.getRequesttypeCd())) {
    		//request.setStatus("Rejected");
    	    request.setStatus(AppCons.STATUS_REJECTED);
        	request.setCreatorRead(0);
        	
        	User userLogin = userService.getUserByUsername(pricipal.getName());
//        	luu lý do reject
        	String fullReasonReject = userLogin.getLastname() + " " + userLogin.getFirstname() + " (" + formater.format(today) + ") : " + reasonReject + " \n";
        	if (request.getComment() != null && !request.getComment().equals("")) {
        		request.setComment(request.getComment() + fullReasonReject);
        	}
        	else {
        		request.setComment(fullReasonReject);
        	}
    	}
    	
    	// Thach: Task sẽ công có khái niệm Reject. File giải thích trong file AppCons.java
    	if (request.getRequesttypeCd().equals("Task")) {
    		request.setStatus("Rejected");
        	request.setCreatorRead(0);
        	request.setManagerRead(0);
        	User userLogin = userService.getUserByUsername(pricipal.getName());
//        	luu lý do reject
        	String fullReasonReject = userLogin.getLastname() + " " + userLogin.getFirstname() + " (" + formater.format(today) + ") : " + reasonReject + " \n";
        	if (request.getComment() != null && !request.getComment().equals("")) {
        		request.setComment(request.getComment() + fullReasonReject);
        	}
        	else {
        		request.setComment(fullReasonReject);
        	}
    	}
    	
    	request.setLastmodified(today);
    	
//    	Bo sung them thong tin sau
//    	request.setLastmodifiedbyAccount(lastmodifiedbyAccount);
//    	request.setLastmodifiedbyId(lastmodifiedbyId);
//    	request.setLastmodifiedbyName(lastmodifiedbyName);
    	
    	requestService.saveOrUpdate(request);
    	
    	return "redirect:detailRequest?id=" + id;
    }
    
    @RequestMapping(value="addComment")
    public ModelAndView showAddComment(@RequestParam("id") int requestId) {
    	ModelAndView mav = new ModelAndView("addComment");
    	mav.addObject("requestId", requestId);
    	return mav;
    }
    
    /**
    * Ghi rõ method này làm gì.
    * Thach: logic xử lý "SaveComment" làm rất nhiều việc không liên quan: Set Reading, Reject.
    * => Cần thiết kế lại cho rõ ràng.
    * @param req
    * @param principal
    * @return
    * @throws IllegalOrphanException
    * @throws NonexistentEntityException
    * @throws Exception
    */
    @RequestMapping(value="saveComment")
    public String processSaveComment(HttpServletRequest req, Principal principal) throws IllegalOrphanException, NonexistentEntityException, Exception {
    	SimpleDateFormat formater = new SimpleDateFormat(AppCons.DATE_FORMAT);
    	Date today = new Date();
    	int id = Integer.parseInt(req.getParameter("requestId"));
    	String commentContent = req.getParameter("comment.content");
//    	Lay thong tin tai khoan dang nhap
//    	Kiem tra tai khoan dang nhap phai tai khoan duoc yeu cau khong
//    	Neu khong phai -> quay lai trang home -> hien thong bao "Ban khong co quyen nay"
    	
//    	Neu phai
    	
    	User userLogin = userService.getUserByUsername(principal.getName());
    	String username = userLogin.getUsername();
    	
    	Request request = requestService.getDaoController().findRequest(id);
    	if (request.getRequesttypeCd().equals("Task") || request.getRequesttypeCd().equals("Leave")) {
    		if (username.equals(request.getManagerUsername())) {
        		if (request.getStatus().equals("Confirm")) {
        			request.setStatus("In-progress"); // Thach: hãy dùng Constant trong AppCons; ko có status In-progress (hãy dùng AppCons.Doing)
            	}
        		request.setCreatorRead(0);
            	request.setAssignerRead(0);
        	}
        	
        	if (!request.getCreatedbyUsername().equals(request.getAssigneeUsername())) {
        		if (username.equals(request.getCreatedbyUsername())) {
    	    		request.setAssignerRead(0);
    	    		request.setManagerRead(0);
    	    	}
    	    	
    	    	if (username.equals(request.getAssigneeUsername())) {
    	    		request.setAssignerRead(0);
    	    		request.setManagerRead(0);
    	    	}
        	}
        	else {
        		if (username.equals(request.getCreatedbyUsername())) {
        			request.setManagerRead(0);
        		}
        	}
    	}
    	
//    	if (request.getRequesttypeCd().equals("Rule") || request.getRequesttypeCd().equals("Announcement")) {
//    		
//    	}
	    	
    		
    	String fullReasonReject = userLogin.getLastname() + " " + userLogin.getFirstname() + " (" + formater.format(today) + ") : " + commentContent + " \n";
    	if (request.getComment() != null) {
    		request.setComment(request.getComment() + fullReasonReject);
    	}
    	else {
    		request.setComment(fullReasonReject);
    	}
    	request.setLastmodified(today);
    	
//    	Bo sung them thong tin sau
//    	request.setLastmodifiedbyAccount(lastmodifiedbyAccount);
//    	request.setLastmodifiedbyId(lastmodifiedbyId);
//    	request.setLastmodifiedbyName(lastmodifiedbyName);
    	
    	requestService.saveOrUpdate(request);
    	
    	return "redirect:browseRequest.html?id=" + id;
    }
    
    /**
     * Show listSendRequest page
     **/
    @RequestMapping(value="listSendRequest")
    public ModelAndView showPageListSendRequest() {
    	ModelAndView mav = new ModelAndView("listSendRequest");
    	
    	// lstRequestType, listUser are already in session (View HomeController)
//    	List<RequestType> lstRequestTypes = masterService.getRequestTypes();
//        mav.addObject("listRequestTypes", lstRequestTypes);
//        List<User> listUsers = userService.getAllUser();
//        mav.addObject("listUsers", listUsers);
    	return mav;
    }
    
    /**
     * Show listReceiveRequest page
     **/
    @RequestMapping(value="listReceiveRequest")
    public ModelAndView showPageListReceiveRequest() {
    	ModelAndView mav = new ModelAndView("listReceiveRequest");
//    	List<RequestType> lstRequestTypes = masterService.getRequestTypes();
//        LOG.debug("lstRequestTypes=" + lstRequestTypes);
//        mav.addObject("listRequestTypes", lstRequestTypes);
//        List<User> listUsers = userService.getAllUser();
//        mav.addObject("listUsers", listUsers);
    	return mav;
    }
    
    /**
     * Show manageListTask page
     **/
    @RequestMapping(value="manageListTask")
    public ModelAndView showManageListTask() {
    	ModelAndView mav = new ModelAndView("manageListTask");
    	return mav;
    }
    
    
    @RequestMapping(value="send.request.load", method = RequestMethod.GET)
    public @ResponseBody String loadSendRequest(Principal principal) throws JSONException{
    	SimpleDateFormat dateFormat = new SimpleDateFormat(AppCons.DATE_FORMAT);
        dateFormat.setLenient(false);
        User userLogin = userService.getUserByUsername(principal.getName());
    	// List<Request> listRequest = requestControllerService.getListRequestByCreatedbyUsername(userLogin.getCd());
        List<Request> listRequest = requestService.getDaoController().getListRequestByCreatedbyUsername(userLogin.getUsername());

        List<JSONObject> listJson = new ArrayList<JSONObject>();
    	for (Request request:listRequest) {
    		JSONObject json = new JSONObject();
    		json.put("requestType", request.getRequesttypeName());
//    		json.put("requestType", request.getRequesttypeCd());
    		json.put("requestId", request.getId());
    		json.put("requestTitle", request.getTitle());
    		//json.put("managerName", request.getManagerId().getLastname() + " " + request.getManagerId().getFirstname());
    		
    		json.put("managerName", request.getManagerName());
    		
    		json.put("managerId", 1);
    		json.put("assignId", 1);
    		json.put("startDate", dateFormat.format(request.getStartdate()));
    		json.put("endDate", dateFormat.format(request.getEnddate()));
    		json.put("content", request.getContent());
    		if (requestService.checkIsRead(request, userLogin) == 1) {
    			json.put("readStatus", 1);
    		}
    		else {
    			json.put("readStatus", 0);
    		}
    		json.put("status", request.getStatus());
    		listJson.add(json);
    	}
    	return listJson.toString();
    } 
    
    @RequestMapping(value="receive.request.load", method = RequestMethod.GET)
    public @ResponseBody String loadReceiveRequest(Principal principal) throws JSONException{
    	SimpleDateFormat dateFormat = new SimpleDateFormat(AppCons.DATE_FORMAT);
        dateFormat.setLenient(false);
        User userLogin = userService.getUserByUsername(principal.getName());
        String username = userLogin.getUsername();
        
    	List<Request> listManagerRequest = requestService.getDaoController().getListRequestByManagerUsername(username);
    	List<Request> listAssignerRequest = requestService.getDaoController().getListRequestByAssigneeUsername(username);
    	List<Request> listRule = requestService.getDaoController().getListRequestByRequestTypeCd("Rule");
    	List<Request> listAnnouncement = requestService.getDaoController().getListRequestByRequestTypeCd("Announcement");
    	listAssignerRequest.removeAll(listManagerRequest);
    	listManagerRequest.addAll(listAssignerRequest);
    	listManagerRequest.removeAll(listRule);
    	listManagerRequest.addAll(listRule);
    	listManagerRequest.removeAll(listAnnouncement);
    	listManagerRequest.addAll(listAnnouncement);
    	List<JSONObject> listJson = new ArrayList<JSONObject>();
    	
    	for (Request request:listManagerRequest) {
    		JSONObject json = new JSONObject();
    		json.put("requestType", request.getRequesttypeName());
//    		json.put("requestType", request.getRequesttypeCd());
    		json.put("requestId", request.getId());
    		json.put("requestTitle", request.getTitle());
    		//json.put("managerName", request.getManagerId().getLastname() + " " + request.getManagerId().getFirstname());
    		json.put("managerName", request.getManagerName());
    		json.put("managerId", request.getManagerUsername());
    		//json.put("assignId", request.getManagerId());
    		json.put("assignId", request.getAssigneeUsername());
    		
    		json.put("startDate", dateFormat.format(request.getStartdate()));
    		json.put("endDate", dateFormat.format(request.getEnddate()));
    		json.put("content", request.getContent());
    		if (requestService.checkIsRead(request, userLogin) == 1) {
    			json.put("readStatus", 1);
    		}
    		else {
    			json.put("readStatus", 0);
    		}
    		
    		json.put("status", request.getStatus());
    		listJson.add(json);
    	}
    	return listJson.toString();
    } 
    
    @RequestMapping(value="my.task.load", method = RequestMethod.GET)
    public @ResponseBody String loadMyListTask(Principal principal) throws JSONException{
    	SimpleDateFormat dateFormat = new SimpleDateFormat(AppCons.DATE_FORMAT);
        dateFormat.setLenient(false);
        User userLogin = userService.getUserByUsername(principal.getName());
        String username = userLogin.getUsername();
        
    	List<Request> listAssignerRequest = requestService.getDaoController().getListRequestByAssigneeUsernameAndRequestTypeCd(username, "Task");
    	List<JSONObject> listJson = new ArrayList<JSONObject>();
    	for (Request request:listAssignerRequest) {
    		JSONObject json = new JSONObject();
    		json.put("requestType", request.getRequesttypeName());
    		json.put("requestId", request.getId());
    		json.put("requestTitle", request.getTitle());
    		//json.put("managerName", request.getManagerId().getLastname() + " " + request.getManagerId().getFirstname());
    		json.put("managerName", request.getManagerName());
    		json.put("managerId", request.getManagerUsername());
    		//json.put("assignId", request.getManagerId());
    		
    		json.put("assignId", request.getAssigneeUsername());
    		
    		if (request.getStartdate() != null) {
    			json.put("startDate", dateFormat.format(request.getStartdate()));
    		}
    		if (request.getEnddate() != null) {
    			json.put("endDate", dateFormat.format(request.getEnddate()));
    		}
    		json.put("content", request.getContent());
    		if (requestService.checkIsRead(request, userLogin) == 1) {
    			json.put("readStatus", 1);
    		}
    		else {
    			json.put("readStatus", 0);
    		}
    		
    		json.put("status", request.getStatus());
    		listJson.add(json);
    	}
    	return listJson.toString();
    }
    
    @RequestMapping(value="manage.task.load", method = RequestMethod.GET)
    public @ResponseBody String loadManageListTask(Principal principal) throws JSONException{
    	SimpleDateFormat dateFormat = new SimpleDateFormat(AppCons.DATE_FORMAT);
        dateFormat.setLenient(false);
        User userLogin = userService.getUserByUsername(principal.getName());
        String username = userLogin.getUsername();
        
    	List<Request> listAssignerRequest = requestService.getDaoController().getListRequestByManagerUsernameAndRequestTypeCd(username, "Task");
    	List<JSONObject> listJson = new ArrayList<JSONObject>();
    	for (Request request:listAssignerRequest) {
    		JSONObject json = new JSONObject();
    		json.put("requestType", request.getRequesttypeName());
    		json.put("requestId", request.getId());
    		json.put("requestTitle", request.getTitle());
    		// json.put("managerName", request.getManagerId().getLastname() + " " + request.getManagerId().getFirstname());
    		json.put("managerName", request.getManagerName());
    		json.put("managerId", request.getManagerUsername());
    		// json.put("assignId", request.getManagerId());
    		json.put("assignId", request.getAssigneeUsername());
    		if (request.getStartdate() != null) {
    			json.put("startDate", dateFormat.format(request.getStartdate()));
    		}
    		if (request.getEnddate() != null) {
    			json.put("endDate", dateFormat.format(request.getEnddate()));
    		}
    		json.put("content", request.getContent());
    		if (requestService.checkIsRead(request, userLogin) == 1) {
    			json.put("readStatus", 1);
    		}
    		else {
    			json.put("readStatus", 0);
    		}
    		
    		json.put("status", request.getStatus());
    		listJson.add(json);
    	}
    	return listJson.toString();
    }
    
//    @RequestMapping(value="searchRequest")
//    public ModelAndView showSearchRequestPage() {
//    	ModelAndView mav = new ModelAndView("searchRequest");
////    	List<RequestType> lstRequestTypes = masterService.getRequestTypes();
////    	LOG.debug("lstRequestTypes=" + lstRequestTypes);
////        mav.addObject("listRequestTypes", lstRequestTypes);
////        List<User> listUsers = userService.getAllUser();
////        mav.addObject("listUsers", listUsers);
//    	mav.addObject("current", "listFunction");
//    	return mav;
//    }
    
    @RequestMapping(value="updateStatus")
    public String processUpdateRequest(@RequestParam("id") int requestId, Principal principal) {
    	Request request = requestService.getDaoController().findRequest(requestId);
    	User userLogin = userService.getUserByUsername(principal.getName());
    	requestService.setUser(userLogin);
    	if (request.getStatus().equals("Created") && request.getAssigneeUsername().equals(principal.getName())) {
    		// request.setStatus("In-progress");
    	    request.setStatus(AppCons.STATUS_DOING);
    	}
    	else if (AppCons.STATUS_DOING.equals(request.getStatus()) && request.getAssigneeUsername().equals(principal.getName())) {
    		request.setStatus("Finish");
    	}
    	else if (request.getStatus().equals("Finish") && (request.getManagerUsername().equals(principal.getName()) || request.getAssigneeUsername().equals(principal.getName()))) {
    		request.setStatus("Re-assign");
    	}
    	requestService.saveOrUpdate(request);
    	return "redirect:browseRequest?id=" + requestId;
    }
    
    @RequestMapping(value="confirmRequest")
    public String processConfirmRequestIsDone(@RequestParam("id") int requestId, Principal principal) {
    	Request request = requestService.getDaoController().findRequest(requestId);
    	User userLogin = userService.getUserByUsername(principal.getName());
    	requestService.setUser(userLogin);
    	if (request.getStatus().equals("Finish") && request.getManagerUsername().equals(principal.getName())) {
    		request.setStatus("Done");
    	}
    	requestService.saveOrUpdate(request);
//    	tao moi rate o day
    	
    	return "redirect:browseRequest?id=" + requestId;
    }
    
    
    @RequestMapping(value="searchRequest")
    public ModelAndView searchRequest(@ModelAttribute(AppCons.MODEL) SearchRequestConditionModel searchConditionModel, Principal principal) {
        ModelAndView mav = new ModelAndView("searchRequest");
        String username = principal.getName();
        
        List<Request> lstRequest = requestService.findRequestByCondition(username, searchConditionModel);
        
        mav.addObject(AppCons.MODEL, searchConditionModel);
        mav.addObject("requests", lstRequest);
        mav.addObject("current", "commonManagement");
        
        return mav;
    }
    
//    @RequestMapping(value="search.request", method = RequestMethod.GET)
//	public @ResponseBody
//			String searchRequest(Principal principal,
//			@RequestParam("createdbyUsername") String createdbyUsername,
//			@RequestParam("startDate") Date startDate,
//			@RequestParam("endDate") Date endDate,
//			@RequestParam("managerUsername") String managerUsername,
//			@RequestParam("assignUsername") String assignUsername,
//			@RequestParam("requestTypeCd") String requestTypeCd,
//			@RequestParam("requestTitle") String requestTitle,
//			@RequestParam("requestContent") String requestContent)
//			throws JSONException {
////    	System.out.println("managerUsername: " + managerUsername);
////    	System.out.println("createdbyUsername: " + createdbyUsername);
////    	System.out.println("assignUsername: " + assignUsername);
////    	System.out.println("requestTypeCd: " + requestTypeCd);
//
//    	List<Request> listRequest;    	
//    	if (createdbyUsername.equals("0") && startDate == null && endDate == null && managerUsername.equals("0") && assignUsername.equals("0") && requestTypeCd.equals("0")) {
//    	    System.out.println("Search All");
//    		if (principal.getName().equals("admin") || principal.getName().equals("manager")) {
//    	    	listRequest = requestService.getDaoController().findRequestEntities();
//    	    }
//    	    else {
//    	    	List<Request> list2 = requestService.getDaoController().searchRequest("0", null, null, principal.getName(), "0", "0");
//    	    	List<Request> list3 = requestService.getDaoController().searchRequest("0", null, null, "0", principal.getName(), "0");
//    	    	list2.removeAll(list3);
//    	    	list2.addAll(list3);
//    	    	listRequest = list2;
//    	    }
//    	}else {
//    		System.out.println("Search Some");
//    		listRequest = requestService.getDaoController().searchRequest(createdbyUsername, startDate, endDate, managerUsername, assignUsername, requestTypeCd);
//    	}
//    	User userLogin = userService.getUserByUsername(principal.getName());
//    	List<JSONObject> listJson = new ArrayList<JSONObject>();
//    	SimpleDateFormat dateFormat = new SimpleDateFormat(AppCons.DATE_FORMAT);
//        dateFormat.setLenient(false);
//    	for (Request request:listRequest) {
//    		if (requestContent.equals("") && requestTitle.equals("")) {
//    			JSONObject json = new JSONObject();
//        		json.put("requestType", request.getRequesttypeName());
//        		json.put("requestId", request.getId());
//        		json.put("requestTitle", request.getTitle());
//                
//        		// Thach.modified.20140916
//        		if (request.getManagerUsername() != null) {
//                    json.put("managerName", request.getManagerName());
//                    json.put("managerId", request.getManagerUsername());
//                }
//        		// json.put("assignId", request.getManagerId());
//        		json.put("assignId", request.getAssigneeUsername());
//        		
//        		// Thach.Modified.20140916
//        		if (request.getStartdate() != null) {
//        		    json.put("startDate", dateFormat.format(request.getStartdate()));
//        		}
//        		
//        		// Thach.Modified.20140916
//        		if (request.getEnddate() != null) {
//        		    json.put("endDate", dateFormat.format(request.getEnddate()));
//        		}
//        		json.put("content", request.getContent());
//        		if (requestService.checkIsRead(request, userLogin) == 1) {
//        			json.put("readStatus", 1);
//        		}
//        		else {
//        			json.put("readStatus", 0);
//        		}
//        		json.put("status", request.getStatus());
//        		listJson.add(json);
//    		}
//    		else if (!requestContent.equals("") && requestTitle.equals("")) {
//    			
//    			if (request.getContent().toLowerCase().contains(requestContent.toLowerCase())) {
//    				JSONObject json = new JSONObject();
//            		json.put("requestType", request.getRequesttypeName());
////            		json.put("requestType", request.getRequesttypeCd());
//            		json.put("requestId", request.getId());
//            		json.put("requestTitle", request.getTitle());
//            		//json.put("managerName", request.getManagerId().getLastname() + " " + request.getManagerId().getFirstname());
//            		json.put("managerName", request.getManagerName());
//            		
//            		json.put("managerId", request.getManagerUsername());
//            		//json.put("assignId", request.getManagerId());
//            		json.put("assignId", request.getAssigneeUsername());
//            		json.put("startDate", dateFormat.format(request.getStartdate()));
//            		json.put("endDate", dateFormat.format(request.getEnddate()));
//            		json.put("content", request.getContent());
//            		if (requestService.checkIsRead(request, userLogin) == 1) {
//            			json.put("readStatus", 1);
//            		}
//            		else {
//            			json.put("readStatus", 0);
//            		}
//            		json.put("status", request.getStatus());
//            		listJson.add(json);
//    			}
//    		}
//    		else if (requestContent.equals("") && !requestTitle.equals("")) {
//    			if (request.getTitle().toLowerCase().contains(requestTitle.toLowerCase())) {
//    				JSONObject json = new JSONObject();
//            		json.put("requestType", request.getRequesttypeName());
////            		json.put("requestType", request.getRequesttypeCd());
//            		json.put("requestId", request.getId());
//            		json.put("requestTitle", request.getTitle());
//            		json.put("managerName", request.getManagerName());
//            		json.put("managerId", request.getManagerUsername());
//            		//json.put("assignId", request.getManagerId());
//            		json.put("assignId", request.getAssigneeUsername());
//            		json.put("startDate", dateFormat.format(request.getStartdate()));
//            		json.put("endDate", dateFormat.format(request.getEnddate()));
//            		json.put("content", request.getContent());
//            		if (requestService.checkIsRead(request, userLogin) == 1) {
//            			json.put("readStatus", 1);
//            		}
//            		else {
//            			json.put("readStatus", 0);
//            		}
//            		json.put("status", request.getStatus());
//            		listJson.add(json);
//    			}
//    		}
//    		else if (!requestContent.equals("") && !requestTitle.equals("")){
//    			System.out.println("Title and content not null");
//    			if (request.getTitle().toLowerCase().contains(requestTitle.toLowerCase()) && request.getContent().toLowerCase().contains(requestContent.toLowerCase())) {
//    				JSONObject json = new JSONObject();
//            		json.put("requestType", request.getRequesttypeName());
////            		json.put("requestType", request.getRequesttypeCd());
//            		json.put("requestId", request.getId());
//            		json.put("requestTitle", request.getTitle());
//            		json.put("managerName", request.getManagerName());
//            		json.put("managerId", 1);
//            		json.put("assignId", 1);
//            		json.put("startDate", dateFormat.format(request.getStartdate()));
//            		json.put("endDate", dateFormat.format(request.getEnddate()));;
//            		json.put("content", request.getContent());
////            		json.put("readStatus", request.getReadstatus());
//            		json.put("status", request.getStatus());
//            		listJson.add(json);
//    			}
//    		}
//    	}
//    	return listJson.toString();
//    }
    
    @RequestMapping(value="response.request.count", method = RequestMethod.GET)
    public @ResponseBody String countResponseRequest(Principal principal,  HttpServletResponse response) throws JSONException{
        LOG.debug("principal.getName()=" + principal.getName());
    	User userLogin = userService.getUserByUsername(principal.getName());
    	String username = userLogin.getUsername();
    	
        List<Request> listApproveRequest = requestService.getDaoController().getListRequestByCreatorCdAndStatusAndCreatorRead(username, "Approved", 0);
        List<Request> listRejectedRequest = requestService.getDaoController().getListRequestByCreatorCdAndStatusAndCreatorRead(username, "Rejected", 0);
        List<Request> listDoingTask = requestService.getDaoController().getListRequestByCreatorCdAndStatusAndCreatorRead(username, "Doing", 0);
        List<Request> listDoingTask1 = requestService.getDaoController().getListRequestByAssignerCdAndStatusAndAssignerRead(username, "Doing", 0);
        List<Request> listDoneTask = requestService.getDaoController().getListRequestByCreatorCdAndStatusAndCreatorRead(username, "Done", 0);
        List<Request> listDoneTask1 = requestService.getDaoController().getListRequestByCreatorCdAndStatusAndCreatorRead(username, "Done", 0);
        
        listDoingTask.removeAll(listDoingTask1);
        listDoingTask.addAll(listDoingTask1);
        listDoneTask.removeAll(listDoneTask1);
        listDoneTask.addAll(listDoneTask1);
        
	    int count = 0;
	    count = listApproveRequest.size() + listRejectedRequest.size() + listDoneTask.size() + listDoingTask.size();
	    
	    response.setContentType("text/plain");
	    
		JSONObject json = new JSONObject();
		json.put("countResponseRequest", count);
    		
    	return json.toString();
    } 
    
    @RequestMapping(value="request.count", method = RequestMethod.GET)
    public @ResponseBody String countRequest(Principal principal) throws JSONException{

    	User userLogin = userService.getUserByUsername(principal.getName());
    	String username = userLogin.getUsername();
    	
        List<Request> listCreatedRequest = requestService.getDaoController().getListRequestByManagerUsernameAndStatusAndManagerRead(username, "Created", 0);
        List<Request> listConfirmRequest = requestService.getDaoController().getListRequestByManagerUsernameAndStatusAndManagerRead(username, "Confirm", 0);
        List<Request> listUpdateRequest = requestService.getDaoController().getListRequestByManagerUsernameAndStatusAndManagerRead(username, "Updated", 0);
        List<Request> listDoingRequest = requestService.getDaoController().getListRequestByManagerUsernameAndStatusAndManagerRead(username, "Doing", 0);
        
        List<Request> listTaskRequest = requestService.getDaoController().getListRequestByAssignerCdAndStatusAndAssignerRead(username, "Created", 0);
        List<Request> listTaskRequest1 = requestService.getDaoController().getListRequestByAssignerCdAndStatusAndAssignerRead(username, "Updated", 0);
        List<Request> listTaskRequest2 = requestService.getDaoController().getListRequestByAssignerCdAndStatusAndAssignerRead(username, "Done", 0);
        List<Request> listRule = requestService.getDaoController().getListRequestByRequestTypeCd("Rule");
        List<Request> listAnnouncement = requestService.getDaoController().getListRequestByRequestTypeCd("Announcement");
        
        listCreatedRequest.removeAll(listTaskRequest);
        listCreatedRequest.addAll(listTaskRequest);
        listUpdateRequest.removeAll(listTaskRequest1);
        listUpdateRequest.addAll(listTaskRequest1);
        int count = 0;
	    count = listCreatedRequest.size() + listUpdateRequest.size() + listTaskRequest2.size() + listDoingRequest.size() + listConfirmRequest.size();
    	
		JSONObject json = new JSONObject();
		json.put("countRequest", count);
    	return json.toString();
    }
    
    @RequestMapping(value="confirm.task", method = RequestMethod.GET)
    public String confirmTask(Principal principal, @RequestParam("requestId") int requestId) throws IllegalOrphanException, NonexistentEntityException, Exception {
    	User userLogin = userService.getUserByUsername(principal.getName());
    	String username = userLogin.getUsername();
    	
    	Request request = requestService.getDaoController().findRequest(requestId);
    	request.setStatus("Confirm");
    	request.setManagerRead(0);
    	// if (request.getAssigneeUsername().equals(userLogin.getCd()) && !request.getCreatedbyUsername().equals(request.getAssigneeUsername())) {
    	if (username.equals(request.getAssigneeUsername()) && (!request.getCreatedbyUsername().equals(request.getAssigneeUsername()))) {
    		request.setCreatorRead(0);
    	}
    	requestService.saveOrUpdate(request);
    	return "redirect:detailRequest?id=" + request.getId(); 
    }
    
    @RequestMapping(value="completedtask")
    public String completedTask(Principal principal, @RequestParam("requestId") int requestId) throws IllegalOrphanException, NonexistentEntityException, Exception {
//    	User userLogin = userService.getUserByUsername(principal.getName());
    	Request request = requestService.getDaoController().findRequest(requestId);
    	request.setStatus("Done");
    	request.setManagerRead(1);
    	request.setAssignerRead(0);
    	request.setCreatorRead(0);
    	requestService.saveOrUpdate(request);
    	return "redirect:detailRequest?id=" + request.getId();	
    }
    
    @RequestMapping(value="detailContent")
    public ModelAndView detailContent(@RequestParam("id") int requestId) {
    	Request request = requestService.getDaoController().findRequest(requestId);
    	ModelAndView mav = new ModelAndView("detailContent");
    	mav.addObject("request", request);
    	return mav;
    }
    
    @RequestMapping(value="listLeaveRequest")
    public ModelAndView showListLeaveRequestPage(Principal principal) {
    	ModelAndView mav = new ModelAndView("listLeaveRequest");
    	// List<Department> listDept = departmentService.getAllDepartment();
    	List<User> listUser = userService.getAllUser();
    	
    	// ThachLe.20140916: listUsers, listDepartments are already in session (View HomeController)
    	// mav.addObject("listUsers", listUser);
    	// mav.addObject("listDepartment", listDept);
    	return mav;
    }
    
    @RequestMapping(value="load.user")
    public @ResponseBody String loadUserByDepartmentCd(@RequestParam("departmentCd") String departmentCd) throws JSONException {
    	List<User> listUser = userService.getUserByDepartmentCd(departmentCd);
    	List<JSONObject> listJson = new ArrayList<JSONObject>();
    	for (User user:listUser) {
    		JSONObject json = new JSONObject();
    		json.put("cd", user.getUsername());
    		json.put("name", user.getLastname() + user.getFirstname());
    		listJson.add(json);
    	}
    	return listJson.toString();
    }
    
    @RequestMapping(value="search.leave.request")
    public @ResponseBody String searchLeaveRequest(Principal principal, @RequestParam("startDay") Date startDay, @RequestParam("endDay") Date endDay, @RequestParam("userCd") String userCd, @RequestParam("departmentCd") String departmentCd) throws JSONException {
    	User userLogin = userService.getUserByUsername(principal.getName());
    	if (startDay == null) {
    		Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_MONTH, 1);
            startDay = c.getTime();
    	}
    	if (endDay == null) {
    		endDay = new Date();
    	}
    	
    	List<Request> listRequest = requestService.getDaoController().searchRequest(userCd, startDay, endDay, username, "", "Leave");
    	List<JSONObject> listJson = new ArrayList<JSONObject>();
    	SimpleDateFormat dateFormat = new SimpleDateFormat(AppCons.DATE_FORMAT);
        dateFormat.setLenient(false);
        if (departmentCd.equals("0") || departmentCd.equals("")) {
        	for (Request request:listRequest) {
        		JSONObject json = new JSONObject();
            	json.put("requestType", request.getRequesttypeName());
            	json.put("requestId", request.getId());
            	json.put("requestTitle", request.getTitle());
                    
            	// Thach.modified.20140919
            	if (request.getManagerUsername() != null) {
            		json.put("managerName", request.getManagerName());
                    json.put("managerId", request.getManagerUsername());
                }
            	// json.put("assignId", request.getManagerId());
            	json.put("assignId", request.getAssigneeUsername());
            		
            	// Thach.Modified.20140919
            	if (request.getStartdate() != null) {
            		json.put("startDate", dateFormat.format(request.getStartdate()));
            	}
            		
            	// Thach.Modified.20140919
            	if (request.getEnddate() != null) {
            		json.put("endDate", dateFormat.format(request.getEnddate()));
            	}
            	json.put("content", request.getContent());
            	if (requestService.checkIsRead(request, userLogin) == 1) {
            		json.put("readStatus", 1);
            	}
            	else {
            		json.put("readStatus", 0);
            	}
            	json.put("status", request.getStatus());
            	listJson.add(json);
        	}
        }
        else {
        	for (Request request:listRequest) {
        	    
        		if (request.getDepartmentCd().equals(departmentCd)) {
        			JSONObject json = new JSONObject();
                	json.put("requestType", request.getRequesttypeName());
                	json.put("requestId", request.getId());
                	json.put("requestTitle", request.getTitle());
                        
                	// Thach.modified.20140825
                	if (request.getManagerUsername() != null) {
                		json.put("managerName", request.getManagerName());
                        json.put("managerId", request.getManagerUsername());
                    }
                	//json.put("assignId", request.getManagerId());
                	json.put("assignId", request.getAssigneeUsername());
                		
                	// Thach.Modified.20140825
                	if (request.getStartdate() != null) {
                		json.put("startDate", dateFormat.format(request.getStartdate()));
                	}
                		
                	// Thach.Modified.20140825
                	if (request.getEnddate() != null) {
                		json.put("endDate", dateFormat.format(request.getEnddate()));
                	}
                	json.put("content", request.getContent());
                	if (requestService.checkIsRead(request, userLogin) == 1) {
                		json.put("readStatus", 1);
                	}
                	else {
                		json.put("readStatus", 0);
                	}
                	json.put("status", request.getStatus());
                	listJson.add(json);
        		}
        	}
        }
    	return listJson.toString();
    }
    
    @RequestMapping(value="listRule")
    public ModelAndView showListRule(Principal principal) {
    	User userLogin = userService.getUserByUsername(principal.getName());
    	
    	List<Request> listRule = requestService.getDaoController().getListRequestByRequestTypeCdAndOrderByCreate("Rule", "DESC");
    	
    	ModelAndView mav = new ModelAndView("listRule");
    	mav.addObject("listRule", listRule);
    	return mav;
    }
    
    @RequestMapping(value="listAnnouncement")
    public ModelAndView showListAnnouncement(Principal principal) {
    	User userLogin = userService.getUserByUsername(principal.getName());
    	
    	List<Request> listAnnouncement = requestService.getDaoController().getListRequestByRequestTypeCdAndOrderByCreate("Announcement", "DESC");
    	
    	ModelAndView mav = new ModelAndView("listAnnouncement");
    	mav.addObject("listAnnouncement", listAnnouncement);
    	return mav;
    }
}
