package com.yinzhiwu.yiwu.exception;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月7日 上午1:41:28
*
*/

@SuppressWarnings("serial")
public class JSMSException extends Exception{

	public JSMSException() {
		super();
	}

	public JSMSException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public JSMSException(String message, Throwable cause) {
		super(message, cause);
	}

	public JSMSException(String message) {
		super(message);
	}

	public JSMSException(Throwable cause) {
		super(cause);
	}

	
}
