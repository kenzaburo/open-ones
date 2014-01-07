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
package vn.com.mks.ca.gui;

import java.io.FileFilter;

import org.apache.log4j.Logger;

import rocky.sizecounter.ISizeCounter;
import rocky.sizecounter.SizeCounterImpl;

import vn.com.mks.ca.biz.BizProcessor;

/**
 * Entry point of the application
 * @author thachln
 *
 */
public class CodeAnalyzer {
    final static Logger LOG = Logger.getLogger("CodeAnalyzer");
    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            CodeAnalyzerGUI window = new CodeAnalyzerGUI();
            
            // Setting ScreenUpdater
            ScreenUpdater screenUpdater = new ScreenUpdater(window);
            
            // Setting Event Handler
            AppEventHandler eventHandler = new AppEventHandler(screenUpdater);
            
            // Create FileFilter
            ISizeCounter sizeCounter = new SizeCounterImpl();
            FileFilter fileFilter = new FileFilterImpl(sizeCounter);
            // Setting Business Processor
            BizProcessor bizProcessor = new BizProcessor(fileFilter);
            bizProcessor.setSizeCounter(sizeCounter);
            eventHandler.setBizProcessor(bizProcessor);
            
            window.setEventHandler(eventHandler);
            
            window.open();
        } catch (Exception ex) {
            LOG.error("Unknown error.", ex);
        }
    }
}
