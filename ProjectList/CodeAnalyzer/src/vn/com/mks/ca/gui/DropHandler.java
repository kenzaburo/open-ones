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

/**
 * @author thachle
 *
 */
public class DropHandler implements DropTargetListener {
    private final static Logger LOG = Logger.getLogger("DropHandler");
    
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
            }
        }
    }

    @Override
    public void dropAccept(DropTargetEvent event) {
        LOG.debug("event.data=" + event.data);
        
    }

}
