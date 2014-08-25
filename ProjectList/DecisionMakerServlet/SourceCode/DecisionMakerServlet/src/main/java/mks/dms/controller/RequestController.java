package mks.dms.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public ModelAndView saveRequest(@ModelAttribute("model") RequestCreateModel model, BindingResult result, Principal principal) {
        // Model to re-display the saved request
        ModelAndView mav = new ModelAndView("createRequest");

        // Debug data of model
        Request request = model.getRequest();
        LOG.debug("Binding result; hasError=" + result.hasErrors());
        //LOG.debug("type id=" + request.getRequesttypeId());
        LOG.debug("type cd=" + request.getRequesttypeCd());                       // have value from client
        //LOG.debug("type name=" + request.getRequesttypeName());
        LOG.debug("title=" + request.getTitle());                                 // For all requests 
        LOG.debug("content=" + request.getContent());                             // For all requests
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
        
        User userCreate = userService.getUserByUsername(principal.getName());
 
        Date today = new Date();
        int saveOrUpdate = requestService.saveOrUpdate(model.getRequest());
        if (saveOrUpdate == 1) {
        	model.getRequest().setStatus("Created");
            model.getRequest().setCreatorRead(1);
            model.getRequest().setCreatedbyId(userCreate);
            model.getRequest().setCreatedbyName(principal.getName());
            model.getRequest().setCreatedbyCd(userCreate.getCd());
            model.getRequest().setAssignerRead(0);
            model.getRequest().setManagerRead(0);
            model.getRequest().setCreated(today);
        }
        if (saveOrUpdate == 2) {
        	model.getRequest().setStatus("Updated");
            model.getRequest().setCreatorRead(1);
            model.getRequest().setCreatedbyId(userCreate);
            model.getRequest().setCreatedbyName(principal.getName());
            model.getRequest().setCreatedbyCd(userCreate.getCd());
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
        //model.setRequest(request);
        //mav.addObject("model", model);
        
        LOG.debug("model.getRequest().getManagerCd()=" + model.getRequest().getManagerCd());
        if (model.getRequest().getManagerId() != null) {
            LOG.debug("model.getRequest().getManagerId().getUsername()=" + model.getRequest().getManagerId().getUsername());
        }
        
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
     * process when click submit in form createRequest
     * 18-08-2014:
     * Thach: Deprecated
     * @see method saveRequest
     *  */
    @RequestMapping(value="createNewRequest")
    public String createNewRequest(HttpServletRequest req) throws ParseException {
    	Date today = new Date();
    	Request request = new Request();
    	String requestCd = req.getParameter("reqType");
    	SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		
    	if (requestCd.equals("Task")) {
    		request.setRequesttypeId(1);
    		int userId = Integer.parseInt(req.getParameter("taskReceiveUser"));
        	User receiveUser = userService.getUserById(userId);
        	sendMail(receiveUser.getEmail(), "Email thử nghiệm", "Email test");
    	}
    	if (requestCd.equals("Rule")) {
    		request.setRequesttypeId(2);
    		int userId = Integer.parseInt(req.getParameter("leaveReceiveUser"));
        	User receiveUser = userService.getUserById(userId);
        	sendMail(receiveUser.getEmail(), "Email thử nghiệm", "Email test");
    	}
    	if (requestCd.equals("Announcement")) {
    		request.setRequesttypeId(3);
    		int userId = Integer.parseInt(req.getParameter("leaveReceiveUser"));
        	User receiveUser = userService.getUserById(userId);
        	sendMail(receiveUser.getEmail(), "Email thử nghiệm", "Email test");
    	}
    	if (requestCd.equals("Leave")) {
    		request.setRequesttypeId(4);
    		String leaveContent = req.getParameter("leaveContent");
    		String leaveTitle = req.getParameter("leaveTitle");
    		Date leaveStartDay = formater.parse(req.getParameter("leaveStartDay"));
    		Date leaveEndDay = formater.parse(req.getParameter("leaveEndDay"));
    		String leaveLabel = req.getParameter("leaveLabel");
    		String leaveCreate = req.getParameter("leaveCreate");
//    		System.out.println(req.getParameter("leaveStartDay"));
    		
    		User createUser = userService.getUserByUsername(leaveCreate);
//    		Department createDepartmentId = requestService.getDepartmentByCd(createUser.getDepartmentId());
    		request.setCreatedbyCd(createUser.getUsername());
    		request.setCreatedbyId(createUser);
    		request.setCreatedbyCd(createUser.getUsername());
//    		request.setDepartmentsId(createDepartmentId);
    		
    		request.setCreated(today);
    		request.setContent(leaveContent);
    		request.setContent(leaveContent);
    		request.setTitle(leaveTitle);
    		request.setStatus("Created");
    		request.setRequesttypeCd("Leave");
    		RequestType requestType = requestService.getRequestTypeByCd("Leave");
    		request.setRequesttypeName(requestType.getName());
    		request.setRequesttypeId(requestType.getId());
//    		set label
    		
    		request.setStartdate(leaveStartDay);
    		request.setEnddate(leaveEndDay);
    		request.setCreatorRead(1);
    		request.setManagerRead(0);
    		request.setAssignerRead(0);
    		int userCd = Integer.parseInt(req.getParameter("leaveReceiveUser"));
        	User receiveUser = userService.getUserById(userCd);
        	request.setManagerId(receiveUser);
        	request.setManagerCd(receiveUser.getUsername());
        	request.setManagerCd(receiveUser.getUsername());

        	requestService.createRequest(request);
        	
        	String emailContent = "Đơn xin nghỉ việc của " + 
        							 leaveCreate + 
        							 "<br>" + 
        							 "Xin nghỉ từ " + req.getParameter("leaveStartDay") + " đến " + req.getParameter("leaveEndDay") +
        							 "<br> " +
        							 "Lý do: " + leaveContent;
        	sendMail(receiveUser.getEmail(), leaveTitle, emailContent);
    	}
    	
    	return "redirect:detailRequest?id="+ request.getId();
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
    public ModelAndView editRequest(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView("editRequest");
        LOG.debug("id=" + id);
        RequestCreateModel requestCreateModel = new RequestCreateModel();
        Request request = requestService.getRequestById(id);
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
//    @RequestMapping(value="editRequest")
//    public ModelAndView editRequest(@RequestParam("id") int id) {
//    	
////    	Lay thong tin tai khoan dang nhap
////    	Kiem tra tai khoan dang nhap phai tai khoan khoi tao yeu cau khong
////    	Neu khong phai -> quay lai trang home -> hien thong bao "Ban khong co quyen nay"
//    	
////    	Neu phai
//    	ModelAndView mav = new ModelAndView("editRequest");
//    	Request request = requestService.getRequestById(id);
//    	List<User> listUsers = userService.getAllUser();
//        mav.addObject("listUsers", listUsers);
//    	mav.addObject("request", request);
//    	return mav;
//    }
    
    @RequestMapping(value="updateRequest")
    public String processUpdateRequest(HttpServletRequest req) throws IllegalOrphanException, NonexistentEntityException, Exception {
    	Date today = new Date();
    	String requestType = req.getParameter("reqType");
    	int requestId = Integer.parseInt(req.getParameter("requestId"));
    	SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    	Request request = requestService.getRequestById(requestId);
    	String leaveCreate = req.getParameter("leaveCreate");
    	if (request.getCreatedbyCd().equals(leaveCreate)) {
    		if (requestType.equals("Task")) {
        		request.setRequesttypeId(1);
        		int userId = Integer.parseInt(req.getParameter("taskReceiveUser"));
            	User receiveUser = userService.getUserById(userId);
            	sendMail(receiveUser.getEmail(), "Email thử nghiệm", "Email test");
        	}
        	if (requestType.equals("Rule")) {
        		request.setRequesttypeId(2);
        		int userId = Integer.parseInt(req.getParameter("leaveReceiveUser"));
            	User receiveUser = userService.getUserById(userId);
            	sendMail(receiveUser.getEmail(), "Email thử nghiệm", "Email test");
        	}
        	if (requestType.equals("Announcement")) {
        		request.setRequesttypeId(3);
        		int userId = Integer.parseInt(req.getParameter("leaveReceiveUser"));
            	User receiveUser = userService.getUserById(userId);
            	sendMail(receiveUser.getEmail(), "Email thử nghiệm", "Email test");
        	}
        	if (requestType.equals("Leave")) {
        		request.setRequesttypeId(4);
        		String leaveContent = req.getParameter("leaveContent");
        		String leaveTitle = req.getParameter("leaveTitle");
        		Date leaveStartDay = formater.parse(req.getParameter("leaveStartDay"));
        		Date leaveEndDay = formater.parse(req.getParameter("leaveEndDay"));
        		String leaveLabel = req.getParameter("leaveLabel");
        		
        		request.setLastmodified(today);
        		request.setContent(leaveContent);
        		request.setTitle(leaveTitle);
//        		request.setStatus("Updated");
        		request.setManagerRead(0);
        		
//        		set label
        		
        		request.setStartdate(leaveStartDay);
        		request.setEnddate(leaveEndDay);
        		int userCd = Integer.parseInt(req.getParameter("leaveReceiveUser"));
            	User receiveUser = userService.getUserById(userCd);
            	request.setManagerId(receiveUser);
            	request.setManagerCd(receiveUser.getUsername());
            	request.setManagerCd(receiveUser.getUsername());
            	
            	
            	
            	String emailContent = "Đơn xin nghỉ việc của " + 
            							leaveCreate + 
            							 "<br>" + 
            							 "Xin nghỉ từ " + req.getParameter("leaveStartDay") + " đến " + req.getParameter("leaveEndDay") +
            							 "<br> " +
            							 "Lý do: " + leaveContent;
            	sendMail(receiveUser.getEmail(), leaveTitle, emailContent);
        	}
        	requestService.updateRequest(request);
        	request.setLastmodified(today);
        	return "redirect:detailRequest?id=" + request.getId();
    	}
    	else {
    		return "redirect:detailRequest?id=" + request.getId();
    	}
    	
    }
    
    @RequestMapping(value="detailRequest")
    public ModelAndView showDetailRequestPage(@RequestParam("id") int id, Principal principal) throws IllegalOrphanException, NonexistentEntityException, Exception {
    	Request request = requestService.getRequestById(id);
    	ModelAndView mav = new ModelAndView("detailRequest");
    	mav.addObject("request", request);
//    	kiem tra tai khoan dang nhap phai tai khoan duoc nhan request ko
//    	neu phai
    	
    	User userLogin = userService.getUserByUsername(principal.getName());
    	
    	if (request.getManagerCd().equals(userLogin.getCd())) {
    		if (request.getManagerRead() == 0) {
        		request.setManagerRead(1);
        		requestService.updateRequest(request);
        	}
    		mav.addObject("isManager", Boolean.TRUE);
    	}
    	
    	
//    	kiem tra tai khoan dang nhap phai tai khoan tao request ko
//    	neu phai
    	if (request.getCreatedbyCd().equals(userLogin.getCd())) {
	    	if (request.getCreatorRead() == 0) {
	    		request.setCreatorRead(1);
	    		requestService.updateRequest(request);
	    		
	    	}
	    	mav.addObject("isCreater", Boolean.TRUE);
    	}
    	return mav;
    }
    
    /**
     * Process when approveRequest
     **/
    @RequestMapping(value="approveRequest")
    public String approveRequest(@RequestParam("id") int id) throws IllegalOrphanException, NonexistentEntityException, Exception {
    	Date today = new Date();
//    	Lay thong tin tai khoan dang nhap
//    	Kiem tra tai khoan dang nhap phai tai khoan duoc yeu cau khong
//    	Neu khong phai -> quay lai trang home -> hien thong bao "Ban khong co quyen nay"
    	
//    	Neu phai
    	Request request = requestService.getRequestById(id);
    	request.setStatus("Approved");
    	request.setLastmodified(today);
    	request.setCreatorRead(0);
    	
//    	Bo sung them thong tin sau
//    	request.setLastmodifiedbyAccount(lastmodifiedbyAccount);
//    	request.setLastmodifiedbyId(lastmodifiedbyId);
//    	request.setLastmodifiedbyName(lastmodifiedbyName);
    	
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
    	request.setStatus("Rejected");
    	request.setCreatorRead(0);
    	
    	User userLogin = userService.getUserByUsername(pricipal.getName());
//    	luu lý do reject
    	String fullReasonReject = userLogin.getLastname() + " " + userLogin.getFirstname() + " (" + formater.format(today) + ") : " + reasonReject + " \n";
    	request.setComment(request.getComment() + fullReasonReject);
    	
    	
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
//    	List<Request> listRequest = requestService.getListRequestByCreatedbyCd(username);
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
//    	List<Request> listRequest = requestService.getListRequestByManagerCd(username);
    	ModelAndView mav = new ModelAndView("listReceiveRequest");
    	List<RequestType> lstRequestTypes = masterService.getRequestTypes();
        LOG.debug("lstRequestTypes=" + lstRequestTypes);
        mav.addObject("lstReqTypes", lstRequestTypes);
        List<User> listUsers = userService.getAllUser();
        mav.addObject("listUsers", listUsers);
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
    		json.put("reason", request.getContent());
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
    	System.out.println("List Assigner " + userLogin.getCd());
    	System.out.println("LoginCd: " + listAssignerRequest.size());
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
    		json.put("reason", request.getContent());
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
    	System.out.println("So lương " + listRequest.size());
    	List<JSONObject> listJson = new ArrayList<JSONObject>();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
    	for (Request request:listRequest) {
    		if (requestContent.equals("") && requestTitle.equals("")) {
    			JSONObject json = new JSONObject();
        		json.put("requestType", request.getRequesttypeName());
//        		json.put("requestType", request.getRequesttypeCd());
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
        		json.put("reason", request.getContent());
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
            		json.put("reason", request.getContent());
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
    			System.out.println(request.getTitle().toLowerCase());
    			System.out.println(requestTitle.toLowerCase());
    			System.out.println(request.getTitle().toLowerCase().contains(requestTitle.toLowerCase()));
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
            		json.put("reason", request.getContent());
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
            		json.put("reason", request.getContent());
//            		json.put("readStatus", request.getReadstatus());
            		json.put("status", request.getStatus());
            		listJson.add(json);
    			}
    		}
    	}
    	System.out.println("Chuoi la " + listJson);
    	return listJson.toString();
    }
    
    @RequestMapping(value="response.request.count", method = RequestMethod.GET)
    public @ResponseBody String countResponseRequest(Principal principal) throws JSONException{
    	User userLogin = userService.getUserByUsername(principal.getName());
    	
        List<Request> listApproveRequest = requestService.getListRequestByCreatorCdAndStatusAndManagerRead(userLogin.getCd(), "Approved", 0);
        List<Request> listRejectedRequest = requestService.getListRequestByCreatorCdAndStatusAndManagerRead(userLogin.getCd(), "Rejected", 0);
        
	    int count = 0;
	    count = listApproveRequest.size() + listRejectedRequest.size();
    	
		JSONObject json = new JSONObject();
		json.put("countResponseRequest", count);
    		
    	return json.toString();
    } 
    
    @RequestMapping(value="request.count", method = RequestMethod.GET)
    public @ResponseBody String countRequest(Principal principal) throws JSONException{
    	User userLogin = userService.getUserByUsername(principal.getName());
    	
        List<Request> listCreatedRequest = requestService.getListRequestByManagerCdAndStatusAndReadstatus(userLogin.getCd(), "Created", 0);
        List<Request> listUpdateRequest = requestService.getListRequestByManagerCdAndStatusAndReadstatus(userLogin.getCd(), "Updated", 0);
        List<Request> listTaskRequest = requestService.getListRequestByManagerCdAndStatusAndReadstatus(userLogin.getCd(), "Created", 0);
        
	    int count = 0;
	    count = listCreatedRequest.size() + listUpdateRequest.size() + listTaskRequest.size();
    	
		JSONObject json = new JSONObject();
		json.put("countRequest", count);
    		
    	return json.toString();
    }
}
