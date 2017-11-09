package com.yinzhiwu.yiwu.event;

/**
*@Author ping
*@Time  创建时间:2017年11月9日上午9:53:26
*
*/

@SuppressWarnings("serial")
public class EventException extends RuntimeException {

	public EventException() {
		super();
	}

	public EventException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EventException(String message, Throwable cause) {
		super(message, cause);
	}

	public EventException(String message) {
		super(message);
	}

	public EventException(Throwable cause) {
		super(cause);
	}

	
}
