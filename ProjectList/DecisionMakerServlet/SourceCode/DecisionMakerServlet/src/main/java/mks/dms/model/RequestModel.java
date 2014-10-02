package mks.dms.model;

import java.io.Serializable;
import java.util.List;

import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.Request;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description Contains request model, which work on view
 * @author ThachLe
 */
public class RequestModel implements Serializable {
    private Request request;
    
    /** List accounts of watchers . */
//    private List<String> listWatcher;
    
//    private List<String> listLabel;

    // 
    /* List codes of Department */
    private List<Department> listDepartment;
    
    private List<MultipartFile> attachments;
    
    /** Name of duration unit. For display only . */
    private String durationUnitName;
    
//    /**
//     * Get value of listLabel.
//     * @return the listLabel
//     */
//    public List<String> getListLabel() {
//        return listLabel;
//    }
//
//    /**
//     * Set the value for listLabel.
//     * @param listLabel the listLabel to set
//     */
//    public void setListLabel(List<String> listLabel) {
//        this.listLabel = listLabel;
//    }

    /**
     * Get value of listDepartment.
     * @return the listDepartment
     */
    public List<Department> getListDepartment() {
        return listDepartment;
    }

    /**
     * Set the value for listDepartment.
     * @param listDepartment the listDepartment to set
     */
    public void setListDepartment(List<Department> listDepartment) {
        this.listDepartment = listDepartment;
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


    /**
     * Get value of request.
     * @return the request
     */
    public Request getRequest() {
        if (request == null) {
            request = new Request();
        } else {
            // Do nothing
        }

        return request;
    }

    /**
     * Set the value for request.
     * @param request the request to set
     */
    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * Get value of durationUnitName.
     * @return the durationUnitName
     */
    public String getDurationUnitName() {
        return durationUnitName;
    }

    /**
     * Set the value for durationUnitName.
     * @param durationUnitName the durationUnitName to set
     */
    public void setDurationUnitName(String durationUnitName) {
        this.durationUnitName = durationUnitName;
    }

}
