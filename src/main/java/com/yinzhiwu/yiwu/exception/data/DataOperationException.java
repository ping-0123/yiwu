package com.yinzhiwu.yiwu.exception.data;

/**
*@Author ping
*@Time  创建时间:2017年10月7日上午9:29:02
*
*/

public class DataOperationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5302571488381785062L;

	public DataOperationException() {
		super();
	}

	public DataOperationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataOperationException(String message) {
		super(message);
	}

	public DataOperationException(Throwable cause) {
		super(cause);
	}
	
	

}
