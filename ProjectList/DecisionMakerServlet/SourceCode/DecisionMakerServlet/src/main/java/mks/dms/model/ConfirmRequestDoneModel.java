/**
 * Licensed to Open-Ones under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones licenses this file to you under the Apache License,
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
package mks.dms.model;

import java.io.Serializable;

/**
 * @author ThachLe
 */
public class ConfirmRequestDoneModel implements Serializable {
    private Integer requestId;
    private String rateLevel;
    private String confirmNote;
    /**
     * Get value of requestId.
     * @return the requestId
     */
    public Integer getRequestId() {
        return requestId;
    }
    /**
     * Set the value for requestId.
     * @param requestId the requestId to set
     */
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }
    /**
     * Get value of rateLevel.
     * @return the rateLevel
     */
    public String getRateLevel() {
        return rateLevel;
    }
    /**
     * Set the value for rateLevel.
     * @param rateLevel the rateLevel to set
     */
    public void setRateLevel(String rateLevel) {
        this.rateLevel = rateLevel;
    }
    /**
     * Get value of confirmNote.
     * @return the confirmNote
     */
    public String getConfirmNote() {
        return confirmNote;
    }
    /**
     * Set the value for confirmNote.
     * @param confirmNote the confirmNote to set
     */
    public void setConfirmNote(String confirmNote) {
        this.confirmNote = confirmNote;
    }

}
