package mks.dms.controller;

import java.security.Principal;

import ldap.entry.Entry;
import ldap.entry.UserEntry;
import ldap.util.LdapService;
import mks.dms.model.ChangePasswordModel;
import mks.dms.model.ConfirmResetPasswordModel;
import mks.dms.model.ResetPasswordModel;
import mks.dms.service.MailService;
import mks.dms.service.ParameterService;
import mks.dms.util.AppCons;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.web.servlet.ModelAndView;

import com.novell.ldap.util.Base64;

import rocky.common.CHARA;

/**
 * @author ThachLe
 */
@Controller
public class PasswordController {
    /**  */
    private static final Logger LOG = Logger.getLogger(PasswordController.class);

    private static final String LDAP_ERROR = "Error_in_authentication_server";

    private static final String WRONG_PASSWORD = "Wrong_password";

    private static final String ACCOUNT_DOES_NOT_EXIST = "Account_no_exist";

    private static final String EMAIL_NO_EXIST = "Email_no_exist";

    @Autowired
    @Qualifier("changePasswordValidator")
    private Validator changePasswdValidator;
    
    @Autowired
    @Qualifier("confirmResetPasswordValidator")
    private Validator confirmResetPasswdValidator;
    
    private MailService mailService;
    
    @Autowired
    public PasswordController(MailService mailService) {
        this.mailService = mailService;
    }

    /**
    * This method is called when binding the HTTP parameter to bean (or model).
    * @param binder
    */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // Refer: http://www.coderanch.com/t/524168/Spring/SessionAttributes-Validator-set-InitBinder
        
        if ((changePasswdValidator != null) && (binder.getTarget() != null) && (changePasswdValidator.supports(binder.getTarget().getClass()))) {
            binder.setValidator(this.changePasswdValidator);
        } else {
            LOG.warn("Could not set changePasswdValidator");
        }
        
        if ((confirmResetPasswdValidator != null) && (binder.getTarget() != null) && (confirmResetPasswdValidator.supports(binder.getTarget().getClass()))) {
            binder.addValidators(this.confirmResetPasswdValidator);
        } else {
            LOG.warn("Could not set confirmResetPasswdValidator");
        }
    }
    
    @RequestMapping(value = "change-password", method = RequestMethod.GET)
    public ModelAndView goChangePassword(Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("change-password");

        mav.addObject(AppCons.MODEL, new ChangePasswordModel());
        return mav;
    }
    
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public ModelAndView changePassword(@ModelAttribute(AppCons.MODEL) @Validated ChangePasswordModel model, BindingResult bindingResult, Principal principal) {
        ModelAndView mav = new ModelAndView("change-password");
        
        if (bindingResult.hasErrors()) {
            LOG.debug("Binding result; hasError=" + bindingResult.hasErrors());
            return mav;
        }
        
        String username = principal.getName();
        String password = model.getOldPassword();
        String newpwd = model.getNewPassword();
        
        LdapService ldapService = new LdapService();
        Entry entry = ldapService.findUserByUid(username);
        
        boolean changedPassOK = false;
        String errorCode = null;
        
        if (entry != null) {
            if (ldapService.checkPassword(username, password)) {
                String userDN = entry.getDn();
                changedPassOK = ldapService.changePass(userDN, newpwd);
                
                if (changedPassOK) {
                    // No nothing
                } else {
                    errorCode = LDAP_ERROR;
                }
            } else {
                errorCode = WRONG_PASSWORD;
                // changedPassOK = false;
            }
        } else {
            errorCode = ACCOUNT_DOES_NOT_EXIST;
            // changedPassOK = false;
        }
        
        model.setNewPassword(CHARA.BLANK);
        model.setConfirmNewPassword(CHARA.BLANK);
        mav.addObject("result", changedPassOK);
        mav.addObject("errorCode", errorCode);

        return mav;
    }

    @RequestMapping(value = "reset-password", method = RequestMethod.GET)
    public ModelAndView goResetPassword(Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("reset-password");
        
        mav.addObject(AppCons.MODEL, new ResetPasswordModel());

        return mav;
    }
    
    /**
    * Processing when user click hyperlink reset password from email.
    * @param model
    * @param bindingResult
    * @param principal
    * @return
    */
    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    public ModelAndView resetPassword(@ModelAttribute(AppCons.MODEL) ResetPasswordModel model, BindingResult bindingResult, Principal principal) {
        ModelAndView mav = new ModelAndView("reset-password");
        
        if (bindingResult.hasErrors()) {
            LOG.debug("Binding result; hasError=" + bindingResult.hasErrors());
            return mav;
        }
        
        String email = model.getEmail();
        
        LdapService ldapService = new LdapService();
        UserEntry userEntry = ldapService.findUserByEmail(email);

        if (userEntry == null) {
            // Could not found the user with email
            model.setResult(false);
            model.setErrorCode(EMAIL_NO_EXIST);
        } else {
            boolean resetPasswordSuccess = mailService.sendMailReset(userEntry);
            model.setResult(resetPasswordSuccess);
        }
        
        mav.addObject(AppCons.MODEL, model);

        return mav;
    }


    /**
    * [Give the description for method].
    * @param key1 encoded of date (format: yyyy-MM-dd HH:mm:ss)
    * @param key2 encoded of number
    * @return
    */
    @RequestMapping(value = "confirm-reset-password", method = RequestMethod.GET)
    public ModelAndView goConfirmResetPassword(@RequestParam("email") String email, @RequestParam("key1") String key1, @RequestParam("key2") String key2) {
        ModelAndView mav = new ModelAndView("confirm-reset-password");
        
        String decodedKey1 = new String(Base64.decode(key1));
        String decodedKey2 = new String(Base64.decode(key2));
        
        ParameterService parameterService = new ParameterService();
        boolean validKey = parameterService.checkKey(email, decodedKey1, decodedKey2);
        
        // Prepare initial data for screen set new password
        ConfirmResetPasswordModel model = new ConfirmResetPasswordModel();
        model.setEmail(email);
        model.setValidKey(validKey);
        
        mav.addObject(AppCons.MODEL, model);

        return mav;
    }
    
    /**
    * [Give the description for method].
    * @param model used email, newPassword, confirmNewPassword
    * @param bindingResult
    * @return
    */
    @RequestMapping(value = "confirmResetPassword", method = RequestMethod.POST)
    public ModelAndView confirmResetPassword(@ModelAttribute(AppCons.MODEL) @Validated ConfirmResetPasswordModel model, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView("confirm-reset-password");
        
        if (bindingResult.hasErrors()) {
            LOG.debug("Binding result; hasError=" + bindingResult.hasErrors());
            return mav;
        }
        
        String email = model.getEmail();
        String newPasswd = model.getNewPassword();
        
        LdapService ldapService = new LdapService();
        Entry entry = ldapService.findUserByEmail(email);
        
        boolean changedPassOK = false;
        String errorCode = null;
        
        if (entry != null) {
            String userDN = entry.getDn();
            changedPassOK = ldapService.changePass(userDN, newPasswd);
            
            if (changedPassOK) {
                // No nothing
            } else {
                errorCode = LDAP_ERROR;
            }
        } else {
            errorCode = ACCOUNT_DOES_NOT_EXIST;
            // changedPassOK = false;
        }
        
        model.setNewPassword(CHARA.BLANK);
        model.setConfirmNewPassword(CHARA.BLANK);
        mav.addObject("result", changedPassOK);
        mav.addObject("errorCode", errorCode);

        return mav;
    }
}
