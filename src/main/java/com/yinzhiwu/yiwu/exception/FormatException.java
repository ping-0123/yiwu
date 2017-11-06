package com.yinzhiwu.yiwu.exception;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月7日 上午1:56:28
*
*/

@SuppressWarnings("serial")
public class FormatException extends Exception{

	public FormatException() {
		super();
	}

	public FormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public FormatException(String message) {
		super(message);
	}

	public FormatException(Throwable cause) {
		super(cause);
	}
	
	
}
