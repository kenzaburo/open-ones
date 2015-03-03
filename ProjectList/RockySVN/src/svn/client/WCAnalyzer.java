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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.ISVNLogEntryHandler;
import org.tmatesoft.svn.core.SVNCancelException;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
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
/**
 * This is a SVNClient wrapper. It provides utilities to analyze the working copy folder of SVN.
 * Prerequisite:
 * - The working copy folder is the folder which is checked-out before.
 * 
 * How to use this class:
 * Case 1:
 *  - Create an instance with constructor:
 *    WCAnalyzer wcAnalyzer = WCAnalyzer(String svnUrl, String wcPath, String username, String password)
 *  - Call method start()
 *    wcAnalyzer.start() 
 * @author thachln
 */
public class WCAnalyzer implements ISVNEventHandler {
    private final static Logger LOG = Logger.getLogger("WCAnalyzer");

    private String svnUrl = null;
    private String wcPath = null;
    private String username = null;
    private String password = null;

    /** Null mean latest revision. */
    private static Long rev = null;

    /** Last time run the checkout. */
    private Date lastCheckDate = null;

    SVNWCClient wcClient = null;

    /**
     * Avoid create instance without parameter.
     */
    private WCAnalyzer() {
        // No nothing
    }

    /**
     * Create an instance of analyzer in case of existed working copy (checkout folder). SVNClient to get information
     * from local working copy.
     * 
     * @param wcPath
     *            path of working copy
     * @param username
     * @param password
     */
    public WCAnalyzer(String wcPath, String username, String password) {
        this.wcPath = wcPath;
        this.username = username;
        this.password = password;
    }

    /**
     * Create an instance in case of the working copy folder is empty (no checkout before).
     * 
     * @param svnUrl
     *            Server Url
     * @param wcPath
     *            working copy folder
     * @param username
     * @param password
     */
    public WCAnalyzer(String svnUrl, String wcPath, String username, String password) {
        this.svnUrl = svnUrl;
        this.wcPath = wcPath;
        this.username = username;
        this.password = password;
    }

    /**
     * [Give the description for method].
     * 
     * @param configFile
     *            It loads configuration from resource file "/svn.properties" svnpath= username= password= rev=-1
     * 
     * @return
     */
    public WCAnalyzer(String configFile) {
        try {
            Properties props = new Properties();
            props.load(CommonUtil.loadResource(configFile));

            String revVal = props.getProperty("rev");

            this.svnUrl = props.getProperty("svnUrl");
            this.wcPath = props.getProperty("wcPath");
            this.username = props.getProperty("username");
            this.password = props.getProperty("password");

            rev = null;
            if ((revVal != null) && (!"-1".equalsIgnoreCase(revVal))) {
                rev = Long.valueOf(revVal);
                this.setRev(rev);
            }

        } catch (IOException ex) {
            LOG.error("Could not load resource file '" + configFile + "'", ex);
        }
    }

    /**
     * 
     */
    public void start() {
        // Get start date of SVN repo

        SVNClient svnCLient = new SVNClient(svnUrl, wcPath , username, password);
        Date firstCommittedDte = svnCLient.getFirstCommittedDate();
        
        LOG.debug("The first time committed '" + firstCommittedDte + "'");
        
        // Clean up - in case any modification from local
        doCleanUp();

//        // Update from SVN to local path (working copy folder) for the first version
//        doUpdate(firstCommittedDte);
//        
//        Calendar cal = new GregorianCalendar();
//        cal.setTime(firstCommittedDte);
//        cal.add(Calendar.DATE, +1);
//        
//        // Update next day
//        doUpdate(new Date());
    }
    
    private SVNWCClient getSVNWCClient() {
        if (wcClient == null) {
            SVNClientManager clientManager = getClientManager();

            wcClient = clientManager.getWCClient();
        }

        return wcClient;
    }

    /**
     * [Give the description for method].
     * 
     * @return
     */
    public SVNClientManager getClientManager() {
        DefaultSVNOptions myOptions = SVNWCUtil.createDefaultOptions(true);;
        SVNClientManager clientManager = SVNClientManager.newInstance(myOptions, username, password);

        clientManager.setEventHandler(this);
        return clientManager;
    }

    /**
     * Get value of wcPath.
     * 
     * @return the wcPath
     */
    public String getWcPath() {
        return wcPath;
    }

    public long doCheckout(String svnUrlPath) {
        SVNURL svnUrl;
        try {
            svnUrl = SVNURL.parseURIEncoded(svnUrlPath);
            return doCheckout(svnUrl);
        } catch (SVNException ex) {
            LOG.error("Parse URL path '" + svnUrlPath + "'", ex);
        }

        return -1;
    }

    public long doCheckout(SVNURL url) {
        SVNUpdateClient updateClient = getClientManager().getUpdateClient();
        File path = new File(wcPath);
        SVNRevision svnRev = ((rev == null || rev == -1)) ? SVNRevision.UNDEFINED : SVNRevision.create(rev);

        try {
            lastCheckDate = new Date();
            LOG.debug("Checkout..." + path + " at " + lastCheckDate);
            long lastRev = updateClient.doCheckout(url, path, svnRev, svnRev, SVNDepth.INFINITY, true);
            LOG.debug("lastRev=" + lastRev);

            return lastRev;
        } catch (SVNException ex) {
            LOG.error("Checkout SVN...", ex);
        }

        return -1;
    }

    public long doCheckout(SVNURL url, long rev) {
        SVNUpdateClient updateClient = getClientManager().getUpdateClient();
        File path = new File(wcPath);
        SVNRevision svnRev = SVNRevision.create(rev);

        try {
            lastCheckDate = new Date();
            LOG.debug("Checkout..." + path + " at " + lastCheckDate);
            long lastRev = updateClient.doCheckout(url, path, svnRev, svnRev, SVNDepth.INFINITY, true);
            LOG.debug("lastRev=" + lastRev);

            return lastRev;
        } catch (SVNException ex) {
            LOG.error("Checkout SVN...", ex);
        }

        return -1;
    }
    
    /**
     * @return true if no error
     */
    public boolean doCleanUp() {
        File path = new File(wcPath);
        
        LOG.debug("Do cleaning up '" + wcPath + "'...");
        try {
            getSVNWCClient().doCleanup(path);
        } catch (SVNException ex) {
            LOG.warn("Could not clean up the path '" + path + "'", ex);
            return false;
        }
        
        return true;
    }
    
    public long doUpdate() {
        SVNUpdateClient updateClient = getClientManager().getUpdateClient();
        File path = new File(wcPath);
        SVNRevision svnRev = (rev == null) ? SVNRevision.UNDEFINED : SVNRevision.create(rev);

        try {
            lastCheckDate = new Date();
            LOG.debug("Update..." + path + " at " + lastCheckDate);
            long lastRev = updateClient.doUpdate(path, svnRev, SVNDepth.INFINITY, false, true);
            LOG.debug("lastRev=" + lastRev);

            return lastRev;
        } catch (SVNException ex) {
            LOG.error("Update SVN...", ex);
        }

        return -1;
    }

    /**
     * Check out at the end of date (23:59).
     * 
     * @param endDate
     *            yyyy/MM/dd
     */
    public long doUpdate(String endDate, String pattern) {
        Date dte = CommonUtil.parse(endDate, pattern);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dte);

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);

        return doUpdate(cal.getTime());
    }

    public long doUpdate(Date endDate) {
        SVNUpdateClient updateClient = getClientManager().getUpdateClient();
        File path = new File(wcPath);
        SVNRevision svnRev = SVNRevision.create(endDate);

        try {
            lastCheckDate = endDate;
            LOG.debug("Update..." + path + " at " + lastCheckDate + ";rev=" + svnRev);
            long lastRev = updateClient.doUpdate(path, svnRev, SVNDepth.INFINITY, false, true);
            LOG.debug("lastRev=" + lastRev);

            return lastRev;
        } catch (SVNException ex) {
            LOG.error("Update SVN...", ex);
        }

        return -1;
    }

    /**
     * Get information of current WC folder.
     * 
     * @return
     * @throws SVNException
     */
    public SVNInfo getInfo() throws SVNException {
        if (wcClient == null) {
            wcClient = getSVNWCClient();
        }

        SVNRevision revision = SVNRevision.HEAD;
        File path = new File(wcPath);
        return wcClient.doInfo(path, revision);
    }

    /**
     * Get information of given path of folder or file.
     * The information is based on the latest revision.
     * @param path
     *            file path or folder path
     * @return
     * @throws SVNException
     */
    public SVNInfo getInfo(String path) throws SVNException {
        SVNRevision revision = SVNRevision.HEAD;
        File file = new File(path);
        return getSVNWCClient().doInfo(file, revision);
    }

    public SVNInfo getInfoBase(String path) throws SVNException {
        SVNRevision revision = SVNRevision.WORKING;
        File file = new File(path);
        return getSVNWCClient().doInfo(file, revision);
    }

    
    public SVNInfo getInfo(File file) throws SVNException {
        SVNRevision revision = SVNRevision.HEAD;
        return getSVNWCClient().doInfo(file, revision);
    }

    /**
     * Get value of lastCheckDate.
     * 
     * @return the lastCheckDate
     */
    public Date getLastCheckDate() {
        return lastCheckDate;
    }

    /**
     * Set the value for lastCheckDate.
     * 
     * @param lastCheckDate
     *            the lastCheckDate to set
     */
    public void setLastCheckDate(Date lastCheckDate) {
        this.lastCheckDate = lastCheckDate;
    }

    /**
     * [Explain the description for this method here].
     * 
     * @throws SVNCancelException
     * @see org.tmatesoft.svn.core.ISVNCanceller#checkCancelled()
     */
    @Override
    public void checkCancelled() throws SVNCancelException {
        // TODO Auto-generated method stub

    }

    /**
     * [Explain the description for this method here].
     * 
     * @param arg0
     * @param arg1
     * @throws SVNException
     * @see org.tmatesoft.svn.core.wc.ISVNEventHandler#handleEvent(org.tmatesoft.svn.core.wc.SVNEvent, double)
     */
    @Override
    public void handleEvent(SVNEvent event, double progress) throws SVNException {
        LOG.debug(event.getAction() + " " + event.getFile().getPath());
    }

    /**
     * Get value of rev.
     * 
     * @return the rev
     */
    public static Long getRev() {
        return rev;
    }

    /**
     * Set the value for rev.
     * 
     * @param rev
     *            the rev to set
     */
    public static void setRev(Long rev) {
        WCAnalyzer.rev = rev;
    }

}

/**
 * Capture logs of history to get the first date committed.
 * 
 * @author ThachLN
 *
 */
class AnalyzeLogHandler implements ISVNLogEntryHandler {
    private final static Logger LOG = Logger.getLogger("LogHandler");
    Date firstDateCommitted = new Date();

    /**
     * Get value of the firstDateCommitted.
     * 
     * @return the firstDateCommitted
     */
    public Date getFirstDateCommitted() {
        return firstDateCommitted;
    }

    @Override
    public void handleLogEntry(SVNLogEntry svnLogEntry) throws SVNException {
        LOG.debug("svnLogEntry=" + svnLogEntry.getRevision() + ";date=" + svnLogEntry.getDate() + ";author="
                + svnLogEntry.getAuthor());
        if (firstDateCommitted.after(svnLogEntry.getDate())) {
            firstDateCommitted = svnLogEntry.getDate();
        } else {
            // Do nothing
        }
    }
}