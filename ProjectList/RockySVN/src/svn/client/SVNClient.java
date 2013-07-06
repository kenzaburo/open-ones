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
package svn.client;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.SVNCancelException;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.ISVNEventHandler;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNEvent;
import org.tmatesoft.svn.core.wc.SVNInfo;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import rocky.common.CommonUtil;
import rocky.common.PropertiesManager;
/**
 * @author thachln
 */
public class SVNClient implements ISVNEventHandler {
    private final static Logger LOG = Logger.getLogger("ClientInformer");
    
    /**  . */
    public static final String DSP_DTE_FMT = "yyyy/MM/dd HH:mm";
    
    private static Properties props;

    private static String wcPath = null;
    private static String username = null;
    private static String password = null;
    
    /** Null mean latest revision. */
    private static Long rev = null;
    
    /** Last time run the checkout. */
    private Date lastCheckDate = null;

    SVNWCClient wcClient = null;

    static {
        try {
            props = PropertiesManager.newInstanceFromProps("/svn.properties");
            wcPath = props.getProperty("svnpath");
            username = props.getProperty("username");
            password = props.getProperty("password");
            String revVal = props.getProperty("rev");
            if ((rev != null) && (!"-1".equalsIgnoreCase(revVal))) {
                rev = Long.valueOf(revVal);
            }
        } catch (IOException ex) {
            LOG.error("Could not load resource file /svn.properties", ex);
        }
    }

    /**
     * [Give the description for method].
     * 
     * @param args
     */
    public static void main(String[] args) {
//        SVNClientManager clientManager = getClientManager();
//
//        SVNWCClient wcClient = clientManager.getWCClient();
//
//        SVNRevision revision = SVNRevision.HEAD;
//        File path = new File(wcPath);
//        try {
//            SVNInfo info = wcClient.doInfo(path, revision);
//            LOG.debug("URL " + info.getURL());
//            LOG.debug("CommittedDate:" + CommonUtil.formatDate(info.getCommittedDate(), "yyyy/MM/dd HH:mm"));
//            LOG.debug("Revision:" + info.getRevision().getNumber());
//            LOG.debug("Last Committer:" + info.getAuthor());
//
//        } catch (SVNException ex) {
//            LOG.error("", ex);
//        }

    }

    
    public SVNClient(String wcPath, String username, String password) {
        this.wcPath = wcPath;
        this.username = username;
        this.password = password;
    }

    /**
     * 
     */
    public SVNClient() {
        // TODO Auto-generated constructor stub
    }


    public SVNWCClient getSVNClient() {
        SVNClientManager clientManager = getClientManager();
        
        return clientManager.getWCClient();
    }

    /**
     * [Give the description for method].
     * @return
     */
    public SVNClientManager getClientManager() {
        DefaultSVNOptions myOptions = SVNWCUtil.createDefaultOptions(true);;
        SVNClientManager clientManager = SVNClientManager.newInstance(myOptions, username, password);
        
        clientManager.setEventHandler(this);
        return clientManager;
    }

    public void doCheckOut() {
        SVNUpdateClient updateClient = getClientManager().getUpdateClient();
        File path = new File(wcPath);
        SVNRevision svnRev = (rev == null) ? SVNRevision.UNDEFINED : SVNRevision.create(rev);

        try {
            lastCheckDate = new Date();
            LOG.debug("Update..." + path + " at " + CommonUtil.formatDate(lastCheckDate, DSP_DTE_FMT));
            long lastRev = updateClient.doUpdate(path, svnRev,  SVNDepth.INFINITY, false, true);
            LOG.debug("lastRev=" + lastRev);
        } catch (SVNException ex) {
            LOG.error("Update SVN...", ex);
        }
    }
    
    public SVNInfo getInfo() throws SVNException {
        if (wcClient == null) {
            wcClient = getSVNClient();
        }

        SVNRevision revision = SVNRevision.HEAD;
        File path = new File(wcPath);
        return wcClient.doInfo(path, revision);
    }

    /**
     * Get value of lastCheckDate.
     * @return the lastCheckDate
     */
    public Date getLastCheckDate() {
        return lastCheckDate;
    }

    /**
     * Set the value for lastCheckDate.
     * @param lastCheckDate the lastCheckDate to set
     */
    public void setLastCheckDate(Date lastCheckDate) {
        this.lastCheckDate = lastCheckDate;
    }

    /**
     * [Explain the description for this method here].
     * @throws SVNCancelException
     * @see org.tmatesoft.svn.core.ISVNCanceller#checkCancelled()
     */
    @Override
    public void checkCancelled() throws SVNCancelException {
        // TODO Auto-generated method stub
        
    }

    /**
     * [Explain the description for this method here].
     * @param arg0
     * @param arg1
     * @throws SVNException
     * @see org.tmatesoft.svn.core.wc.ISVNEventHandler#handleEvent(org.tmatesoft.svn.core.wc.SVNEvent, double)
     */
    @Override
    public void handleEvent(SVNEvent event, double progress) throws SVNException {
        LOG.debug(event.getAction() + " " + event.getFile().getPath());
    }

}
