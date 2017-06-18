package com.yinzhiwu.springmvc3.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

public class BaseController {
	
	protected Log logger = LogFactory.getLog(getClass());
	
	protected String getErrorsMessage(Errors error) {
		if(error.hasErrors()){
			FieldError fieldError = error.getFieldError();
			return fieldError.getField() + "should be " + fieldError.getDefaultMessage();
		}
		return null;
	}
}
