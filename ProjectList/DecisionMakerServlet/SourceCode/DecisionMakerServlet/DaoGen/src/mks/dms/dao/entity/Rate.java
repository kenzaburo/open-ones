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
@Table(name = "rate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rate.findAll", query = "SELECT r FROM Rate r"),
    @NamedQuery(name = "Rate.findById", query = "SELECT r FROM Rate r WHERE r.id = :id"),
    @NamedQuery(name = "Rate.findByReqId", query = "SELECT r FROM Rate r WHERE r.reqId = :reqId"),
    @NamedQuery(name = "Rate.findByUsername", query = "SELECT r FROM Rate r WHERE r.username = :username"),
    @NamedQuery(name = "Rate.findByEmail", query = "SELECT r FROM Rate r WHERE r.email = :email"),
    @NamedQuery(name = "Rate.findByRank", query = "SELECT r FROM Rate r WHERE r.rank = :rank"),
    @NamedQuery(name = "Rate.findByCreated", query = "SELECT r FROM Rate r WHERE r.created = :created"),
    @NamedQuery(name = "Rate.findByCreatedbyUsername", query = "SELECT r FROM Rate r WHERE r.createdbyUsername = :createdbyUsername"),
    @NamedQuery(name = "Rate.findByLastmodified", query = "SELECT r FROM Rate r WHERE r.lastmodified = :lastmodified"),
    @NamedQuery(name = "Rate.findByLastmodifiedbyUsername", query = "SELECT r FROM Rate r WHERE r.lastmodifiedbyUsername = :lastmodifiedbyUsername")})
public class Rate implements Serializable {
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
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "EMAIL")
    private String email;
    @Lob
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "RANK")
    private Character rank;
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

    public Rate() {
    }

    public Rate(Integer id) {
        this.id = id;
    }

    public Rate(Integer id, int reqId, String username, Date created, String createdbyUsername) {
        this.id = id;
        this.reqId = reqId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Character getRank() {
        return rank;
    }

    public void setRank(Character rank) {
        this.rank = rank;
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
        if (!(object instanceof Rate)) {
            return false;
        }
        Rate other = (Rate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mks.dms.dao.entity.Rate[ id=" + id + " ]";
    }
    
}
