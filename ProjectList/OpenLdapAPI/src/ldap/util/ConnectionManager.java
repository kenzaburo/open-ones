/**
 * 
 */
package ldap.util;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPException;

/*
 * ConnectionManager
 */

public class ConnectionManager {

    /** Logging. */
    private final static Logger LOG = Logger.getLogger(ConnectionManager.class);

    /** An LdapConnection's Object for connect and bind to server. */
    private static LDAPConnection ldapConnection = null;

    /**
     * Constructor ConnectionManager This constructor has been setup private for
     * apply Singleton Pattern. So once we need a LDAPConnection we just call
     * getInstance without create ConnectManager's Object
     */
    private ConnectionManager() {
    }

    /**
     * getInstance will receive parameters to configure
     *         LDAPconnect and return it.
     * @return an LDAPConnection has been connected to server
     */
    public static LDAPConnection getInstance(ServerConfig ldapCfg) {
        if (ldapConnection == null) {
            ldapConnection = new LDAPConnection();
            connection(ldapCfg);
        }
        return ldapConnection;
    }

    /**
     * disconnect's going to break connection to LDAP server connecting.
     */
    public static void disconnect() {
        if (ldapConnection != null) {
            try {
                // disconnect to server
                ldapConnection.disconnect();
                LOG.info("Disconnected to LDAP Server!");
            } catch (LDAPException ex) {
                LOG.error("Disconnect to LDAP Server FAILED!", ex); // can't
                                                                    // disconnect
            }
        }
    }

    /**
     * connect's going to connect to LDAP server.
     */
    public static boolean connection(ServerConfig ldapCfg) {
        boolean result;
        try {
            LOG.info("Connecting to LDAP Server: " + ldapCfg.getHost());
            ldapConnection = new LDAPConnection();
            ldapConnection.connect(ldapCfg.getHost(), ldapCfg.getPort()); // Connect
                                                                          // to
                                                                          // server
                                                                          // with
                                                                          // custom
                                                                          // port
            ldapConnection.bind(ldapCfg.getVersion(), ldapCfg.getLoginDN(), ldapCfg.getPwdLogin().getBytes("UTF8")); // bind
                                                                                                                     // to
                                                                                                                     // server
            LOG.info("Connected to LDAP Server!");
            result = true;
        } catch (LDAPException ex) {
            LOG.error("Connect to LDAP Server FAILED!", ex); // Connect or bind
                                                             // fails
            result = false;
        } catch (UnsupportedEncodingException ex) {
            LOG.error("WRONG PASS WORD!", ex); // password can't encoding
            result = false;
        }
        return result;
    }
}
