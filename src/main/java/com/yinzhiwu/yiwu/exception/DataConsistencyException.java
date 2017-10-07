package com.yinzhiwu.yiwu.exception;

public class DataConsistencyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataConsistencyException(String message) {
		super(message);
	}

	public DataConsistencyException() {
		super();
	}

	public DataConsistencyException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataConsistencyException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataConsistencyException(Throwable cause) {
		super(cause);
	}
	
	

}
