package mks.dms.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mks.dms.dao.controller.exceptions.IllegalOrphanException;
import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.RequestType;
import mks.dms.dao.entity.User;
import mks.dms.model.RequestCreateModel;
import mks.dms.service.MasterService;
import mks.dms.service.RequestControllerService;

import org.apache.log4j.Logger;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
/**
 * @author ThachLe, TruongTho
 *
 */
@Controller
public class RequestController {
	/**  */
	private static final Logger LOG = Logger.getLogger(RequestController.class);
	
	private final MasterService masterService;
	
	private final RequestControllerService requestService;
	
    @Autowired
    public RequestController(MasterService masterService, RequestControllerService requestService) {
        this.masterService = masterService;
        this.requestService = requestService;
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

        LOG.debug("CreateRequest controller init data: " + requestCreateModel.getListRequestType().size());

        // Add object to modelandview
        mav.addObject("model", requestCreateModel);
        
        List<RequestType> lstRequestTypes = masterService.getRequestTypes();
        LOG.debug("lstRequestTypes=" + lstRequestTypes);
        mav.addObject("lstReqTypes", lstRequestTypes);
        List<User> listUsers = masterService.getAllUser();
        mav.addObject("listUsers", listUsers);
//        mav.addObject("result", 0);
    	return mav;
    }
    
    /**
    * This method process saving a request from client.
    * @param model contains data are submitted from client
    * @return the current view with result of saving
    * @author ThachLe
    * @see /DecisionMakerServlet/src/main/webapp/WEB-INF/views/Request/_createTask.jsp
    */
    @RequestMapping(value = "saveRequest", method = RequestMethod.POST)
    public ModelAndView saveRequest(@ModelAttribute("model") RequestCreateModel model, BindingResult result) {
        // Model to re-display the saved request
        ModelAndView mav = new ModelAndView("createRequest");

        // Debug data of model
        Request request = model.getRequest();
        LOG.debug("Binding result; hasError=" + result.hasErrors());
        LOG.debug("type=" + request.getType());
        LOG.debug("title=" + request.getTitle());
        LOG.debug("content=" + request.getContent());
        LOG.debug("assigned id=" + request.getAssignedId().getId());
        LOG.debug("assigned cd=" + request.getAssignedId().getCd());
        LOG.debug("assigned username=" + request.getAssignedId().getUsername());
        
        LOG.debug("manager id=" + request.getManagerId().getId());
        LOG.debug("manager cd=" + request.getManagerId().getCd());
        LOG.debug("manager username=" + request.getManagerId().getUsername());
        
        // Save request by call to request controller service
        requestService.saveRequest(model, masterService);;
                
        LOG.debug("SaveRequest controller init data: " + model.getRequest());
        
        return mav;
    }
    
    /** 
     * process when click submit in form createRequest
     *  */
    @RequestMapping(value="createNewRequest")
    public String createNewRequest(HttpServletRequest req) throws ParseException {
    	Date today = new Date();
    	Request request = new Request();
    	String requestType = req.getParameter("reqType");
    	SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		
    	if (requestType.equals("Task")) {
    		request.setType(1);
    		int userId = Integer.parseInt(req.getParameter("taskReceiveUser"));
        	User receiveUser = masterService.getUserById(userId);
        	sendMail(receiveUser.getEmail(), "Email thử nghiệm", "Email test");
    	}
    	if (requestType.equals("Rule")) {
    		request.setType(2);
    		int userId = Integer.parseInt(req.getParameter("leaveReceiveUser"));
        	User receiveUser = masterService.getUserById(userId);
        	sendMail(receiveUser.getEmail(), "Email thử nghiệm", "Email test");
    	}
    	if (requestType.equals("Announcement")) {
    		request.setType(3);
    		int userId = Integer.parseInt(req.getParameter("leaveReceiveUser"));
        	User receiveUser = masterService.getUserById(userId);
        	sendMail(receiveUser.getEmail(), "Email thử nghiệm", "Email test");
    	}
    	if (requestType.equals("Leave")) {
    		request.setType(4);
    		String leaveContent = req.getParameter("leaveContent");
    		String leaveTitle = req.getParameter("leaveTitle");
    		Date leaveStartDay = formater.parse(req.getParameter("leaveStartDay"));
    		Date leaveEndDay = formater.parse(req.getParameter("leaveEndDay"));
    		String leaveLabel = req.getParameter("leaveLabel");
    		String leaveCreate = req.getParameter("leaveCreate");
//    		System.out.println(req.getParameter("leaveStartDay"));
    		
    		User createUser = masterService.getUserByUsername(leaveCreate);
    		Department createDepartmentId = masterService.getDepartmentByCd(createUser.getDepartmentId());
    		request.setCreatedbyAccount(createUser.getUsername());
    		request.setCreatedbyId(createUser);
    		request.setCreatedbyName(createUser.getUsername());
    		request.setDepartmentsId(createDepartmentId);
    		
    		request.setCreated(today);
    		request.setContent(leaveContent);
    		request.setContent(leaveContent);
    		request.setTitle(leaveTitle);
    		request.setStatus("Created");
    		request.setRequesttypeCd("Leave");
//    		set label
    		
    		request.setStartdate(leaveStartDay);
    		request.setEndate(leaveEndDay);
    		request.setReadstatus(1);
    		int userCd = Integer.parseInt(req.getParameter("leaveReceiveUser"));
        	User receiveUser = masterService.getUserById(userCd);
        	request.setManagerId(receiveUser);
        	request.setManagerAccount(receiveUser.getUsername());
        	request.setManagerName(receiveUser.getUsername());
        	
        	masterService.createRequest(request);
        	
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
     * Show page editRequest
     **/
    @RequestMapping(value="editRequest")
    public ModelAndView editRequest(@RequestParam("id") int id) {
    	
//    	Lay thong tin tai khoan dang nhap
//    	Kiem tra tai khoan dang nhap phai tai khoan khoi tao yeu cau khong
//    	Neu khong phai -> quay lai trang home -> hien thong bao "Ban khong co quyen nay"
    	
//    	Neu phai
    	ModelAndView mav = new ModelAndView("editRequest");
    	Request request = masterService.getRequestById(id);
    	List<User> listUsers = masterService.getAllUser();
        mav.addObject("listUsers", listUsers);
    	mav.addObject("request", request);
    	return mav;
    }
    
    @RequestMapping(value="updateRequest")
    public String processUpdateRequest(HttpServletRequest req) throws IllegalOrphanException, NonexistentEntityException, Exception {
    	Date today = new Date();
    	String requestType = req.getParameter("reqType");
    	int requestId = Integer.parseInt(req.getParameter("requestId"));
    	SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    	Request request = masterService.getRequestById(requestId);
    	String leaveCreate = req.getParameter("leaveCreate");
    	if (request.getCreatedbyName().equals(leaveCreate)) {
    		if (requestType.equals("Task")) {
        		request.setType(1);
        		int userId = Integer.parseInt(req.getParameter("taskReceiveUser"));
            	User receiveUser = masterService.getUserById(userId);
            	sendMail(receiveUser.getEmail(), "Email thử nghiệm", "Email test");
        	}
        	if (requestType.equals("Rule")) {
        		request.setType(2);
        		int userId = Integer.parseInt(req.getParameter("leaveReceiveUser"));
            	User receiveUser = masterService.getUserById(userId);
            	sendMail(receiveUser.getEmail(), "Email thử nghiệm", "Email test");
        	}
        	if (requestType.equals("Announcement")) {
        		request.setType(3);
        		int userId = Integer.parseInt(req.getParameter("leaveReceiveUser"));
            	User receiveUser = masterService.getUserById(userId);
            	sendMail(receiveUser.getEmail(), "Email thử nghiệm", "Email test");
        	}
        	if (requestType.equals("Leave")) {
        		request.setType(4);
        		String leaveContent = req.getParameter("leaveContent");
        		String leaveTitle = req.getParameter("leaveTitle");
        		Date leaveStartDay = formater.parse(req.getParameter("leaveStartDay"));
        		Date leaveEndDay = formater.parse(req.getParameter("leaveEndDay"));
        		String leaveLabel = req.getParameter("leaveLabel");
        		
        		request.setLastmodified(today);
        		request.setContent(leaveContent);
        		request.setTitle(leaveTitle);
        		request.setStatus("Updated");
        		request.setReadstatus(1);
        		
//        		set label
        		
        		request.setStartdate(leaveStartDay);
        		request.setEndate(leaveEndDay);
        		int userCd = Integer.parseInt(req.getParameter("leaveReceiveUser"));
            	User receiveUser = masterService.getUserById(userCd);
            	request.setManagerId(receiveUser);
            	request.setManagerAccount(receiveUser.getUsername());
            	request.setManagerName(receiveUser.getUsername());
            	
            	
            	
            	String emailContent = "Đơn xin nghỉ việc của " + 
            							leaveCreate + 
            							 "<br>" + 
            							 "Xin nghỉ từ " + req.getParameter("leaveStartDay") + " đến " + req.getParameter("leaveEndDay") +
            							 "<br> " +
            							 "Lý do: " + leaveContent;
            	sendMail(receiveUser.getEmail(), leaveTitle, emailContent);
        	}
        	masterService.updateRequest(request);
        	request.setLastmodified(today);
        	return "redirect:detailRequest?id=" + request.getId();
    	}
    	else {
    		return "redirect:detailRequest?id=" + request.getId();
    	}
    	
    }
    
    @RequestMapping(value="detailRequest")
    public ModelAndView showDetailRequestPage(@RequestParam("id") int id) throws IllegalOrphanException, NonexistentEntityException, Exception {
    	Request request = masterService.getRequestById(id);
    	ModelAndView mav = new ModelAndView("detailRequest");
    	mav.addObject("request", request);
//    	kiem tra tai khoan dang nhap phai tai khoan duoc nhan request ko
//    	neu phai
    	if (request.getReadstatus() == 1) {
    		request.setReadstatus(2);
    		masterService.updateRequest(request);
    	}
    	
//    	kiem tra tai khoan dang nhap phai tai khoan tao request ko
//    	neu phai
    	if (request.getReadstatus() == 3) {
    		request.setReadstatus(4);
    		masterService.updateRequest(request);
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
    	Request request = masterService.getRequestById(id);
    	request.setStatus("Approved");
    	request.setLastmodified(today);
    	request.setReadstatus(3);
    	
//    	Bo sung them thong tin sau
//    	request.setLastmodifiedbyAccount(lastmodifiedbyAccount);
//    	request.setLastmodifiedbyId(lastmodifiedbyId);
//    	request.setLastmodifiedbyName(lastmodifiedbyName);
    	
    	masterService.updateRequest(request);
    	
    	return "redirect:detailRequest?id=" + id;
    }
    
    /**
     * Process when rejecte Request
     **/
    @RequestMapping(value="rejectRequest")
    public String rejectRequest(HttpServletRequest req) throws IllegalOrphanException, NonexistentEntityException, Exception {
    	SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
    	Date today = new Date();
    	int id = Integer.parseInt(req.getParameter("requestId"));
    	String reasonReject = req.getParameter("rejectContent");
    	
//    	Lay thong tin tai khoan dang nhap
//    	Kiem tra tai khoan dang nhap phai tai khoan duoc yeu cau khong
//    	Neu khong phai -> quay lai trang home -> hien thong bao "Ban khong co quyen nay"
    	
//    	Neu phai
    	Request request = masterService.getRequestById(id);
    	request.setStatus("Rejected");
    	request.setReadstatus(3);
    	
//    	luu lý do reject
    	String fullReasonReject = "Ten tai khoan comment" + formater.format(today) + reasonReject;
    	request.setComment(fullReasonReject);
    	
    	request.setLastmodified(today);
    	
//    	Bo sung them thong tin sau
//    	request.setLastmodifiedbyAccount(lastmodifiedbyAccount);
//    	request.setLastmodifiedbyId(lastmodifiedbyId);
//    	request.setLastmodifiedbyName(lastmodifiedbyName);
    	
    	masterService.updateRequest(request);
    	
    	return "redirect:detailRequest?id=" + id;
    }
}
