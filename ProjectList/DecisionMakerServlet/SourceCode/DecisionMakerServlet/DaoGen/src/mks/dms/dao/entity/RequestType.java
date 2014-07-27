/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mks.dms.dao.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
    @NamedQuery(name = "RequestType.findByEnabled", query = "SELECT r FROM RequestType r WHERE r.enabled = :enabled")})
public class RequestType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CD")
    private String cd;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "ENABLED")
    private Boolean enabled;

    public RequestType() {
    }

    public RequestType(Integer id) {
        this.id = id;
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
