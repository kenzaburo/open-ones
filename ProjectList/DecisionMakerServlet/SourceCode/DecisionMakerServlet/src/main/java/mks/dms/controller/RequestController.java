package mks.dms.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.RequestType;
import mks.dms.dao.entity.User;
import mks.dms.service.MasterService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import javax.servlet.http.HttpServletRequest;
/**
 * @author ThachLe
 *
 */
@Controller
public class RequestController {
	/**  */
	private static final Logger LOG = Logger.getLogger(RequestController.class);
	
	private final MasterService masterService;
	
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
	
    @Autowired
    public RequestController(MasterService masterService) {
        this.masterService = masterService;
    }
    
    @RequestMapping(value="createRequest" , method = RequestMethod.GET)
    public ModelAndView createTask(Model model){
        ModelAndView mav = new ModelAndView("createRequest");
        
        List<RequestType> lstRequestTypes = masterService.getRequestTypes();
        LOG.debug("lstRequestTypes=" + lstRequestTypes);
        mav.addObject("lstReqTypes", lstRequestTypes);
        List<User> listUsers = masterService.getAllUser();
        mav.addObject("listUsers", listUsers);
        mav.addObject("result", 0);
    	return mav;
    }
    
    @RequestMapping(value="createNewRequest")
    public ModelAndView createNewRequest(HttpServletRequest req) throws ParseException {
    	Date today = new Date();
    	Request request = new Request();
    	String requestType = req.getParameter("reqType");
    	SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		
    	if (requestType.equals("Task")) {
    		request.setType(1);
    		int userCd = Integer.parseInt(req.getParameter("leaveReceiveUser"));
        	User receiveUser = masterService.getUserByCd(userCd);
        	sendMail(receiveUser.getEmail(), "Email thử nghiệm", "Email test");
    	}
    	if (requestType.equals("Rule")) {
    		request.setType(2);
    		int userCd = Integer.parseInt(req.getParameter("leaveReceiveUser"));
        	User receiveUser = masterService.getUserByCd(userCd);
        	sendMail(receiveUser.getEmail(), "Email thử nghiệm", "Email test");
    	}
    	if (requestType.equals("Announcement")) {
    		request.setType(3);
    		int userCd = Integer.parseInt(req.getParameter("leaveReceiveUser"));
        	User receiveUser = masterService.getUserByCd(userCd);
        	sendMail(receiveUser.getEmail(), "Email thử nghiệm", "Email test");
    	}
    	if (requestType.equals("Leave")) {
    		request.setType(4);
    		String leaveContent = req.getParameter("leaveContent");
    		String leaveTitle = req.getParameter("leaveTitle");
    		Date leaveStartDay = formater.parse(req.getParameter("leaveStartDay"));
    		Date leaveEndDay = formater.parse(req.getParameter("leaveEndDay"));
    		String leaveLabel = req.getParameter("leaveLabel");
    		
    		System.out.println(req.getParameter("leaveStartDay"));
    		
//    		lấy thông tin từ account đăng nhập
//    		request.setCreatedbyAccount(createdbyAccount);
//    		request.setCreatedbyId(createdbyId);
//    		request.setCreatedbyName(createdbyName);
//    		request.setDepartmentsId(departmentsId);
    		
    		request.setCreated(today);
    		request.setContent(leaveContent);
    		request.setContent(leaveContent);
    		request.setTitle(leaveTitle);
    		
//    		set label
    		
    		request.setStartdate(leaveStartDay);
    		request.setEndate(leaveEndDay);
    		int userCd = Integer.parseInt(req.getParameter("leaveReceiveUser"));
        	User receiveUser = masterService.getUserByCd(userCd);
        	
        	masterService.createRequest(request);
        	
        	String emailContent = "Đơn xin nghỉ việc của " + 
//        							 createdbyName + 
        							 "<br>" + 
        							 "Xin nghỉ từ " + req.getParameter("leaveStartDay") + " đến " + req.getParameter("leaveEndDay") +
        							 "<br> " +
        							 "Lý do: " + leaveContent;
//        	sendMail(receiveUser.getEmail(), leaveTitle, emailContent);
    	}
    	
    	ModelAndView mav = new ModelAndView("createRequest");
    	List<RequestType> lstRequestTypes = masterService.getRequestTypes();
        LOG.debug("lstRequestTypes=" + lstRequestTypes);
        mav.addObject("lstReqTypes", lstRequestTypes);
        List<User> listUsers = masterService.getAllUser();
        mav.addObject("listUsers", listUsers);
        mav.addObject("result", 1);
    	return mav;
    }
    
    @RequestMapping(value="testsendemail")
    public String sendEmail() {
    	sendMail("truongtho88.nl@gmail.com", "Email thử nghiệm", "Email test");
    	return "home";
    }
}
