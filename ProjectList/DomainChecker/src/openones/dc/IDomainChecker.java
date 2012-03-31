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

/**
 * @author Thach.Le
 */
public interface IDomainChecker {

    /**
     * Check domain name is available or not.
     * @param domain domain without extension. Ex: open-ones
     * @param ext domain extension include dot character. Ex: .com
     * @return true if it has not registered yes.
     * false if it is registered.
     * Example: isAvailable("google.com") returns false.
     */
    boolean isAvailable(String domain, String ext);

    /**
     * Check many domain names are available or not.
     * @param domain name of domain without extension. Ex: open-ones
     * @param exts array of extension with dot character. Ex: {".com", ".co"}
     * @return array of result checking of domain and relative extension
     */
    boolean[] checkAvailable(String domain, String[] exts);
}
