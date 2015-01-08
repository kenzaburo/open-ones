package ldap.dao;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ldap.entry.Entry;
import ldap.entry.UserEntry;
import ldap.util.ConnectionManager;
import ldap.util.LdapConfiguration;

import org.apache.log4j.Logger;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPSearchResults;

/*
 * EntryDAO
 * @author HoaNV, ThachLe
 */
public class UserDAO extends BaseDAO {

    /** Logging. */
    private final static Logger LOG = Logger.getLogger(UserDAO.class);

    public UserDAO(LdapConfiguration ldapCfg) {
        super(ldapCfg);
    }

    /**
     * add Method which's going to add an Entry into LDAP server.
     * 
     * @throws LDAPException
     * @return true.
     * 
     */
    public boolean add(Entry entry) {
        boolean result = false;

        if (entry != null) {
            ConnectionManager.connection(ldapCfg);

            LOG.debug("Adding entry DN '" + entry.getDn() + "'");
            LDAPEntry newEntry = new LDAPEntry(entry.getDn(), entry.getAttributeSet());
            try {
                // add user
                LDAPConnection ldapConn = ConnectionManager.getInstance(ldapCfg);
                ldapConn.add(newEntry);
                LOG.info("Add entry '" + entry.getDn() + "' succeed!");
                // if (entry.getGroup() != null) {
                // addUserToGroup(newEntry.getDN(), entry.getGroup());
                // }
                result = true;
            } catch (LDAPException ldapEx) {
                // duplicate user
                LOG.error("Add entry '" + entry.getDn() + "' fail!", ldapEx);
                result = false;
            } finally {
                ConnectionManager.disconnect();
            }
        } else {
            // Do nothing
        }

        return result;
    }

    /**
     * addEntryList Method which's going to add an List of Entry into LDAP
     * server
     * 
     * @exception LDAPException
     *                when LDAPconnection can't connect to server
     * @return list of entries which could not added. If no entry errors, return
     *         list with zero item (null not).
     */
    public List<Entry> addEntryList(List<Entry> entryList) {
        List<Entry> faillist = new ArrayList<Entry>();

        boolean addOK;

        int len = (entryList != null) ? entryList.size() : 0; // Check if input
                                                              // list null or
                                                              // not

        for (int i = 0; i < len; i++) {
            // Add an entry
            addOK = add(entryList.get(i));

            if (!addOK) {
                // Add fail - duplication entry
                faillist.add(entryList.get(i));
                LOG.warn("Duplication Entry '" + entryList.get(i).getDn() + "' !");
            } else {
                // do nothing
            }
        }

        return faillist;
    }

    /**
     * add Method which's going to delete an Entry to LDAP server
     * 
     * @exception LDAPException
     *                when LDAPconnection can't connect to server
     * @return True if delete success otherwise false if delete fail
     */
    public boolean deleteUser(Entry entry) {
        boolean result;
        if (entry != null) {
            try {
                boolean connected = ConnectionManager.connection(ldapCfg);
                if (connected) {
                    ConnectionManager.getInstance(ldapCfg).delete(entry.getDn());
                    ConnectionManager.disconnect();
                    result = true;
                    LOG.info("Delete entry '" + entry.getDn() + "' succeed!");
                } else {
                    result = false;
                }
            } catch (LDAPException ex) {
                LOG.error("Delete entry '" + entry.getDn() + "' FAILED!", ex);
                result = false;
            }
        } else {
            // do nothing
            result = false;
        }
        return result;
    }

    /**
     * FindByUid Method which's going to find/Search an Entry of LDAP server
     * 
     * @exception LDAPException
     *                when LDAPconnection can't connect to server
     * @return Null if Entry don't exits otherwise an entry with that uid
     */
    public Entry findByUid(String uid) {
        UserEntry entry = null;
        boolean connected = ConnectionManager.connection(ldapCfg);
        if (connected) {
            int searchScope = LDAPConnection.SCOPE_SUB;
            try {
                LDAPConnection ldapConn = ConnectionManager.getInstance(ldapCfg);
                LDAPSearchResults result = ldapConn.search(ldapCfg.getRootDN(), searchScope, "(uid=" + uid + ")", null,
                        false);
                entry = new UserEntry(result.next());
            } catch (LDAPException ex) {
                LOG.error("Can't find entry which has uid = '" + uid + "'", ex);
                entry = null;
            } finally {
                ConnectionManager.disconnect();
            }
        } else {
            // do nothing
        }
        return entry;
    }

    
    public UserEntry findByEmail(String email) {
        UserEntry entry = null;
        boolean connected = ConnectionManager.connection(ldapCfg);
        if (connected) {
            int searchScope = LDAPConnection.SCOPE_SUB;
            try {
                LDAPConnection ldapConn = ConnectionManager.getInstance(ldapCfg);
                LDAPSearchResults result = ldapConn.search(ldapCfg.getRootDN(), searchScope, "(mail=" + email + ")", null,
                        false);
                entry = new UserEntry(result.next());
            } catch (LDAPException e) {
                LOG.error("Can't find entry which has Email = '" + email + "'");
                entry = null;
            } finally {
                ConnectionManager.disconnect();
            }
        } else {
            // do nothing
        }
        return entry;
    }
    
    /**
     * FindAll Method which's going to find/Search an Entry of LDAP server.
     * 
     * @exception LDAPException
     *                when LDAPconnection can't connect to server
     * @return EntryList which contain every user's entry. Null if don't have
     *         anyentry
     */
    public List<Entry> findAll() {
        List<Entry> entryList = new ArrayList<Entry>();
        boolean connected = ConnectionManager.connection(ldapCfg);
        if (connected) {
            int searchScope = LDAPConnection.SCOPE_SUB;
            try {
                LDAPConnection ldapConn = ConnectionManager.getInstance(ldapCfg);
                LDAPSearchResults result = ldapConn.search(ldapCfg.getRootDN(), searchScope,
                        "(objectClass=inetOrgPerson)", null, false);
                Entry entry = null;
                while (result.hasMore()) {
                    entry = new UserEntry(result.next());
                    entryList.add(entry);
                }

                return entryList;
            } catch (LDAPException ex) {
                LOG.error("Error When Find all!", ex);
                entryList = null;
            } finally {
                ConnectionManager.disconnect();
            }
            return entryList;
        } else {
            return null;
        }

    }

    /**
     * Update Method which's going to update/modify an Entry of LDAP server.
     * 
     * @exception LDAPException
     *                when LDAPconnection can't connect to server
     * @return TRUE if update this entry success, FALSE if update this false
     */
    public boolean update(Entry entry) {
        boolean result = false;
        boolean connected = ConnectionManager.connection(ldapCfg);
        if (connected) {
            // get all attributes
            @SuppressWarnings("unchecked")
            Iterator<LDAPAttribute> iterator = entry.getAttributeSet().iterator();

            ArrayList<LDAPModification> modList = new ArrayList<LDAPModification>();
            LDAPAttribute ldapAttribute = null;
            while (iterator.hasNext()) {
                ldapAttribute = iterator.next();
                modList.add(new LDAPModification(LDAPModification.REPLACE, ldapAttribute));
            }

            // store all attribute into LDAPModification Array
            LDAPModification[] mods = new LDAPModification[modList.size()];
            modList.toArray(mods);

            // connect and update entry
            try {
                LDAPConnection ldapConn = ConnectionManager.getInstance(ldapCfg);
                ldapConn.modify(entry.getDn(), mods);
                ConnectionManager.disconnect();
                result = true;
                LOG.info("Update entry '" + entry.getDn() + "' succeed!");
            } catch (LDAPException ex) {
                LOG.error("Can't update Entry '" + entry.getDn() + "'", ex);
            }
        } else {
            result = false;
        }
        return result;
    }

    /**
     * AddUserToGroup Method which's going to update/modify an Entry of LDAP
     * server.
     * 
     * @exception LDAPException
     *                when LDAPconnection can't connect to server
     * @return TRUE if update this entry success, FALSE if update this false
     */
    public boolean addUserToGroup(String userdn, String groupdn) {
        throw new UnsupportedOperationException();
    }

    public Entry findByDN(String DN) {
        // Split to get Uid
        String[] split = DN.split(",");
        String uid = split[0].substring(4);

        Entry entry = findByUid(uid);
        return entry;
    }

    public List<Entry> findByOU(String dn) {
        List<Entry> resultList = new ArrayList<Entry>();
        GroupDAO group = new GroupDAO(ldapCfg);
        Entry g = group.findGroupByDN(dn);
        ConnectionManager.connection(ldapCfg);
        int searchScope = LDAPConnection.SCOPE_ONE;
        try {
            LDAPConnection ldapConn = ConnectionManager.getInstance(ldapCfg);
            LDAPSearchResults result = ldapConn.search(g.getDn(), searchScope, "(objectClass=inetOrgPerson)", null,
                    false);
            while (result.hasMore()) {
                UserEntry entry = new UserEntry(result.next());
                resultList.add(entry);
            }
        } catch (LDAPException e) {
            LOG.error("Can't find Group which has DN = '" + dn + "'");
            return null;
        } finally {
            ConnectionManager.disconnect();
        }
        return resultList;
    }

    public boolean checkPass(String uid, String pwd) {
        boolean result;
        ConnectionManager.connection(ldapCfg);
        LDAPConnection ldapConn = ConnectionManager.getInstance(ldapCfg);
        LDAPAttribute attr = new LDAPAttribute("userPassword", pwd);
        UserEntry entry = (UserEntry) findByUid(uid);
        try {
            result = ldapConn.compare(entry.getDn(), attr);
            LOG.debug("Password " + entry.getPassword());
        } catch (LDAPException e) {
            LOG.error("Can't check pass = '" + pwd + "'", e);
            result = false;
        }finally {
            ConnectionManager.disconnect();
        }
        return result;
    }

    public boolean changePassword(String uid, String pwd, String newpwd) {
        boolean result;
        UserEntry entry = (UserEntry) findByUid(uid);
        boolean checkpass = checkPass(uid, pwd);
        if (!checkpass) {
            result = false;
        } else {
            try {
                ConnectionManager.connection(ldapCfg);
                LDAPConnection ldapConn = ConnectionManager.getInstance(ldapCfg);
                LDAPModification[] modifications = new LDAPModification[2];

                LDAPAttribute deletePassword = new LDAPAttribute("userPassword", pwd);
                modifications[0] = new LDAPModification(LDAPModification.DELETE, deletePassword);

                LDAPAttribute addPassword = new LDAPAttribute("userPassword", newpwd.getBytes("UTF8"));
                modifications[1] = new LDAPModification(LDAPModification.ADD, addPassword);

                ldapConn.modify(entry.getDn(), modifications);

                LOG.info("Your password has been modified.");
                result = true;
            } catch (LDAPException ex) {
                if (ex.getResultCode() == LDAPException.NO_SUCH_OBJECT) {
                    LOG.info("Entry doesn't exist!");
                } else {
                    LOG.info("Error, can't change password", ex);
                }
                result = false;
            } catch (UnsupportedEncodingException e) {
                result = false;
            } finally {
                ConnectionManager.disconnect();
            }
        }
        return result;
    }
}
