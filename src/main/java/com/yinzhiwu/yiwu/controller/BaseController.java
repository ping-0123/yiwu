package com.yinzhiwu.yiwu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public ResponseEntity handlerException(HttpServletRequest request,HttpServletResponse response, Exception e){
		logger.error(e.getMessage(),e);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", e.getMessage());
		return ResponseEntity.status(response.getStatus()).body(map);
		
	}
}
