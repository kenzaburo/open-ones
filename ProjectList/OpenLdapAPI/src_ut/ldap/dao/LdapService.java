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
package ldap.dao;

import java.io.IOException;
import java.util.Properties;

import ldap.entry.Entry;
import ldap.util.ServerConfig;

import org.apache.log4j.Logger;

import rocky.common.PropertiesManager;

import com.novell.ldap.LDAPConnection;

/**
 * @author ThachLN
 *
 */
public class LdapService {
	private static final Logger LOG = Logger.getLogger(LdapService.class);
	protected static ServerConfig ldapCfg = null;


	/** OU for starting lookup accounts . */
	private static String userOU = null;

	public LdapService(String configPath) {
		try {
			LOG.info("Loading configuration...");
			Properties props = PropertiesManager.newInstanceFromProps(configPath);
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

			userOU = props.getProperty("userOU");

			ldapCfg = new ServerConfig(host, port, version, loginDN, pwdLogin, rootDN);
		} catch (IOException ex) {
			LOG.error("Could not load configuration from resource '" + configPath + "'", ex);
		}
	}
	
	public ServerConfig getConfig() {
	    return ldapCfg;
	}
}
