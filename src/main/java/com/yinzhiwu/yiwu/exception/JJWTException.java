package com.yinzhiwu.yiwu.exception;

/**
*@Author ping
*@Time  创建时间:2017年11月13日上午10:39:44
*
*/

@SuppressWarnings("serial")
public class JJWTException extends RuntimeException {

	public JJWTException() {
		super();
	}

	public JJWTException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public JJWTException(String message, Throwable cause) {
		super(message, cause);
	}

	public JJWTException(String message) {
		super(message);
	}

	public JJWTException(Throwable cause) {
		super(cause);
	}
	
	
}
