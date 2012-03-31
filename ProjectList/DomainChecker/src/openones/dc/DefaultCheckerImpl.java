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
package openones.dc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

/**
 * @author thachln
 */
public class DefaultCheckerImpl implements IDomainChecker {
    /** Logger. */
    static final Logger LOG = Logger.getLogger("DefaultCheckerImpl");

    @Override
    public boolean isAvailable(String domain, String ext) {
        try {
            String rtype = "status";
            String apikey = "demokey";

            String requestURL = String.format("http://whoapi.com/api-v1/?domain=%s&rtype=%s&apikey=%s", domain + ext, rtype,
                    apikey);
            LOG.debug("requestURL=" + requestURL);

            URL url = new URL(requestURL);

            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }

            in.close();

        } catch (IOException ex) {
            LOG.error("", ex);
        }
        return false;
    }

    @Override
    public boolean[] checkAvailable(String domain, String[] exts) {
        // TODO Auto-generated method stub
        return null;
    }

}
