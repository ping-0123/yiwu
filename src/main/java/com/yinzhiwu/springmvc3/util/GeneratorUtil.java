package com.yinzhiwu.springmvc3.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GeneratorUtil {

	private static final String PREFIX= "E5";
	
	private static final int LENGTH = 8;
	
	private static final char PADDING_CHAR = '0';
	
	private static final DateFormat DATE_FORMAT =new SimpleDateFormat("yyyyMMdd");
	
	public static String generateMemberId(int id){
		 return String.format(PREFIX+"%" + PADDING_CHAR + (LENGTH-PREFIX.length()) + "d", id);
	}
	
	public static String generateYzwId(String maxId){
		String id_date = maxId.substring(0,7);
		Date date = null;
		Date today = new Date();
		try {
			date = DATE_FORMAT.parse(id_date);
		} catch (ParseException e) {
			return _generate_yzw_id(today, 1);
		}
		if(today.after(date)){
			return _generate_yzw_id(today, 1);
		}
		
		int  id_num = Integer.valueOf( maxId.replace(id_date, ""));
		return _generate_yzw_id(today, id_num + 1);
	}
	
	public static String generateContractNo(String orderId){
		return "YZW" + orderId;
	}

	private static String _generate_yzw_id(Date today, int i) {
		return DATE_FORMAT.format(today) + String.format("%03d",i);
	}
	
	public static void main(String[] args) {
		System.out.println(generateYzwId("2017051003"));
	}
}
