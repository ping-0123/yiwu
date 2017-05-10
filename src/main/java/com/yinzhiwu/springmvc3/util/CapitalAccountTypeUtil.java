package com.yinzhiwu.springmvc3.util;

public class CapitalAccountTypeUtil {

	public static int toTypeId(String typeName){
		switch (typeName) {
		case "微信":
			return 17000013;
		case "支付宝":
			return 17000014;
		default:
			break;
		}
		try {
			throw new Exception(typeName + " is not a CapitalAccountType");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 17000014;
	}
	
	
}
