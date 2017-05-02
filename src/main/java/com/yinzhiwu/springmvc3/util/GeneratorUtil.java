package com.yinzhiwu.springmvc3.util;

public class GeneratorUtil {

	private static final String PREFIX= "E5";
	
	private static final int LENGTH = 8;
	
	private static final char PADDING_CHAR = '0';
	
	public static String generateMemberId(int id){
		 return String.format(PREFIX+"%" + PADDING_CHAR + (LENGTH-PREFIX.length()) + "d", id);
	}
}
