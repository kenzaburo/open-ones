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
package mks.dms.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mks.dms.info.AccountInfo;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author ThachLe
 */
public class JSonUtil {
    private final static Logger LOG = Logger.getLogger(JSonUtil.class);

    public static List<AccountInfo> parseJSonAccountInfo(String jsonAccountInfo) {
        List<AccountInfo> listAccount = new ArrayList<AccountInfo>();
        // map string json to AccountInfo list
        ObjectMapper mapper = new ObjectMapper();
            try {
                listAccount.addAll(mapper.readValue(jsonAccountInfo, (listAccount.getClass())));
            } catch (JsonParseException ex) {
                LOG.error("Could not parse json data '" + jsonAccountInfo + "'", ex);
            } catch (JsonMappingException ex) {
                LOG.error("Could not parse json data '" + jsonAccountInfo + "'", ex);
            } catch (IOException ex) {
                LOG.error("Could not parse json data '" + jsonAccountInfo + "'", ex);
            }

        return listAccount;
    }
}
