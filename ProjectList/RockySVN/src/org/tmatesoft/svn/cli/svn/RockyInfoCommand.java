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
package org.tmatesoft.svn.cli.svn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.cli.SVNCommandUtil;
import org.tmatesoft.svn.cli.svn.SVNInfoCommand;
import org.tmatesoft.svn.cli.svn.SVNOption;
import org.tmatesoft.svn.cli.svn.SVNXMLCommand;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNErrorCode;
import org.tmatesoft.svn.core.SVNErrorMessage;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.internal.util.SVNPathUtil;
import org.tmatesoft.svn.core.internal.wc.SVNErrorManager;
import org.tmatesoft.svn.core.internal.wc.SVNPath;
import org.tmatesoft.svn.core.wc.ISVNInfoHandler;
import org.tmatesoft.svn.core.wc.SVNInfo;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCClient;
import org.tmatesoft.svn.util.SVNLogType;

/**
 * @author thachle
 *
 */
public class RockyInfoCommand extends SVNXMLCommand implements ISVNInfoHandler {
    private final static Logger LOG = Logger.getLogger("InfoCommand");
    
    private SVNInfo svnInfo;
    public RockyInfoCommand() {
        super("RockyInfo", null);
    }

    @Override
    public void handleInfo(SVNInfo info) throws SVNException {
        this.svnInfo = info;
        String path = null;
        if (info.getFile() != null) {
            path = getSVNEnvironment().getRelativePath(info.getFile());
            path = SVNCommandUtil.getLocalPath(path);
        } else {
            path = info.getPath();
            if ("".equals(path) && info.getURL() != null) {
                path = SVNPathUtil.tail(info.getURL().getPath());
            }
        }
        LOG.debug("Path=" + path);
        LOG.debug("Revision = " + info.getRevision());
        LOG.debug("Revision = " + info.getAuthor());
         
    }

    @Override
    protected Collection createSupportedOptions() {
        Collection options = new LinkedList();
        options.add(SVNOption.REVISION);
        options.add(SVNOption.RECURSIVE);
        options.add(SVNOption.DEPTH);
        options.add(SVNOption.TARGETS);
        options.add(SVNOption.INCREMENTAL);
        options.add(SVNOption.XML);
        options.add(SVNOption.CHANGELIST);
        return options;
    }

    @Override
    public void run() throws SVNException {
        List targets = new ArrayList(); 
        if (getSVNEnvironment().getTargets() != null) {
            targets.addAll(getSVNEnvironment().getTargets());
        }
        targets = getSVNEnvironment().combineTargets(targets, true);
        if (targets.isEmpty()) {
            targets.add("");
        }
        if (getSVNEnvironment().isXML()) {
            if (!getSVNEnvironment().isIncremental()) {
                printXMLHeader("info");
            }
        } else if (getSVNEnvironment().isIncremental()) {
            SVNErrorManager.error(SVNErrorMessage.create(SVNErrorCode.CL_ARG_PARSING_ERROR, "'incremental' option only valid in XML mode"), SVNLogType.CLIENT);
        }
        SVNDepth depth = getSVNEnvironment().getDepth();
        if (depth == SVNDepth.UNKNOWN) {
            depth = SVNDepth.EMPTY;
        }
        SVNWCClient client = getSVNEnvironment().getClientManager().getWCClient();
        boolean seenNonExistingTargets = false;
        for(int i = 0; i < targets.size(); i++) {
            String targetName = (String) targets.get(i);
            SVNPath target = new SVNPath(targetName, true);
            SVNRevision pegRevision = target.getPegRevision();
            if (target.isURL() && pegRevision == SVNRevision.UNDEFINED) {
                pegRevision = SVNRevision.HEAD;
            }
            try {
                if (target.isFile()) {
                    client.doInfo(target.getFile(), pegRevision, getSVNEnvironment().getStartRevision(), depth, 
                            getSVNEnvironment().getChangelistsCollection(), this);
                } else {
                    client.doInfo(target.getURL(), pegRevision, getSVNEnvironment().getStartRevision(), depth, this);
                }
            } catch (SVNException e) {
                SVNErrorMessage err = e.getErrorMessage();
                if (err.getErrorCode() == SVNErrorCode.UNVERSIONED_RESOURCE) {
                    getSVNEnvironment().getErr().print(SVNCommandUtil.getLocalPath(target.getTarget()) + ": (Not a versioned resource)\n\n");
                }  else {
                    getSVNEnvironment().handleWarning(err, new SVNErrorCode[] {SVNErrorCode.RA_ILLEGAL_URL, SVNErrorCode.WC_PATH_NOT_FOUND}, 
                        getSVNEnvironment().isQuiet());
                    getSVNEnvironment().getErr().println();
                    seenNonExistingTargets = true;
                }
            }
        }
        if (getSVNEnvironment().isXML() && !getSVNEnvironment().isIncremental()) {
            printXMLFooter("info");
        }
        if (seenNonExistingTargets) {
            SVNErrorManager.error(SVNErrorMessage.create(SVNErrorCode.ILLEGAL_TARGET, "Could not display info for all targets because some targets don't exist"), SVNLogType.CLIENT);
        }
    }

    /**
     * Get value of svnInfo.
     * @return the svnInfo
     */
    public SVNInfo getSvnInfo() {
        return svnInfo;
    }

    /**
     * Set the value for svnInfo.
     * @param svnInfo the svnInfo to set
     */
    public void setSvnInfo(SVNInfo svnInfo) {
        this.svnInfo = svnInfo;
    }

}
