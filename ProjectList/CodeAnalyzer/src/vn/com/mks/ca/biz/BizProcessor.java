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

import java.io.File;
import java.io.FileFilter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.wc.SVNInfo;

import rocky.sizecounter.ISizeCounter;
import rocky.sizecounter.SizeMetaData;
import rocky.sizecounter.UnitType;
import rocky.sizecounter.UnsupportedFileType;
import vn.com.mks.ca.dao.MainSourceDao;
import vn.com.mks.ca.ent.FileEntity;
import vn.mkss.codereporter.SVNAnalyzer;

/**
 * @author ThachLN
 *
 */
public class BizProcessor {
    private final static Logger LOG = Logger.getLogger("BizProcessor");
    private FileFilter fileFilter = null;
    private SVNAnalyzer svnAnalyzer = null;
    private ISizeCounter sizeCounter = null;
    
    public BizProcessor() {
    }
    
    /**
     * Create new instance of BizProcessor with filter.
     * <br/>
     * fileFilter is null: accept all files and folders.
     * @param paths
     */
    public BizProcessor(FileFilter fileFilter) {
        this.fileFilter = fileFilter;
    }

    /**
     * Get detailed information of folder.
     * <br/> 
     * 
     * @param path Path of File or Folder
     * @return
     */
    public List<FileEntity> analyzeFolder(String path) {
        LOG.debug("folderPath=" + path);
        File file = new File(path);
        String projectName = "ProjectName";
        
        // Call dao to parse information into database
        MainSourceDao.analyzeFolder(projectName, path, fileFilter);

        return MainSourceDao.getFileInfo(projectName);
        
        // return analyzeFolder(file);
    }
    
    /**
     * The entry point of analyzing file or folder.
     * @param file File or Folder
     */
    private List<FileEntity> analyzeFolder(File file) {
        List<FileEntity> lstFileEnt = null;
        if (file.isHidden()) {
            // Do nothing
            // Default, skip hidden files or folders
        } else if (file.isFile()) {
            lstFileEnt = new ArrayList<FileEntity>();
            parseFile(file, lstFileEnt);
        } else if (file.isDirectory()) {
            lstFileEnt = new ArrayList<FileEntity>();
            scanPath(file, lstFileEnt);
        }
        
        return lstFileEnt;
    }

    /**
     * [Give the description for method].
     * <br/>
     * The first call must transfer the file is a folder.
     * @param file File or Folder
     * @param lstFileEnt
     */
    private void scanPath(File file, List<FileEntity> lstFileEnt) {
        File[] files;
        if (fileFilter == null) {
            files = file.listFiles();
        } else {
            files = file.listFiles(fileFilter);
        }
        
        int len = (files != null) ? files.length : 0;
        
        for (int i = 0; i < len; i++) {
            if (files[i].isHidden()) {
                // Do nothing
                // Default, skip hidden files or folders
            } else if (files[i].isFile()) {
                parseFile(files[i], lstFileEnt);
            } else if (files[i].isDirectory()) {
                // call recursively
                scanPath(files[i], lstFileEnt);
            }
        }        
    }
    
    /**
     * [Give the description for method].
     * @param path
     * @param lstFileEnt output list. It's prepared from caller.
     */
    private void parseFile(File file, List<FileEntity> lstFileEnt) {
        if ((file == null) || (lstFileEnt == null)) {
            return;
        }
        
        FileEntity fileEnt = new FileEntity();
        
        fileEnt.setParentPath(file.getParent());
        fileEnt.setFileName(file.getName());
        
        long lastModified = file.lastModified();
        if (lastModified != 0L) {
            fileEnt.setModifiedDate(new Date(lastModified)); 
        }
        fileEnt.setSizeKB(file.length() / 1024);
        
        if (svnAnalyzer != null) {
            try {
                SVNInfo fileInfo = svnAnalyzer.getInfo(file);
                if (fileInfo != null) {
                    fileEnt.setRevision(fileInfo.getCommittedRevision().getNumber());
                }
            } catch (RuntimeException svnEx) {
                LOG.warn("Error getting SVN information", svnEx);
            }
        }
        // Count Step
        if (sizeCounter != null) {
            try {
                SizeMetaData smd = sizeCounter.countSize(file.getPath());

                if (smd.getUnit() == UnitType.LOC) {
                    fileEnt.setNumStep(smd.getSize());
                    fileEnt.setNumComment(smd.getComment());
                    fileEnt.setNumBlank(smd.getBlank());
                }
            } catch (UnsupportedFileType ex) {
                LOG.warn("Unsupported file '" + file.getPath() + "'");
            }
        }

        // Append to list
        lstFileEnt.add(fileEnt);
    }

    /**
     * [Give the description for method].
     * <br/>
     * List of input paths to be parsed: arrPath
     * @param paths Path of Files or Folders
     * @return Map of (path, list of FileEntity)
     */
    public Map<String, List<FileEntity>> analyzeFolder(String[] paths) {
        Map<String, List<FileEntity>> mapResult = new HashMap<String, List<FileEntity>>();
        LOG.debug("paths=" + paths);
        int len = (paths != null) ? paths.length : 0;
        
        List<FileEntity> lstFileEnt;
        for (int i = 0; i < len; i++) {
            lstFileEnt = analyzeFolder(paths[i]);
            mapResult.put(paths[i], lstFileEnt);
        }
        
        return mapResult;
    }

    /**
     * Get value of sizeCounter.
     * @return the sizeCounter
     */
    public ISizeCounter getSizeCounter() {
        return sizeCounter;
    }

    /**
     * Set the value for sizeCounter.
     * @param sizeCounter the sizeCounter to set
     */
    public void setSizeCounter(ISizeCounter sizeCounter) {
        this.sizeCounter = sizeCounter;
    }
}
