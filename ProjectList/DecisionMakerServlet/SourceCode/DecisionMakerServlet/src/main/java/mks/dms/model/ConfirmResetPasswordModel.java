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
public class ConfirmResetPasswordModel implements Serializable {
    private Boolean validKey = null;
    private String email;

    private String newPassword;
    private String confirmNewPassword;
    private Boolean matchedPassword = null;

    /**
     * Get value of validKey.
     * @return the validKey
     */
    public Boolean getValidKey() {
        return validKey;
    }

    /**
     * Set the value for validKey.
     * @param validKey the validKey to set
     */
    public void setValidKey(Boolean validKey) {
        this.validKey = validKey;
    }
    
    /**
     * Get value of email.
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * Set the value for email.
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Get value of newPassword.
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }
    /**
     * Set the value for newPassword.
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    /**
     * Get value of confirmNewPassword.
     * @return the confirmNewPassword
     */
    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }
    /**
     * Set the value for confirmNewPassword.
     * @param confirmNewPassword the confirmNewPassword to set
     */
    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

	/**
	 * @return the matchedPassword
	 */
	public Boolean getMatchedPassword() {
		return matchedPassword;
	}

	/**
	 * @param matchedPassword the matchedPassword to set
	 */
	public void setMatchedPassword(Boolean matchedPassword) {
		this.matchedPassword = matchedPassword;
	}
}
