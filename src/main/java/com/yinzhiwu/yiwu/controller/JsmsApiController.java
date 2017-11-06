package com.yinzhiwu.yiwu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.service.JSMSService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/jsms")
@Api(description="极光短信APIs, 发送、验证短信验证码, 发送短信消息")
public class JsmsApiController {
	
	@Autowired JSMSService jsmsService;
	
	@GetMapping("/verify")
	public String verify(@RequestParam String echostr) {
		return echostr;
	}
	
/*	@PostMapping("/send_code/register")
	@ApiOperation("发送注册短信验证码")*/
}
