package mks.dms.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import mks.dms.model.datatable.DepartmentModel;
import mks.dms.model.datatable.ParameterModel;
import mks.dms.model.datatable.RequestTypeModel;
import mks.dms.model.datatable.StatusFlowModel;
import mks.dms.model.datatable.SystemUserModel;
import mks.dms.service.MasterService;
import mks.dms.service.SystemService;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

/**
 * @author ThachLe
 */
@Controller
public class InitDataController {
    /**  */
    private static final Logger LOG = Logger.getLogger(InitDataController.class);

    private final SystemService systemService;
    private final MasterService masterService;

    @Autowired
    public InitDataController(SystemService requestService, MasterService masterService) {
        this.systemService = requestService;
        this.masterService = masterService;
    }

    @RequestMapping(value = "init-data", method = RequestMethod.GET)
    public ModelAndView goInitData(Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("init-data");

        return mav;
    }
    
//    @RequestMapping(value = "initData", method = RequestMethod.POST)
//    public ModelAndView initData(Model model, Principal principal) {
//        ModelAndView mav = new ModelAndView("init-data");
//
//        boolean initResult = systemService.initData(principal.getName());
//
//        mav.addObject("result", initResult);
//
//        ExRequestTypeJpaController reqTypeDaoCtrl = new ExRequestTypeJpaController(BaseService.getEmf());
//        List<RequestType> lstRequestType = reqTypeDaoCtrl.findRequestTypeEntities();
//        mav.addObject("lstRequestType", lstRequestType);
//
//        return mav;
//    }
    
    /**
    * Provide array data of Request types.
    * <br/>
    * It provides data for handsontable
    * @see init-data.jsp
    * @return
    */
    @RequestMapping(value = "load-request-type", method = RequestMethod.GET)
    public @ResponseBody String loadRequestTypes() {
        return MasterService.getJsonRequestTypes();
    }

//    @RequestMapping(value = "saveAllRequestType", method = RequestMethod.GET)
//    @ResponseBody
//    public String saveAllRequestTypeGET(@RequestBody RequestTypeModel requestTypeModel, Principal principal) throws JSONException {
//        return saveAllRequestType(requestTypeModel, principal);
//    }
    
    @RequestMapping(method = RequestMethod.POST, value = "saveAllRequestType")
    @ResponseBody
    public String saveAllRequestType(@RequestBody RequestTypeModel requestTypeModel, Principal princial) {
    	JsonObject jso = new JsonObject();
        try {
            requestTypeModel = masterService.saveAllRequestType(requestTypeModel, princial.getName());
            String jsonRefreshDepartment = requestTypeModel.getJsonData();
            LOG.debug("jsonRefreshDepartment=" + jsonRefreshDepartment);
            
            jso.addProperty("success", "true");
            jso.addProperty("data", "Test");
        } catch (Exception ex) {
            LOG.error("Could not save the request types", ex);
            jso.addProperty("error", ex.getMessage());
        }
        return jso.toString();
    }
    
    @RequestMapping(value = "testAjax", method = RequestMethod.POST)
    @ResponseBody
    public String testAjax(HttpServletRequest request, Principal principal) throws JSONException, IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json1 = "";
	        if (br != null) {
	            json1 = br.readLine();
	        }
	    JSONObject jsonObject = new JSONObject(json1);
	    String first = (String) jsonObject.get("first");
	    String second = (String) jsonObject.get("second");
    	JSONObject json = new JSONObject();
    	json.put("result", first + second);
    	return json.toString();
    }
    
    /**
    * Provide array of department.
    * @return
    */
    @RequestMapping(value = "load-department", method = RequestMethod.GET)
    public @ResponseBody String loadDepartment() {
        return MasterService.getJsonDepartments();
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveAllDepartment")
    @ResponseBody
    public String saveAllDepartment(@RequestBody DepartmentModel departmentModel, Principal princial) {
        JsonObject jso = new JsonObject();
        try {
            departmentModel = masterService.saveAllDepartment(departmentModel, princial.getName());
            String jsonRefreshDepartment = departmentModel.getJsonData();
            LOG.debug("jsonRefreshDepartment=" + jsonRefreshDepartment);
            
            jso.addProperty("success", "true");
            jso.addProperty("data", "Test");
        } catch (Exception ex) {
            LOG.error("Could not save the request types", ex);
            jso.addProperty("error", ex.getMessage());
        }
        return jso.toString();
    }

    @RequestMapping(value = "load-system-user", method = RequestMethod.GET)
    public @ResponseBody String loadSystemUser() {
        return MasterService.getJsonSystemUser();
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveSystemUser")
    @ResponseBody
    public String saveSystemUser(@RequestBody SystemUserModel userUser, Principal princial) {
        JsonObject jso = new JsonObject();
        try {
            userUser = masterService.saveAllUser(userUser, princial.getName());
            String jsonSystemUser = userUser.getJsonData();
            LOG.debug("jsonSystemUser=" + jsonSystemUser);
            
            jso.addProperty("success", "true");
            jso.addProperty("data", "Test");
        } catch (Exception ex) {
            LOG.error("Could not save the request types", ex);
            jso.addProperty("error", ex.getMessage());
        }
        return jso.toString();
    }
    
    @RequestMapping(value = "load-status-flow-request", method = RequestMethod.GET)
    public @ResponseBody String loadStatusFlowRequest() {
        return MasterService.getJsonStatusFlowRequest();
    }
    
    
    @RequestMapping(method = RequestMethod.POST, value = "saveAllStatusFlowRequest")
    @ResponseBody
    public String saveAllStatusFlowRequest(@RequestBody StatusFlowModel statusFlowModel, Principal princial) {
        JsonObject jso = new JsonObject();
        try {
            statusFlowModel = masterService.saveAllStatusFlowRequest(statusFlowModel, princial.getName());
            String jsonRefreshStatusFlow = statusFlowModel.getJsonData();
            LOG.debug("jsonRefreshStatusFlow=" + jsonRefreshStatusFlow);
            
            jso.addProperty("success", "true");
            jso.addProperty("data", "Test");
        } catch (Exception ex) {
            LOG.error("Could not save the request types", ex);
            jso.addProperty("error", ex.getMessage());
        }
        return jso.toString();
    }
    
    
    @RequestMapping(value = "load-parameter", method = RequestMethod.GET)
    public @ResponseBody String loadParameter() {
        return MasterService.getJsonParameter();
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "saveAllParameter")
    @ResponseBody
    public String saveAllParameter(@RequestBody ParameterModel parameterModel, Principal princial) {
        JsonObject jso = new JsonObject();
        try {
            parameterModel = masterService.saveAllParameter(parameterModel, princial.getName());
            String jsonRefreshParameter = parameterModel.getJsonData();
            LOG.debug("jsonRefreshParameter=" + jsonRefreshParameter);
            
            jso.addProperty("success", "true");
            jso.addProperty("data", "Test");
        } catch (Exception ex) {
            LOG.error("Could not save the request types", ex);
            jso.addProperty("error", ex.getMessage());
        }
        return jso.toString();
    }
}
