package com.yinzhiwu.yiwu.exception.business;

/**
*@Author ping
*@Time  创建时间:2017年10月28日上午10:55:43
*
*/

@SuppressWarnings("serial")
public class LessonCommentException extends BusinessException{

	public LessonCommentException() {
		super();
	}

	public LessonCommentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LessonCommentException(String message, Throwable cause) {
		super(message, cause);
	}

	public LessonCommentException(String message) {
		super(message);
	}

	public LessonCommentException(Throwable cause) {
		super(cause);
	}

	
}
