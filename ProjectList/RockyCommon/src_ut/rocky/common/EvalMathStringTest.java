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
package rocky.common;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @author thachle
 *
 */
public class EvalMathStringTest extends TestCase {

    /**
     * [Explain the description for this method here].
     * @throws java.lang.Exception
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * [Explain the description for this method here].
     * @throws java.lang.Exception
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link rocky.common.EvalMathString#performStringExpr(java.lang.String)}.
     */
    public void testPerformStringExpr() {
        EvalMathString ems = new EvalMathString();
        
        String eval = ems.performStringExpr("((15 -  2 * 3))");
        assertEquals("9", eval);
    }

    public void testPerformStringExpr02() {
        EvalMathString ems = new EvalMathString();
        
        String eval = ems.performStringExpr("(15 -  2 * 3)");
        assertEquals("9", eval);
    }
    /**
     * Test method for {@link rocky.common.EvalMathString#performStringExpr(java.lang.String)}.
     */
    public void testPerformStringExprEx01() {
        EvalMathString ems = new EvalMathString();
        
        String eval = ems.performStringExprEx("test exp(15 -  2 * 3)");
        assertEquals("test 9", eval);
    }
    
    public void testPerformStringExprEx02() {
        EvalMathString ems = new EvalMathString();
        
        Map<String, Object> mapValue = new HashMap<String, Object>();
        mapValue.put("A", 15);
        String eval = ems.performStringExprEx("test exp(${A} -  2 * 3)", mapValue );
        assertEquals("test 9", eval);
    }
    public void testPerformStringExprEx03() {
        EvalMathString ems = new EvalMathString();
        
        Map<String, Object> mapValue = new HashMap<String, Object>();
        mapValue.put("A", BigInteger.valueOf(15));
        String eval = ems.performStringExprEx("test exp(${A} -  2 * 3)", mapValue );
        assertEquals("test 9", eval);
    }

}
