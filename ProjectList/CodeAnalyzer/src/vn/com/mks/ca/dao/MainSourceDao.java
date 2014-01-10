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
package vn.com.mks.ca.dao;

import java.io.FileFilter;
import java.util.List;

import javax.persistence.EntityManager;

import codereport.daocontroller.FileInfoJpaController;
import codereport.entity.FileInfo;

import vn.com.mks.ca.AppUtil;
import vn.com.mks.ca.ent.FileEntity;
import vn.mkss.codereporter.AppSVNReporter;
import vn.mkss.codeworker.BasePersistentManager;

/**
 * @author ThachLN
 *
 */
public class MainSourceDao extends BasePersistentManager {
    public static void analyzeFolder(String projectName, String wcPath, FileFilter fileFilter) {
        AppSVNReporter svnReporter = new AppSVNReporter(projectName, wcPath, fileFilter);
        
        svnReporter.start();
    }

    /**
     * [Give the description for method].
     * @param projectName
     */
    public static List<FileEntity> getFileInfo(String projectName) {
        EntityManager em = getEM();
        FileInfoJpaController fileInfoJpaController = new FileInfoJpaController(em.getEntityManagerFactory());
        
        List<FileInfo> lstFileInfo = fileInfoJpaController.findFileInfoEntities();
        
        return AppUtil.convertFileInfos2FileEntities(lstFileInfo);
    }
}
