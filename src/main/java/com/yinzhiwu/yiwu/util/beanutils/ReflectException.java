package com.yinzhiwu.yiwu.util.beanutils;

/**
*@Author ping
*@Time  创建时间:2017年10月25日下午2:16:36
*
*/

@SuppressWarnings("serial")
public class ReflectException extends RuntimeException{

	public ReflectException() {
		super();
	}

	public ReflectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ReflectException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReflectException(String message) {
		super(message);
	}

	public ReflectException(Throwable cause) {
		super(cause);
	}

	
}
