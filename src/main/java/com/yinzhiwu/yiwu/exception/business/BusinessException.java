package com.yinzhiwu.yiwu.exception.business;

/**
 * 业务异常
 * @author ping
 * @Date 2017年10月28日 上午12:08:47
 *
 */
@SuppressWarnings("serial")
public class BusinessException extends Exception{

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}
	
	

}
