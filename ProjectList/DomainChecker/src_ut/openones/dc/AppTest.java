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

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author thachln
 */
public class AppTest {

    /**
     * Test method for {@link openones.dc.App#main(java.lang.String[])}.
     */
    @Test
    public void testGetNextDomain() {
        String nextDomain = App.getNextDomain("aaa");
        assertEquals("aab", nextDomain);

        nextDomain = App.getNextDomain("abc");
        assertEquals("abd", nextDomain);

        nextDomain = App.getNextDomain("aaz");
        assertEquals("abz", nextDomain);

        nextDomain = App.getNextDomain("zzz");
        assertEquals("zzza", nextDomain);
    }

    @Test
    public void testMainDomain3C() {
        App.main(new String[]{"eap", "zzz", ".com", ".co", ".vn"});
    }
    
    @Test
    public void testMainDomain4C() {
        App.main(new String[]{"aaaa", "zzzz", ".com", ".co", ".vn"});
    }
    
    @Test
    public void testMainInvalidArgument() {
        App.main(new String[]{""});
    }
}
