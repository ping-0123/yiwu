package com.yinzhiwu.springmvc3.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

public class BaseController {

	protected String getErrorsMessage(Errors error) {
		if(error.hasErrors()){
			FieldError fieldError = error.getFieldError();
			return fieldError.getField() + "should be " + fieldError.getDefaultMessage();
		}
		return null;
	}
}
