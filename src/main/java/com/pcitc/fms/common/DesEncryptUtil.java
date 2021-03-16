package com.pcitc.fms.common;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DesEncryptUtil {
	public final static String KEY = "raidkjnh";

	public static void main(String[] args) {
		System.out.println(DesEncryptUtil.desEncrypt2String("promace@123"));
	}

	public static final String CIPHER_ALGORITHM_ECB = "DES/ECB/PKCS5Padding";
	public static final String CIPHER_ALGORITHM_CBC = "DES/CBC/PKCS5Padding";

	public static String desEncrypt2String(String data, String Key, String IvKey) {
		return HexUtils.encodeHexStr(desEncrypt(data.getBytes(), Key.getBytes(), IvKey.getBytes()));
	}

	public static String desDecrypt2String(String data, String Key, String IvKey) {
		return new String(desDecrypt(HexUtils.decodeHex(data.toCharArray()), Key.getBytes(), IvKey.getBytes()));
	}

	public static String desEncrypt2String(String data, String Key) {
		return desEncrypt2String(data, Key, Key);
	}

	public static String desDecrypt2String(String data, String Key) {
		return desDecrypt2String(data, Key, Key);
	}

	public static String desEncrypt2String(String data) {
		return desEncrypt2String(data, KEY);
	}

	public static String desDecrypt2String(String data) {
		return desDecrypt2String(data, KEY);
	}

	public static byte[] desEncrypt(String data) {
		return desEncrypt(data.getBytes(), KEY.getBytes());
	}

	public static byte[] desDecrypt(String data) {
		return desDecrypt(HexUtils.decodeHex(data.toCharArray()), KEY.getBytes());
	}

	/**
	 * 对称加解密DES
	 */
	// -----------DES加密与解密--------------

	/**
	 * 使用对称加密DES方式对数据进行加密
	 * 
	 * @param data
	 *            要加密的数据（字节数组）
	 * @param keyData
	 *            加密用的密钥（字节数组）长度必须为8个字节否则返回null
	 * @return 返回加密过的数据（字节数组）
	 */
	public static byte[] desEncrypt(byte[] data, byte[] keyData) {
		return desEncrypt(data, keyData, keyData);
	}

	public static byte[] desEncrypt(byte[] data, byte[] keyData, byte[] ivKeyData) {
		return des(Cipher.ENCRYPT_MODE, data, keyData, ivKeyData);
	}

	/**
	 * 解密使用对称加密DES方式加密过的数据
	 * 
	 * @param data
	 *            加密过的数据（字节数组）
	 * @param keyData
	 *            解密用的密钥（字节数组）长度必须为8个字节否则返回null
	 * @return 返回解密后的字节数组
	 */
	public static byte[] desDecrypt(byte[] data, byte[] keyData) {
		return desDecrypt(data, keyData, keyData);
	}

	public static byte[] desDecrypt(byte[] data, byte[] keyData, byte[] ivKeyData) {
		return des(Cipher.DECRYPT_MODE, data, keyData, ivKeyData);
	}

	private static byte[] des(int mode, byte[] data, byte[] keyData, byte[] ivKeyData) {
		byte[] ret = null;
		if (data != null && data.length > 0 && keyData != null && keyData.length == 8) {
			try {
				Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
				DESKeySpec keySpec = new DESKeySpec(keyData);
				IvParameterSpec iv = new IvParameterSpec(ivKeyData);
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
				SecretKey key = keyFactory.generateSecret(keySpec);

				cipher.init(mode, key, iv);

				ret = cipher.doFinal(data);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
}
