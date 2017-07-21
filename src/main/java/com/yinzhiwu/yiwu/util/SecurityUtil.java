package com.yinzhiwu.yiwu.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SecurityUtil {
	
	private static final Log logger = LogFactory.getLog(SecurityUtil.class);
	
	private static final String KEY_MD5 = "MD5";
	
	private static final int ENCRYPT_LENGTH = 32;

	public static String encryptByMd5(String inputStr) {
		BigInteger bigInteger = null;

		try {
			MessageDigest md = MessageDigest.getInstance(KEY_MD5);
			byte[] inputData = inputStr.getBytes();
			md.update(inputData);
			bigInteger = new BigInteger(md.digest());
		} catch (Exception e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
		return bigInteger.toString(ENCRYPT_LENGTH);
	}
}
