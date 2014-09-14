package ldap.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ldap.entry.Entry;
import ldap.entry.GroupEntry;
import ldap.util.ConnectionManager;
import ldap.util.ServerConfig;

import org.apache.log4j.Logger;

import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;

public class GroupDAO extends BaseDAO {
    /** Logging. */
    private final static Logger LOG = Logger.getLogger(GroupDAO.class);

    /**
     * Create an instance with given configuration.
     * @param ldapCfg
     */
    public GroupDAO(ServerConfig ldapCfg) {
        super(ldapCfg);
    }

    /**
    * Create a new group entry.
    * @param entry
    * @return
    */
    public boolean add(Entry entry) {
        boolean result = false;

        if (entry != null) {
            ConnectionManager.connection(ldapCfg);
            LDAPEntry newEntry = new LDAPEntry(entry.getDn(), entry.getAttributeSet());
            try {
                // add user
                ConnectionManager.getInstance(ldapCfg).add(newEntry);
                LOG.info("Add Group '" + entry.getDn() + "' succeed!");
                result = true;
            } catch (LDAPException ldapEx) {
                // duplicate user
                LOG.error("Add Group '" + entry.getDn() + "' fail!", ldapEx);
                result = false;
            } finally {
                ConnectionManager.disconnect();
            }
        } else {
            // Do nothing
        }

        return result;
    }

    public boolean addUserToGroup(String userdn, String groupdn) {
        boolean result = false;
        // Entry Old which has been change DN to add new Group
        UserDAO userDao = new UserDAO(ldapCfg);
        Entry user = userDao.findByDN(userdn);
        boolean deleted = userDao.deleteUser(user);
        if (deleted) {
            // Get DN to change
            String dn = user.getDn();

            // Slipt it to get OU
            String[] seperate = dn.split(",");

            List<String> arrayDN = Arrays.asList(seperate);

            // New User that'll be add to new Group
            LOG.info("GET DN '" + user.getDn());

            String[] split = user.getDn().split(",");
            String uid = split[0].substring(4);
            String newDN = "uid=" + uid + ",";// +
                                              // findGroupByOU(groupdn).getDn();
            user.setDn(newDN);
            LOG.info("NEW DN '" + user.getDn());
            user.configAttributeSet();

            boolean added = userDao.add(user);
            if (added) {
                result = true;
                LOG.info("Add to Group : " + groupdn + " successed!");
            } else {
                // do nothing
            }
        } else {
            // do nothing
        }
        return result;
    }

    /*
     * Delete a group with given dn.
     * 
     * @param groupDN Ex "ou=Users,dc=yourdomain,dc=com"
     * 
     * @see vn.fpt.fsoft.ldap.dao.IEntryDAO#deleteGroup(java.lang.String)
     */
    public boolean deleteGroup(String groupDn) {
        // Return false in default
        boolean result = false;
        GroupDAO groupDao = new GroupDAO(ldapCfg);
        Entry entry = groupDao.findGroupByDN(groupDn);

        if (entry != null) {
            try {
                boolean connected = ConnectionManager.connection(ldapCfg);
                if (connected) {
                    // delete it
                    ConnectionManager.getInstance(ldapCfg).delete(entry.getDn());
                    ConnectionManager.disconnect();
                    result = true;
                    LOG.info("Delete entry '" + entry.getDn() + "' succeed!");
                } else {
                    LOG.error("Could not connect to LDAP Server.");
                }
            } catch (LDAPException ex) {
                LOG.error("Delete entry '" + entry.getDn() + "' FAILED!", ex);
            }
        } else {
            LOG.debug("Group '" + groupDn + "' not found");
        }
        return result;
    }

    /**
     * Delete a group.
     * 
     * @param dn
     * @param recursively
     *            true: delete groups and their content recursively
     * @return true if deleted successfully.
     * @author ThachLe
     */
    public boolean deleteGroup(String dn, boolean recursively) {
        boolean result = false;
        // GroupDAO groupDao = new GroupDAO(ldapCfg);
        Entry groupEntry = this.findGroupByDN(dn);

        if (groupEntry != null) {
            try {
                boolean connected = ConnectionManager.connection(ldapCfg);
                if (connected) {
                    List<Entry> subEntries = groupEntry.getChild();
                    int subEntriesSize = (subEntries != null) ? subEntries.size() : 0;

                    if (recursively) {
                        // Delete their contents
                        // throw new LDAPException(arg0, arg1, arg2)

                    } else {
                        if (subEntriesSize > 0) {
                            // The group is not empty
                            LOG.warn("The group is not empty");
                            // throw new LDAPException(arg0, arg1, arg2)
                        } else {
                            // delete it
                            LDAPConnection ldapConn = ConnectionManager.getInstance(ldapCfg);
                            ldapConn.delete(dn);
                            result = true;
                            LOG.info("Delete entry '" + groupEntry.getDn() + "' succeed!");
                        }
                    }
                } else {
                    LOG.error("Could not connect to LDAP.");
                    result = false;
                }
            } catch (LDAPException ex) {
                LOG.error("Delete entry '" + groupEntry.getDn() + "' FAILED!", ex);
                result = false;
            } finally {
                ConnectionManager.disconnect();
            }
        } else {
            // do nothing
            result = false;
        }

        return result;
    }

    private List<Entry> getGroupChilds(Entry entry) {
        List<Entry> results = new ArrayList<Entry>();
        ConnectionManager.connection(ldapCfg);
        int searchScope = LDAPConnection.SCOPE_ONE;
        try {
            LDAPSearchResults result = ConnectionManager.getInstance(ldapCfg).search(entry.getDn(), searchScope,
                    "(objectClass=organizationalUnit)", null, false);
            GroupEntry temp = null;
            while (result.hasMore()) {
                temp = new GroupEntry(result.next());
                temp.setEntryList(getGroupChilds(temp));
                LOG.info("Entry : " + temp.getDn());
                results.add(temp);
            }
        } catch (LDAPException e) {
            return null;
        } finally {
            ConnectionManager.disconnect();
        }
        return results;
    }

    public Entry findGroupByDN(String dn) {
        LOG.debug("dn=" + dn);
        GroupEntry entry = null;
        ConnectionManager.connection(ldapCfg);
        int searchScope = LDAPConnection.SCOPE_SUB;
        try {
            String ouName = dn.split(",")[0].substring(3);
            LOG.debug("ouName=" + ouName);
            LDAPConnection ldapConn = ConnectionManager.getInstance(ldapCfg);
            LDAPSearchResults result = ldapConn.search(dn, searchScope, "(ou=" + ouName + ")", null, false);
            entry = new GroupEntry(result.next());
            ConnectionManager.disconnect();
            entry.setEntryList(getGroupChilds(entry));
        } catch (LDAPException e) {
            LOG.error("Can't find Group which has DN = '" + dn + "'; rootDN='" + ldapCfg.getRootDN() + "'");
            return null;
        }
        return entry;
    }

    public List<Entry> findGroupsByDN(String dn, boolean recursively) {
        LOG.debug("dn=" + dn);
        List<Entry> lstGroupEntry = new ArrayList<Entry>();
        LDAPConnection ldapConn = ConnectionManager.getInstance(ldapCfg);

        try {
            int searchScope = (recursively) ? LDAPConnection.SCOPE_SUB : LDAPConnection.SCOPE_ONE;
            LDAPSearchResults result = ldapConn
                    .search(dn, searchScope, "(objectClass=organizationalUnit)", null, false);
            GroupEntry ge = null;
            while (result.hasMore()) {
                ge = new GroupEntry(result.next());
                lstGroupEntry.add(ge);
            }
        } catch (LDAPException ex) {
            LOG.error("Can't find Group which has DN = '" + dn + "'; rootDN='" + ldapCfg.getRootDN() + "'");
        }

        return lstGroupEntry;
    }
}
