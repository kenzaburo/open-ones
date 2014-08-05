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
import java.util.Properties;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.SVNCancelException;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.util.SVNURLUtil;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.internal.wc17.db.statement.SVNWCDbSchema.WORKING_NODE__Fields;
import org.tmatesoft.svn.core.wc.ISVNEventHandler;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNEvent;
import org.tmatesoft.svn.core.wc.SVNInfo;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import org.tmatesoft.svn.core.wc2.SvnCheckout;
import org.tmatesoft.svn.core.wc2.SvnInfo;
import org.tmatesoft.svn.core.wc2.SvnOperationFactory;
import org.tmatesoft.svn.core.wc2.SvnTarget;
import org.tmatesoft.svn.core.wc2.SvnUpdate;

import rocky.common.CommonUtil;

/**
 * SVNClient wrapper. It loads configuration from resource file
 * "/svn.properties" svnpath= username= password= rev=-1
 * 
 * @author thachln
 */
public class SVNClient implements ISVNEventHandler {
	private final static Logger LOG = Logger.getLogger("SVNClient");

	/** . */
	public static final String DSP_DTE_FMT = "yyyy/MM/dd HH:mm";

	private static Properties props;
	private static String svnUrl = null;
	private static String wcPath = null;
	private static String username = null;
	private static String password = null;

	/** Null mean latest revision. */
	private static Long rev = null;

	/** Last time run the checkout. */
	private Date lastCheckDate = null;

	SVNWCClient wcClient = null;

	/**
	 * Avoid create instance without parameter.
	 */
	private SVNClient() {
		// No nothing
	}

	/**
	 * Create an instance of SVNClient to get information from local working
	 * copy.
	 * 
	 * @param wcPath
	 *            path of working copy
	 * @param username
	 * @param password
	 */
	public static SVNClient newClientFromWC(String wcPath, String username,
			String password) {
		SVNClient svnClient = new SVNClient();
		svnClient.wcPath = wcPath;
		svnClient.username = username;
		svnClient.password = password;

		return svnClient;
	}

	public static SVNClient newClientFromUrl(String svnUrl, String username,
			String password) {
		SVNClient svnClient = new SVNClient();
		svnClient.svnUrl = svnUrl;
		svnClient.username = username;
		svnClient.password = password;

		return svnClient;
	}

	/**
	 * [Give the description for method].
	 * 
	 * @param configFile
	 * @return
	 */
	public static SVNClient newClientFromConfiguration(String configFile) {
		try {
			Properties props = new Properties();
			props.load(CommonUtil.loadResource(configFile));

			String revVal = props.getProperty("rev");

			SVNClient svnClient = new SVNClient();
			svnClient.svnUrl = props.getProperty("svn.url");
			svnClient.wcPath = props.getProperty("svn.wc");
			svnClient.username = props.getProperty("username");
			svnClient.password = props.getProperty("password");

			rev = null;
			if ((revVal != null) && (!"-1".equalsIgnoreCase(revVal))) {
				rev = Long.valueOf(revVal);
				svnClient.setRev(rev);
			}

			return svnClient;
		} catch (IOException ex) {
			LOG.error("Could not load resource file '" + configFile + "'", ex);
		}

		return null;
	}

	/**
	 * [Give the description for method].
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// SVNClientManager clientManager = getClientManager();
		//
		// SVNWCClient wcClient = clientManager.getWCClient();
		//
		// SVNRevision revision = SVNRevision.HEAD;
		// File path = new File(wcPath);
		// try {
		// SVNInfo info = wcClient.doInfo(path, revision);
		// LOG.debug("URL " + info.getURL());
		// LOG.debug("CommittedDate:" +
		// CommonUtil.formatDate(info.getCommittedDate(), "yyyy/MM/dd HH:mm"));
		// LOG.debug("Revision:" + info.getRevision().getNumber());
		// LOG.debug("Last Committer:" + info.getAuthor());
		//
		// } catch (SVNException ex) {
		// LOG.error("", ex);
		// }

	}

	public SVNWCClient getSVNWCClient() {
		SVNClientManager clientManager = getClientManager();

		return clientManager.getWCClient();
	}

	/**
	 * [Give the description for method].
	 * 
	 * @return
	 */
	public SVNClientManager getClientManager() {
		DefaultSVNOptions myOptions = SVNWCUtil.createDefaultOptions(true);
		;
		SVNClientManager clientManager = SVNClientManager.newInstance(
				myOptions, username, password);

		clientManager.setEventHandler(this);
		return clientManager;
	}

	/**
	 * Get value of wcPath.
	 * 
	 * @return the wcPath
	 */
	public static String getWcPath() {
		return wcPath;
	}

	/**
	 * Set the value for wcPath.
	 * 
	 * @param wcPath
	 *            the wcPath to set
	 */
	public static void setWcPath(String wcPath) {
		SVNClient.wcPath = wcPath;
	}

	public long doCheckout(String svnUrlPath) {
		SVNURL svnUrl;
		try {
			svnUrl = SVNURL.parseURIEncoded(svnUrlPath);
			return doCheckout(svnUrl, null);
		} catch (SVNException ex) {
			LOG.error("Parse URL path '" + svnUrlPath + "'", ex);
		}

		return -1;
	}

	public long doCheckout(String svnUrlPath, Long rev) {
		SVNURL svnUrl;
		try {
			svnUrl = SVNURL.parseURIEncoded(svnUrlPath);
			return doCheckout(svnUrl, rev);
		} catch (SVNException ex) {
			LOG.error("Parse URL path '" + svnUrlPath + "'", ex);
		}

		return -1;
	}

	public long doCheckout(SVNURL url, Long rev) {
		SVNUpdateClient updateClient = getClientManager().getUpdateClient();
		File path = new File(wcPath);
		SVNRevision svnRev = (rev == null) ? SVNRevision.UNDEFINED
				: SVNRevision.create(rev);

		try {
			lastCheckDate = new Date();
			LOG.debug("CleanUp..." + path + " at "
					+ CommonUtil.formatDate(lastCheckDate, DSP_DTE_FMT));
			doCleanUp();
			LOG.debug("Checkout..." + path + " at "
					+ CommonUtil.formatDate(lastCheckDate, DSP_DTE_FMT));
			
			long lastRev = updateClient.doCheckout(url, path, svnRev, svnRev,
					SVNDepth.INFINITY, true);
			LOG.debug("lastRev=" + lastRev);

			return lastRev;
		} catch (SVNException ex) {
			LOG.error("Checkout SVN...", ex);
		}

		return -1;
	}

	public void doCleanUp() {
		SVNWCClient workingCopyClient = getClientManager().getWCClient();
		File path = new File(wcPath);
		try {
			workingCopyClient.doCleanup(path);
		} catch (SVNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public long doUpdate2() {
		final SvnOperationFactory svnOperationFactory = new SvnOperationFactory();
		try {
			final SvnUpdate update = svnOperationFactory.createUpdate();
			update.setSingleTarget(SvnTarget.fromFile(new File(wcPath)));

			// checkout.setSource(SvnTarget.fromURL(SVNURL.parseURIEncoded(svnUrl)));
			// ... other options
			long[] retValues = update.run();
			return retValues[0];
		} catch (SVNException ex) {
			LOG.error("Update", ex);
		} finally {
			svnOperationFactory.dispose();
		}

		return -1;
	}

	public long doUpdate() {
		SVNUpdateClient updateClient = getClientManager().getUpdateClient();
		File path = new File(wcPath);
		SVNRevision svnRev = (rev == null) ? SVNRevision.UNDEFINED
				: SVNRevision.create(rev);

		try {
			lastCheckDate = new Date();
			LOG.debug("Update..." + path + " at "
					+ CommonUtil.formatDate(lastCheckDate, DSP_DTE_FMT));
			long lastRev = updateClient.doUpdate(path, svnRev,
					SVNDepth.INFINITY, false, true);
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
			LOG.debug("Update..." + path + " at "
					+ CommonUtil.formatDate(lastCheckDate, DSP_DTE_FMT));
			long lastRev = updateClient.doUpdate(path, svnRev,
					SVNDepth.INFINITY, false, true);
			LOG.debug("lastRev=" + lastRev);

			return lastRev;
		} catch (SVNException ex) {
			LOG.error("Update SVN...", ex);
		}

		return -1;
	}


	/**
	 * @return SVNInfo of the lastest svn Revision
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
	
	public Long getLattestRevision() throws SVNException{
	    if (wcClient == null) {
            wcClient = getSVNWCClient();
        }
	    
	    return wcClient.doGetRevisionProperty(SVNURL.parseURIEncoded(svnUrl), null, SVNRevision.HEAD, null);
	}
	
	/**
	 * @return SvnInfo of local working directory
	 * @throws SVNException
	 */
	public SVNInfo getLocalInfo() throws SVNException{
		if (wcClient == null) {
			wcClient = getSVNWCClient();
		}

		SVNRevision revision = SVNRevision.WORKING;
		File path = new File(wcPath);
		return wcClient.doInfo(path, revision);
	}

	/**
	 * [Give the description for method].
	 * 
	 * @param path
	 *            file path or folder path
	 * @return
	 * @throws SVNException
	 */
	public SVNInfo getInfo(String path) throws SVNException {
		if (wcClient == null) {
			wcClient = getSVNWCClient();
		}

		SVNRevision revision = SVNRevision.HEAD;
		File file = new File(path);
		return wcClient.doInfo(file, revision);
	}

	public SVNInfo getInfo(File file) throws SVNException {
		if (wcClient == null) {
			wcClient = getSVNWCClient();
		}

		SVNRevision revision = SVNRevision.HEAD;
		return wcClient.doInfo(file, revision);
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
	 * @see org.tmatesoft.svn.core.wc.ISVNEventHandler#handleEvent(org.tmatesoft.svn.core.wc.SVNEvent,
	 *      double)
	 */
	@Override
	public void handleEvent(SVNEvent event, double progress)
			throws SVNException {
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
		SVNClient.rev = rev;
	}

}
