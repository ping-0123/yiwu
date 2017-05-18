package com.yinzhiwu.springmvc3.util;

import org.springframework.util.StringUtils;

public class UrlUtil {
	
	public static String HEAD_ICON_PATH = "/resources/images/headIcons";
	
	public static String TWEET_COVER_ICON_PATH="/resources/images/tweetCoverIcons";
	
	public static String toHeadIcomUrl(String headName) {
		if(StringUtils.hasLength(headName))
			return HEAD_ICON_PATH + "/" + headName;
		return null;
	}
	
	public static String toTweetCoverIconUrl(String fileName)
	{
		if(StringUtils.hasLength(fileName))
			return TWEET_COVER_ICON_PATH + "/" + fileName;
		return null;
	}
}
