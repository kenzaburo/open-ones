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
package openones.portlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.portlet.RenderRequest;

import org.apache.log4j.Logger;

import rocky.common.PropertiesManager;

/**
 * This Utility provides: - get logon user of the portal or configurable logon user for development environment.
 * @author Thach.Le
 */
public class PortletSupport {
    /** Logger. */
    static final Logger LOG = Logger.getLogger("PortletUtil");

    /** Default user if it's not declared in the configuration file. Or it could not got from Portal. */
    static final String DEFAULT_USER = null;

    /** Default configuration of debug user. */
    static final String DEFAULT_CONF = "/dev.properties";

    /** Out: Logon user from the Portal or from the configuration. */
    private String logonUser;

    /** Out: Roles of logon user. It should be overrided in the sub class. */
    private List<String> userRoles = null;

    /**
     * Create an instance of the Utility with default configuration resource "/dev.properties". This support to get
     * logon use of Portal which is the container of the portlet. If the portlet runs within development environment of
     * GlashFish + Open Portlet Container).
     * @param request request from the client
     */
    public PortletSupport(RenderRequest request) {
        this(request, null);
    }

    /**
     * Create an instance of the Utility. This support to get logon use of Portal which is the container of the portlet.
     * If the portlet runs within development environment of
     * @param request request from the client
     * @param confResource configuration resource properties
     */
    public PortletSupport(RenderRequest request, String confResource) {
        // Get the logon user by Portlet render
        logonUser = (String) request.getRemoteUser();

        // Could not get the logon user
        if (logonUser == null) { // Maybe, The portlet is running on development environment
            // Load logon user from the configuration for debug
            try {
                if (confResource == null) {
                    confResource = DEFAULT_CONF;
                }
                Properties props = PropertiesManager.newInstanceFromProps(confResource);
                // Get the logon user by key "username" from the resource "/dev.properties"
                // If no key "username", default logon user is "guest" (without double-quotes)
                logonUser = props.getProperty("username");

                // Default role is none
                String roles = props.getProperty("roles", "none");
                StringTokenizer tokenizer = new StringTokenizer(roles, ",; ");
                userRoles = new ArrayList<String>();
                while (tokenizer.hasMoreTokens()) {
                    userRoles.add(tokenizer.nextToken().trim());
                }

            } catch (IOException ex) {
                LOG.warn("Could not load resource /dev.properties", ex);
            }
        } else { // Assume the logon is is not "user.name.given". This is production environment
            LOG.debug("Logon user:" + logonUser);
        }

        if (logonUser == null) {
            LOG.debug("Return default user:" + DEFAULT_USER);
            logonUser = DEFAULT_USER;
        }
    }

    /**
     * Get logon use of Portal which is the container of the portlet. If this portlet runs within development
     * environment of GlashFish + Open Portlet Container).
     * @return String of logon user.
     */
    public String getLogonUser() {
        return logonUser;
    }

    /**
     * Get list of roles of logon user in development environment.
     * @return List of values of roles
     * @see configuration file dev.properties, property "roles"
     */
    public List<String> getUserRoles() {
        return userRoles;
    }

    /**
     * Get the first role of logon user. It supports for developer rapidly.
     * @return String of role value.
     * @see configuration file dev.properties.
     */
    public String getUserFirstRole() {
        return userRoles.get(0);
    }
}
