/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mks.dms.dao.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ThachLe
 */
@Entity
@Table(name = "request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r"),
    @NamedQuery(name = "Request.findById", query = "SELECT r FROM Request r WHERE r.id = :id"),
    @NamedQuery(name = "Request.findByRequesttypeCd", query = "SELECT r FROM Request r WHERE r.requesttypeCd = :requesttypeCd"),
    @NamedQuery(name = "Request.findByRequesttypeName", query = "SELECT r FROM Request r WHERE r.requesttypeName = :requesttypeName"),
    @NamedQuery(name = "Request.findByTitle", query = "SELECT r FROM Request r WHERE r.title = :title"),
    @NamedQuery(name = "Request.findByStartdate", query = "SELECT r FROM Request r WHERE r.startdate = :startdate"),
    @NamedQuery(name = "Request.findByEnddate", query = "SELECT r FROM Request r WHERE r.enddate = :enddate"),
    @NamedQuery(name = "Request.findByAssigneeUsername", query = "SELECT r FROM Request r WHERE r.assigneeUsername = :assigneeUsername"),
    @NamedQuery(name = "Request.findByAssigneeName", query = "SELECT r FROM Request r WHERE r.assigneeName = :assigneeName"),
    @NamedQuery(name = "Request.findByManagerUsername", query = "SELECT r FROM Request r WHERE r.managerUsername = :managerUsername"),
    @NamedQuery(name = "Request.findByManagerName", query = "SELECT r FROM Request r WHERE r.managerName = :managerName"),
    @NamedQuery(name = "Request.findByLabel1", query = "SELECT r FROM Request r WHERE r.label1 = :label1"),
    @NamedQuery(name = "Request.findByLabel2", query = "SELECT r FROM Request r WHERE r.label2 = :label2"),
    @NamedQuery(name = "Request.findByLabel3", query = "SELECT r FROM Request r WHERE r.label3 = :label3"),
    @NamedQuery(name = "Request.findByDuration", query = "SELECT r FROM Request r WHERE r.duration = :duration"),
    @NamedQuery(name = "Request.findByDurationunit", query = "SELECT r FROM Request r WHERE r.durationunit = :durationunit"),
    @NamedQuery(name = "Request.findByDepartmentCd", query = "SELECT r FROM Request r WHERE r.departmentCd = :departmentCd"),
    @NamedQuery(name = "Request.findByDepartmentName", query = "SELECT r FROM Request r WHERE r.departmentName = :departmentName"),
    @NamedQuery(name = "Request.findByStatus", query = "SELECT r FROM Request r WHERE r.status = :status"),
    @NamedQuery(name = "Request.findByCreatorRead", query = "SELECT r FROM Request r WHERE r.creatorRead = :creatorRead"),
    @NamedQuery(name = "Request.findByManagerRead", query = "SELECT r FROM Request r WHERE r.managerRead = :managerRead"),
    @NamedQuery(name = "Request.findByAssignerRead", query = "SELECT r FROM Request r WHERE r.assignerRead = :assignerRead"),
    @NamedQuery(name = "Request.findByPlaneffort", query = "SELECT r FROM Request r WHERE r.planeffort = :planeffort"),
    @NamedQuery(name = "Request.findByPlanunit", query = "SELECT r FROM Request r WHERE r.planunit = :planunit"),
    @NamedQuery(name = "Request.findByFilename1", query = "SELECT r FROM Request r WHERE r.filename1 = :filename1"),
    @NamedQuery(name = "Request.findByFilename2", query = "SELECT r FROM Request r WHERE r.filename2 = :filename2"),
    @NamedQuery(name = "Request.findByFilename3", query = "SELECT r FROM Request r WHERE r.filename3 = :filename3"),
    @NamedQuery(name = "Request.findByCreated", query = "SELECT r FROM Request r WHERE r.created = :created"),
    @NamedQuery(name = "Request.findByCreatedbyUsername", query = "SELECT r FROM Request r WHERE r.createdbyUsername = :createdbyUsername"),
    @NamedQuery(name = "Request.findByLastmodified", query = "SELECT r FROM Request r WHERE r.lastmodified = :lastmodified"),
    @NamedQuery(name = "Request.findByLastmodifiedbyUsername", query = "SELECT r FROM Request r WHERE r.lastmodifiedbyUsername = :lastmodifiedbyUsername")})
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "REQUESTTYPE_CD")
    private String requesttypeCd;
    @Column(name = "REQUESTTYPE_NAME")
    private String requesttypeName;
    @Basic(optional = false)
    @Column(name = "TITLE")
    private String title;
    @Lob
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startdate;
    @Column(name = "ENDDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enddate;
    @Column(name = "ASSIGNEE_USERNAME")
    private String assigneeUsername;
    @Column(name = "ASSIGNEE_NAME")
    private String assigneeName;
    @Lob
    @Column(name = "ASSIGNEE_NOTE")
    private String assigneeNote;
    @Column(name = "MANAGER_USERNAME")
    private String managerUsername;
    @Column(name = "MANAGER_NAME")
    private String managerName;
    @Column(name = "LABEL1")
    private String label1;
    @Column(name = "LABEL2")
    private String label2;
    @Column(name = "LABEL3")
    private String label3;
    @Column(name = "DURATION")
    private Integer duration;
    @Column(name = "DURATIONUNIT")
    private Integer durationunit;
    @Column(name = "DEPARTMENT_CD")
    private String departmentCd;
    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "CREATOR_READ")
    private Integer creatorRead;
    @Column(name = "MANAGER_READ")
    private Integer managerRead;
    @Column(name = "ASSIGNER_READ")
    private Integer assignerRead;
    @Column(name = "PLANEFFORT")
    private Integer planeffort;
    @Column(name = "PLANUNIT")
    private String planunit;
    @Lob
    @Column(name = "ATTACHMENT1")
    private byte[] attachment1;
    @Column(name = "FILENAME1")
    private String filename1;
    @Lob
    @Column(name = "ATTACHMENT2")
    private byte[] attachment2;
    @Column(name = "FILENAME2")
    private String filename2;
    @Lob
    @Column(name = "ATTACHMENT3")
    private byte[] attachment3;
    @Column(name = "FILENAME3")
    private String filename3;
    @Lob
    @Column(name = "LIKES")
    private String likes;
    @Lob
    @Column(name = "FOR_USER")
    private String forUser;
    @Basic(optional = false)
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = "CREATEDBY_USERNAME")
    private String createdbyUsername;
    @Column(name = "LASTMODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;
    @Column(name = "LASTMODIFIEDBY_USERNAME")
    private String lastmodifiedbyUsername;

    public Request() {
    }

    public Request(Integer id) {
        this.id = id;
    }

    public Request(Integer id, String title, Date created, String createdbyUsername) {
        this.id = id;
        this.title = title;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequesttypeCd() {
        return requesttypeCd;
    }

    public void setRequesttypeCd(String requesttypeCd) {
        this.requesttypeCd = requesttypeCd;
    }

    public String getRequesttypeName() {
        return requesttypeName;
    }

    public void setRequesttypeName(String requesttypeName) {
        this.requesttypeName = requesttypeName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getAssigneeUsername() {
        return assigneeUsername;
    }

    public void setAssigneeUsername(String assigneeUsername) {
        this.assigneeUsername = assigneeUsername;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getAssigneeNote() {
        return assigneeNote;
    }

    public void setAssigneeNote(String assigneeNote) {
        this.assigneeNote = assigneeNote;
    }

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    public String getLabel3() {
        return label3;
    }

    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDurationunit() {
        return durationunit;
    }

    public void setDurationunit(Integer durationunit) {
        this.durationunit = durationunit;
    }

    public String getDepartmentCd() {
        return departmentCd;
    }

    public void setDepartmentCd(String departmentCd) {
        this.departmentCd = departmentCd;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCreatorRead() {
        return creatorRead;
    }

    public void setCreatorRead(Integer creatorRead) {
        this.creatorRead = creatorRead;
    }

    public Integer getManagerRead() {
        return managerRead;
    }

    public void setManagerRead(Integer managerRead) {
        this.managerRead = managerRead;
    }

    public Integer getAssignerRead() {
        return assignerRead;
    }

    public void setAssignerRead(Integer assignerRead) {
        this.assignerRead = assignerRead;
    }

    public Integer getPlaneffort() {
        return planeffort;
    }

    public void setPlaneffort(Integer planeffort) {
        this.planeffort = planeffort;
    }

    public String getPlanunit() {
        return planunit;
    }

    public void setPlanunit(String planunit) {
        this.planunit = planunit;
    }

    public byte[] getAttachment1() {
        return attachment1;
    }

    public void setAttachment1(byte[] attachment1) {
        this.attachment1 = attachment1;
    }

    public String getFilename1() {
        return filename1;
    }

    public void setFilename1(String filename1) {
        this.filename1 = filename1;
    }

    public byte[] getAttachment2() {
        return attachment2;
    }

    public void setAttachment2(byte[] attachment2) {
        this.attachment2 = attachment2;
    }

    public String getFilename2() {
        return filename2;
    }

    public void setFilename2(String filename2) {
        this.filename2 = filename2;
    }

    public byte[] getAttachment3() {
        return attachment3;
    }

    public void setAttachment3(byte[] attachment3) {
        this.attachment3 = attachment3;
    }

    public String getFilename3() {
        return filename3;
    }

    public void setFilename3(String filename3) {
        this.filename3 = filename3;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getForUser() {
        return forUser;
    }

    public void setForUser(String forUser) {
        this.forUser = forUser;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedbyUsername() {
        return createdbyUsername;
    }

    public void setCreatedbyUsername(String createdbyUsername) {
        this.createdbyUsername = createdbyUsername;
    }

    public Date getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    public String getLastmodifiedbyUsername() {
        return lastmodifiedbyUsername;
    }

    public void setLastmodifiedbyUsername(String lastmodifiedbyUsername) {
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mks.dms.dao.entity.Request[ id=" + id + " ]";
    }

    //////////////////////////////////////////////////////////////////////
    // Below method are coded manually
    
    /**
    * Set array of username into member "likes".
    * member "likes" contains list of username with separator is a space
    *
    * @param arrLikes
    */
    public void setLikes(String[] arrLikes) {
        StringBuffer sb = new StringBuffer();
        int len = (arrLikes != null) ? arrLikes.length : 0;
        
        if (len > 0) {
            sb.append(arrLikes[0]);
            
            for (int i = 1; i < len; i ++) {
                sb.append(" ").append(arrLikes[i]);
            }
        }
        
        this.likes = sb.toString();
    }

    public String[] getListLikes() {
        if (this.likes != null) {
            return likes.split(" ");
        }
        
        return null;
    }
    
    public void addLike(String username) {
        if ((username != null) && (!username.isEmpty())) {
            this.likes += (" " + username);
        }
    }
}
