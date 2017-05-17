package com.yinzhiwu.springmvc3.util;

import org.springframework.util.StringUtils;

public class UrlUtil {
	
	public static String HEAD_ICON_PATH = "/resources/images/headIcons";
	
	private static String TWEET_COVER_ICON_PATH="/recources/images/tweetCoverIcons";
	
	public static String toHeadIcomUrl(String headName) {
		if(StringUtils.hasLength(headName))
			return HEAD_ICON_PATH + "/" + headName;
		return null;
	}
}
