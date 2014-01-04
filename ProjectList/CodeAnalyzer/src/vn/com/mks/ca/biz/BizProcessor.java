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
package vn.com.mks.ca.biz;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.wc.SVNInfo;

import codereport.entity.FileInfo;

import vn.com.mks.ca.ent.FileEntity;
import vn.com.mks.ca.gui.ScreenUpdater;
import vn.mkss.codereporter.SVNAnalyzer;

/**
 * @author ThachLN
 *
 */
public class BizProcessor {
    private final static Logger LOG = Logger.getLogger("BizProcessor");
    /**
     * [Give the description for method].
     * @param path Path of File or Folder
     * @return
     */
    public static List<FileEntity> analyzeFolder(String path) {
        LOG.debug("folderPath=" + path);
        List<FileEntity> lstFileEntity = new ArrayList<FileEntity>();
        
        String username = null;
        String password = null;
        SVNAnalyzer svnAnalyzer = new SVNAnalyzer(path, username, password);
        
        FileEntity fe = new FileEntity();
        fe.setParentPath(path);
        
        SVNInfo fileInfo = svnAnalyzer.getInfo(path);
        fe.setRevision(fileInfo.getCommittedRevision().getNumber());
        
        lstFileEntity.add(fe);
        
        return lstFileEntity;
    }
    /**
     * [Give the description for method].
     * @param paths Path of Files or Folders
     */
    public static List<FileEntity> analyzeFolder(String[] paths) {
        LOG.debug("analyzeFolder:folderPaths=" + paths);
        return null;
    }
}
