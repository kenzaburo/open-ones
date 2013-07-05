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

import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;

import rocky.common.CommonUtil;

/**
 * @author thachle
 */
public class Runner implements Runnable {
    private final static Logger LOG = Logger.getLogger("Runner");
    private static final int ERROR = -1;

    private String cmdLine = null;

    public Runner(String cmd) {
        this.cmdLine = cmd;
    }

    @Override
    public void run() {
        LOG.info("Current date: " + new Date());

        if (CommonUtil.isNNandNB(cmdLine)) {
            runCommand();
        } else {
            LOG.info("No command to run");
        }

    }

    private int runCommand() {
        try {
            Process proc = Runtime.getRuntime().exec(cmdLine);
            return proc.exitValue();
        } catch (IOException ex) {
            LOG.error("Could not execute '" + cmdLine + "'", ex);
        }

        return ERROR;
    }

    public static Runnable newInstance(String cmd) {
        Runner runner = new Runner(cmd);

        return runner;
    }

}
