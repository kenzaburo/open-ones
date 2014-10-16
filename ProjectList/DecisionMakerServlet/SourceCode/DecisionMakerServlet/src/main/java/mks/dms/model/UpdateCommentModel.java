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
 * @author ThachLN
 */
public class UpdateCommentModel implements Serializable {
    private Integer requestId;
    private Integer commentId;

    /** Content of new comment . */
    private String value;

    /**
     * Get value of requestId.
     * 
     * @return the requestId
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Set the value for requestId.
     * 
     * @param requestId the requestId to set
     */
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    /**
     * Get value of commentId.
     * 
     * @return the commentId
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * Set the value for commentId.
     * 
     * @param commentId the commentId to set
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * Get value of value.
     * 
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value for value.
     * 
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Get value of name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value for name.
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get value of pk.
     * 
     * @return the pk
     */
    public String getPk() {
        return pk;
    }

    /**
     * Set the value for pk.
     * 
     * @param pk the pk to set
     */
    public void setPk(String pk) {
        this.pk = pk;
    }

    /** Reserved . */
    private String name;

    /** Reserved . */
    private String pk;
}
