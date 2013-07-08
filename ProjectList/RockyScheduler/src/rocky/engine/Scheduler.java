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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import rocky.common.CommonUtil;
import app.Setting;

/**
 * @author thachln
 */
public class Scheduler {
    private final static Logger LOG = Logger.getLogger("Scheduler");
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    //private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
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
    
    public static int main(String[] args) {
        if (args.length < 2) {
            usage();
            System.exit(1);
        }
        
        String settingFile = args[0];
        String cmd  = args[1];
        Setting setting = AppUtil.loadSetting(settingFile);
        Runner runner = Runner.newInstance(cmd);
        
        
        Scheduler scheduler = new Scheduler(setting, runner);
        runner.setExecutorService(scheduler.getExecutor());
        
        scheduler.start();
        
        
        
        return 0;
    }
    
    public ScheduledExecutorService getExecutor() {
        return executor;
    }

    private static void usage() {
        System.out.println("Scheduler <setting file path> <cmd line>");
    }

    public void start() {
        List<Date> lstDate = parseSetting(setting);
        
        LOG.debug("Current time: " + new Date() + ". Check the scheduler...");
        if (CommonUtil.isNNandNB(lstDate)) {
            Date firstPeriod = lstDate.get(0);
            long delayedTime = (firstPeriod.getTime() - System.currentTimeMillis()) / 1000;;
            delayedTime = 1;
            LOG.info("Start scheduled task at " + firstPeriod + ". Waiting " + delayedTime + " seconds..." );
            runner = new Runnable() {
                @Override
                public void run() {
                    LOG.debug("done");
                }
            };
            scheduleHandler = executor.schedule(runner, 500, TimeUnit.MILLISECONDS);
            //scheduleHandler = executor.scheduleAtFixedRate(runner, 1, 2, TimeUnit.SECONDS);
            
            LOG.debug("scheduleHandler.isCancelled()=" + scheduleHandler.isCancelled());
            LOG.debug("scheduleHandler.isDone()=" + scheduleHandler.isDone());

            // executor.scheduleAtFixedRate(runner, delayedTime, 600, TimeUnit.MILLISECONDS);
        }
    }

    private List<Date> parseSetting(Setting setting) {
        List<Date> lstDte = new ArrayList<Date>();
        List<String> lstHour = setting.getLstHour();
        int len = (lstHour != null) ? lstHour.size(): 0;
        
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis()); // get current date
        cal.set(Calendar.SECOND, 0);
        
        String hourStr; // HH:mm, HH
        String hour;
        String minute;
        for (int i = 0; i < len; i++) {
            hourStr = lstHour.get(i);
            hour = CommonUtil.parsePattern(hourStr, "(\\d\\d?).*");
            minute = CommonUtil.parsePattern(hourStr, "\\d\\d?:(\\d\\d?)");
            
            cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hour));
            
            if (CommonUtil.isNNandNB(minute)) {
                cal.set(Calendar.MINUTE, Integer.valueOf(minute));
            } else {
                cal.set(Calendar.MINUTE, 0);
            }
            
            lstDte.add(cal.getTime());
        }
        
        return lstDte;
    }
}
