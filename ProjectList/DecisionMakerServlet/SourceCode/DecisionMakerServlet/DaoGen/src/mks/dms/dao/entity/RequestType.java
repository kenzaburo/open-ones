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
 * @author ThachLe
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
    @NamedQuery(name = "RequestType.findByCreatedbyUsername", query = "SELECT r FROM RequestType r WHERE r.createdbyUsername = :createdbyUsername"),
    @NamedQuery(name = "RequestType.findByLastmodified", query = "SELECT r FROM RequestType r WHERE r.lastmodified = :lastmodified"),
    @NamedQuery(name = "RequestType.findByLastmodifiedbyUsername", query = "SELECT r FROM RequestType r WHERE r.lastmodifiedbyUsername = :lastmodifiedbyUsername")})
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
    @Basic(optional = false)
    @Column(name = "CREATEDBY_USERNAME")
    private String createdbyUsername;
    @Column(name = "LASTMODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;
    @Column(name = "LASTMODIFIEDBY_USERNAME")
    private String lastmodifiedbyUsername;

    public RequestType() {
    }

    public RequestType(Integer id) {
        this.id = id;
    }

    public RequestType(Integer id, String cd, String name, Date created, String createdbyUsername) {
        this.id = id;
        this.cd = cd;
        this.name = name;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
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
