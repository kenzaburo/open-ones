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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Configuration info for scheduler
 * @author thachle
 */
public class Setting {
    private final static Logger LOG = Logger.getLogger("Setting");
    /** Hourly flag. */
    private boolean isHourly;

    /*- 
     * Format of items: HH[:mm]
     * 1
     * 2:30
     *  
     **/
    private List<String> lstHour = new ArrayList<String>();

    /**
     * Get value of isHourly.
     * @return the isHourly
     */
    public boolean isHourly() {
        return isHourly;
    }

    /**
     * Set the value for isHourly.
     * @param isHourly the isHourly to set
     */
    public void setHourly(boolean isHourly) {
        this.isHourly = isHourly;
    }

    /**
     * Get value of lstHour.
     * @return the lstHour
     */
    public List<String> getLstHour() {
        return lstHour;
    }

    /**
     * Set the value for lstHour.
     * @param lstHour the lstHour to set
     */
    public void setLstHour(List<String> lstHour) {
        this.lstHour = lstHour;
    }

    /**
     * [Give the description for method].
     * @param period HH:mm
     * @return true: Add success
     * false: parameter is null or invalid format "HH:mm" or "HH"
     */
    public boolean addTime(String period) {
        if (period == null) {
            return false;
        }
        
        if (period.matches("\\d\\d?(:\\d\\d?)?")) {
            lstHour.add(period);
            return true;
        } else {
            LOG.debug(period + " did not matched with pattern HH:mm, HH");
            return false;
        }
    }
}
