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
package com.fms1.common;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.spi.MyInitialContextFactory;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Open-Ones team
 *
 */
public class WorkUnitTest {
    private DataSource dataSource;

    
    /**
     * [Give the description for method].
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        // sets up the InitialContextFactoryForTest as default factory.
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                MyInitialContextFactory.class.getName());

        // binds the object
        dataSource = getDataSource();
        MyInitialContextFactory.bind("java:jdbc/fms1", dataSource);
    }
    
    private DataSource getDataSource() {
        OracleConnectionPoolDataSource dataSource = null;
        try {
            dataSource = new OracleConnectionPoolDataSource();
            dataSource.setUser("PRJ360");
            dataSource.setPassword("PRJ360");
            dataSource.setServerName("localhost");
            dataSource.setPortNumber(1521);
            dataSource.setDatabaseName("XE");
            dataSource.setURL("jdbc:oracle:thin:@localhost:1521:XE");
            return dataSource;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return dataSource;
    }
    
    @Test
    public void testInitialContext() throws Exception {
        Context ctx = new InitialContext();
        Object ref = ctx.lookup("java:jdbc/fms1");
        DataSource result = (DataSource) PortableRemoteObject.narrow(ref, DataSource.class);
        assertSame(dataSource, result);
    }
    /**
     * Test method for {@link com.fms1.common.WorkUnit#getWorkUnitInfo(long)}.
     */
    @Test
    public void testGetWorkUnitInfoLong() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getChildrenProjectUnit(long, java.sql.Date, java.sql.Date)}.
     */
    @Test
    public void testGetChildrenProjectUnit() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getChildrenGroups(long)}.
     */
    @Test
    public void testGetChildrenGroups() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getGroupInfo(long)}.
     */
    @Test
    public void testGetGroupInfoLong() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getGroups(long[])}.
     */
    @Test
    public void testGetGroups() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getGroupsIDbyWorkUnitID(long)}.
     */
    @Test
    public void testGetGroupsIDbyWorkUnitID() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#updateGroup(com.fms1.infoclass.group.GroupInfo)}.
     */
    @Test
    public void testUpdateGroup() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getChildrenWU(long)}.
     */
    @Test
    public void testGetChildrenWU() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getWUList(java.lang.String)}.
     */
    @Test
    public void testGetWUListString() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getGroupInfo(java.lang.String)}.
     */
    @Test
    public void testGetGroupInfoString() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getWUByType(int)}.
     */
    @Test
    public void testGetWUByType() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getOperationGroupList()}.
     */
    @Test
    public void testGetOperationGroupList() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#deleteWorkUnitOrg(long, java.lang.String)}.
     */
    @Test
    public void testDeleteWorkUnitOrg() {
        long id = 8181; // WorkUnitId
        long groupID = 475;
        String groupName = "Development";
        WorkUnit.doDeleteWorkUnitGroupAction(id, groupID, groupName);
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#addWorkUnitOrgAction(com.fms1.infoclass.OrganizationInfo)}.
     */
    @Test
    public void testAddWorkUnitOrgAction() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#updateWorkUnitOrg(com.fms1.infoclass.WorkUnitInfo)}.
     */
    @Test
    public void testUpdateWorkUnitOrg() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#addWorkUnitGroup(com.fms1.infoclass.WorkUnitInfo, com.fms1.infoclass.group.GroupInfo)}.
     */
    @Test
    public void testAddWorkUnitGroup() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#updateWorkUnitGroup(com.fms1.infoclass.WorkUnitInfo, com.fms1.infoclass.group.GroupInfo, java.lang.String)}.
     */
    @Test
    public void testUpdateWorkUnitGroup() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#doDeleteWorkUnitGroupAction(long, long, java.lang.String)}.
     */
    @Test
    public void testDoDeleteWorkUnitGroupAction() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#addWorkUnitProjectAction(com.fms1.infoclass.WorkUnitInfo, com.fms1.infoclass.ProjectInfo, com.fms1.infoclass.AssignmentInfo)}.
     */
    @Test
    public void testAddWorkUnitProjectAction() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#doDeleteWorkUnitProjectAction(long, long)}.
     */
    @Test
    public void testDoDeleteWorkUnitProjectAction() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getWorkUnitByProjectId(long)}.
     */
    @Test
    public void testGetWorkUnitByProjectId() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getAllWU()}.
     */
    @Test
    public void testGetAllWU() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getWUListByType(int)}.
     */
    @Test
    public void testGetWUListByType() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getWUList(int, java.lang.String)}.
     */
    @Test
    public void testGetWUListIntString() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getUserBelongGroupAssignments(java.lang.String)}.
     */
    @Test
    public void testGetUserBelongGroupAssignments() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getWorkUnitInfo(java.lang.String)}.
     */
    @Test
    public void testGetWorkUnitInfoString() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getNumOperationGroup()}.
     */
    @Test
    public void testGetNumOperationGroup() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getNumSupportGroup()}.
     */
    @Test
    public void testGetNumSupportGroup() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getProjects(long, java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testGetProjects() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getClosedProjects(long, java.sql.Date, java.sql.Date, java.lang.String)}.
     */
    @Test
    public void testGetClosedProjects() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getGroupType(com.fms1.infoclass.WorkUnitInfo)}.
     */
    @Test
    public void testGetGroupType() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#workUnitHome(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.fms1.infoclass.WorkUnitInfo)}.
     */
    @Test
    public void testWorkUnitHome() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getRunningProjects(java.sql.Date, java.sql.Date)}.
     */
    @Test
    public void testGetRunningProjectsDateDate() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.fms1.common.WorkUnit#getRunningProjects(long, java.sql.Date, java.sql.Date)}.
     */
    @Test
    public void testGetRunningProjectsLongDateDate() {
        fail("Not yet implemented"); // TODO
    }

}
