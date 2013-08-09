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
package app;

import rocky.engine.Scheduler;

/**
 * @author thachln
 */
public class RockyScheduler {
    public static void main(String[] args) {
        String settingFilePath = "/schedule.xml";
        String cmdLine = "";

        String[] myArgs = {settingFilePath, cmdLine};
        int retCode = Scheduler.main(myArgs);
    }
}