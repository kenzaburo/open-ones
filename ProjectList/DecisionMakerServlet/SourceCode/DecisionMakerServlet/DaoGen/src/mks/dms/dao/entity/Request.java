/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mks.dms.dao.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    @NamedQuery(name = "Request.findByType", query = "SELECT r FROM Request r WHERE r.type = :type"),
    @NamedQuery(name = "Request.findByTitle", query = "SELECT r FROM Request r WHERE r.title = :title"),
    @NamedQuery(name = "Request.findByContent", query = "SELECT r FROM Request r WHERE r.content = :content"),
    @NamedQuery(name = "Request.findByStartdate", query = "SELECT r FROM Request r WHERE r.startdate = :startdate"),
    @NamedQuery(name = "Request.findByEndate", query = "SELECT r FROM Request r WHERE r.endate = :endate"),
    @NamedQuery(name = "Request.findByAssignedAccount", query = "SELECT r FROM Request r WHERE r.assignedAccount = :assignedAccount"),
    @NamedQuery(name = "Request.findByAssignedName", query = "SELECT r FROM Request r WHERE r.assignedName = :assignedName"),
    @NamedQuery(name = "Request.findByWatchersId", query = "SELECT r FROM Request r WHERE r.watchersId = :watchersId"),
    @NamedQuery(name = "Request.findByManagerAccount", query = "SELECT r FROM Request r WHERE r.managerAccount = :managerAccount"),
    @NamedQuery(name = "Request.findByManagerName", query = "SELECT r FROM Request r WHERE r.managerName = :managerName"),
    @NamedQuery(name = "Request.findByLabelsId", query = "SELECT r FROM Request r WHERE r.labelsId = :labelsId"),
    @NamedQuery(name = "Request.findByStatus", query = "SELECT r FROM Request r WHERE r.status = :status"),
    @NamedQuery(name = "Request.findByPlaneffort", query = "SELECT r FROM Request r WHERE r.planeffort = :planeffort"),
    @NamedQuery(name = "Request.findByPlanunit", query = "SELECT r FROM Request r WHERE r.planunit = :planunit"),
    @NamedQuery(name = "Request.findByCreated", query = "SELECT r FROM Request r WHERE r.created = :created"),
    @NamedQuery(name = "Request.findByCreatedbyAccount", query = "SELECT r FROM Request r WHERE r.createdbyAccount = :createdbyAccount"),
    @NamedQuery(name = "Request.findByCreatedbyName", query = "SELECT r FROM Request r WHERE r.createdbyName = :createdbyName"),
    @NamedQuery(name = "Request.findByLastmodified", query = "SELECT r FROM Request r WHERE r.lastmodified = :lastmodified"),
    @NamedQuery(name = "Request.findByLastmodifiedbyId", query = "SELECT r FROM Request r WHERE r.lastmodifiedbyId = :lastmodifiedbyId"),
    @NamedQuery(name = "Request.findByLastmodifiedbyName", query = "SELECT r FROM Request r WHERE r.lastmodifiedbyName = :lastmodifiedbyName"),
    @NamedQuery(name = "Request.findByLastmodifiedbyAccount", query = "SELECT r FROM Request r WHERE r.lastmodifiedbyAccount = :lastmodifiedbyAccount")})
public class Request implements Serializable {
    @Lob()
    @Column(name = "COMMENT")
    private String comment;
    @Lob
    @Column(name = "ATTACHMENT1")
    private byte[] attachment1;
    @Lob
    @Column(name = "ATTACHMENT2")
    private byte[] attachment2;
    @Lob
    @Column(name = "ATTACHMENT3")
    private byte[] attachment3;
    @Column(name = "READSTATUS")
    private Integer readstatus;
    @Column(name = "REQUESTTYPE_ID")
    private Integer requesttypeId;
    @Column(name = "REQUESTTYPE_CD")
    private String requesttypeCd;
    @Column(name = "REQUESTTYPE_NAME")
    private String requesttypeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requestId")
    private Collection<LabelRequest> labelRequestCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TYPE")
    private Integer type;
    @Basic(optional = false)
    @Column(name = "TITLE")
    private String title;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startdate;
    @Column(name = "ENDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endate;
    @Column(name = "ASSIGNED_ACCOUNT")
    private String assignedAccount;
    @Column(name = "ASSIGNED_NAME")
    private String assignedName;
    @Column(name = "WATCHERS_ID")
    private Integer watchersId;
    @Column(name = "MANAGER_ACCOUNT")
    private String managerAccount;
    @Column(name = "MANAGER_NAME")
    private String managerName;
    @Column(name = "LABELS_ID")
    private Integer labelsId;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "PLANEFFORT")
    private Integer planeffort;
    @Column(name = "PLANUNIT")
    private String planunit;
    @Basic(optional = false)
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "CREATEDBY_ACCOUNT")
    private String createdbyAccount;
    @Column(name = "CREATEDBY_NAME")
    private String createdbyName;
    @Column(name = "LASTMODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;
    @Column(name = "LASTMODIFIEDBY_ID")
    private Integer lastmodifiedbyId;
    @Column(name = "LASTMODIFIEDBY_NAME")
    private String lastmodifiedbyName;
    @Column(name = "LASTMODIFIEDBY_ACCOUNT")
    private String lastmodifiedbyAccount;
    @JoinColumn(name = "DEPARTMENTS_ID", referencedColumnName = "ID")
    @ManyToOne
    private Department departmentsId;
    @JoinColumn(name = "CREATEDBY_ID", referencedColumnName = "ID")
    @ManyToOne
    private User createdbyId;
    @JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
    @ManyToOne
    private User managerId;
    @JoinColumn(name = "ASSIGNED_ID", referencedColumnName = "ID")
    @ManyToOne
    private User assignedId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reqId")
    private Collection<Watcher> watcherCollection;

    public Request() {
    }

    public Request(Integer id) {
        this.id = id;
    }

    public Request(Integer id, String title, Date created) {
        this.id = id;
        this.title = title;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Date getEndate() {
        return endate;
    }

    public void setEndate(Date endate) {
        this.endate = endate;
    }

    public String getAssignedAccount() {
        return assignedAccount;
    }

    public void setAssignedAccount(String assignedAccount) {
        this.assignedAccount = assignedAccount;
    }

    public String getAssignedName() {
        return assignedName;
    }

    public void setAssignedName(String assignedName) {
        this.assignedName = assignedName;
    }

    public Integer getWatchersId() {
        return watchersId;
    }

    public void setWatchersId(Integer watchersId) {
        this.watchersId = watchersId;
    }

    public String getManagerAccount() {
        return managerAccount;
    }

    public void setManagerAccount(String managerAccount) {
        this.managerAccount = managerAccount;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Integer getLabelsId() {
        return labelsId;
    }

    public void setLabelsId(Integer labelsId) {
        this.labelsId = labelsId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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


    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedbyAccount() {
        return createdbyAccount;
    }

    public void setCreatedbyAccount(String createdbyAccount) {
        this.createdbyAccount = createdbyAccount;
    }

    public String getCreatedbyName() {
        return createdbyName;
    }

    public void setCreatedbyName(String createdbyName) {
        this.createdbyName = createdbyName;
    }

    public Date getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    public Integer getLastmodifiedbyId() {
        return lastmodifiedbyId;
    }

    public void setLastmodifiedbyId(Integer lastmodifiedbyId) {
        this.lastmodifiedbyId = lastmodifiedbyId;
    }

    public String getLastmodifiedbyName() {
        return lastmodifiedbyName;
    }

    public void setLastmodifiedbyName(String lastmodifiedbyName) {
        this.lastmodifiedbyName = lastmodifiedbyName;
    }

    public String getLastmodifiedbyAccount() {
        return lastmodifiedbyAccount;
    }

    public void setLastmodifiedbyAccount(String lastmodifiedbyAccount) {
        this.lastmodifiedbyAccount = lastmodifiedbyAccount;
    }

    public Department getDepartmentsId() {
        return departmentsId;
    }

    public void setDepartmentsId(Department departmentsId) {
        this.departmentsId = departmentsId;
    }

    public User getCreatedbyId() {
        return createdbyId;
    }

    public void setCreatedbyId(User createdbyId) {
        this.createdbyId = createdbyId;
    }

    public User getManagerId() {
        return managerId;
    }

    public void setManagerId(User managerId) {
        this.managerId = managerId;
    }

    public User getAssignedId() {
        return assignedId;
    }

    public void setAssignedId(User assignedId) {
        this.assignedId = assignedId;
    }

    @XmlTransient
    public Collection<Watcher> getWatcherCollection() {
        return watcherCollection;
    }

    public void setWatcherCollection(Collection<Watcher> watcherCollection) {
        this.watcherCollection = watcherCollection;
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


    @XmlTransient
    public Collection<LabelRequest> getLabelRequestCollection() {
        return labelRequestCollection;
    }

    public void setLabelRequestCollection(Collection<LabelRequest> labelRequestCollection) {
        this.labelRequestCollection = labelRequestCollection;
    }

    public Integer getRequesttypeId() {
        return requesttypeId;
    }

    public void setRequesttypeId(Integer requesttypeId) {
        this.requesttypeId = requesttypeId;
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

    public Integer getReadstatus() {
        return readstatus;
    }

    public void setReadstatus(Integer readstatus) {
        this.readstatus = readstatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public byte[] getAttachment1() {
        return attachment1;
    }

    public void setAttachment1(byte[] attachment1) {
        this.attachment1 = attachment1;
    }

    public byte[] getAttachment2() {
        return attachment2;
    }

    public void setAttachment2(byte[] attachment2) {
        this.attachment2 = attachment2;
    }

    public byte[] getAttachment3() {
        return attachment3;
    }

    public void setAttachment3(byte[] attachment3) {
        this.attachment3 = attachment3;
    }
    
}
