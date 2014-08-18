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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author ThachLN
 */
@Entity
@Table(name = "department")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
    @NamedQuery(name = "Department.findById", query = "SELECT d FROM Department d WHERE d.id = :id"),
    @NamedQuery(name = "Department.findByParentid", query = "SELECT d FROM Department d WHERE d.parentid = :parentid"),
    @NamedQuery(name = "Department.findByCd", query = "SELECT d FROM Department d WHERE d.cd = :cd"),
    @NamedQuery(name = "Department.findByParentcd", query = "SELECT d FROM Department d WHERE d.parentcd = :parentcd"),
    @NamedQuery(name = "Department.findByName", query = "SELECT d FROM Department d WHERE d.name = :name"),
    @NamedQuery(name = "Department.findByDescription", query = "SELECT d FROM Department d WHERE d.description = :description"),
    @NamedQuery(name = "Department.findByEnabled", query = "SELECT d FROM Department d WHERE d.enabled = :enabled"),
    @NamedQuery(name = "Department.findByManagerId", query = "SELECT d FROM Department d WHERE d.managerId = :managerId"),
    @NamedQuery(name = "Department.findByManagerAccount", query = "SELECT d FROM Department d WHERE d.managerAccount = :managerAccount"),
    @NamedQuery(name = "Department.findByManagerName", query = "SELECT d FROM Department d WHERE d.managerName = :managerName"),
    @NamedQuery(name = "Department.findByCreated", query = "SELECT d FROM Department d WHERE d.created = :created"),
    @NamedQuery(name = "Department.findByCreatedbyId", query = "SELECT d FROM Department d WHERE d.createdbyId = :createdbyId"),
    @NamedQuery(name = "Department.findByCreatedbyAccount", query = "SELECT d FROM Department d WHERE d.createdbyAccount = :createdbyAccount"),
    @NamedQuery(name = "Department.findByCreatedbyName", query = "SELECT d FROM Department d WHERE d.createdbyName = :createdbyName"),
    @NamedQuery(name = "Department.findByLastmodified", query = "SELECT d FROM Department d WHERE d.lastmodified = :lastmodified"),
    @NamedQuery(name = "Department.findByLastmodifiedbyId", query = "SELECT d FROM Department d WHERE d.lastmodifiedbyId = :lastmodifiedbyId"),
    @NamedQuery(name = "Department.findByLastmodifiedbyName", query = "SELECT d FROM Department d WHERE d.lastmodifiedbyName = :lastmodifiedbyName"),
    @NamedQuery(name = "Department.findByLastmodifiedbyAccount", query = "SELECT d FROM Department d WHERE d.lastmodifiedbyAccount = :lastmodifiedbyAccount")})
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "PARENTID")
    private int parentid;
    @Basic(optional = false)
    @Column(name = "CD")
    private String cd;
    @Column(name = "PARENTCD")
    private String parentcd;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "ENABLED")
    private Boolean enabled;
    @Column(name = "MANAGER_ID")
    private Integer managerId;
    @Column(name = "MANAGER_ACCOUNT")
    private String managerAccount;
    @Column(name = "MANAGER_NAME")
    private String managerName;
    @Basic(optional = false)
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "CREATEDBY_ID")
    private Integer createdbyId;
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
    @OneToMany(mappedBy = "departmentsId")
    private Collection<Request> requestCollection;

    public Department() {
    }

    public Department(Integer id) {
        this.id = id;
    }

    public Department(Integer id, int parentid, String cd, String name, Date created) {
        this.id = id;
        this.parentid = parentid;
        this.cd = cd;
        this.name = name;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getParentcd() {
        return parentcd;
    }

    public void setParentcd(String parentcd) {
        this.parentcd = parentcd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getCreatedbyId() {
        return createdbyId;
    }

    public void setCreatedbyId(Integer createdbyId) {
        this.createdbyId = createdbyId;
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

    @XmlTransient
    public Collection<Request> getRequestCollection() {
        return requestCollection;
    }

    public void setRequestCollection(Collection<Request> requestCollection) {
        this.requestCollection = requestCollection;
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
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mks.dms.dao.entity.Department[ id=" + id + " ]";
    }
    
}
