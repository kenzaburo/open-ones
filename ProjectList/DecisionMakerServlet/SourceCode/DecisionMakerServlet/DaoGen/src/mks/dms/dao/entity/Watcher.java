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
@Table(name = "watcher")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Watcher.findAll", query = "SELECT w FROM Watcher w"),
    @NamedQuery(name = "Watcher.findById", query = "SELECT w FROM Watcher w WHERE w.id = :id"),
    @NamedQuery(name = "Watcher.findByReqId", query = "SELECT w FROM Watcher w WHERE w.reqId = :reqId"),
    @NamedQuery(name = "Watcher.findByUserUsername", query = "SELECT w FROM Watcher w WHERE w.userUsername = :userUsername"),
    @NamedQuery(name = "Watcher.findByCreated", query = "SELECT w FROM Watcher w WHERE w.created = :created"),
    @NamedQuery(name = "Watcher.findByCreatedbyUsername", query = "SELECT w FROM Watcher w WHERE w.createdbyUsername = :createdbyUsername"),
    @NamedQuery(name = "Watcher.findByLastmodified", query = "SELECT w FROM Watcher w WHERE w.lastmodified = :lastmodified"),
    @NamedQuery(name = "Watcher.findByLastmodifiedbyUsername", query = "SELECT w FROM Watcher w WHERE w.lastmodifiedbyUsername = :lastmodifiedbyUsername")})
public class Watcher implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "REQ_ID")
    private int reqId;
    @Column(name = "USER_USERNAME")
    private String userUsername;
    @Basic(optional = false)
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "CREATEDBY_USERNAME")
    private String createdbyUsername;
    @Column(name = "LASTMODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;
    @Column(name = "LASTMODIFIEDBY_USERNAME")
    private String lastmodifiedbyUsername;

    public Watcher() {
    }

    public Watcher(Integer id) {
        this.id = id;
    }

    public Watcher(Integer id, int reqId, Date created) {
        this.id = id;
        this.reqId = reqId;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
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
        if (!(object instanceof Watcher)) {
            return false;
        }
        Watcher other = (Watcher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mks.dms.dao.entity.Watcher[ id=" + id + " ]";
    }
    
}
