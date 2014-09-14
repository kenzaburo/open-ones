package ldap.test;

import ldap.util.ServerConfig;

import com.novell.ldap.LDAPConnection;

public class Config {
    
    /**
    * OpenLdap on localhost.
    * @return
    */
    public static ServerConfig getServerConfig() {
        String host = "localhost";
        String rootDN = "dc=maxcrc,dc=com";
        int version= LDAPConnection.LDAP_V3;
        int port = LDAPConnection.DEFAULT_PORT;
        String pwdLogin = "admin12345";

        String loginDN = "cn=Manager,dc=maxcrc,dc=com";
        
        ServerConfig ldapCfg = new ServerConfig(host, port, version, loginDN, pwdLogin, rootDN);
        
        return ldapCfg;
    }

}
