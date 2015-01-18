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
package ldap.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import rocky.common.PropertiesManager;

import com.novell.ldap.LDAPConnection;

/**
 * @author ThachLN
 *
 */
public class LdapConfigurationUtil {
    private static final Logger LOG = Logger.getLogger(LdapConfigurationUtil.class);
    
    public static LdapConfiguration getConfiguration(String configResource) {
        LdapConfiguration ldapConfiguration = null; 

        try {
            LOG.info("Loading configuration...");
            Properties props = PropertiesManager.newInstanceFromProps(configResource);
            String host = props.getProperty("host");
            String strPort = props.getProperty("port");
            int port = ((strPort != null) && (!strPort.isEmpty()))
                    ? Integer.valueOf(strPort)
                    : LDAPConnection.DEFAULT_PORT;

            String strVersion = props.getProperty("version");
            int version = ((strVersion != null) && (!strVersion.isEmpty()))
                    ? Integer.valueOf(strVersion)
                    : LDAPConnection.LDAP_V3;

            String loginDN = props.getProperty("loginDN");
            String pwdLogin = props.getProperty("pwdLogin");
            String rootDN = props.getProperty("rootDN");

            // ldapConfiguration = new LdapConfiguration(host, port, version, loginDN, pwdLogin, rootDN);
            ldapConfiguration = new LdapConfiguration();
            ldapConfiguration.setHost(host);
            ldapConfiguration.setPort(port);
            ldapConfiguration.setVersion(version);
            ldapConfiguration.setLoginDN(loginDN);
            ldapConfiguration.setPwdLogin(pwdLogin);
            ldapConfiguration.setRootDN(rootDN);
        } catch (IOException ex) {
            LOG.error("Could not load configuration from resource '" + configResource + "'", ex);
        }
        
        return ldapConfiguration;
    }
}
