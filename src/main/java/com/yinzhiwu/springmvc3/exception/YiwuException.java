package com.yinzhiwu.springmvc3.exception;

public class YiwuException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7748510614043398656L;

	public YiwuException() {
		super();
	}

	public YiwuException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public YiwuException(String message, Throwable cause) {
		super(message, cause);
	}

	public YiwuException(String message) {
		super(message);
	}

	public YiwuException(Throwable cause) {
		super(cause);
	}
	

}
