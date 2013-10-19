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

/**
 * @author thachle
 *
 */
public class TextUtil {
    /**
     * Check subTest is around by startCh and endCd in text.
     * @param text
     * @param subText
     * @param startCh
     * @param endCd
     * @return
     */
    public static boolean startEndBy(String text, String subText, char startCh, char endCd) {
        if ((subText.charAt(0) == startCh) && (subText.charAt(subText.length() - 1) == endCd)) {
            return true;
        } else {
            // Do nothing
            // Check in parent expression
        }
        int idx = text.indexOf(subText);

        if ((idx > 0) && (idx + subText.length() < text.length())) {
            char leftCh = text.charAt(idx - 1);
            char rightCh = text.charAt(idx + subText.length());

            return ((leftCh == startCh) && (rightCh == endCd));

        } else {
            return false;
        }
    }
    
    
    /**
     * Get first line of a string.
     * <br/>
     * Line separator is '\n' character
     * @param text
     * @return
     */
    public static String getFirstLine(String text) {
        int idxLineSeparator = text.indexOf(Constant.LF);
        
        return (idxLineSeparator > 0) ? text.substring(0, idxLineSeparator - 1) : text;
    }

    /**
     * Count statements of block.
     * @param text
     * @return
     */
    public static int countLine(String text) {
        String[] lines = text.split(Constant.LF);
        
        return (lines == null) ? 0 : lines.length;
    }
}
