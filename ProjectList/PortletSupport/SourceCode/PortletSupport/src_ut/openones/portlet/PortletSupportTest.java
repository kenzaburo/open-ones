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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.portlet.RenderRequest;
import javax.portlet.SampleRenderRequest;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Thach.Le
 */
public class PortletSupportTest {
    RenderRequest request = new SampleRenderRequest();
    PortletSupport portletUtil = new PortletSupport(request);
    /**
     * Test method for {@link openones.portlet.PortletSupport#getLogonUser(javax.portlet.RenderRequest)}.
     */
    @Test
    public void testGetLogonUser() {

        String logonUser = portletUtil.getLogonUser();

        Assert.assertEquals("demo", logonUser);
    }

    /**
     * Test method for {@link openones.portlet.PortletSupport#getUserRoles()}.
     */
    @Test
    public void testGetUserRoles() {
        String[] roles = portletUtil.getUserRoles().toArray(new String[]{});
        Assert.assertArrayEquals(new String[]{"admin", "manager"}, roles);
    }

    /**
     * Test method for {@link openones.portlet.PortletSupport#getUserFirstRole()}.
     */
    @Test
    public void testGetUserFirstRole() {
        Assert.assertEquals("admin", portletUtil.getUserFirstRole());
    }

    @Test
    public void testSplit() {
        String roles = "admin; manager";
        List<String> userRoles = Arrays.asList(roles.split(",;"));
        // Assert.assertArrayEquals(new String[]{"admin", "manager"}, userRoles.toArray(new String[]{}));

        StringTokenizer tokenizer = new StringTokenizer(roles, ",; ");
        userRoles = new ArrayList<String>();
        while (tokenizer.hasMoreTokens()) {
            userRoles.add(tokenizer.nextToken().trim());
        }
        Assert.assertArrayEquals(new String[]{"admin", "manager"}, userRoles.toArray(new String[]{}));
    }
}
