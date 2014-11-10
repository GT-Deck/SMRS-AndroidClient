package com.decker.smrsremote;

import android.content.Context;
import android.provider.Settings;

public class UdpTransaction implements Runnable {
	private final String DeviceID = Settings.Secure.ANDROID_ID;
	public String targetAddress = null;
	public Context con = null;
	UdpClient client;

	UdpTransaction(Context context, String address) {

		targetAddress = address;
		con = context;

		client = new UdpClient(con, targetAddress, DeviceID.getBytes());
	}

	public Boolean call() throws Exception {
		if (client.Start()) {
			return true;
		}
		return false;
	}

	@Override
	public void run() {
		client.Start();

	}

}
