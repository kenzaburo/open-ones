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
package rocky.engine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.Setting;

/**
 * @author thachle
 *
 */
public class AppUtilTest {

    /**
     * [Give the description for method].
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * [Give the description for method].
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link rocky.engine.AppUtil#loadSetting(java.lang.String)}.
     */
    @Test
    public void testLoadSetting() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link rocky.engine.AppUtil#saveSetting(app.Setting, java.lang.String)}.
     */
    @Test
    public void testSaveSetting() {
        Setting setting = new Setting();
        setting.addTime("4:40");
        setting.addTime("1");
        
        AppUtil.saveSetting(setting, "TestSettingOut.xml");
    }

}
