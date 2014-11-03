package com.decker.smrsremote;

public class StarMadeServer {
	private String serverName = "";
	private String serverIP = "";

	public StarMadeServer(String Name, String IP) {
		serverName = Name;
		serverIP = IP;
	}

	public void setName(String Name) {
		serverName = Name;
	}

	public void setIP(String IP) {
		serverIP = IP;
	}

	public String getName() {
		return serverName;
	}

	public String getIP() {
		return serverIP;
	}
}
