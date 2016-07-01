/*
 * Copyright 2002-2016 Jalal Kiswani.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jk.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.jk.exceptions.JKEmptyReportException;
import com.jk.exceptions.JKSecurityException;

/**
 * The Class JKEncDec.
 *
 * @author Jalal Kiswani
 */
public class JKEncDec {
	static String IV = "AAAAAAAAAAAAAAAA";
	static String plaintext = "test text 123\0\0\0"; /* Note null padding */
	static String encryptionKey = "0123456789abcdef";

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws Exception
	 *             the exception
	 */
	public static void main(String[] args) throws Exception {
		String encrypt = encrypt("123");
		System.out.println(encrypt);
		System.out.println(decrypt("97,-32,64,9,-124,-41,-95,94,-73,-72,28,-126,-76,79,-108,109"));
		
		System.out.println(encode("3"));
		
	}

	/**
	 * Encrypt.
	 *
	 * @param plainText
	 *            the plain text
	 * @return the string
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
	 * Decrypt.
	 *
	 * @param cipherText
	 *            the cipher text
	 * @return the string
	 */
	public static String decrypt(String cipherText) {
		try {
			byte[] cipherBytes = toBytes(cipherText);
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
			SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
			return new String(cipher.doFinal(cipherBytes), "UTF-8").trim();
		} catch (Exception e) {
			throw new JKSecurityException(e);
		}
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

	/**
	 * Decode.
	 *
	 * @param source
	 *            the source
	 * @return the string
	 */
	public static String decode(final String source) {
		return decodeFromBase64(source);
	}

	/**
	 * Decode from base 64.
	 *
	 * @param string
	 *            the string
	 * @return the string
	 */
	public static String decodeFromBase64(final String string) {
		return new String(Base64.decodeBase64(string));
	}

	/**
	 * Encode.
	 *
	 * @param source
	 *            the source
	 * @return the string
	 */
	public static String encode(final String source) {
		return encodeInToBase64(source);
	}

	/**
	 * Encode in to base 64.
	 *
	 * @param string
	 *            the string
	 * @return the string
	 */
	public static String encodeInToBase64(final String string) {
		return Base64.encodeBase64String(string.getBytes());
	}

}