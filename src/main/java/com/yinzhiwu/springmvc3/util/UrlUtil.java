package com.yinzhiwu.springmvc3.util;

import org.springframework.util.StringUtils;

public class UrlUtil {
	
	public static String HEAD_ICON_PATH = "/resources/images/headIcons";
	
	public static String toHeadIcomUrl(String headName) {
		if(StringUtils.hasLength(headName))
			return HEAD_ICON_PATH + "/" + headName;
		return null;
	}
}
