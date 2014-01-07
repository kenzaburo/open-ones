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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import vn.com.mks.ca.AppUtil;
import vn.com.mks.ca.Setting;
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
                // Map(Path, List of FileEntity
                Collection<String> keys = resultData.keySet();
                
                displayTreeView(keys, resultData.values());
                
                // Get first element
                List<FileEntity> lstFileEnt = (List<FileEntity>) resultData.values().iterator().next();
                
                displayLoadFolder(lstFileEnt);
                break;
        }
        
        
    }

    /**
     * [Give the description for method].
     * @param path Collection of String 
     * @param fileEntities Collection of FileEntity
     */
    private void displayTreeView(Collection<String> paths, Collection<Object> fileEntities) {
        Tree tree = window.tree;
        clearTreeItems(tree);
        addTreeItems(tree, paths, fileEntities);
    }

    /**
     * [Give the description for method].
     * @param tree
     */
    private void clearTreeItems(Tree tree) {
        if (tree == null) {
            return;
        }

        // Remove rows
        int nItem = tree.getItemCount();
        for (int i = nItem - 1; i >= 0; i--) {
            tree.getItem(i).dispose();
        }
    }


    /**
     * [Give the description for method].
     * @param tree
     * @param paths 
     * @param fileEntities Collection of FileEntity
     */
    private void addTreeItems(Tree tree, Collection<String> paths, Collection<Object> fileEntities) {
        TreeItem trtmRoot = new TreeItem(tree, SWT.NONE);
        trtmRoot.setText("All");
        
        String path;
        TreeItem trtmFolder;
        for (Iterator<String> it = paths.iterator(); it.hasNext(); ) {
            path = it.next();
            trtmFolder = new TreeItem(trtmRoot, SWT.NONE);
            trtmFolder.setText(path);
            trtmFolder.setData(path, fileEntities);
        }
        
        trtmRoot.setExpanded(true);
    }
    /**
     * Display detailed information into the TableView.
     * @param lstFileEnt
     */
    private void displayLoadFolder(List<FileEntity> lstFileEnt) {
        int len = (lstFileEnt != null) ? lstFileEnt.size() : 0; 
        LOG.debug("len=" + len);
        
        // Display TableView
        Table table = window.table;
        // Setting headers
        
        clearColumns(table);
        
        // Add new columns
        Setting setting = new Setting();
        String tabName = "Code counter";
        String[] headers = setting.getHeaders(tabName);
        String[] properties = setting.getProperties(tabName);
        int[] sizes = setting.getSizes(tabName);
        
        addColumns(table, headers, sizes);
        // Add data row
        FileEntity fileEnt;
        String[] rowData;
        TableItem tableItem;
        for (int i = 0; i < len; i++) {
            
            fileEnt = lstFileEnt.get(i);
            rowData = AppUtil.formatDataRow(properties, fileEnt);

            tableItem = new TableItem(table, SWT.NONE);
            tableItem.setText(rowData);
        }
    }


    /**
     * Clear dispose all rows and columns from table.
     * @param table to be disposed rows and columns
     */
    private void clearColumns(Table table) {
        if (table == null) {
            return;
        }

        // Remove rows
        int nItem = table.getItemCount();
        for (int i = nItem - 1; i >= 0; i--) {
            table.getItem(i).dispose();
        }
        
        // Clear columns
        int nColumn = table.getColumnCount();
        for (int i = nColumn - 1; i >= 0; i--) {
            table.getColumn(i).dispose();
        }
    }
    
    /**
     * Add columns to the table.
     * @param table to be added columns
     * @param headers header names
     * @param sizes size of column
     */
    private void addColumns(Table table, String[] headers, int[] sizes) {
        int len = (headers != null) ? headers.length : 0;

        TableColumn column;
        for (int i = 0; i < len; i++) {
            column = new TableColumn(table, SWT.NONE);
            column.setText(headers[i]);
            column.setWidth(sizes[i]);
        }

    }
}
