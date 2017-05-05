package com.yinzhiwu.springmvc3.controller;


import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.hibernate.procedure.internal.Util.ResultClassesResolutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.dao.impl.DistributerDaoImpl;
import com.yinzhiwu.springmvc3.entity.Customer;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.model.DistributerApiView;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.service.DistributerService;

@RestController
@RequestMapping("/api/distributer")
public class DistributerController {
	private static final Log logger = LogFactory.getLog(DistributerDaoImpl.class);
	
	
	@Autowired
	private DistributerService  distributerService;

	@RequestMapping(value="/register", method={RequestMethod.POST})
	public YiwuJson<DistributerApiView> register(String invitationCode,
										@Valid @ModelAttribute Distributer distributer,
										BindingResult bindingResult,
										Model model){
		if(bindingResult.hasErrors()){
			 FieldError field = bindingResult.getFieldError();
			 String message =   field.getField() + " " + field.getDefaultMessage();
			 logger.info(message);
			 return new YiwuJson<>(200,false,message,null,false);
		}
		
		return  distributerService.register(invitationCode, distributer);
	}
	
	
	@RequestMapping(value="/loginByWechat", method={RequestMethod.POST,RequestMethod.GET})
	public YiwuJson<DistributerApiView> loginByWechat(@RequestParam String  wechatNo ){
		return distributerService.loginByWechat(wechatNo);
	}
	
	
	@GetMapping(value="/loginByAccount")
	public YiwuJson<DistributerApiView> loginByAccount(String account, String password){
		return distributerService.loginByAccount(account,password);
	}
	
	
	@GetMapping(value="/{id}")
	public YiwuJson<DistributerApiView> getDistributerInfo(@PathVariable int id){
		return distributerService.findById(id);
	}
	
	
	
	
	

}
