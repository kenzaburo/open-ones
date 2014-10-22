package openones.mail;

public class MailServerInfo {
    /** Name or address . */
    private String name;
    private int port;
    private boolean requireAuth;
    private String username;
    private String password;
    
    private boolean enableStarttls;
    /**
     * Get value of name.
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * Set the value for name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * Get value of requireAuth.
     * @return the requireAuth
     */
    public boolean isRequireAuth() {
        return requireAuth;
    }
    /**
     * Set the value for requireAuth.
     * @param requireAuth the requireAuth to set
     */
    public void setRequireAuth(boolean requireAuth) {
        this.requireAuth = requireAuth;
    }
    /**
     * Get value of username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * Set the value for username.
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Get value of password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * Set the value for password.
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Get value of enableStarttls.
     * @return the enableStarttls
     */
    public boolean isEnableStarttls() {
        return enableStarttls;
    }
    /**
     * Set the value for enableStarttls.
     * @param enableStarttls the enableStarttls to set
     */
    public void setEnableStarttls(boolean enableStarttls) {
        this.enableStarttls = enableStarttls;
    }

}
