/**
 * MKS Copyright 2012.
 */
package com.mks.cms.gae.dto;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.Persistent;

/**
 * @author Thach Le
 */
public class BaseDTO implements Serializable {
    @Persistent
    public int orderNo;

    @Persistent
    private Date created;

    @Persistent
    private String createdBy;

    @Persistent
    private Date lastModified;

    @Persistent
    private String lastModifiedBy;

    /**
     * Get value of orderNo.
     * @return the orderNo
     */
    public int getOrderNo() {
        return orderNo;
    }

    /**
     * Set the value for orderNo.
     * @param orderNo the orderNo to set
     */
    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * Get value of created.
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Set the value for created.
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * Get value of createdBy.
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the value for createdBy.
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Get value of lastModified.
     * @return the lastModified
     */
    public Date getLastModified() {
        return lastModified;
    }

    /**
     * Set the value for lastModified.
     * @param lastModified the lastModified to set
     */
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Get value of lastModifiedBy.
     * @return the lastModifiedBy
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * Set the value for lastModifiedBy.
     * @param lastModifiedBy the lastModifiedBy to set
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

}
