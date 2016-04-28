package com.jk.secutity;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.jk.exceptions.JKSecurityException;

public class EncDec {
	static String IV = "AAAAAAAAAAAAAAAA";
	static String plaintext = "test text 123\0\0\0"; /* Note null padding */
	static String encryptionKey = "0123456789abcdef";

	public static void main(String[] args) throws Exception {
		String encrypt = encrypt("123");
		System.out.println(encrypt);
		System.out.println(decrypt("97,-32,64,9,-124,-41,-95,94,-73,-72,28,-126,-76,79,-108,109"));
	}

	/**
	 * 
	 * @param plainText
	 * @param encryptionKey
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String plainText) {
		try {
			plainText = fixText(plainText);
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
			SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
			return toString(cipher.doFinal(plainText.getBytes("UTF-8")));
		} catch (Exception e) {
			throw new JKSecurityException(e);
		}
	}

	/**
	 * 
	 * @param bytes
	 * @return
	 */
	private static String toString(byte[] bytes) {
		StringBuffer buf = new StringBuffer();
		int i = 0;
		for (byte b : bytes) {
			if (i++ != 0) {
				buf.append(",");
			}
			buf.append(b);
		}
		return buf.toString();
	}

	/**
	 * 
	 * @param cipherText
	 * @param encryptionKey
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String cipherText) throws Exception {
		byte[] cipherBytes = toBytes(cipherText);
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		return new String(cipher.doFinal(cipherBytes), "UTF-8").trim();
	}

	/**
	 * 
	 * @param cipherText
	 * @return
	 */
	private static byte[] toBytes(String cipherText) {
		// rever to bytes from string called from toString(byte[]) method
		String[] split = cipherText.split(",");
		byte[] bytes = new byte[split.length];
		int i = 0;
		for (String b : split) {
			bytes[i++] = Byte.parseByte(b);
		}
		return bytes;
	}

	/**
	 * 
	 * @param plainText
	 * @return
	 */
	private static String fixText(String plainText) {
		int mod = plainText.length() % 16;
		for (int i = 0; i < plaintext.length() - mod; i++) {
			plainText += " ";
		}
		return plainText;
	}

}