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
package mks.dms.extentity;

import mks.dms.dao.entity.User;
import rocky.common.CHARA;

/**
 * @author ThachLe
 *
 */
public class ExUser extends User {
    
    /**
    * Get full name from first name and last name.
    * </br>
    * The first name is appended to last name as Vietnamese
    * @return
    */
    public String getFullname() {
        return this.getLastname() + CHARA.SPACE + this.getFirstname(); 
    }
    
    /**
    * Get full name from given first name and last name.
    * </br>
    * The first name is appended to last name as Vietnamese
    * @param user
    * @return
    */
    public static String getFullname(User user) {
        return user.getLastname() + CHARA.SPACE + user.getFirstname();
    }
}
