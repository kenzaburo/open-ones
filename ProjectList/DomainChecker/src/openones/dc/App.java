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

import org.apache.log4j.Logger;

/**
 * Scanning domain to check it is available or not.
 * @author thachln
 */
public final class App {
    /** Logger. */
    static final Logger LOG = Logger.getLogger("App");

    /** Default start domain name. */
    static final String DEF_START_DOMAIN = "aaa";

    /** 10 character domain size. */
    static final String DEF_END_DOMAIN = "zzzzzzzzzz";

    /** Start domain name will be scanned. */
    private static String startDomain = DEF_START_DOMAIN;

    /** End domain name will be stopped scanning. */
    private static String endDomain = DEF_END_DOMAIN;

    /** Extensions to append to the domain name to make a fully domain. */
    private static String[] exts = {".com", ".vn"};

    /**
     * Entry point of the application.
     * @param args "start domain name", "end domain name" and extensions
     */
    public static void main(String[] args) {
        if ((args != null) && (args.length > 0) && (args[0].length() > 0)) {
            if (args.length >= 1) {
                startDomain = args[0];
            }

            if (args.length >= 2) {
                endDomain = args[1];

                // Determine extension. Ex .co .com
                if (args.length > 2) {
                    exts = new String[args.length - 2];
                    // Scan from argument 3
                    for (int i = 2; i < args.length; i++) {
                        exts[i - 2] = args[i];
                    }
                }
            }
        } else {
            usage();
            System.exit(1);
        }
        System.out.println("Domain checker starts with '" + startDomain + "' and end with '" + endDomain + "'");
        System.out.print("Apply extension(s): ");
        for (String ext : exts) {
            System.out.print(ext + " ");
        }
        System.out.println();

        String domain;
        IDomainChecker checker = new VinaHostCheckerImpl();
        boolean[] checkResult;

        String curDomain = startDomain;
        while (compare(curDomain, endDomain) <= 0) {
            checkResult = checker.checkAvailable(curDomain, exts);

            for (int i = 0; i < checkResult.length; i++) {
                System.out.println(curDomain + exts[i] + "," + checkResult[i]);
            }
            curDomain = getNextDomain(curDomain);
        }
    }

    /**
     * Get the next domain name to scan. Ex: If curren domain name is "aaa", next domain name is "aab".
     * @param curDomain
     * @return
     */
    static String getNextDomain(String curDomain) {
        String nextDomain;

        int idxCheckedChar = curDomain.length() - 1; // right most character
        char checkedCh;

        while (idxCheckedChar >= 0) { // to the first character
            checkedCh = curDomain.charAt(idxCheckedChar);
            if (checkedCh < 'z') { //
                nextDomain = curDomain.substring(0, idxCheckedChar) + (char) (checkedCh + 1)
                        + curDomain.substring(idxCheckedChar + 1);
                return nextDomain;
            } else { // last character reaches to 'z'
                // check the left side character
                idxCheckedChar--;
            }
        }
        nextDomain = curDomain + 'a';

        return nextDomain;
    }

    /**
     * Compare string1 and string2 with priority order: length, alphabet order. Ex: abc abaa
     * @param st1
     * @param st2
     * @return -1 if length of st1 < length of st2; or st1 < st2
     */
    private static int compare(String st1, String st2) {
        if (st1.length() < st2.length()) {
            return -1;
        } else {
            return st1.compareTo(st2);
        }
    }

    /**
     * Guideline for the command line of the application.
     */
    static void usage() {
        System.out.println("java -jar domainchecker.jar [StartDomain] [EndDomain] [Extension]");
        System.out.println("Ex: java -jar DomainChecker.jar aaaa zzzz .com .vn");
    }

}
