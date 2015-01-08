package ldap.test;

import ldap.util.LdapConfiguration;

import com.novell.ldap.LDAPConnection;

public class Config {
    
    /**
    * OpenLdap on localhost.
    * @return
    */
    public static LdapConfiguration getServerConfig() {
        String host = "localhost";
        String rootDN = "dc=maxcrc,dc=com";
        int version= LDAPConnection.LDAP_V3;
        int port = LDAPConnection.DEFAULT_PORT;
        String pwdLogin = "admin12345";

        String loginDN = "cn=Manager,dc=maxcrc,dc=com";
        
        LdapConfiguration ldapConfiguration = new LdapConfiguration();
        ldapConfiguration.setHost(host);
        ldapConfiguration.setPort(port);
        ldapConfiguration.setVersion(version);
        ldapConfiguration.setLoginDN(loginDN);
        ldapConfiguration.setPwdLogin(pwdLogin);
        ldapConfiguration.setRootDN(rootDN);
        
        return ldapConfiguration;
    }

}
