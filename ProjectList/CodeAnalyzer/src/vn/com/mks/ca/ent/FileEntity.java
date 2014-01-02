/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package vn.com.mks.ca.ent;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ThachLN
 *
 */
public class FileEntity implements Serializable {
    private String parentPath;
    private String fileName;
    private Long revision;
    private Date createdDate;
    private Date modifiedDate;
    /**
     * Get value of parentPath.
     * @return the parentPath
     */
    public String getParentPath() {
        return parentPath;
    }
    /**
     * Set the value for parentPath.
     * @param parentPath the parentPath to set
     */
    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }
    /**
     * Get value of fileName.
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * Set the value for fileName.
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * Get value of revision.
     * @return the revision
     */
    public Long getRevision() {
        return revision;
    }
    /**
     * Set the value for revision.
     * @param revision the revision to set
     */
    public void setRevision(Long revision) {
        this.revision = revision;
    }
    /**
     * Get value of createdDate.
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }
    /**
     * Set the value for createdDate.
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    /**
     * Get value of modifiedDate.
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }
    /**
     * Set the value for modifiedDate.
     * @param modifiedDate the modifiedDate to set
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    /**
     * Get value of step.
     * @return the step
     */
    public int getStep() {
        return step;
    }
    /**
     * Set the value for step.
     * @param step the step to set
     */
    public void setStep(int step) {
        this.step = step;
    }
    private int step;
}
