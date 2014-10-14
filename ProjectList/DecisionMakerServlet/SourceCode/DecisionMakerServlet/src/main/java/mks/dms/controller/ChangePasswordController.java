package mks.dms.controller;

import java.security.Principal;

import ldap.entry.Entry;
import ldap.util.LdapService;
import mks.dms.model.ChangePasswordModel;
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
import org.springframework.web.servlet.ModelAndView;

import rocky.common.CHARA;

/**
 * @author ThachLe
 */
@Controller
public class ChangePasswordController {
    /**  */
    private static final Logger LOG = Logger.getLogger(ChangePasswordController.class);

    private static final String LDAP_ERROR = "Error_in_authentication_server";

    private static final String WRONG_PASSWORD = "Wrong_password";

    private static final String ACCOUNT_DOES_NOT_EXIST = "Account_no_exist";

    @Autowired
    @Qualifier("changePasswordValidator")
    private Validator validator;
    
    /**
    * This method is called when binding the HTTP parameter to bean (or model).
    * @param binder
    */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // Refer: http://www.coderanch.com/t/524168/Spring/SessionAttributes-Validator-set-InitBinder
        
        if ((validator != null) && (binder.getTarget() != null) && (validator.supports(binder.getTarget().getClass()))) {
            binder.setValidator(this.validator);
        } else {
            LOG.warn("Could not set validator");
        }
    }
    
    @RequestMapping(value = "change-password", method = RequestMethod.GET)
    public ModelAndView goChangePassword(Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("change-password");

        mav.addObject(AppCons.MODEL, new ChangePasswordModel());
        return mav;
    }
    
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public ModelAndView changePass(@ModelAttribute(AppCons.MODEL) @Validated ChangePasswordModel model, BindingResult bindingResult, Principal principal) {
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
        String desc = null;
        
        if (entry != null) {
            if (ldapService.checkPassword(username, password)) {
                String userDN = entry.getDn();
                changedPassOK = ldapService.changePass(userDN, newpwd);
                
                if (changedPassOK) {
                    // No nothing
                } else {
                    desc = LDAP_ERROR;
                }
            } else {
                desc = WRONG_PASSWORD;
                // changedPassOK = false;
            }
        } else {
            desc = ACCOUNT_DOES_NOT_EXIST;
            // changedPassOK = false;
        }
        
        model.setNewPassword(CHARA.BLANK);
        model.setConfirmNewPassword(CHARA.BLANK);
        mav.addObject("result", changedPassOK);
        mav.addObject("desc", desc);

        return mav;
    }
    
}
