package mks.dm.handler;

import vn.fpt.fsoft.ldap.util.ServerConfig;

import com.novell.ldap.LDAPConnection;

public class Config {

	public static ServerConfig getServerConfig() {
        String host = "125.253.118.80";
        String rootDN = "dc=fsoft,dc=com";
        int version= LDAPConnection.LDAP_V3;
        int port = LDAPConnection.DEFAULT_PORT;
        String pwdLogin = "fsoft";

        String loginDN = "cn=Manager,dc=fsoft,dc=com,dc=vn";
        
        ServerConfig ldapCfg = new ServerConfig(host, port, version, loginDN, pwdLogin, rootDN);
        
        return ldapCfg;
    }
	
}
