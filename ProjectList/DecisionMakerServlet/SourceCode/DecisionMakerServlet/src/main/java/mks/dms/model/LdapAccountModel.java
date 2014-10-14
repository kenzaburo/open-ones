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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ldap.entry.Entry;
import ldap.entry.UserEntry;
import rocky.common.CHARA;

import com.google.gson.Gson;

/**
 * @author ThachLN
 */
public class LdapAccountModel implements Serializable {
    private String groupDn;
    private List<Object[]> data = null;
    
    public LdapAccountModel() {
        // TODO Auto-generated constructor stub
    }
    
    public LdapAccountModel(String groupDn, List<Object[]> data) {
        this.groupDn = groupDn;
        this.data = data;
    }

    public String getJsonAccounts() {
        Gson gson = new Gson();
        
        String jsonData;

        jsonData = gson.toJson(data);
        
        // add prefix
        StringBuffer sb = new StringBuffer();
        sb.append("{ \"data\": ")
          .append(jsonData)
          .append("}");
        
        jsonData = sb.toString();

        return jsonData;
    }
    public List<Object[]> getData() {
        return data;
    }

    public void setData(List<Object[]> data) {
        this.data = data;
    }


    /**
     * Get value of groupDn.
     * @return the groupDn
     */
    public String getGroupDn() {
        return groupDn;
    }

    /**
     * Set the value for groupDn.
     * @param groupDn the groupDn to set
     */
    public void setGroupDn(String groupDn) {
        this.groupDn = groupDn;
    }

    public void setUsers(List<Entry> lstUser) {
        Iterator<Entry> itEntry = lstUser.iterator();
        
        Entry user;
        
        if (data == null) {
            data = new ArrayList<Object[]>(lstUser.size());
        }
        Object[] arrObjs;
        UserEntry userEntry;
        while (itEntry.hasNext()) {
            user = itEntry.next();
            
            userEntry = ((UserEntry) user);
            arrObjs = new Object[4];
            arrObjs[0] = formatJson(userEntry.getUid());
            arrObjs[1] = formatJson(userEntry.getEmail());
            arrObjs[2] = formatJson(userEntry.getLastName());
            arrObjs[3] = formatJson(user.getName());
            arrObjs[3] = formatJson(user.getDn());

            data.add(arrObjs);
        }        
    }

    private Object formatJson(String text) {
        if (text == null) {
            text = CHARA.BLANK;
        }
        
        return text;
    }

}
