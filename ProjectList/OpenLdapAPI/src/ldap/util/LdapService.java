/**
 * Licensed to Open-Ones under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones licenses this file to you under the Apache License,
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
package ldap.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import ldap.dao.GroupDAO;
import ldap.dao.UserDAO;
import ldap.entry.Entry;
import ldap.entry.GroupEntry;
import ldap.entry.UserEntry;

import org.apache.log4j.Logger;

import rocky.common.PropertiesManager;

import com.google.gson.Gson;
import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.util.Base64;

/**
 * @author ThachLN
 *
 */
public class LdapService {
	private static final String LDAP_CFG_PATH = "/ldap.properties";
	private static final String NODEROOT = "Users";
	private static final Logger LOG = Logger.getLogger(LdapService.class);
	protected static ServerConfig ldapCfg = null;

	private Entry root = null;
	
	String json = null;

	/** OU for starting lookup accounts . */
	private static String userOU = null;

	private static LdapService defaultInstance = new LdapService();
	static {
		try {
			LOG.info("Loading configuration...");
			Properties props = PropertiesManager.newInstanceFromProps(LDAP_CFG_PATH);
			String host = props.getProperty("host");
			String strPort = props.getProperty("port");
			int port = ((strPort != null) && (!strPort.isEmpty()))
			        ? Integer.valueOf(strPort)
			        : LDAPConnection.DEFAULT_PORT;

			String strVersion = props.getProperty("version");
			int version = ((strVersion != null) && (!strVersion.isEmpty()))
			        ? Integer.valueOf(strVersion)
			        : LDAPConnection.LDAP_V3;

			String loginDN = props.getProperty("loginDN");
			String pwdLogin = props.getProperty("pwdLogin");
			String rootDN = props.getProperty("rootDN");

			userOU = props.getProperty("userOU");

			ldapCfg = new ServerConfig(host, port, version, loginDN, pwdLogin, rootDN);
		} catch (IOException ex) {
			LOG.error("Could not load configuration from resource '" + LDAP_CFG_PATH + "'", ex);
		}
	}

	/**
	 * Get default implementation of Ldap service.
	 * 
	 * @return an instance of LdapService
	 */
	public static LdapService getInstance() {
		return defaultInstance;
	}

	/**
	 * [Give the description for method].
	 * 
	 * @return
	 */
	public Entry findGroups() {
		GroupDAO group = new GroupDAO(ldapCfg);
		root = group.findGroupByDN(userOU);
		return root;
	}

	/**
	 * [Give the description for method].
	 * 
	 * @param name
	 * @return
	 */
	public Entry findGroupByDN(String name) {
		GroupDAO group = new GroupDAO(ldapCfg);
		return group.findGroupByDN(name);
	}

	public Entry findUserByUid(String uid) {
		UserDAO userdao = new UserDAO(ldapCfg);
		return userdao.findByUid(uid);
	}

	public List<Entry> findUsersByDN(String name) {
		UserDAO users = new UserDAO(ldapCfg);
		return users.findByOU(name);
	}

	public boolean addGroup(GroupEntry entry) {
		GroupDAO dao = new GroupDAO(ldapCfg);
		return dao.add(entry);
	}

	public boolean addUser(UserEntry entry) {
		UserDAO dao = new UserDAO(ldapCfg);
		return dao.add(entry);
	}

	/**
	* Check password of user.
	* @param uid account name
	* @param pwd password
	* @return
	* 
	* Step 1: verify with plain password.
	* If not success go to Step 2.
	* 
	* Step 2: verify with encrypted password
	*/
	public boolean checkPassword(String uid, String pwd) {
		UserDAO userDao = new UserDAO(ldapCfg);
		boolean result = userDao.checkPass(uid, pwd);
		if (!result) {
		    // Try to check with encrypted pass
		    result = userDao.checkPass(uid, encrypt(pwd));
		} else {
		    // Do nothing
		}

		return result;
	}

    /**
    * [Give the description for method].
    * @param ou
    * @return
    * @see http://www.jstree.com/docs/json/
    * Using node "a_attr" to store unique dn of note. The dn is determine selected node.
    */
    public String getJsonLdap(String ou) {

        root = findGroups();
        NodeBean beanRoot = new NodeBean();

        beanRoot.addAttr("dn", root.getDn());
        beanRoot.setText(NODEROOT);

        if (root != null) {
            List<NodeBean> subBeans = makeListBean(root);
            // beanRoot.setType("#");
            beanRoot.setChildren(subBeans);
        }

        Gson gson = new Gson();
        json = gson.toJson(beanRoot);

        return json;
    }
    
    
    /**
    * Build tree of node.
    * @param node
    * @return
    */
    private List<NodeBean> makeListBean(Entry node) {
        // LOG.debug("noderoot: DN=" + noderoot.getDn() + ";groupOU=" + noderoot.getGroupOU());
        node = findGroupByDN(node.getDn());
        List<NodeBean> beans = new ArrayList<NodeBean>();
        int sizeChildGroup = node.getChild().size();

        // Find users
        // get User Entry
        List<Entry> userChild = findUsersByDN(node.getDn());
        int sizeUser = (userChild != null) ? userChild.size() : 0;
        NodeBean bean;
        
        UserEntry userNode;
        for (int i = 0; i < sizeUser; i++) {
            userNode = (UserEntry) userChild.get(i);
            bean = new NodeBean();
            bean.addAttr("dn", userNode.getDn());
            bean.setParent(node.getName());
            bean.setType("file");
            bean.setText(userNode.getUid());
            beans.add(bean);
        }
        
        if (sizeChildGroup > 0) {
            Entry childNode;
            for (int i = 0; i < sizeChildGroup; i++) {
                childNode = node.getChild().get(i);
                bean = new NodeBean();
                bean.addAttr("dn", childNode.getDn());
                
                bean.setParent(node.getName());
                
                bean.setChildren(makeListBean(childNode));
                bean.setText(childNode.getName());
                beans.add(bean);
            }
        }
        return beans;
    }
//	public boolean changePass(String userDN, String newPwd) {
//		boolean result = false;
//		ConnectionManager.connection(ldapCfg);
//		LDAPAttribute attributePassword = new LDAPAttribute("userPassword");
//		try {	        
//	        ConnectionManager.connection(ldapCfg);
//            LDAPConnection ldapConn = ConnectionManager.getInstance(ldapCfg);
//            LDAPModification[] modifications = new LDAPModification[2];
//
//            LDAPAttribute deletePassword = new LDAPAttribute("userPassword");
//            modifications[0] = new LDAPModification(LDAPModification.DELETE, deletePassword);
//
//            LDAPAttribute addPassword = new LDAPAttribute("userPassword", newPwd);
//            modifications[1] = new LDAPModification(LDAPModification.ADD, addPassword);
//
//            ldapConn.modify(userDN, modifications);
//
//            LOG.info("Your password has been modified.");
//            result = true;
//        } catch (LDAPException e) {
//	        LOG.info("Reset Pass False",e);
//	        result = false;
//        }finally{
//        	ConnectionManager.disconnect();
//        }
//		return result;
//	}
	
    public boolean changePass(String userDN, String newPwd) {
        boolean result = false;
        ConnectionManager.connection(ldapCfg);

        String encryptedPwd = encrypt(newPwd);
        LDAPAttribute attributePassword = new LDAPAttribute("userPassword", encryptedPwd);

        try {            
            ConnectionManager.connection(ldapCfg);
            LDAPConnection ldapConn = ConnectionManager.getInstance(ldapCfg);
            
            LDAPModification modification = new LDAPModification(LDAPModification.REPLACE, attributePassword);

            ldapConn.modify(userDN, modification);
            
            LOG.info("Your password has been modified.");
            result = true;
        } catch (LDAPException e) {
            LOG.info("Reset Pass False", e);
            result = false;
        } finally {
            ConnectionManager.disconnect();
        }

        return result;
    }

    /**
    * Encrypt password with algorithm SSA.
    * Refer:
    * {@link http://www.novell.com/documentation/developer/samplecode/jldap_sample/controls/SimplePassword.java.html}
    * {@link https://forums.netiq.com/archive/index.php/t-10133.html}
    * 
    * @param newPwd
    * @return
    */
    public static String encrypt(String newPwd) {
        String encryptedPwd = null;
        String algorithm = "SHA";
        
        try {
            
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] digest = md.digest(newPwd.getBytes());
            encryptedPwd = "{" + algorithm + "}" + Base64.encode(digest);
            
        } catch (NoSuchAlgorithmException ex) {
            LOG.error("Could not encrypt password with algorithm" + algorithm, ex);
        }
        
        return encryptedPwd;
    }

    public UserEntry findUserByEmail(String email) {
		UserDAO users = new UserDAO(ldapCfg);
		return users.findByEmail(email);
    }
}
