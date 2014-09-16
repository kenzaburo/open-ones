package mks.dms.model;

import java.io.Serializable;
import java.util.List;

import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.Request;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description Contains request model, which work on view
 * @author TriLVH, ThachLe
 */
public class RequestCreateModel implements Serializable {
    /* List of current request type get on database */
//    private List<RequestType> listRequestType;
    /* List of current user from database */
    //private List<User> listUser;
    
    /* List of  Department from database*/
    private List<Department> listDepartment;

    private String labels;
    /* created request */
    private Request request;

    /* List user watch request */
    private Integer[] listWatcher;

    private List<MultipartFile> attachments;
//    public List<RequestType> getListRequestType() {
//        return listRequestType;
//    }
//
//    public void setListRequestType(List<RequestType> listRequestType) {
//        this.listRequestType = listRequestType;
//    }

//    public List<User> getListUser() {
//        return listUser;
//    }
//
//    public void setListUser(List<User> listUser) {
//        this.listUser = listUser;
//    }

    /**
     *  Create default request model.
     *  <br/>
     *  Default, the type is "Task"
     */
//    public RequestCreateModel() {
//        request = new Request();
//        request.setRequesttypeCd(AppCons.TASK);
//    }
    
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public List<Department> getListDepartment() {
        return listDepartment;
    }

    public void setListDepartment(List<Department> listDepartment) {
        this.listDepartment = listDepartment;
    }

    public Integer[] getListWatcher() {
        return listWatcher;
    }

    public void setListWatcher(Integer[] listWatcher) {
        this.listWatcher = listWatcher;
    }

    /**
     * Get value of labels.
     * @return the labels
     */
    public String getLabels() {
        return labels;
    }

    /**
     * Set the value for labels.
     * @param labels the labels to set
     */
    public void setLabels(String labels) {
        this.labels = labels;
    }

    /**
     * Get value of attachments.
     * @return the attachments
     */
    public List<MultipartFile> getAttachments() {
        return attachments;
    }

    /**
     * Set the value for attachments.
     * @param attachments the attachments to set
     */
    public void setAttachments(List<MultipartFile> attachments) {
        this.attachments = attachments;
    }
    
}
