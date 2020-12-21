package ch.txg.core.md5;

import java.security.MessageDigest;

public class MD5Checksum {

	public String  MD5_checksum(String inputString) throws Exception{

		if (inputString.length() < 1) {
			System.err.println("String to MD5 digest needs at least 1 character as input parameter");
			return "";
		}

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(inputString.getBytes());
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return sb.toString();
	}

}
