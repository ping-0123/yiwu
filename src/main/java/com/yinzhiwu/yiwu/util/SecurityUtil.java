package com.yinzhiwu.yiwu.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SecurityUtil {

	private static final Log logger = LogFactory.getLog(SecurityUtil.class);

	private static final String KEY_MD5 = "MD5";
	private static final String KEY_SHA1 = "SHA1";

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
	
	
	public static String encryptBySHA1(String str){
	    if (null == str || 0 == str.length()){
	        return null;
	    }
	    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
	            'a', 'b', 'c', 'd', 'e', 'f'};
	    try {
	        MessageDigest mdTemp = MessageDigest.getInstance(KEY_SHA1);
	        mdTemp.update(str.getBytes("UTF-8"));
	         
	        byte[] md = mdTemp.digest();
	        int j = md.length;
	        char[] buf = new char[j * 2];
	        int k = 0;
	        for (int i = 0; i < j; i++) {
	            byte byte0 = md[i];
	            buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
	            buf[k++] = hexDigits[byte0 & 0xf];
	        }
	        return new String(buf);
	    } catch (NoSuchAlgorithmException e) {
	    	logger.error("no SHA1 encript algorithm", e);
	    } catch (UnsupportedEncodingException e) {
	    	logger.error("unsupported encoding", e );
	    }
	    return null;
	}
}
