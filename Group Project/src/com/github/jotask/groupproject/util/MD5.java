package com.github.jotask.groupproject.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Static class for encrypt a string for security proposes
 * Code bellows to the follow webpage
 * http://www.java2s.com/Code/Java/Security/UseMD5toencryptastring.htm
 *
 * @author Jose Vives
 *
 * @version 1.0
 *
 */
public class MD5 {
	
	private static MessageDigest digester;

    static {
        try {
            digester = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * Encrypt a string with a MD5 algorithm
     *
	 * @param str
	 * 			The text for encrypt
	 * @return
	 * 			The encrypt string
	 */
	public static String encrypt(String str){

		if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encrypt cannot be null or zero length");
        }

        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            }
            else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
        
	}

}
