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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ThachLN
 */
@Entity
@Table(name = "requesttype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RequestType.findAll", query = "SELECT r FROM RequestType r"),
    @NamedQuery(name = "RequestType.findById", query = "SELECT r FROM RequestType r WHERE r.id = :id"),
    @NamedQuery(name = "RequestType.findByCd", query = "SELECT r FROM RequestType r WHERE r.cd = :cd"),
    @NamedQuery(name = "RequestType.findByName", query = "SELECT r FROM RequestType r WHERE r.name = :name"),
    @NamedQuery(name = "RequestType.findByDescription", query = "SELECT r FROM RequestType r WHERE r.description = :description"),
    @NamedQuery(name = "RequestType.findByEnabled", query = "SELECT r FROM RequestType r WHERE r.enabled = :enabled"),
    @NamedQuery(name = "RequestType.findByCreated", query = "SELECT r FROM RequestType r WHERE r.created = :created"),
    @NamedQuery(name = "RequestType.findByCreatedbyId", query = "SELECT r FROM RequestType r WHERE r.createdbyId = :createdbyId"),
    @NamedQuery(name = "RequestType.findByCreatedbyCd", query = "SELECT r FROM RequestType r WHERE r.createdbyCd = :createdbyCd"),
    @NamedQuery(name = "RequestType.findByCreatedbyName", query = "SELECT r FROM RequestType r WHERE r.createdbyName = :createdbyName"),
    @NamedQuery(name = "RequestType.findByLastmodified", query = "SELECT r FROM RequestType r WHERE r.lastmodified = :lastmodified"),
    @NamedQuery(name = "RequestType.findByLastmodifiedbyId", query = "SELECT r FROM RequestType r WHERE r.lastmodifiedbyId = :lastmodifiedbyId"),
    @NamedQuery(name = "RequestType.findByLastmodifiedbyName", query = "SELECT r FROM RequestType r WHERE r.lastmodifiedbyName = :lastmodifiedbyName"),
    @NamedQuery(name = "RequestType.findByLastmodifiedbyCd", query = "SELECT r FROM RequestType r WHERE r.lastmodifiedbyCd = :lastmodifiedbyCd")})
public class RequestType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "CD")
    private String cd;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "ENABLED")
    private Boolean enabled;
    @Basic(optional = false)
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "CREATEDBY_ID")
    private Integer createdbyId;
    @Column(name = "CREATEDBY_CD")
    private String createdbyCd;
    @Column(name = "CREATEDBY_NAME")
    private String createdbyName;
    @Column(name = "LASTMODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;
    @Column(name = "LASTMODIFIEDBY_ID")
    private Integer lastmodifiedbyId;
    @Column(name = "LASTMODIFIEDBY_NAME")
    private String lastmodifiedbyName;
    @Column(name = "LASTMODIFIEDBY_CD")
    private String lastmodifiedbyCd;

    public RequestType() {
    }

    public RequestType(Integer id) {
        this.id = id;
    }

    public RequestType(Integer id, String cd, String name, Date created) {
        this.id = id;
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

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
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

    public String getCreatedbyCd() {
        return createdbyCd;
    }

    public void setCreatedbyCd(String createdbyCd) {
        this.createdbyCd = createdbyCd;
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

    public String getLastmodifiedbyCd() {
        return lastmodifiedbyCd;
    }

    public void setLastmodifiedbyCd(String lastmodifiedbyCd) {
        this.lastmodifiedbyCd = lastmodifiedbyCd;
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
        if (!(object instanceof RequestType)) {
            return false;
        }
        RequestType other = (RequestType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mks.dms.dao.entity.RequestType[ id=" + id + " ]";
    }
    
}
