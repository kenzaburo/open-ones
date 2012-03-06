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
package javax.portlet;

import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;

/**
 * Sample RenderRequest for Testing only.
 * @author Thach.Le
 *
 */
public class SampleRenderRequest implements RenderRequest {

    /**
     * [Explain the description for this method here].
     * @param arg0
     * @return
     * @see javax.portlet.PortletRequest#getAttribute(java.lang.String)
     */
    public Object getAttribute(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getAttributeNames()
     */
    public Enumeration<String> getAttributeNames() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getAuthType()
     */
    public String getAuthType() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getContextPath()
     */
    public String getContextPath() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getLocale()
     */
    public Locale getLocale() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getLocales()
     */
    public Enumeration<Locale> getLocales() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @param arg0
     * @return
     * @see javax.portlet.PortletRequest#getParameter(java.lang.String)
     */
    public String getParameter(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getParameterMap()
     */
    public Map<String, String[]> getParameterMap() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getParameterNames()
     */
    public Enumeration<String> getParameterNames() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @param arg0
     * @return
     * @see javax.portlet.PortletRequest#getParameterValues(java.lang.String)
     */
    public String[] getParameterValues(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getPortalContext()
     */
    public PortalContext getPortalContext() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getPortletMode()
     */
    public PortletMode getPortletMode() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getPortletSession()
     */
    public PortletSession getPortletSession() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @param arg0
     * @return
     * @see javax.portlet.PortletRequest#getPortletSession(boolean)
     */
    public PortletSession getPortletSession(boolean arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getPreferences()
     */
    public PortletPreferences getPreferences() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getPrivateParameterMap()
     */
    public Map<String, String[]> getPrivateParameterMap() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @param arg0
     * @return
     * @see javax.portlet.PortletRequest#getProperties(java.lang.String)
     */
    public Enumeration<String> getProperties(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @param arg0
     * @return
     * @see javax.portlet.PortletRequest#getProperty(java.lang.String)
     */
    public String getProperty(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getPropertyNames()
     */
    public Enumeration<String> getPropertyNames() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getPublicParameterMap()
     */
    public Map<String, String[]> getPublicParameterMap() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getRemoteUser()
     */
    public String getRemoteUser() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getRequestedSessionId()
     */
    public String getRequestedSessionId() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getResponseContentType()
     */
    public String getResponseContentType() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getResponseContentTypes()
     */
    public Enumeration<String> getResponseContentTypes() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getScheme()
     */
    public String getScheme() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getServerName()
     */
    public String getServerName() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getServerPort()
     */
    public int getServerPort() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getUserPrincipal()
     */
    public Principal getUserPrincipal() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getWindowID()
     */
    public String getWindowID() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#getWindowState()
     */
    public WindowState getWindowState() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * [Explain the description for this method here].
     * @param arg0
     * @return
     * @see javax.portlet.PortletRequest#isPortletModeAllowed(javax.portlet.PortletMode)
     */
    public boolean isPortletModeAllowed(PortletMode arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#isRequestedSessionIdValid()
     */
    public boolean isRequestedSessionIdValid() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.PortletRequest#isSecure()
     */
    public boolean isSecure() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * [Explain the description for this method here].
     * @param arg0
     * @return
     * @see javax.portlet.PortletRequest#isUserInRole(java.lang.String)
     */
    public boolean isUserInRole(String arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * [Explain the description for this method here].
     * @param arg0
     * @return
     * @see javax.portlet.PortletRequest#isWindowStateAllowed(javax.portlet.WindowState)
     */
    public boolean isWindowStateAllowed(WindowState arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * [Explain the description for this method here].
     * @param arg0
     * @see javax.portlet.PortletRequest#removeAttribute(java.lang.String)
     */
    public void removeAttribute(String arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * [Explain the description for this method here].
     * @param arg0
     * @param arg1
     * @see javax.portlet.PortletRequest#setAttribute(java.lang.String, java.lang.Object)
     */
    public void setAttribute(String arg0, Object arg1) {
        // TODO Auto-generated method stub

    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see javax.portlet.RenderRequest#getETag()
     */
    public String getETag() {
        // TODO Auto-generated method stub
        return null;
    }

    public Cookie[] getCookies() {
        // TODO Auto-generated method stub
        return null;
    }

}
