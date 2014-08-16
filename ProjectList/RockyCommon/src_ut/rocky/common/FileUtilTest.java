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
package rocky.common;

import java.io.IOException;

import junit.framework.TestCase;

/**
 * @author ThachLe
 *
 */
public class FileUtilTest extends TestCase {

    /* 
     * [Explain the description for this method here].
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /* 
     * [Explain the description for this method here].
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link rocky.common.FileUtil#getContent(java.lang.String, java.lang.String)}.
     */
    public void testGetContentStringString() {
        try {
            String fileContent = FileUtil.getContent("/testPropsToEntity.properties", Constant.DEF_ENCODE);
            assertEquals("name=Rocky", fileContent);
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
    }

}
