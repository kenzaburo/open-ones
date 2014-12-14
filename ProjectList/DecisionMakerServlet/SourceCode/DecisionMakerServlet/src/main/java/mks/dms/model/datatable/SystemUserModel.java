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
package mks.dms.model.datatable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mks.dms.dao.entity.User;
import mks.dms.util.AppCons.RESULT;
import mks.dms.util.AppUtil;

/**
 * @author ThachLe
 *
 */
public class SystemUserModel extends AbstractTableObjectModel implements Serializable {
    /** Index column CD in data list . */
    public final static int IDX_USERNAME = 0;
    
    /** Index column NAME in data list . */
    public final static int IDX_FIRSTNAME = 1;
    
    public final static int IDX_LASTNAME = 2;
    
    public final static int IDX_EMAIL = 3;

    /** Store result of saving. */
    public final static int IDX_RESULT = 4;

    /**
    * Convert from the data list (List of object[]) to contextual data list.
    * @return
    */
    public List<User> getDataList() {
        List<User> lstUser = null;

        if (data == null) {
            // Do nothing
        } else {
            lstUser = new ArrayList<User>();
            Object[] row;
            User user;

            for (Iterator<Object[]> itRow = data.iterator(); itRow.hasNext(); ) {
                row = itRow.next();

                user = new User();
                user.setUsername(String.valueOf(row[IDX_USERNAME]));
                user.setFirstname(String.valueOf(row[IDX_FIRSTNAME]));
                user.setLastname(String.valueOf(row[IDX_LASTNAME]));
                user.setEmail(String.valueOf(row[IDX_EMAIL]));
                

                lstUser.add(user);
            }
        }
        
        return lstUser;
    }

    public void setResultList(List<RESULT> lstResult) {
        if ((lstResult == null) || (this.data == null)) {
            return;
        } else {
            Object[] row;
            User user;
            int i = 0;
            int numResult = lstResult.size();
            for (Iterator<Object[]> itRow = data.iterator(); itRow.hasNext(); i++) {
                row = itRow.next();

                user = new User();
                user.setUsername(String.valueOf(row[IDX_USERNAME]));
                user.setFirstname(String.valueOf(row[IDX_FIRSTNAME]));
                user.setLastname(String.valueOf(row[IDX_LASTNAME]));
                user.setEmail(String.valueOf(row[IDX_EMAIL]));

                if (i < numResult) {
                    row[IDX_RESULT] = lstResult.get(i).name();
                }
            }
        }
    }

    @Override
    public void setDataList(List<?> lstSystemUser) {
        Iterator itUser = lstSystemUser.iterator();
        
        User user;
        
        if (data == null) {
            data = new ArrayList<Object[]>(lstSystemUser.size());
        }
        
        Object[] arrObjs;
        while (itUser.hasNext()) {
            user = (User) itUser.next();
            
            arrObjs = new Object[5];
            arrObjs[0] = AppUtil.formatJson(user.getUsername());
            arrObjs[1] = AppUtil.formatJson(user.getFirstname());
            arrObjs[2] = AppUtil.formatJson(user.getLastname());
            arrObjs[3] = AppUtil.formatJson(user.getEmail());
            // Reserved column 5 for Result of saving
            
            data.add(arrObjs);
        }
    }

}
