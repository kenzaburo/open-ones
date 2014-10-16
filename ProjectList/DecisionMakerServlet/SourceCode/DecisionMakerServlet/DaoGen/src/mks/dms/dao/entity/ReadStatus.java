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
@Table(name = "read_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReadStatus.findAll", query = "SELECT r FROM ReadStatus r"),
    @NamedQuery(name = "ReadStatus.findById", query = "SELECT r FROM ReadStatus r WHERE r.id = :id"),
    @NamedQuery(name = "ReadStatus.findByReqId", query = "SELECT r FROM ReadStatus r WHERE r.reqId = :reqId"),
    @NamedQuery(name = "ReadStatus.findByReqStatus", query = "SELECT r FROM ReadStatus r WHERE r.reqStatus = :reqStatus"),
    @NamedQuery(name = "ReadStatus.findByUsername", query = "SELECT r FROM ReadStatus r WHERE r.username = :username"),
    @NamedQuery(name = "ReadStatus.findByEmail", query = "SELECT r FROM ReadStatus r WHERE r.email = :email"),
    @NamedQuery(name = "ReadStatus.findByIsRead", query = "SELECT r FROM ReadStatus r WHERE r.isRead = :isRead"),
    @NamedQuery(name = "ReadStatus.findByCreated", query = "SELECT r FROM ReadStatus r WHERE r.created = :created"),
    @NamedQuery(name = "ReadStatus.findByCreatedbyUsername", query = "SELECT r FROM ReadStatus r WHERE r.createdbyUsername = :createdbyUsername"),
    @NamedQuery(name = "ReadStatus.findByLastmodified", query = "SELECT r FROM ReadStatus r WHERE r.lastmodified = :lastmodified"),
    @NamedQuery(name = "ReadStatus.findByLastmodifiedbyUsername", query = "SELECT r FROM ReadStatus r WHERE r.lastmodifiedbyUsername = :lastmodifiedbyUsername")})
public class ReadStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "REQ_ID")
    private int reqId;
    @Basic(optional = false)
    @Column(name = "REQ_STATUS")
    private String reqStatus;
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "IS_READ")
    private Integer isRead;
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

    public ReadStatus() {
    }

    public ReadStatus(Integer id) {
        this.id = id;
    }

    public ReadStatus(Integer id, int reqId, String reqStatus, String username, Date created, String createdbyUsername) {
        this.id = id;
        this.reqId = reqId;
        this.reqStatus = reqStatus;
        this.username = username;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
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

    public String getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
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
        if (!(object instanceof ReadStatus)) {
            return false;
        }
        ReadStatus other = (ReadStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mks.dms.dao.entity.ReadStatus[ id=" + id + " ]";
    }
    
}
