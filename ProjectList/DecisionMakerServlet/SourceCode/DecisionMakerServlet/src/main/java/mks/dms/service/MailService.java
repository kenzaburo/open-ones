package mks.dms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;

import ldap.entry.UserEntry;
import mks.dms.dao.controller.ExParameterJpaController;
import mks.dms.dao.entity.Parameter;
import mks.dms.util.AppCons;
import openones.mail.MailInfo;
import openones.mail.MailServerInfo;
import openones.mail.MailUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocky.common.CommonUtil;
import rocky.common.Constant;
import rocky.common.FileUtil;

import com.novell.ldap.util.Base64;

@Service
public class MailService extends BaseService {

	private static final String TEMPLATE_PATH = "/html-mail-template.html";
	private static Logger LOG = Logger.getLogger(MailService.class);
	
	
	private MailServerInfo mailServerInfo;
	
	@Autowired
	public MailService(MailServerInfo mailServerInfo) {
	    this.mailServerInfo = mailServerInfo;
	}
	

	/**
	* [Give the description for method].
	* Step 1: Create a record of reset link into database
	* Step 2: Send email to the user 
	* @param user
	* @return
	*/
	public boolean sendMailReset(UserEntry user) {
        boolean result = false;
        String content = FileUtil.getContent(TEMPLATE_PATH, true, Constant.DEF_ENCODE);
        MailInfo mail = new MailInfo();
        // variable path
        Map<String, Object> varMap = new HashMap<String, Object>();
        varMap.put("uid", user.getUid());
        varMap.put("to", user.getEmail());
        varMap.put("password", user.getPassword());
        varMap.put("from", "MeKongSolution");

        EntityManager em = BaseService.getEmf().createEntityManager();
        try {
            em.getTransaction().begin();

            // Save parameter
            /*
             * CD = "ResetPasswd" NAME = account VALUE = yyyy-MM-dd HH:mm:ss
             */
            Parameter param = new Parameter();
            param.setCd(AppCons.RESET_PASSWORD);
            param.setName(user.getEmail());

            Date curDate = new Date();
            String strDate = CommonUtil.formatDate(curDate, AppCons.DATETIME_FORMAT);
            param.setValue(strDate);

            param.setCreated(curDate);
            param.setCreatedbyUsername(user.getUid());
            param.setEnabled(true);
            
            // Set Random key
            Random random = new Random();
            param.setDescription(String.valueOf(random.nextLong()));

            String encodeValue = encode(param.getValue());

            // Key 1: current date
            // Key 2:
            String randomNumber = encode(param.getDescription());
            String resetPasswordLink = getResetPasswordLink() + "?email=" + user.getEmail() + "&key1=" + encodeValue
                    + "&key2=" + randomNumber;
            
            // Step 1: Save the parameter into database
            em.persist(param);
            
            
            varMap.put("resetPasswordLink", resetPasswordLink);

            // Content after replaced values of variables
            LOG.debug("content before replace=: " + content);
            content = CommonUtil.formatPattern(content, varMap);
            LOG.debug("content after replace=: " + content);

            ArrayList<String> list = new ArrayList<String>();
            list.add(user.getEmail());
            mail.setMailTo(list);
            
            mail.setMailFrom(getResetEmailFromAddress());
            
            mail.setMailSubject(getResetEmailSubject());

            mail.setMailBody(content);
            
            // Step 2: Email sending
            MailUtil.sendSimpleHtmlEmail(mailServerInfo, mail);

            em.getTransaction().commit();
            
            result = true;

        } catch (MessagingException ex) {
            em.getTransaction().rollback();
            LOG.error("Error!. Can't send", ex);
        } finally {
            em.close();
        }

		return result;
	}

    /**
    * Encode value of parameter by Base64 encoder.
    * @param param
    * @return
    */
    private String encode(String text) {
        return Base64.encode(text);
    }

    private String getResetPasswordLink() {
        String resetPasswordLink;
        ExParameterJpaController paramDaoCtrl = new ExParameterJpaController(BaseService.getEmf());
        
        boolean isEnable = true;
        resetPasswordLink = paramDaoCtrl.findParameterByName(AppCons.PARAM_EMAIL, AppCons.PARAM_RESET_PASSWORD_LINK, isEnable);
        
        return resetPasswordLink;
    }
    
    private String getResetEmailSubject() {
        String resetPasswordLink;
        ExParameterJpaController paramDaoCtrl = new ExParameterJpaController(BaseService.getEmf());
        
        boolean isEnable = true;
        resetPasswordLink = paramDaoCtrl.findParameterByName(AppCons.PARAM_EMAIL, AppCons.PARAM_RESET_PASSWORD_SUBJECT, isEnable);
        
        return resetPasswordLink;
    }
    

    private String getResetEmailFromAddress() {
        String resetPasswordFromName;
        ExParameterJpaController paramDaoCtrl = new ExParameterJpaController(BaseService.getEmf());
        
        boolean isEnable = true;
        resetPasswordFromName = paramDaoCtrl.findParameterByName(AppCons.PARAM_EMAIL, AppCons.PARAM_RESET_PASSWORD_FROM_ADDR, isEnable);
        
        return resetPasswordFromName;
    }
	
}
