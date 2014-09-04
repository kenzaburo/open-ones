package mks.dms.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.ParseException;
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
import mks.dms.dao.entity.RequestType;
import mks.dms.dao.entity.User;
import mks.dms.model.RequestCreateModel;
import mks.dms.service.MasterService;
import mks.dms.service.RequestControllerService;
import mks.dms.service.UserControllerService;
import mks.dms.util.AppCons;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
/**
 * @author ThachLe, TruongTho
 *
 */
@Controller
@SessionAttributes({"lstReqTypes","listUsers", "listDurationUnits"})
public class RequestController {
	/**  */
	private static final Logger LOG = Logger.getLogger(RequestController.class);

	private final MasterService masterService;
	
	private final RequestControllerService requestService;
	
	private final UserControllerService userService;
	
    @Autowired
    public RequestController(MasterService masterService, RequestControllerService requestService, UserControllerService userService) {
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        // true passed to CustomDateEditor constructor means convert empty String to null
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));        
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
    public ModelAndView createRequest(Model model){
        ModelAndView mav = new ModelAndView("createRequest");
        // Get RequestCreateModel from service
        RequestCreateModel requestCreateModel = requestService.getRequestCreateModel(masterService);

//        LOG.debug("CreateRequest controller init data: " + requestCreateModel.getListRequestType().size());
        LOG.debug("model.getClass()=" + model.getClass());
        
        // Add object to modelandview
        mav.addObject("model", requestCreateModel);
        
//        List<RequestType> lstRequestTypes = masterService.getRequestTypes();
//        LOG.debug("lstRequestTypes=" + lstRequestTypes);
//        mav.addObject("lstReqTypes", lstRequestTypes);
//        List<User> listUsers = userService.getAllUser();
//        mav.addObject("listUsers", listUsers);
//        
//        List<DurationUnit> listDurationUnits = MasterService.getDurationUnits();
//        mav.addObject("listDurationUnits", listDurationUnits);
        
//        mav.addObject("result", 0);
    	return mav;
    }
    
    /**
    * This method process saving (create or edit) a request from client.
    * @param model contains data are submitted from client
    * @return the current view with result of saving
    * @author ThachLe
    * @see /DecisionMakerServlet/src/main/webapp/WEB-INF/views/Request/_createTask.jsp
    */
    @RequestMapping(value = "saveRequest", method = RequestMethod.POST)
    public ModelAndView saveRequest(@ModelAttribute("model") RequestCreateModel model, BindingResult result, Principal principal, HttpServletRequest httpRequest) {
        // Model to re-display the saved request
    	SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
    	Date today = new Date();
    	ModelAndView mav = new ModelAndView("editRequest");
        User userLogin = userService.getUserByUsername(principal.getName());
        // Debug data of model
        Request request = model.getRequest();
        LOG.debug("Binding result; hasError=" + result.hasErrors());
        //LOG.debug("type id=" + request.getRequesttypeId());
        LOG.debug("type cd=" + request.getRequesttypeCd());                       // have value from client
        //LOG.debug("type name=" + request.getRequesttypeName());
        LOG.debug("title=" + request.getTitle());                                 // For all requests 
        LOG.debug("content=" + request.getContent()); 
        LOG.debug("Creator id=" + userLogin.getId());   							// For all requests
        if (request.getAssignedId() != null) {
            LOG.debug("assigned id=" + request.getAssignedId().getId());          // Task
            // LOG.debug("assigned cd=" + request.getAssignedId().getCd());
            // LOG.debug("assigned username=" + request.getAssignedId().getUsername());
        }
        
        
        if (request.getManagerId() != null) {
            LOG.debug("manager id=" + request.getManagerId().getId());                // Task | Leave
            // LOG.debug("manager cd=" + request.getManagerId().getCd());             // Task | Leave
            // LOG.debug("manager username=" + request.getManagerId().getUsername()); // Task | Leave
        }
        
        LOG.debug("Start date=" + request.getStartdate());                         // Task | Leave
        LOG.debug("End date=" + request.getEnddate());                             // Task | Leave
        
        if (model.getAttachments() != null) {
            LOG.debug("Number of attachment: " + model.getAttachments().size());
            LOG.debug("Name: " + model.getAttachments().get(0).getOriginalFilename());
            try {
                LOG.debug("Number of size: " + model.getAttachments().get(0).getBytes().length);
            } catch (IOException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }
        } else {
            LOG.debug("No attachment");
        }
        
        int saveOrUpdate = requestService.saveOrUpdate(model.getRequest());
        if (saveOrUpdate == 1) {
        	model.getRequest().setStatus("Created");
            model.getRequest().setCreatorRead(1);
        	if (request.getRequesttypeCd().equals("Task")) {
        		if (request.getAssignedId().getId() == userLogin.getId()) {
        			model.getRequest().setStatus("Doing");
                    model.getRequest().setAssignerRead(1);
        		}
                else {
                	model.getRequest().setAssignerRead(0);
                }	
        	}
            model.getRequest().setCreatedbyId(userLogin);
            model.getRequest().setCreatedbyName(principal.getName());
            model.getRequest().setCreatedbyCd(userLogin.getCd());
            model.getRequest().setManagerRead(0);
            model.getRequest().setCreated(today);
        }
        if (saveOrUpdate == 2) {
        	if (request.getRequesttypeCd().equals("Leave") || request.getRequesttypeCd().equals("Task")) {
            	String comment = httpRequest.getParameter("commentTask");
            	String fullComment = "";
            	if (!comment.equals("")) {
            		fullComment = userLogin.getLastname() + " " + userLogin.getFirstname() + " (" + formater.format(today) + ") : " + comment + " \n";
            	}
            	if (request.getComment() != null) {
            		request.setComment(request.getComment() + fullComment);
            	}
            	else {
            		request.setComment(fullComment);
            	}
            	
            }
        	
        	if (request.getRequesttypeCd().equals("Task")) {
        		if (request.getManagerId().getId() == userLogin.getId()) {
        			model.getRequest().setStatus("Updated");
                    model.getRequest().setAssignerRead(1);
                    model.getRequest().setCreatorRead(1);
        		}
        		else if (request.getCreatedbyId().getId() == userLogin.getId() && request.getAssignedId().getId() != userLogin.getId()) {
                	model.getRequest().setStatus("Updated");
                	model.getRequest().setManagerRead(1);
                	model.getRequest().setAssignerRead(1);
                }
                else if (request.getAssignedId().getId() == userLogin.getId() && request.getCreatedbyId().getId() != userLogin.getId()) {
                	model.getRequest().setStatus("Updated1");
                	model.getRequest().setManagerRead(1);
                	model.getRequest().setCreatorRead(1);
                }
                else if (request.getAssignedId().getId() == userLogin.getId() && request.getCreatedbyId().getId() == userLogin.getId()) {
                	model.getRequest().setStatus("Updated");
                	model.getRequest().setManagerRead(1);
                	model.getRequest().setAssignerRead(0);
                	model.getRequest().setCreatorRead(0);
                }
            }
        	
            model.getRequest().setCreatedbyId(userLogin);
            model.getRequest().setCreatedbyName(principal.getName());
            model.getRequest().setCreatedbyCd(userLogin.getCd());
            if (request.getAssignedId().getId() == userLogin.getId())
            	model.getRequest().setAssignerRead(1);
            else
            	model.getRequest().setAssignerRead(0);
            model.getRequest().setManagerRead(0);
            model.getRequest().setCreated(today);
        }
        
        // Save request by call to request controller service
        boolean retOK = requestService.saveRequest(model, masterService);;
                
        LOG.debug("SaveRequest controller init data: " + model.getRequest());
        
        // Send email if the request is "Leave"
        if (retOK && (AppCons.LEAVE.equals(request.getRequesttypeCd()))) {
            sendEmailLeave(request);
        }

        
		// Enable flag save.success
        mav.addObject(AppCons.SAVE_STATUS, AppCons.SUCCESS);
        // Refresh model
        model.setRequest(request);
        mav.addObject("model", model);
        
        LOG.debug("model.getRequest().getManagerCd()=" + model.getRequest().getManagerCd());
        if (model.getRequest().getManagerId() != null) {
            LOG.debug("model.getRequest().getManagerId().getUsername()=" + model.getRequest().getManagerId().getUsername());
        }
        
//        return "redirect:detailRequest?id=" + request.getId();
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
        RequestCreateModel requestCreateModel = new RequestCreateModel();
        
        Request request = requestService.getRequestById(id);
        if (request.getManagerCd().equals(userLogin.getCd())) {
        	mav.addObject("isManager", Boolean.TRUE);
        }
        if (request.getRequesttypeCd().equals("Task") && request.getCreatedbyCd().equals(userLogin.getCd()) && request.getCreatedbyCd().equals(request.getAssignedCd())) {
        	mav.addObject("isCreatorAssigner", Boolean.TRUE);
        }
        if (request.getRequesttypeCd().equals("Task") && request.getCreatedbyCd().equals(userLogin.getCd()) && !request.getCreatedbyCd().equals(request.getAssignedCd())) {
        	mav.addObject("isCreator", Boolean.TRUE);
        }
        if (request.getRequesttypeCd().equals("Task") && request.getAssignedCd().equals(userLogin.getCd())) {
        	mav.addObject("isAssigner", Boolean.TRUE);
        }
        requestCreateModel.setRequest(request);;
        
        // Add object to modelandview
        mav.addObject("model", requestCreateModel);

        return mav;
    }

    @RequestMapping(value="downloadFile")
    public void downloadFile(@RequestParam("id") int id, HttpServletResponse response) {
        LOG.debug("id=" + id);
        Request request = requestService.getRequestById(id);
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
        } catch (IllegalOrphanException ex) {
            jsonResult = "{result: 'FAIL'}";
            LOG.error("Could not delete the request id " + requestId, ex);
        } catch (NonexistentEntityException ex) {
            jsonResult = "{result: 'FAIL'}";
            LOG.error("Could not delete the request id " + requestId, ex);
        }
        
        return jsonResult;
    }    

    @RequestMapping(value="detailRequest")
    public ModelAndView showDetailRequestPage(@RequestParam("id") int id, Principal principal) throws IllegalOrphanException, NonexistentEntityException, Exception {
    	Request request = requestService.getRequestById(id);
    	ModelAndView mav = new ModelAndView("detailRequest");
    	mav.addObject("request", request);
//    	kiem tra tai khoan dang nhap phai tai khoan duoc nhan request ko
//    	neu phai
    	
    	User userLogin = userService.getUserByUsername(principal.getName());
    	if (request.getRequesttypeCd().equals("Leave")) {
    		if (request.getManagerCd().equals(userLogin.getCd())) {
        		if (request.getManagerRead() == 0) {
            		request.setManagerRead(1);
            		requestService.updateRequest(request);
            	}
        		mav.addObject("isManager", Boolean.TRUE);
        	}
        	
        	if (request.getCreatedbyCd().equals(userLogin.getCd())) {
    	    	if (request.getCreatorRead() == 0) {
    	    		request.setCreatorRead(1);
    	    		requestService.updateRequest(request);
    	    		
    	    	}
    	    	mav.addObject("isCreator", Boolean.TRUE);
        	}
    	}
    	if (request.getRequesttypeCd().equals("Task")) {
    		if (request.getAssignedCd().equals(request.getCreatedbyCd())) {
    			if (request.getManagerCd().equals(userLogin.getCd())) {
            		if (request.getManagerRead() == 0) {
                		request.setManagerRead(1);
                		requestService.updateRequest(request);
                	}
            		mav.addObject("isManager", Boolean.TRUE);
            	}
            	
            	if (request.getCreatedbyCd().equals(userLogin.getCd())) {
        	    	if (request.getCreatorRead() == 0) {
        	    		request.setCreatorRead(1);
        	    		request.setAssignerRead(1);
        	    		requestService.updateRequest(request);
        	    	}
        	    	mav.addObject("isCreatorAssigner", Boolean.TRUE);
            	}
            	
            	
    		}
    		else {
    			if (request.getAssignedCd().equals(userLogin.getCd())) {
    				if (request.getAssignerRead() == 0) {
    					request.setAssignerRead(1);
        				requestService.updateRequest(request);
    				}
    				mav.addObject("isAssigner", Boolean.TRUE);
    			}
    			if (request.getManagerCd().equals(userLogin.getCd())) {
            		if (request.getManagerRead() == 0) {
                		request.setManagerRead(1);
                		requestService.updateRequest(request);
                	}
            		mav.addObject("isManager", Boolean.TRUE);
            	}
    			if (request.getCreatedbyCd().equals(userLogin.getCd())) {
        	    	if (request.getCreatorRead() == 0) {
        	    		request.setCreatorRead(1);
        	    		requestService.updateRequest(request);
        	    	}
        	    	mav.addObject("isCreator", Boolean.TRUE);
            	}
    		}
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
    	
    	Request request = requestService.getRequestById(id);
    	if (request.getRequesttypeCd().equals("Leave")) {
    		request.setStatus("Approved");
    	}
    	if (request.getRequesttypeCd().equals("Task") && (request.getStatus().equals("Created") || request.getStatus().equals("Updated"))) {
    		request.setStatus("Doing");
    		request.setManagerRead(0);
    	}
    	
    	request.setLastmodified(today);
    	request.setCreatorRead(0);
    	
    	request.setLastmodified(today);
    	request.setLastmodifiedbyCd(userLogin.getCd());
    	request.setLastmodifiedbyName(userLogin.getLastname() + userLogin.getFirstname());
    	request.setLastmodifiedbyId(userLogin.getId());
    	
    	requestService.updateRequest(request);
    	
    	return "redirect:detailRequest?id=" + id;
    }
    
    /**
     * Process when rejecte Request
     **/
    @RequestMapping(value="rejectRequest")
    public String rejectRequest(HttpServletRequest req, Principal pricipal) throws IllegalOrphanException, NonexistentEntityException, Exception {
    	SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
    	Date today = new Date();
    	int id = Integer.parseInt(req.getParameter("requestId"));
    	String reasonReject = req.getParameter("rejectContent");
    	
//    	Lay thong tin tai khoan dang nhap
//    	Kiem tra tai khoan dang nhap phai tai khoan duoc yeu cau khong
//    	Neu khong phai -> quay lai trang home -> hien thong bao "Ban khong co quyen nay"
    	
//    	Neu phai
    	Request request = requestService.getRequestById(id);
    	
    	if (request.getRequesttypeCd().equals("Leave")) {
    		request.setStatus("Rejected");
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
    	
    	requestService.updateRequest(request);
    	
    	return "redirect:detailRequest?id=" + id;
    }
    
    @RequestMapping(value="addComment")
    public String addComment(HttpServletRequest req, Principal principal) throws IllegalOrphanException, NonexistentEntityException, Exception {
    	SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
    	Date today = new Date();
    	int id = Integer.parseInt(req.getParameter("requestId"));
    	String commentContent = req.getParameter("commentContent");
    	
//    	Lay thong tin tai khoan dang nhap
//    	Kiem tra tai khoan dang nhap phai tai khoan duoc yeu cau khong
//    	Neu khong phai -> quay lai trang home -> hien thong bao "Ban khong co quyen nay"
    	
//    	Neu phai
    	
    	User userLogin = userService.getUserByUsername(principal.getName());
    	
    	Request request = requestService.getRequestById(id);
    	if (userLogin.getCd().equals(request.getManagerCd())) {
    		if (request.getStatus().equals("Confirm")) {
    			request.setStatus("Doing");
        	}
    		request.setCreatorRead(0);
        	request.setAssignerRead(0);
    	}
    	
    	if (!request.getCreatedbyCd().equals(request.getAssignedCd())) {
    		if (userLogin.getCd().equals(request.getCreatedbyCd())) {
	    		request.setAssignerRead(0);
	    		request.setManagerRead(0);
	    	}
	    	
	    	if (userLogin.getCd().equals(request.getAssignedCd())) {
	    		request.setAssignerRead(0);
	    		request.setManagerRead(0);
	    	}
    	}
    	else {
    		if (userLogin.getCd().equals(request.getCreatedbyCd())) {
    			request.setManagerRead(0);
    		}
    	}
	    	
    		
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
    	
    	requestService.updateRequest(request);
    	
    	return "redirect:detailRequest?id=" + id;
    }
    
    /**
     * Show listSendRequest page
     **/
    @RequestMapping(value="listSendRequest")
    public ModelAndView showPageListSendRequest() {
    	ModelAndView mav = new ModelAndView("listSendRequest");
    	List<RequestType> lstRequestTypes = masterService.getRequestTypes();
        mav.addObject("lstReqTypes", lstRequestTypes);
        List<User> listUsers = userService.getAllUser();
        mav.addObject("listUsers", listUsers);
    	return mav;
    }
    
    /**
     * Show listReceiveRequest page
     **/
    @RequestMapping(value="listReceiveRequest")
    public ModelAndView showPageListReceiveRequest() {
    	ModelAndView mav = new ModelAndView("listReceiveRequest");
    	List<RequestType> lstRequestTypes = masterService.getRequestTypes();
        LOG.debug("lstRequestTypes=" + lstRequestTypes);
        mav.addObject("lstReqTypes", lstRequestTypes);
        List<User> listUsers = userService.getAllUser();
        mav.addObject("listUsers", listUsers);
    	return mav;
    }
    
    /**
     * Show myListTask page
     **/
    @RequestMapping(value="mylisttask")
    public ModelAndView showMyListTask() {
    	ModelAndView mav = new ModelAndView("mylisttask");
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
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        User userLogin = userService.getUserByUsername(principal.getName());
    	List<Request> listRequest = requestService.getListRequestByCreatedbyCd(userLogin.getCd());
    	List<JSONObject> listJson = new ArrayList<JSONObject>();
    	for (Request request:listRequest) {
    		JSONObject json = new JSONObject();
    		json.put("requestType", request.getRequesttypeName());
//    		json.put("requestType", request.getRequesttypeCd());
    		json.put("requestId", request.getId());
    		json.put("requestTitle", request.getTitle());
    		json.put("managerName", request.getManagerId().getLastname() + " " + request.getManagerId().getFirstname());
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
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        User userLogin = userService.getUserByUsername(principal.getName());
    	List<Request> listManagerRequest = requestService.getListRequestByManagerCd(userLogin.getCd());
    	List<Request> listAssignerRequest = requestService.getListRequestByAssignedCd(userLogin.getCd());
    	for (Request request:listManagerRequest) {
    		System.out.println("Manager");
    		System.out.println(request.getId());
    	}
    	for (Request request:listAssignerRequest) {
    		System.out.println("Assigner");
    		System.out.println(request.getId());
    	}
    	listAssignerRequest.removeAll(listManagerRequest);
    	listManagerRequest.addAll(listAssignerRequest);
    	List<JSONObject> listJson = new ArrayList<JSONObject>();
    	for (Request request:listManagerRequest) {
    		JSONObject json = new JSONObject();
    		json.put("requestType", request.getRequesttypeName());
//    		json.put("requestType", request.getRequesttypeCd());
    		json.put("requestId", request.getId());
    		json.put("requestTitle", request.getTitle());
    		json.put("managerName", request.getManagerId().getLastname() + " " + request.getManagerId().getFirstname());
    		json.put("managerId", request.getManagerId());
    		json.put("assignId", request.getManagerId());
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
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        User userLogin = userService.getUserByUsername(principal.getName());
    	List<Request> listAssignerRequest = requestService.getListRequestByAssignedCdAndRequestTypeCd(userLogin.getCd(), "Task");
    	List<JSONObject> listJson = new ArrayList<JSONObject>();
    	for (Request request:listAssignerRequest) {
    		JSONObject json = new JSONObject();
    		json.put("requestType", request.getRequesttypeName());
    		json.put("requestId", request.getId());
    		json.put("requestTitle", request.getTitle());
    		json.put("managerName", request.getManagerId().getLastname() + " " + request.getManagerId().getFirstname());
    		json.put("managerId", request.getManagerId());
    		json.put("assignId", request.getManagerId());
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
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        User userLogin = userService.getUserByUsername(principal.getName());
    	List<Request> listAssignerRequest = requestService.getListRequestByManagerCdAndRequestTypeCd(userLogin.getCd(), "Task");
    	List<JSONObject> listJson = new ArrayList<JSONObject>();
    	for (Request request:listAssignerRequest) {
    		JSONObject json = new JSONObject();
    		json.put("requestType", request.getRequesttypeName());
    		json.put("requestId", request.getId());
    		json.put("requestTitle", request.getTitle());
    		json.put("managerName", request.getManagerId().getLastname() + " " + request.getManagerId().getFirstname());
    		json.put("managerId", request.getManagerId());
    		json.put("assignId", request.getManagerId());
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
    
    @RequestMapping(value="searchRequest")
    public ModelAndView showSearchRequestPage() {
    	ModelAndView mav = new ModelAndView("searchRequest");
    	List<RequestType> lstRequestTypes = masterService.getRequestTypes();
    	LOG.debug("lstRequestTypes=" + lstRequestTypes);
        mav.addObject("lstReqTypes", lstRequestTypes);
        List<User> listUsers = userService.getAllUser();
        mav.addObject("listUsers", listUsers);
    	return mav;
    }
    
    @RequestMapping(value="search.request", method = RequestMethod.GET)
    public @ResponseBody String searchRequest(Principal principal, @RequestParam("createdbyCd") String createdbyCd, @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, @RequestParam("managerId") String managerCd, @RequestParam("assignId") String assignCd, @RequestParam("requestTypeCd") String requestTypeCd, @RequestParam("requestTitle") String requestTitle, @RequestParam("requestContent") String requestContent) throws JSONException {
    	List<Request> listRequest;    	
    	if (createdbyCd.equals("") && startDate == null && endDate == null && managerCd.equals("0") && assignCd.equals("0") && requestTypeCd.equals("0")) {
    		listRequest = requestService.getAllRequest();
    	}else {
    		listRequest = requestService.searchRequest(createdbyCd, startDate, endDate, managerCd, assignCd, requestTypeCd);
    	}
    	User userLogin = userService.getUserByUsername(principal.getName());
    	List<JSONObject> listJson = new ArrayList<JSONObject>();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
    	for (Request request:listRequest) {
    		if (requestContent.equals("") && requestTitle.equals("")) {
    			JSONObject json = new JSONObject();
        		json.put("requestType", request.getRequesttypeName());
        		json.put("requestId", request.getId());
        		json.put("requestTitle", request.getTitle());
                
        		// Thach.modified.20140825
        		if (request.getManagerId() != null) {
                    json.put("managerName", request.getManagerId().getLastname() + " "
                            + request.getManagerId().getFirstname());
                    json.put("managerId", request.getManagerId());
                }
        		json.put("assignId", request.getManagerId());
        		
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
    		else if (!requestContent.equals("") && requestTitle.equals("")) {
    			
    			if (request.getContent().toLowerCase().contains(requestContent.toLowerCase())) {
    				JSONObject json = new JSONObject();
            		json.put("requestType", request.getRequesttypeName());
//            		json.put("requestType", request.getRequesttypeCd());
            		json.put("requestId", request.getId());
            		json.put("requestTitle", request.getTitle());
            		json.put("managerName", request.getManagerId().getLastname() + " " + request.getManagerId().getFirstname());
            		json.put("managerId", request.getManagerId());
            		json.put("assignId", request.getManagerId());
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
    		}
    		else if (requestContent.equals("") && !requestTitle.equals("")) {
    			if (request.getTitle().toLowerCase().contains(requestTitle.toLowerCase())) {
    				JSONObject json = new JSONObject();
            		json.put("requestType", request.getRequesttypeName());
//            		json.put("requestType", request.getRequesttypeCd());
            		json.put("requestId", request.getId());
            		json.put("requestTitle", request.getTitle());
            		json.put("managerName", request.getManagerId().getLastname() + " " + request.getManagerId().getFirstname());
            		json.put("managerId", request.getManagerId());
            		json.put("assignId", request.getManagerId());
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
    		}
    		else if (!requestContent.equals("") && !requestTitle.equals("")){
    			System.out.println("Title and content not null");
    			if (request.getTitle().toLowerCase().contains(requestTitle.toLowerCase()) && request.getContent().toLowerCase().contains(requestContent.toLowerCase())) {
    				JSONObject json = new JSONObject();
            		json.put("requestType", request.getRequesttypeName());
//            		json.put("requestType", request.getRequesttypeCd());
            		json.put("requestId", request.getId());
            		json.put("requestTitle", request.getTitle());
            		json.put("managerName", request.getManagerId().getLastname() + " " + request.getManagerId().getFirstname());
            		json.put("managerId", 1);
            		json.put("assignId", 1);
            		json.put("startDate", dateFormat.format(request.getStartdate()));
            		json.put("endDate", dateFormat.format(request.getEnddate()));;
            		json.put("content", request.getContent());
//            		json.put("readStatus", request.getReadstatus());
            		json.put("status", request.getStatus());
            		listJson.add(json);
    			}
    		}
    	}
    	return listJson.toString();
    }
    
    @RequestMapping(value="response.request.count", method = RequestMethod.GET)
    public @ResponseBody String countResponseRequest(Principal principal) throws JSONException{
    	User userLogin = userService.getUserByUsername(principal.getName());
    	
        List<Request> listApproveRequest = requestService.getListRequestByCreatorCdAndStatusAndCreatorRead(userLogin.getCd(), "Approved", 0);
        List<Request> listRejectedRequest = requestService.getListRequestByCreatorCdAndStatusAndCreatorRead(userLogin.getCd(), "Rejected", 0);
        List<Request> listDoingTask = requestService.getListRequestByCreatorCdAndStatusAndCreatorRead(userLogin.getCd(), "Doing", 0);
        List<Request> listDoingTask1 = requestService.getListRequestByAssignerCdAndStatusAndAssignerRead(userLogin.getCd(), "Doing", 0);
        List<Request> listDoneTask = requestService.getListRequestByCreatorCdAndStatusAndCreatorRead(userLogin.getCd(), "Done", 0);
        List<Request> listDoneTask1 = requestService.getListRequestByCreatorCdAndStatusAndCreatorRead(userLogin.getCd(), "Done", 0);
        
        listDoingTask.removeAll(listDoingTask1);
        listDoingTask.addAll(listDoingTask1);
        listDoneTask.removeAll(listDoneTask1);
        listDoneTask.addAll(listDoneTask1);
        
	    int count = 0;
	    count = listApproveRequest.size() + listRejectedRequest.size() + listDoneTask.size() + listDoingTask.size();
    	
		JSONObject json = new JSONObject();
		json.put("countResponseRequest", count);
    		
    	return json.toString();
    } 
    
    @RequestMapping(value="request.count", method = RequestMethod.GET)
    public @ResponseBody String countRequest(Principal principal) throws JSONException{
    	User userLogin = userService.getUserByUsername(principal.getName());
    	
        List<Request> listCreatedRequest = requestService.getListRequestByManagerCdAndStatusAndReadstatus(userLogin.getCd(), "Created", 0);
        List<Request> listConfirmRequest = requestService.getListRequestByManagerCdAndStatusAndReadstatus(userLogin.getCd(), "Confirm", 0);
        List<Request> listUpdateRequest = requestService.getListRequestByManagerCdAndStatusAndReadstatus(userLogin.getCd(), "Updated", 0);
        List<Request> listDoingRequest = requestService.getListRequestByManagerCdAndStatusAndReadstatus(userLogin.getCd(), "Doing", 0);
        List<Request> listTaskRequest = requestService.getListRequestByAssignerCdAndStatusAndAssignerRead(userLogin.getCd(), "Created", 0);
        List<Request> listTaskRequest1 = requestService.getListRequestByAssignerCdAndStatusAndAssignerRead(userLogin.getCd(), "Updated", 0);
        List<Request> listTaskRequest2 = requestService.getListRequestByAssignerCdAndStatusAndAssignerRead(userLogin.getCd(), "Done", 0);
        
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
    	Request request = requestService.getRequestById(requestId);
    	request.setStatus("Confirm");
    	request.setManagerRead(0);
    	if (request.getAssignedCd().equals(userLogin.getCd()) && !request.getCreatedbyCd().equals(request.getAssignedCd())) {
    		request.setCreatorRead(0);
    	}
    	requestService.updateRequest(request);
    	return "redirect:detailRequest?id=" + request.getId(); 
    }
    
    @RequestMapping(value="completedtask")
    public String completedTask(Principal principal, @RequestParam("requestId") int requestId) throws IllegalOrphanException, NonexistentEntityException, Exception {
//    	User userLogin = userService.getUserByUsername(principal.getName());
    	Request request = requestService.getRequestById(requestId);
    	request.setStatus("Done");
    	request.setManagerRead(1);
    	request.setAssignerRead(0);
    	request.setCreatorRead(0);
    	requestService.updateRequest(request);
    	return "redirect:detailRequest?id=" + request.getId();	
    }
    
    @RequestMapping(value="detailContent")
    public ModelAndView detailContent(@RequestParam("id") int requestId) {
    	Request request = requestService.getRequestById(requestId);
    	ModelAndView mav = new ModelAndView("detailContent");
    	mav.addObject("request", request);
    	return mav;
    }
    
    @RequestMapping(value="listLeaveRequest")
    public ModelAndView showListLeaveRequestPage(Principal principal) {
    	ModelAndView mav = new ModelAndView("listLeaveRequest");
    	return mav;
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
    	List<Request> listRequest = requestService.searchRequest(userCd, startDay, endDay, userLogin.getCd(), "", "Leave");
    	List<JSONObject> listJson = new ArrayList<JSONObject>();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
    	for (Request request:listRequest) {
    		JSONObject json = new JSONObject();
        	json.put("requestType", request.getRequesttypeName());
        	json.put("requestId", request.getId());
        	json.put("requestTitle", request.getTitle());
                
        	// Thach.modified.20140825
        	if (request.getManagerId() != null) {
        		json.put("managerName", request.getManagerId().getLastname() + " " + request.getManagerId().getFirstname());
                json.put("managerId", request.getManagerId());
            }
        	json.put("assignId", request.getManagerId());
        		
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
    	return listJson.toString();
    }
}
