package com.decker.smrsremote;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.annotation.SuppressLint;
import android.util.Base64;

@SuppressLint("TrulyRandom")
public class Encrypt {
	private final String characterEncoding = "UTF-8";
	private final String cipherTransformation = "AES/CBC/PKCS5Padding";
	private final String aesEncryptionAlgorithm = "AES";

	public Encrypt() {

	}

	public byte[] decrypt(byte[] cipherText, byte[] key, byte[] initialVector)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(cipherTransformation);
		} catch (NoSuchAlgorithmException X) {
			throw X;
		}
		SecretKeySpec secretKeySpecy = new SecretKeySpec(key,
				aesEncryptionAlgorithm);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpecy, ivParameterSpec);
		cipherText = cipher.doFinal(cipherText);
		return cipherText;
	}

	@SuppressLint("TrulyRandom")
	public byte[] encrypt(byte[] plainText, byte[] key, byte[] initialVector)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(cipherTransformation);
		} catch (NoSuchPaddingException Ex) {
			throw Ex;
		}
		/*
		 * The bug has been traced to this point. cipher.init() is throwing an
		 * invalid key exception because of a bad key being passed Android
		 * Unique ID the key being passed was not a valid/expected value.
		 */
		SecretKeySpec secretKeySpec = new SecretKeySpec(key,
				aesEncryptionAlgorithm);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		} catch (InvalidKeyException EK) {
			System.out.println("Invalid Key Exception");
		} catch (InvalidAlgorithmParameterException IA) {
			System.out.println("Invalid Algorithm");
		}
		plainText = cipher.doFinal(plainText);
		return plainText;

	}

	public String encrypt(String plainText, String key)
			throws UnsupportedEncodingException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException {
		byte[] plainTextbytes = plainText.getBytes(characterEncoding);
		byte[] keyBytes = getKeyBytes(key);
		return Base64.encodeToString(
				encrypt(plainTextbytes, keyBytes, keyBytes), Base64.DEFAULT);
	}

	public String decrypt(String encryptedText, String key)
			throws KeyException, GeneralSecurityException,
			GeneralSecurityException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException, IOException {
		byte[] cipheredBytes = Base64.decode(encryptedText, Base64.DEFAULT);
		byte[] keyBytes = getKeyBytes(key);
		return new String(decrypt(cipheredBytes, keyBytes, keyBytes),
				characterEncoding);
	}

	private byte[] getKeyBytes(String key) throws UnsupportedEncodingException {
		byte[] parameterKeyBytes = null;
		byte[] keyBytes = new byte[16];
		try {
			parameterKeyBytes = key.getBytes(characterEncoding);
		} catch (UnsupportedEncodingException Ex) {
			throw Ex;
		}
		System.arraycopy(parameterKeyBytes, 0, keyBytes, 0,
				Math.min(parameterKeyBytes.length, keyBytes.length));
		return keyBytes;

	}

}
