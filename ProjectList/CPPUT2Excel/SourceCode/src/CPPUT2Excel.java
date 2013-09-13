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

import java.io.File;
import java.io.FilenameFilter;

import org.apache.log4j.Logger;

import rocky.common.CommonUtil;
import vn.mks.engine.ireport.IOutput;

/**
 * @author thachle
 */
public class CPPUT2Excel implements FilenameFilter {
    private final static Logger LOG = Logger.getLogger("RockyCPPChecker");
    private final static String[] ACCEPT_EXTENSIONS = {"h", "cpp"}; 
    private String sourcePath;
    private String resourceTemplate;
    private String outputFolder;

    /** Optional parameter. */
    private String encoding;

    IOutput outputer = null;
    File[] lstFile;
    /**
     * @param sourcePath
     * @param resourceTemplate
     * @param outputFolder
     */
    public CPPUT2Excel(String sourcePath, String resourceTemplate, String outputFolder) {
        super();
        this.sourcePath = sourcePath;
        this.resourceTemplate = resourceTemplate;
        this.outputFolder = outputFolder;
        //IOutput outputer = IOutput.newInstance();
    }

    public static void main(String[] args) {
        if ((args == null) || (args.length < 3)) {
            usage();
            System.exit(1);
        }

        CPPUT2Excel checker = new CPPUT2Excel(args[0], args[1], args[2]);

        if (args.length > 3) {
            checker.setEncoding(args[3]);
        }
        checker.start();

    }

    /**
     * Setting encoding of file to be checked.
     * @param encoding Ex: UTF-8, SHIFT_JIS,...
     */
    private void setEncoding(String encoding) {
        this.encoding = encoding;

    }

    /**
     * Scan files JAVA in folder recursively to check bugs.
     * @param folder
     */
    private void scanFolder(File folder) {
        // Scan file Java
        File[] lstFile = folder.listFiles(this);

        if (lstFile != null) {
            // Scan files
            for (File ff : lstFile) {
                if (ff.isFile()) {
                    LOG.debug("File:" + ff.getPath());
                    //report.writeBug(ff.getParent(), ff.getName(), check(ff));
                }
            }

            // Scan folders
            for (File ff : lstFile) {
                if (ff.isDirectory()) {
                    scanFolder(ff);
                }
            }
        }
    }

    /**
     * Scan files Java in the folder recursively to check bugs.
     */
    public void start() {
        File file = new File(sourcePath);
        //report = new BugReport(resourceTemplate);

        if (file.isFile()) {
            //List<BugInfo> lstBug = check(file);
            //report.writeBug(file.getPath(), file.getName(), lstBug);
        }
        if (file.isDirectory()) {
            scanFolder(file);
        }

        //report.toFile(outputFolder);
    }

//    private List<BugInfo> check(File file) {
//        return checker.check(file); // No Processor
//    }

    /**
     * Implementer of FilenameFilter. Accept extension file .java (ignore case)
     * @param dir
     * @param name
     * @return
     */
    @Override
    public boolean accept(File dir, String name) {
        
        File f = new File(name);
        
        if (f.isDirectory()) {
            return true;
        } else {
            String ext = CommonUtil.getExtension(name);
            return (CommonUtil.find(ACCEPT_EXTENSIONS, ext) >= 0); 
        }
    }

    private static void usage() {
        System.out.println("CPPUT2Excel <source-path> <resource-template> <output-folder> [encoding]");
    }
}
