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

import org.apache.log4j.Logger;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import vn.com.mks.ca.biz.BizProcessor;

/**
 * @author ThachLN
 *
 */
public class AppEventHandler extends SelectionAdapter implements DropTargetListener, IEventHandler {
    final static Logger LOG = Logger.getLogger("AppEventHandler");
    
    IEventHandler eventProcessor;
    
    /**
     * Constructor with event handler
     * @param eventProcessor EventHandler
     * <br/>
     * Drap and Drop event
     */
    public AppEventHandler() {
        this.eventProcessor = this;
    }
    
    /**
     * Constructor with event handler
     * @param eventProcessor EventHandler
     * <br/>
     * Drap and Drop event
     */
    public AppEventHandler(IEventHandler eventProcessor) {
        this.eventProcessor = eventProcessor;
    }
    
    // Override method of SelectionAdapter - Start
    /**
     * Process events.
     * <br/>
     * From:
     * Menu
     * Toolbar
     * @param e
     * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    public void widgetSelected(SelectionEvent event) {
        if ((event != null) && (event.item != null)) {
            Object eventData = event.item.getData();
            LOG.info("item data:" + eventData);
            if (eventData instanceof Integer) {
                // Maybe it's command
                int cmdCd = (Integer) eventData;
            }
        }
    }
    
 // Override method of SelectionAdapter - End
    
    // Implementation of DropTargetListener - Start
    @Override
    public void dragEnter(DropTargetEvent event) {
        LOG.debug("event.data=" + event.data);
        
    }

    @Override
    public void dragLeave(DropTargetEvent event) {
        LOG.debug("event.data=" + event.data + ";event.getSource()=" + event.getSource().getClass());
        DropTarget source = (DropTarget) event.getSource();
    }

    @Override
    public void dragOperationChanged(DropTargetEvent event) {
        LOG.debug("event.data=" + event.data);
        
    }

    @Override
    public void dragOver(DropTargetEvent event) {
        // LOG.debug("event.data=" + event.data);
        
    }

    /**
     * Process event drop the files or folders from other application.
     * @param event
     * @see org.eclipse.swt.dnd.DropTargetListener#drop(org.eclipse.swt.dnd.DropTargetEvent)
     */
    @Override
    public void drop(DropTargetEvent event) {
        LOG.debug("event.data=" + event.data);
        if (event.data != null) {
            LOG.debug("event.data=" + event.data.getClass());
            if (event.data instanceof String[]) {
                String[] arrData = (String[]) event.data;
                int len = (arrData != null) ? arrData.length : 0;
                for (int i = 0; i < len; i++) {
                    LOG.debug("event.data[" + i + "]:" + arrData[i]);
                }
                if (len == 1) {
                    // Only one folder
                    processEvent(Command.CMD_OPEN_FOLDER, arrData[0]);
                } else {
                    // Many folders
                    processEvent(Command.CMD_OPEN_FOLDER, arrData);
                }
            }
        }
    }

    @Override
    public void dropAccept(DropTargetEvent event) {
        LOG.debug("event.data=" + event.data);
        
    }

    // Implementation of DropTargetListener - End
    
    // Implementation of IEventHandler - Start
    
    /**
     * [Explain the description for this method here].
     * @param cmdCd
     * @param data
     * @see vn.com.mks.ca.gui.IEventHandler#processEvent(int, java.lang.Object)
     */
    @Override
    public void processEvent(int cmdCd, Object data) {
        switch (cmdCd) {
            case Command.CMD_OPEN_FOLDER :
                if (data instanceof String) {
                    String folderPath = (String) data;
                    BizProcessor.analyzeFolder(folderPath);
                } else if (data instanceof String[]) {
                    String[] folderPaths = (String[]) data;
                    BizProcessor.analyzeFolder(folderPaths);
                }
                break;
            default :
                break;
        }

    }
    // Implementation of IEventHandler - End
}
