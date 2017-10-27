package com.yinzhiwu.yiwu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.model.YiwuJson;

public class BaseController {

	protected Log logger = LogFactory.getLog(getClass());

	protected String getErrorsMessage(Errors error) {
		if (error.hasErrors()) {
			FieldError fieldError = error.getFieldError();
			return fieldError.getField() + " " + fieldError.getDefaultMessage();
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler
	@ResponseBody
	public YiwuJson handlerException(HttpServletRequest request,HttpServletResponse response, Exception e){
		logger.error(e.getMessage(),e);
		return YiwuJson.createByErrorCodeMessage(response.getStatus(), e.getStackTrace().toString());
//		return ResponseEntity.status(response.getStatus()).body(map);
		
	}
}
