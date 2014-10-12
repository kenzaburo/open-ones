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
@Table(name = "status_flow")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusFlow.findAll", query = "SELECT s FROM StatusFlow s"),
    @NamedQuery(name = "StatusFlow.findById", query = "SELECT s FROM StatusFlow s WHERE s.id = :id"),
    @NamedQuery(name = "StatusFlow.findBySeqNo", query = "SELECT s FROM StatusFlow s WHERE s.seqNo = :seqNo"),
    @NamedQuery(name = "StatusFlow.findByRequesttypeCd", query = "SELECT s FROM StatusFlow s WHERE s.requesttypeCd = :requesttypeCd"),
    @NamedQuery(name = "StatusFlow.findByTypeUser", query = "SELECT s FROM StatusFlow s WHERE s.typeUser = :typeUser"),
    @NamedQuery(name = "StatusFlow.findByCurrentStatus", query = "SELECT s FROM StatusFlow s WHERE s.currentStatus = :currentStatus"),
    @NamedQuery(name = "StatusFlow.findByNextStatus", query = "SELECT s FROM StatusFlow s WHERE s.nextStatus = :nextStatus"),
    @NamedQuery(name = "StatusFlow.findByCreated", query = "SELECT s FROM StatusFlow s WHERE s.created = :created"),
    @NamedQuery(name = "StatusFlow.findByCreatedbyUsername", query = "SELECT s FROM StatusFlow s WHERE s.createdbyUsername = :createdbyUsername"),
    @NamedQuery(name = "StatusFlow.findByLastmodified", query = "SELECT s FROM StatusFlow s WHERE s.lastmodified = :lastmodified"),
    @NamedQuery(name = "StatusFlow.findByLastmodifiedbyUsername", query = "SELECT s FROM StatusFlow s WHERE s.lastmodifiedbyUsername = :lastmodifiedbyUsername")})
public class StatusFlow implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "SEQ_NO")
    private int seqNo;
    @Basic(optional = false)
    @Column(name = "REQUESTTYPE_CD")
    private String requesttypeCd;
    @Basic(optional = false)
    @Column(name = "TYPE_USER")
    private String typeUser;
    @Basic(optional = false)
    @Column(name = "CURRENT_STATUS")
    private String currentStatus;
    @Basic(optional = false)
    @Column(name = "NEXT_STATUS")
    private String nextStatus;
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

    public StatusFlow() {
    }

    public StatusFlow(Integer id) {
        this.id = id;
    }

    public StatusFlow(Integer id, int seqNo, String requesttypeCd, String typeUser, String currentStatus, String nextStatus, Date created, String createdbyUsername) {
        this.id = id;
        this.seqNo = seqNo;
        this.requesttypeCd = requesttypeCd;
        this.typeUser = typeUser;
        this.currentStatus = currentStatus;
        this.nextStatus = nextStatus;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public String getRequesttypeCd() {
        return requesttypeCd;
    }

    public void setRequesttypeCd(String requesttypeCd) {
        this.requesttypeCd = requesttypeCd;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getNextStatus() {
        return nextStatus;
    }

    public void setNextStatus(String nextStatus) {
        this.nextStatus = nextStatus;
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
        if (!(object instanceof StatusFlow)) {
            return false;
        }
        StatusFlow other = (StatusFlow) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mks.dms.dao.entity.StatusFlow[ id=" + id + " ]";
    }
    
}
