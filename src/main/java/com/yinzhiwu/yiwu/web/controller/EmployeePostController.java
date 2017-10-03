package com.yinzhiwu.yiwu.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.yzw.EmployeePostYzw;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.YiwuJson.ReturnCode;
import com.yinzhiwu.yiwu.service.EmployeePostYzwService;

@Controller
@RequestMapping(value="/system/employeePosts")
public class EmployeePostController extends BaseController{

	@Autowired EmployeePostYzwService employeePostService;
	
	@PostMapping
	@ResponseBody
	public YiwuJson<?> create(@Valid EmployeePostYzw ep, BindingResult result){
		if(result.hasErrors())
			return YiwuJson.createByErrorCodeMessage(ReturnCode.ILLEGAL_ARGUMENT.getCode(), getErrorsMessage(result));
		
		try {
			employeePostService.save(ep);
			return YiwuJson.createBySuccess();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
	}
	
	
}
