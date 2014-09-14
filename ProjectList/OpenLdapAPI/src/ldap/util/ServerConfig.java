package ldap.util;

import com.novell.ldap.LDAPConnection;

/**
 * @author HoaNV, ThachLe
 * Class ServerConfig presents LDAP connection
 * */
public class ServerConfig {

    /** hostLDAP it's a String variable for store IP of LDAP Server. */
    private String host = "localhost";

    /** portLDAP it's a Integer variable for store Port of LDAP Server. */
    private int port = LDAPConnection.DEFAULT_PORT;

    /** versionLDAP it's a String variable for store Version of LDAP using. */
    private int version = LDAPConnection.LDAP_V3;

    /** loginDN it's a String variable for store Root DN of LDAP Server. */
    private String loginDN = null;

    /** pwdLogin is a String variable for store password of Root DN. */
    private String pwdLogin = null;

    /** rootDN it's a String variable for store DN of LDAP Server. */
    private String rootDN = null;

    
    /**
     * Create an instance of LDAPManager
     * @param host
     * @param port
     * @param version
     * @param loginDN
     * @param pwdLogin
     * @param rootDN
     */
    public ServerConfig(String host, int port, int version, String loginDN, String pwdLogin, String rootDN) {
        this.host = host;
        this.port = port;
        this.version = version;
        this.loginDN = loginDN;
        this.pwdLogin = pwdLogin;
        this.rootDN = rootDN;
    }

    /**
     * Get value of host.
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * Set the value for host.
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Get value of port.
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * Set the value for port.
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Get value of version.
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Set the value for version.
     * @param version the version to set
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Get value of loginDN.
     * @return the loginDN
     */
    public String getLoginDN() {
        return loginDN;
    }

    /**
     * Set the value for loginDN.
     * @param loginDN the loginDN to set
     */
    public void setLoginDN(String loginDN) {
        this.loginDN = loginDN;
    }

    /**
     * Get value of pwdLogin.
     * @return the pwdLogin
     */
    public String getPwdLogin() {
        return pwdLogin;
    }

    /**
     * Set the value for pwdLogin.
     * @param pwdLogin the pwdLogin to set
     */
    public void setPwdLogin(String pwdLogin) {
        this.pwdLogin = pwdLogin;
    }

    /**
     * Get value of rootDN.
     * @return the rootDN
     */
    public String getRootDN() {
        return rootDN;
    }

    /**
     * Set the value for rootDN.
     * @param rootDN the rootDN to set
     */
    public void setRootDN(String rootDN) {
        this.rootDN = rootDN;
    }

}
