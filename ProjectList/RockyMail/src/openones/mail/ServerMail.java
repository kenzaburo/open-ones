/**
 * 
 */
package openones.mail;

/**
 * @author YenMH
 *
 */
public class ServerMail {
	private String serverName;
	private String serverPort;
	private boolean serverAuth;
	private String userLogin;
	private String passLogin;
	
	public ServerMail() {
		this.setServerName("");
		this.setServerPort("");
		this.setServerAuth(false);
		this.setUserLogin("");
		this.setPassLogin("");
	}
	
	public ServerMail(String serverName, String serverPort, boolean serverAuth,
			String userName, String password){
		this.setServerName(serverName);
		this.setServerPort(serverPort);
		this.setServerAuth(serverAuth);
		this.setUserLogin(userName);
		this.setPassLogin(password);
	}
	
	
	
	
	/**
	 * @param serverName the serverName to set
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/**
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * @param serverAuth the serverAuth to set
	 */
	public void setServerAuth(boolean serverAuth) {
		this.serverAuth = serverAuth;
	}

	/**
	 * @return the serverAuth
	 */
	public boolean isServerAuth() {
		return serverAuth;
	}

	/**
	 * @param serverPort the serverPort to set
	 */
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	/**
	 * @return the serverPort
	 */
	public String getServerPort() {
		return serverPort;
	}

	/**
	 * @param userLogin the userLogin to set
	 */
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	/**
	 * @return the userLogin
	 */
	public String getUserLogin() {
		return userLogin;
	}

	/**
	 * @param passLogin the passLogin to set
	 */
	public void setPassLogin(String passLogin) {
		this.passLogin = passLogin;
	}

	/**
	 * @return the passLogin
	 */
	public String getPassLogin() {
		return passLogin;
	}	
	
	
	
}
