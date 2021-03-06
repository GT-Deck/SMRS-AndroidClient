package com.decker.smrsremote;

import android.content.Context;
import android.provider.Settings.Secure;

public class UdpTransaction implements Runnable {
	private final String DeviceID;
	public String targetAddress = null;
	public Context con = null;
	private UdpClient client;

	UdpTransaction(Context context, String address) {

		targetAddress = address;
		con = context;
		DeviceID = Secure
				.getString(con.getContentResolver(), Secure.ANDROID_ID);
		client = new UdpClient(con, targetAddress, DeviceID.getBytes());

	}

	@Override
	public void run() {
		client.Start();

	}

}
