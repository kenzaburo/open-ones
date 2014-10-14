package mks.dms.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import ldap.entry.Entry;
import ldap.util.LdapService;
import mks.dms.model.LdapAccountModel;
import mks.dms.service.MasterService;
import mks.dms.util.AppCons;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ThachLe
 */
@Controller
public class LdapController {
    private static final Logger LOG = Logger.getLogger(MasterController.class);
    private LdapService ldapService = LdapService.getInstance();
    
    @RequestMapping(value = "load-account-ldap", method = RequestMethod.GET)
    public ModelAndView goLoadAccountLdap(Principal principal) {
        ModelAndView mav = new ModelAndView("load-account-ldap");

        // Debug
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken authenToken = (UsernamePasswordAuthenticationToken) principal;
        }
        
        return mav;
    }
    
    @RequestMapping(value = "/ldap.getNodeRoot", method = RequestMethod.GET)
    public @ResponseBody String returnNode(@RequestParam("id") String ou) {
        String json = ldapService.getJsonLdap(ou);
        return json;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "processLoadAccountLdap")
    @ResponseBody
    public String processLoadAccountLdap(@RequestBody LdapAccountModel request, Principal principal) {
        List<Object[]> data = request.getData();
        String groupDn = request.getGroupDn();

        MasterService masterService = new MasterService();
        Map<String, MasterService.ImportResult> mapResult = masterService.importUser(groupDn, data, principal.getName());
        LOG.debug("groupDN=" +groupDn);

        return "{\"success\": true}";
    }
    
    /**
    * Provide array of department.
    * @return
    */
    @RequestMapping(value = "account.load", method = RequestMethod.GET)
    public @ResponseBody String loadAccount(@RequestParam("groupDn") String groupDn) {
        return getJsonAccounts(groupDn);
    }
    
    private String getJsonAccounts(String groupDn) {
        LdapAccountModel ldapAccountModel = new LdapAccountModel(); 
        List<Entry> lstUser = ldapService.findUsersByDN(groupDn);
        
        ldapAccountModel.setUsers(lstUser);

        String jsonData = ldapAccountModel.getJsonAccounts();

        LOG.debug("jsonData=" + jsonData);
        
        return jsonData;
    }
}
