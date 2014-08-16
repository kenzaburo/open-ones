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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import rocky.common.CommonUtil;
import rocky.common.Constant;
import rocky.common.PropertiesManager;

/**
 * @author thachln
 *
 */
public class Cache {
    static final private Logger LOG = Logger.getLogger(Cache.class);
    
    /** Current folder is as default . */
    private static String cacheFilePath = "CacheFile.xml";
    private String outputCache = null;
    
    Properties props = null; 
//    static {
//        try {
//            props = PropertiesManager.newInstanceFromXML(cacheFilePath);
//        } catch (IOException ex) {
//            LOG.error("Could not load file '" + cacheFilePath + "'", ex);
//        }
//    }
    
    public Cache() {
        
    }
    
    public Cache(String propertyFilePath) {
        cacheFilePath = propertyFilePath;
        try {
            props = PropertiesManager.newInstanceFromXML(cacheFilePath);
        } catch (IOException ex) {
            LOG.error("Could not load file '" + cacheFilePath + "'", ex);
        }
    }
    
    public void add(String key, String value) {
        props.put(key, value);
    }
    
    public boolean contain(String key) {
        return props.containsKey(key);
    }
    
    public String getValue(String key) {
        if (key == null) {
            return null;
        }

        return (String) props.get(key);
    }

    public void save() {
        String outputFilePath = (CommonUtil.isNNandNB(outputCache) ? outputCache : cacheFilePath);

        try {
            FileOutputStream fos = new FileOutputStream(outputFilePath);
            props.storeToXML(fos, "Simple file cache updated as " + new Date(), Constant.DEF_ENCODE);
        } catch (FileNotFoundException ex) {
            LOG.error("Could not found file: " + outputFilePath, ex);
        } catch (IOException ex) {
            LOG.error("Could write to file: " + outputFilePath, ex);
        }
    }

    /**
     * Get value of outputCache.
     * @return the outputCache
     */
    public String getOutputCache() {
        return outputCache;
    }

    /**
     * Set the value for outputCache.
     * @param outputCache the outputCache to set
     */
    public void setOutputCache(String outputCache) {
        this.outputCache = outputCache;
    }
}
