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
package mks.dms.util;

/**
 * @author ThachLN
 */
public class AppCons {

    /** Request Types . */
    public static final String TASK = "Task";
    public static final String LEAVE = "Leave";
    public static final String ANNOUNCEMENT = "Announcement";
    public static final String RULE = "Rule";
    
    ////////////////////////////////////////////////////////
    /*
     * Status of events
     */
    public static final String SAVE_STATUS = "SAVE_STATUS";
    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    
	////////////////////////////////////////////////////////
	/*
	* Rate
	*/
    public static final String BAD = "Bad";
    public static final String NORMAL = "Normal";
    public static final String GOOD = "Good";
    public static final String PERFECT = "Perfect";
    public static final String EXCELLENT = "Excellent";
    
    ////////////////////////////////////////////////////////
    /*
    * Status of Request
    */
    public static final String STATUS_CREATED = "Created";
    
    ////////////////////////////////////////////////////////
    /*
    * Status of Request
    * Flows of Task:
    *  Created => Doing => Finish       =>       Done          
    *             \Change by owner/   \Change by manager/
    *  Done => RE-assigned
    *      \ Change by owner or manager /
    *      
    * Follow of Leave:
    *  Create  => Rejected
    *  Create  => Approved
    *             
    */
    public static final String STATUS_DOING = "Doing";
 
    public static final String STATUS_REJECTED = "Rejected";
    public static final String STATUS_APPROVED = "Approved";
    public static final String STATUS_REASSIGN = "Re-assigned";
    public static final String STATUS_DONE = "Done";
    public static final String STATUS_FINISH = "Finish";
    
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String ALL = "All";
    public static final String MODEL = "model";
    
    public enum TYPE_USER {
        Owner,
        Manager,
        Creator
    };
    
    /** Store result of create for update data. */
    public enum RESULT {
        CREATE_OK,
        CREATE_FAIL,
        UPDATE_OK,
        UPDATE_FAIL
    };
    
    ////////////////////////////////////////////////////////
    /*
    * Parameters
    */
    public static final String PARAM_RANK = "Rank";
    public static final String PARAM_EMAIL = "Email";
    
    public static final String RESET_PASSWORD = "ResetPasswd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String PARAM_RESET_PASSWORD_SUBJECT = "ResetPasswdSubject";
    public static final String PARAM_RESET_PASSWORD_LINK = "ResetPasswdLink";
    public static final String PARAM_RESET_PASSWORD_FROM_ADDR = "ResetPasswdFromEmail";
    public static final String PARAM_RESET_PASSWORD_FROM_NAME = "ResetPasswdFromName";

    public static final String BEGIN_JSON_DATA = "{ \"data\": ";
    public static final Object END_JSON_DATA = "}";

}
