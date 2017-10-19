package com.yinzhiwu.yiwu.context;

import java.security.Key;

import io.jsonwebtoken.impl.crypto.MacProvider;

/**
*@Author ping
*@Time  创建时间:2017年10月19日下午3:57:01
*
*/

public abstract class JJWTConfig {
	
	public static final String AUTHORIZATION_HEADER_PREFIX = "Yiwu ";
	public static final Key secretKey = MacProvider.generateKey();
	public static final long lifeCycleInSeconds = 7200; //两小时
}
