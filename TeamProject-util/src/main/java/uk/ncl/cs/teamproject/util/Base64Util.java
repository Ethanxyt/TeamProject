package uk.ncl.cs.teamproject.util;

public class Base64Util {
	/**
	 * base64Decoding
	 * @param str base64String
	 * @return Byte Stream
	 * @author yantao xu
	 */
//	@SuppressWarnings("restriction")
	public static byte[] decode(String str) {
		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bt;
	}

	/**
	 * base64Coding
	 * @param b Byte Stream
	 * @return base64String
	 */
//	@SuppressWarnings("restriction")
	public static String encode(byte[] b) {
		if (b == null) {
			return null;
		}
//		return java.util.Base64.getEncoder().encodeToString(b);
		return (new sun.misc.BASE64Encoder()).encode(b);
		
	}

}
