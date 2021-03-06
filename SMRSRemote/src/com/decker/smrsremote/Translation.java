package com.decker.smrsremote;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import android.content.Context;

public class Translation {
	/**
	 * 
	 * @param AppContext
	 * @param sendData
	 * @param Key
	 * @param InitialVector
	 * @return returns null if encryption was unsuccessful
	 */
	static byte[] Encrypt(Context AppContext, byte[] sendData, byte[] Key,
			byte[] InitialVector) {

		Encrypt encryption = new Encrypt();
		byte[] encryptedData = null;
		try {
			System.out.println("Before");
			encryptedData = encryption.encrypt(sendData, Key, InitialVector);
			System.out.println("After");

		} catch (NoSuchAlgorithmException NSAE) {
			ToastMessage.showToast(AppContext, NSAE.toString());
		} catch (NoSuchPaddingException NSPE) {
			ToastMessage.showToast(AppContext, NSPE.toString());
		} catch (InvalidKeyException IKE) {
			ToastMessage.showToast(AppContext, IKE.toString());
		} catch (InvalidAlgorithmParameterException IAPE) {
			ToastMessage.showToast(AppContext, IAPE.toString());
		} catch (IllegalBlockSizeException IBSE) {
			ToastMessage.showToast(AppContext, IBSE.toString());
		} catch (BadPaddingException BPE) {
			ToastMessage.showToast(AppContext, BPE.toString());
		}

		return encryptedData;
	}

	/**
	 * 
	 * @param AppContext
	 * @param recieved
	 * @param Key
	 * @return return the decrypted String, empty string if failed
	 */
	static String decrypt(Context AppContext, DatagramPacket recieved,
			byte[] Key) {
		byte[] recievedBytes = null;
		Encrypt encryption = new Encrypt();
		String recievedString = "";

		try {
			recievedBytes = encryption.decrypt(recieved.getData(), Key, Key);
		} catch (NoSuchAlgorithmException NSAE) {
			ToastMessage.showToast(AppContext, NSAE.toString());
		} catch (NoSuchPaddingException NSPE) {
			ToastMessage.showToast(AppContext, NSPE.toString());
		} catch (InvalidKeyException IKE) {
			ToastMessage.showToast(AppContext, IKE.toString());
		} catch (InvalidAlgorithmParameterException IAPE) {
			ToastMessage.showToast(AppContext, IAPE.toString());
		} catch (IllegalBlockSizeException IBSE) {
			ToastMessage.showToast(AppContext, IBSE.toString());
		} catch (BadPaddingException BPE) {
			ToastMessage.showToast(AppContext, BPE.toString());
		}
		if (recievedBytes == null) {
			return recievedString;
		}
		try {
			recievedString = new String(recievedBytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			ToastMessage.showToast(AppContext, e.toString());
		}

		return recievedString;
	}
}
