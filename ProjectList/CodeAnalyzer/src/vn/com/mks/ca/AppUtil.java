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
package vn.com.mks.ca;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import rocky.common.BeanUtil;
import rocky.common.CommonUtil;
import rocky.common.Constant;
import vn.com.mks.ca.ent.FileEntity;

/**
 * @author thachle
 *
 */
public class AppUtil {
    final private static Logger LOG = Logger.getLogger("AppUtil");
    
    /**
     * [Give the description for method].
     * @param properties list of property of entity FileEntity to be displayed
     * @param fe
     * @return
     */
    public static String[] formatDataRow(String[] properties, FileEntity fe) {
        int len = (properties != null) ? properties.length : 0;
        String[] columns = new String[len];
        String propertyName = null;
        try {
            Map<String, Method> mapReadMethod = BeanUtil.getReadMethodMap(fe);
            Method method = null;
            Object result;
            for (int i = 0; i <  len; i++) {
                propertyName = properties[i];
                method = mapReadMethod.get(propertyName);
                result = method.invoke(fe);
                
                columns[i] = formatData(result);
            }
        } catch (Exception ex) {
            LOG.error("Parse read method of FileEntity:" + propertyName, ex);
        }

        return columns;
    }
    
    /**
     * Format data for displaying.
     * @param data
     * @return
     */
    private static String formatData(Object data) {
        if (data == null) {
            return null;
        }
        
        String result;
        if (data instanceof Date) {
            result = CommonUtil.formatDate((Date) data, Constant.DEF_DATEFMT); 
        } else {
            result = data.toString();
        }
        
        return result;
    }
}
