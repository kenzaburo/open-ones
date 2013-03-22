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
package rocky.sizecounter;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author thachln
 */
public class SizeCounterImplTest {

    /**
     * Test method for {@link rocky.sizecounter.SizeCounterImpl#countSize(java.lang.String)}.
     */
    @Test
    public void testCountSize() {
        ISizeCounter scounter = new SizeCounterImpl();
        try {
            SizeMetaData smd = scounter
                    .countSize("C:/UTR.xls");
            assertEquals(UnitType.SHEET, smd.getUnit());
            assertEquals(10, smd.getSize());

            assertEquals(UnitType.PAGE, smd.getUnit1());
            assertEquals(20, smd.getSize1());

            assertEquals(UnitType.UTC, smd.getUnit2());
            assertEquals(62, smd.getSize2());

        } catch (UnsupportedFileType ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

}
