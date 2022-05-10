package uk.ncl.cs.teamproject.util;

import java.security.MessageDigest;

/**
 * message-digest algorithm 5
 *
 * The length of the md5, which defaults to 128 bits, which is a binary string of 128 zeros and ones.
 *
 * 128/4 = 32 is 32 bits when converted to hexadecimal.
 * @author yantao xu
 */
public class SignUtil {

	/**
	 * Generatemd5
	 * 
	 * @param message
	 * @return
	 */
	public static String getMD5(String message) {
		String md5str = "";
		try {
			// 1 Create an object that provides the message digest algorithm, initialized as an md5 algorithm object
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 2 Turning messages into byte arrays
			byte[] input = message.getBytes();

			// 3 Calculated byte array, this is the 128 bits
			byte[] buff = md.digest(input);

			// 4 Convert each byte of the array (one byte occupies eight bits) into a hexadecimal concatenated md5 string
			md5str = bytesToHex(buff);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5str;
	}
	
	public static String getMD5LowerCase(String message) {
		return getMD5(message).toLowerCase();
	}

	/**
	 * Binary to hexadecimal
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes) {
		StringBuffer md5str = new StringBuffer();
		// convert each byte of the array into a hexadecimal concatenated md5 string
		int digital;
		for (int i = 0; i < bytes.length; i++) {
			digital = bytes[i];

			if (digital < 0) {
				digital += 256;
			}
			if (digital < 16) {
				md5str.append("0");
			}
			md5str.append(Integer.toHexString(digital));
		}
		return md5str.toString().toUpperCase();
	}
	
}
