package mks.dms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import mks.dms.dao.entity.Department;
import mks.dms.util.AppCons;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description Contains request model, which work on view
 * @author ThachLe
 */
public class RequestModel implements Serializable {
    /**  . */
    private Integer requestId;
    
    /** Default type: Task. */
    private String requestTypeCd = AppCons.TASK;

    /* Properties of request */
    private String title;
    
    private String content;
    
    /** Account of assignee . */
    private String assigneeAccount;
    
    /** Account of manager . */
    private String managerAccount;
    
    private Date startDate;
    
    private Date endDate;
    
    /** List accounts of watchers . */
    private List<String> listWatcher;
    
    private List<String> listLabel;
    
    private Integer duration;
    /** Code of unit . */
    private Integer durationUnit;
    
    // For Announcement, Rul
    private String departmentCd;

    // 
    /* List codes of Department */
    private List<Department> listDepartment;
    
    private List<MultipartFile> attachments;
    // For display
    private String filename1;
    
    /**
     * Get value of requestId.
     * @return the requestId
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Set the value for requestId.
     * @param requestId the requestId to set
     */
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    /**
     * Get value of requestTypeCd.
     * @return the requestTypeCd
     */
    public String getRequestTypeCd() {
        return requestTypeCd;
    }

    /**
     * Set the value for requestTypeCd.
     * @param requestTypeCd the requestTypeCd to set
     */
    public void setRequestTypeCd(String requestTypeCd) {
        this.requestTypeCd = requestTypeCd;
    }

    /**
     * Get value of title.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value for title.
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get value of content.
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Set the value for content.
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Get value of assigneeAccount.
     * @return the assigneeAccount
     */
    public String getAssigneeAccount() {
        return assigneeAccount;
    }

    /**
     * Set the value for assigneeAccount.
     * @param assigneeAccount the assigneeAccount to set
     */
    public void setAssigneeAccount(String assigneeAccount) {
        this.assigneeAccount = assigneeAccount;
    }

    /**
     * Get value of managerAccount.
     * @return the managerAccount
     */
    public String getManagerAccount() {
        return managerAccount;
    }

    /**
     * Set the value for managerAccount.
     * @param managerAccount the managerAccount to set
     */
    public void setManagerAccount(String managerAccount) {
        this.managerAccount = managerAccount;
    }

    /**
     * Get value of startDate.
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Set the value for startDate.
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Get value of endDate.
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Set the value for endDate.
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Get value of listWatcher.
     * @return the listWatcher
     */
    public List<String> getListWatcher() {
        return listWatcher;
    }

    /**
     * Set the value for listWatcher.
     * @param listWatcher the listWatcher to set
     */
    public void setListWatcher(List<String> listWatcher) {
        this.listWatcher = listWatcher;
    }

    /**
     * Get value of listLabel.
     * @return the listLabel
     */
    public List<String> getListLabel() {
        return listLabel;
    }

    /**
     * Set the value for listLabel.
     * @param listLabel the listLabel to set
     */
    public void setListLabel(List<String> listLabel) {
        this.listLabel = listLabel;
    }

    /**
     * Get value of duration.
     * @return the duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Set the value for duration.
     * @param duration the duration to set
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * Get value of durationUnit.
     * @return the durationUnit
     */
    public Integer getDurationUnit() {
        return durationUnit;
    }

    /**
     * Set the value for durationUnit.
     * @param durationUnit the durationUnit to set
     */
    public void setDurationUnit(Integer durationUnit) {
        this.durationUnit = durationUnit;
    }

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
     * Get value of filename1.
     * @return the filename1
     */
    public String getFilename1() {
        return filename1;
    }

    /**
     * Set the value for filename1.
     * @param filename1 the filename1 to set
     */
    public void setFilename1(String filename1) {
        this.filename1 = filename1;
    }

    /**
     * Get value of departmentCd.
     * @return the departmentCd
     */
    public String getDepartmentCd() {
        return departmentCd;
    }

    /**
     * Set the value for departmentCd.
     * @param departmentCd the departmentCd to set
     */
    public void setDepartmentCd(String departmentCd) {
        this.departmentCd = departmentCd;
    }

}
