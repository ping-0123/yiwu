package com.yinzhiwu.yiwu.exception.business;

/**
*@Author ping
*@Time  创建时间:2017年10月28日下午3:31:43
*
*/

@SuppressWarnings("serial")
public class LessonAppointmentException extends BusinessException {

	public LessonAppointmentException() {
		super();
	}

	public LessonAppointmentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LessonAppointmentException(String message, Throwable cause) {
		super(message, cause);
	}

	public LessonAppointmentException(String message) {
		super(message);
	}

	public LessonAppointmentException(Throwable cause) {
		super(cause);
	}

	
}
