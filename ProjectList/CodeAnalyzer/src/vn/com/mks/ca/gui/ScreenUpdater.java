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

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import vn.com.mks.ca.AppCons;
import vn.com.mks.ca.ent.FileEntity;

/**
 * @author ThachLN
 *
 */
public class ScreenUpdater implements IScreenUpdater {
    final static Logger LOG = Logger.getLogger("ScreenUpdater");
    CodeAnalyzerGUI window;
    /**
     * @param window
     */
    public ScreenUpdater(CodeAnalyzerGUI window) {
        this.window = window;
    }

    /**
     * [Explain the description for this method here].
     * @param cmdCd
     * @param resultData
     * @see vn.com.mks.ca.gui.IScreenUpdater#postCommand(int, java.util.Map)
     */
    @Override
    public void postCommand(int cmdCd, Map<String, Object> resultData) {
        LOG.debug("cmdCd=" + cmdCd);
        switch (cmdCd) {
            case Command.CMD_OPEN_FOLDER:
                String key = Integer.valueOf(cmdCd).toString();
                List<FileEntity> lstFileEnt = (List<FileEntity>) resultData.get(key);
                displayLoadFolder(lstFileEnt);
                break;
        }
        
        
    }

    /**
     * Display detailed information into the TableView.
     * @param lstFileEnt
     */
    private void displayLoadFolder(List<FileEntity> lstFileEnt) {
        int len = (lstFileEnt != null) ? lstFileEnt.size() : 0; 
        LOG.debug("len=" + len);
        Table table = window.table;
        
        FileEntity fileEnt;
        String[] rowData;
        TableItem tableItem;
        for (int i = 0; i < len; i++) {
            fileEnt = lstFileEnt.get(i);
            rowData = new String[4];
            rowData[0] = fileEnt.getParentPath();
            rowData[1] = fileEnt.getFileName();
            rowData[2] = fileEnt.getRevision().toString();
            rowData[3] = String.valueOf(fileEnt.getStep());
            tableItem = new TableItem(table, SWT.NONE);
            tableItem.setText(rowData);
        }
    }
}
