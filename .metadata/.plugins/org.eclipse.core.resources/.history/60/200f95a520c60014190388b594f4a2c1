package com.decker.smrsremote;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.content.Context;

public class UdpClient {
	private InetAddress URL = null;
	private final String firstMessage = "hello";
	private final String secondMessage = "start";
	private Boolean canSend = false;
	private DatagramSocket socket = null;
	private DatagramPacket sendP;
	private DatagramPacket recieveP = null;
	private byte[] sendData;
	private byte[] recieveData;
	private byte[] encryptedData = null;
	private byte[] Key = null;
	private Context Appcontext = null;
	Encrypt encryption;

	/**
	 * @param context
	 *            current application context
	 * @param address
	 *            String address to send packet to
	 * @param key
	 *            array of bytes for encryption
	 */
	UdpClient(Context context, String address, byte[] key) {
		Key = key;
		Appcontext = context;
		sendData = firstMessage.getBytes();
		recieveData = new byte[100];
		encryption = new Encrypt();
		// Encrypt the payload
		encryptedData = Translation.Encrypt(Appcontext, sendData, Key, Key);
		if (encryptedData != null) {
			canSend = true;

		}
		/* Create the packets for sending and recieving */
		try {

			URL = InetAddress.getByName(address);

			sendP = new DatagramPacket(encryptedData, encryptedData.length,
					URL, 3444);
			recieveP = new DatagramPacket(recieveData, recieveData.length);
			canSend = true;
		} catch (UnknownHostException ex) {
			canSend = false;
			ToastMessage.showToast(context,
					"UnknownHostException: Please check URL");
		}

		/* Create the socket for sending and recieving */
		try {
			socket = new DatagramSocket();
			socket.setSoTimeout(10000);
			canSend = true;
		} catch (SocketException e) {
			canSend = false;
			ToastMessage.showToast(context,
					"SocketException: Unable to create socket");
		}

	}

	/**
	 * @return Boolean True if start message successfully sent
	 */
	public Boolean Start() {
		if (canSend) {
			try {
				socket.send(sendP);
				socket.receive(recieveP);
				Respond(recieveP);
			} catch (IOException e) {
				return false;
			}
			return canSend;
		}
		return canSend;
	}

	private void Respond(DatagramPacket response) {
		String[] splitString = translate(response).split(",");
		int recievingPort = Integer.parseInt(splitString[0]);
		byte[] PrivateKey = splitString[1].getBytes();

		byte[] encryptedResponse = Translation.Encrypt(Appcontext,
				secondMessage.getBytes(), PrivateKey, PrivateKey);

		sendP = new DatagramPacket(encryptedResponse, encryptedResponse.length,
				URL, recievingPort);
		try {
			socket.send(sendP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String translate(DatagramPacket recieved) {
		String recievedString;
		recievedString = Translation.decrypt(Appcontext, recieved, Key);
		return recievedString;
	}

}
