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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Watcher.findByUserCd", query = "SELECT w FROM Watcher w WHERE w.userCd = :userCd"),
    @NamedQuery(name = "Watcher.findByUserName", query = "SELECT w FROM Watcher w WHERE w.userName = :userName"),
    @NamedQuery(name = "Watcher.findByCreated", query = "SELECT w FROM Watcher w WHERE w.created = :created"),
    @NamedQuery(name = "Watcher.findByCreatedbyId", query = "SELECT w FROM Watcher w WHERE w.createdbyId = :createdbyId"),
    @NamedQuery(name = "Watcher.findByCreatedbyCd", query = "SELECT w FROM Watcher w WHERE w.createdbyCd = :createdbyCd"),
    @NamedQuery(name = "Watcher.findByCreatedbyName", query = "SELECT w FROM Watcher w WHERE w.createdbyName = :createdbyName"),
    @NamedQuery(name = "Watcher.findByLastmodified", query = "SELECT w FROM Watcher w WHERE w.lastmodified = :lastmodified"),
    @NamedQuery(name = "Watcher.findByLastmodifiedbyId", query = "SELECT w FROM Watcher w WHERE w.lastmodifiedbyId = :lastmodifiedbyId"),
    @NamedQuery(name = "Watcher.findByLastmodifiedbyName", query = "SELECT w FROM Watcher w WHERE w.lastmodifiedbyName = :lastmodifiedbyName"),
    @NamedQuery(name = "Watcher.findByLastmodifiedbyCd", query = "SELECT w FROM Watcher w WHERE w.lastmodifiedbyCd = :lastmodifiedbyCd")})
public class Watcher implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "USER_CD")
    private String userCd;
    @Column(name = "USER_NAME")
    private String userName;
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
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne
    private User userId;
    @JoinColumn(name = "REQ_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Request reqId;

    public Watcher() {
    }

    public Watcher(Integer id) {
        this.id = id;
    }

    public Watcher(Integer id, Date created) {
        this.id = id;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCd() {
        return userCd;
    }

    public void setUserCd(String userCd) {
        this.userCd = userCd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Request getReqId() {
        return reqId;
    }

    public void setReqId(Request reqId) {
        this.reqId = reqId;
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
