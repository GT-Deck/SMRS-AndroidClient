package com.decker.smrsremote;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;



import android.content.Context;
public class UdpClient 
{
	private InetAddress URL = null;
	private final String firstMessage = "hello";
	private Boolean canSend = false;
	private DatagramSocket socket = null;
	private DatagramPacket sendP = null;
	private DatagramPacket recieveP = null;
	private byte[] sendData;
	private byte[] recieveData;
	private byte[] encryptedData = null;
	Encrypt encryption;
	
	/**
	 * @param context current application context
	 * @param address address to send packet to
	 * @param key key for encryption
	 */
	UdpClient( Context context, String address,byte[] key)
	{
		sendData = firstMessage.getBytes();
		recieveData = new byte[100];
		encryption = new Encrypt();
		
		/*encrypt the payload*/
		try{
			encryptedData = encryption.encrypt(sendData, key, key);
		}
		catch(NoSuchAlgorithmException NSAE){
			ToastMessage.showToast(context,NSAE.toString());
			canSend = false;
		}
		catch(NoSuchPaddingException NSPE){
			ToastMessage.showToast(context,NSPE.toString());
			canSend = false;
		}
		catch(InvalidKeyException IKE){
			ToastMessage.showToast(context,IKE.toString());
			canSend = false;
		}
		catch(InvalidAlgorithmParameterException IAPE){
			ToastMessage.showToast(context,IAPE.toString());
			canSend = false;
		}
		catch(IllegalBlockSizeException IBSE){
			ToastMessage.showToast(context,IBSE.toString());
			canSend = false;
		}
		catch(BadPaddingException BPE){
			ToastMessage.showToast(context,BPE.toString());
			canSend = false;
		}
		
		/*Create the packets for sending and recieving*/
		try{	
		URL = InetAddress.getByName(address);
		sendP= new DatagramPacket(encryptedData,sendData.length,URL,3444);
		recieveP = new DatagramPacket(recieveData,recieveData.length);
		canSend = true;
		}
		catch(UnknownHostException ex){
			canSend = false;
			ToastMessage.showToast(context,"UnknownHostException: Please check URL");
		}
		
		/*Create the socket for sending and recieving*/
		try {
			socket = new DatagramSocket();
			socket.setSoTimeout(10000);
			canSend = true;
			} 
		catch (SocketException e) {
			canSend = false;
			ToastMessage.showToast(context,"SocketException: Unable to create socket");
		}
		
		
	}
	//TODO Send the encrypted payload, recieve and decrypt callback, send encrypted response
	/**
	 * @return   Boolean True if start message successfully sent
	 */
	public Boolean Start()
	{
		if(canSend)
		{
			try {
				socket.send(sendP);
				socket.receive(recieveP);
				Respond();
			} 
			catch (IOException e) {
				return false;
			}
			return canSend;
		}
		return canSend;
	}
	private void Respond()
	{
		
	}

}
