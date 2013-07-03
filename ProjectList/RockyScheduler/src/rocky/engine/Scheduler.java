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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import app.Setting;

/**
 * @author thachln
 */
public class Scheduler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> scheduleHandler;
    
    private Runnable runner;
    private Setting setting;
    
    public Scheduler(Setting setting, Runnable runner) {
        this.setting = setting;
        this.runner = runner;        
    }

    public Scheduler(ScheduledFuture<?> scheduleHandler) {
        this.scheduleHandler = scheduleHandler;
    }
    
    public static void main(String[] args) {
        if (args.length < 2) {
            usage();
            System.exit(1);
        }
        
        String settingFile = args[0];
        String cmd  = args[0];
        Setting setting = AppUtil.loadSetting(settingFile);
        Runnable runner = Runner.newInstance(cmd);
        
        Scheduler scheduler = new Scheduler(setting, runner);
        
    }
    
    private static void usage() {
        System.out.println("Scheduler <setting file path> <cmd line>");
    }

    public void start() {
        
        scheduler.schedule(runner, 60 * 60, TimeUnit.SECONDS);
    }
}
