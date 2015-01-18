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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import ldap.dao.GroupDAO;
import ldap.dao.UserDAO;
import ldap.entry.Entry;
import ldap.entry.GroupEntry;
import ldap.entry.UserEntry;

import org.apache.log4j.Logger;

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
    private static final Logger LOG = Logger.getLogger(LdapService.class);

	private static final String NODEROOT = "Users";
	
    /** Attribute for a Ldap role's group entry. */
    private static final String LDAPATTR_MEMBER = "member";

	private LdapConfiguration ldapConfiguration = null;

	private Entry root = null;
	
	String json = null;

	/**
	 * @param configResource classpath resource configuration.
	 */
	public LdapService(String configResource) {
	    ldapConfiguration = LdapConfigurationUtil.getConfiguration(configResource);
	}
	
	public LdapService(LdapConfiguration ldapConfiguration) {
	    this.ldapConfiguration = ldapConfiguration;
	}

	/**
	 * [Give the description for method].
	 * 
	 * @return
	 */
	public Entry findGroups() {
		GroupDAO group = new GroupDAO(ldapConfiguration);
		root = group.findGroupByDN(ldapConfiguration.getRootGroupOU());
		return root;
	}

	/**
	 * [Give the description for method].
	 * 
	 * @param name
	 * @return
	 */
	public Entry findGroupByDN(String name) {
		GroupDAO group = new GroupDAO(ldapConfiguration);
		return group.findGroupByDN(name);
	}

	public Entry findUserByUid(String uid) {
		UserDAO userdao = new UserDAO(ldapConfiguration);
		return userdao.findByUid(uid);
	}

	public List<Entry> findUsersByDN(String name) {
		UserDAO users = new UserDAO(ldapConfiguration);
		return users.findByOU(name);
	}

	public boolean addGroup(GroupEntry entry) {
		GroupDAO dao = new GroupDAO(ldapConfiguration);
		return dao.add(entry);
	}

    public boolean deleteGroup(String groupDn) {
        GroupDAO dao = new GroupDAO(ldapConfiguration);
        return dao.deleteGroup(groupDn);
    }
	   
	public boolean addUser(UserEntry entry) {
		UserDAO dao = new UserDAO(ldapConfiguration);
		return dao.add(entry);
	}

    /**
     * Delete User.
     * 
     * @param userDn is dn of group wanna delete
     * @return is deleteUser?
     */
    public boolean deleteUser(String userDn) {
        UserDAO dao = new UserDAO(ldapConfiguration);

        return dao.deleteUser(dao.findByDN(userDn));
    }

    /**
     * Delete Group.
     * 
     * @param userDn is dn of group wanna delete
     * @param roleDn is of group
     * @return is deleteUser?
     */
    public boolean deleteUser(String userDn, String roleDn) {
        UserDAO dao = new UserDAO(ldapConfiguration);
        UserEntry ue = (UserEntry) dao.findByDN(userDn);

        boolean result = false;

        if (dao.deleteUser(ue)) {
            // Delete roles
            result = deleteRole(roleDn, ue.getDn());
        } else {
            // Do nothing.
        }

        return result;
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
		UserDAO userDao = new UserDAO(ldapConfiguration);
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
        ConnectionManager.connection(ldapConfiguration);

        String encryptedPwd = encrypt(newPwd);
        LDAPAttribute attributePassword = new LDAPAttribute("userPassword", encryptedPwd);

        try {            
            ConnectionManager.connection(ldapConfiguration);
            LDAPConnection ldapConn = ConnectionManager.getInstance(ldapConfiguration);
            
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
		UserDAO users = new UserDAO(ldapConfiguration);
		return users.findByEmail(email);
    }

    
    /**
     * Add role for a specific user.
     * 
     * @param roleDn string
     * @param uid of user
     * @return success?
     */
    public boolean addRole(String roleDn, String uid) {
        ConnectionManager.connection(ldapConfiguration);
        LDAPConnection ldapConn = ConnectionManager.getInstance(ldapConfiguration);
        LDAPModification modifications = new LDAPModification();
        LDAPAttribute addR = new LDAPAttribute(LDAPATTR_MEMBER, uid);
        modifications = new LDAPModification(LDAPModification.ADD, addR);
        boolean result;
        try {
            ldapConn.modify(roleDn, modifications);
            result = true;
        } catch (LDAPException ex) {
            if (ex.getResultCode() == LDAPException.NO_SUCH_OBJECT) {
                LOG.info("Entry doesn't exist!");
            } else {
                LOG.info("Error, can't add Role", ex);
            }
            result = false;
        }

        ConnectionManager.disconnect();
        return result;
    }

    /**
     * Delete Role.
     * 
     * @param roleDn from Ldap
     * @param uid from Ldap
     * @return is deleteRole?
     */
    public boolean deleteRole(String roleDn, String uid) {
        ConnectionManager.connection(ldapConfiguration);
        LDAPConnection con = ConnectionManager.getInstance(ldapConfiguration);

        LDAPAttribute dele = new LDAPAttribute(LDAPATTR_MEMBER, uid);
        LDAPModification del = new LDAPModification(LDAPModification.DELETE, dele);

        boolean result;
        try {
            con.modify(roleDn, del);
            result = true;
        } catch (LDAPException ex) {
            if (ex.getResultCode() == LDAPException.NO_SUCH_OBJECT) {
                LOG.info("Entry doesn't exist!");
            } else {
                LOG.info("Error, can't delete Role", ex);
            }
            result = false;
        }

        ConnectionManager.disconnect();

        return result;
    }

    /**
     * Update Role.
     * 
     * @param roleDn from Ldap
     * @param uid from Ldap
     * @param oldUid from Ldap
     * @return is updateRole?
     */
    public boolean updateRole(String roleDn, String uid, String oldUid) {
        String curRoleDn = getRole(oldUid);

        ConnectionManager.connection(ldapConfiguration);
        LDAPConnection con = ConnectionManager.getInstance(ldapConfiguration);

        LDAPModification[] mods = new LDAPModification[2];

        LDAPAttribute dele = new LDAPAttribute(LDAPATTR_MEMBER, oldUid);
        mods[0] = new LDAPModification(LDAPModification.DELETE, dele);

        LDAPAttribute ad = new LDAPAttribute(LDAPATTR_MEMBER, uid);
        mods[1] = new LDAPModification(LDAPModification.ADD, ad);

        boolean result;
        try {
            con.modify(curRoleDn, mods[0]);
            con.modify(roleDn, mods[1]);
            result = true;
        } catch (LDAPException ex) {
            if (ex.getResultCode() == LDAPException.NO_SUCH_OBJECT) {
                LOG.info("Entry doesn't exist!");
            } else {
                LOG.info("Error, can't change Role", ex);
            }
            result = false;
        }

        ConnectionManager.disconnect();

        return result;
    }

    /**
     * Get role dn string of a specific user.
     * 
     * @param userDn for looking up
     * @return role dn
     */
    public String getRole(String userDn) {
        ConnectionManager.connection(ldapConfiguration);
        LDAPConnection ldapConn = ConnectionManager.getInstance(ldapConfiguration);
        LDAPModification[] mods = new LDAPModification[2];
        LDAPAttribute target = new LDAPAttribute(LDAPATTR_MEMBER, userDn);
        mods[0] = new LDAPModification(LDAPModification.DELETE, target);
        mods[1] = new LDAPModification(LDAPModification.ADD, target);

        final String dnRoleAdmin = ldapConfiguration.getDnRoleAdmin();
        final String dnRoleUser = ldapConfiguration.getDnRoleUser();

        // Go into each Role group and delete the specific target (if delete
        // success, add that target back and return immediately the result)
        // catch block in each test below do nothing since this is just for test
        // whether the statement can run smoothly

        // Test Admin role
        try {
            ldapConn.modify(dnRoleAdmin, mods);
            return dnRoleAdmin;
        } catch (LDAPException e) {
            // Do nothing.
        }

        // Test User role
        try {
            ldapConn.modify(dnRoleUser, mods);
            return dnRoleUser;
        } catch (LDAPException e) {
            // Do nothing.
        }

        ConnectionManager.disconnect();

        return "";
    }


    /**
     * Get value of ldapConfiguration.
     * @return the ldapConfiguration
     */
    public LdapConfiguration getLdapConfiguration() {
        return ldapConfiguration;
    }

    /**
     * Set the value for ldapConfiguration.
     * @param ldapConfiguration the ldapConfiguration to set
     */
    public void setLdapConfiguration(LdapConfiguration ldapConfiguration) {
        this.ldapConfiguration = ldapConfiguration;
    }
}
