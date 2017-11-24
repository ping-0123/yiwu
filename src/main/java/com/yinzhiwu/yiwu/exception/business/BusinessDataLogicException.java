package com.yinzhiwu.yiwu.exception.business;

/**
*@Author ping
*@Time  创建时间:2017年11月24日下午2:09:32
*
*/

public class BusinessDataLogicException extends RuntimeException{

	private static final long serialVersionUID = 7171647137588388913L;

	public BusinessDataLogicException() {
		super();
	}

	public BusinessDataLogicException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BusinessDataLogicException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessDataLogicException(String message) {
		super(message);
	}

	public BusinessDataLogicException(Throwable cause) {
		super(cause);
	}

	
}
