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
@Table(name = "label")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Label.findAll", query = "SELECT l FROM Label l"),
    @NamedQuery(name = "Label.findById", query = "SELECT l FROM Label l WHERE l.id = :id"),
    @NamedQuery(name = "Label.findByCd", query = "SELECT l FROM Label l WHERE l.cd = :cd"),
    @NamedQuery(name = "Label.findByType", query = "SELECT l FROM Label l WHERE l.type = :type"),
    @NamedQuery(name = "Label.findByName", query = "SELECT l FROM Label l WHERE l.name = :name"),
    @NamedQuery(name = "Label.findByCreated", query = "SELECT l FROM Label l WHERE l.created = :created"),
    @NamedQuery(name = "Label.findByCreatedbyId", query = "SELECT l FROM Label l WHERE l.createdbyId = :createdbyId"),
    @NamedQuery(name = "Label.findByCreatedbyCd", query = "SELECT l FROM Label l WHERE l.createdbyCd = :createdbyCd"),
    @NamedQuery(name = "Label.findByCreatedbyName", query = "SELECT l FROM Label l WHERE l.createdbyName = :createdbyName"),
    @NamedQuery(name = "Label.findByLastmodified", query = "SELECT l FROM Label l WHERE l.lastmodified = :lastmodified"),
    @NamedQuery(name = "Label.findByLastmodifiedbyId", query = "SELECT l FROM Label l WHERE l.lastmodifiedbyId = :lastmodifiedbyId"),
    @NamedQuery(name = "Label.findByLastmodifiedbyName", query = "SELECT l FROM Label l WHERE l.lastmodifiedbyName = :lastmodifiedbyName"),
    @NamedQuery(name = "Label.findByLastmodifiedbyCd", query = "SELECT l FROM Label l WHERE l.lastmodifiedbyCd = :lastmodifiedbyCd")})
public class Label implements Serializable {
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
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "labelId")
    private Collection<LabelRequest> labelRequestCollection;

    public Label() {
    }

    public Label(Integer id) {
        this.id = id;
    }

    public Label(Integer id, String cd, String type, String name, Date created) {
        this.id = id;
        this.cd = cd;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @XmlTransient
    public Collection<LabelRequest> getLabelRequestCollection() {
        return labelRequestCollection;
    }

    public void setLabelRequestCollection(Collection<LabelRequest> labelRequestCollection) {
        this.labelRequestCollection = labelRequestCollection;
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
        if (!(object instanceof Label)) {
            return false;
        }
        Label other = (Label) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mks.dms.dao.entity.Label[ id=" + id + " ]";
    }
    
}
