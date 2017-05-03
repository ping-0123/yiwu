package com.yinzhiwu.springmvc3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.service.AccountService;

@Controller
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@ResponseBody
	@PostMapping("/login")
	public YiwuJson<?> login(@RequestParam String account, @RequestParam String password){
		
		return accountService.login(account,password);
	}
}
