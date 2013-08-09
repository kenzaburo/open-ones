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
package svn.client;

import org.junit.Test;

/**
 * @author thachln
 *
 */
public class SVNClientTest {

    /**
     * Test method for {@link svn.client.SVNClient#doCheckOut(java.util.Date)}.
     */
    @Test
    public void testDoUpdateDate() {
        SVNClient svCln = SVNClient.newClient("/svn.properties");
        svCln.doUpdate("2013/07/02", "yyyy/MM/dd");
    }

}
