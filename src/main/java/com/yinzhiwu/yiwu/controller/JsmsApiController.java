package com.yinzhiwu.yiwu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.exception.FormatException;
import com.yinzhiwu.yiwu.exception.JSMSException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.JSMSService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/jsms")
@Api(description="极光短信APIs, 发送、验证短信验证码, 发送短信消息")
public class JsmsApiController extends BaseController{
	
	@Autowired JSMSService jsmsService;
	
	@GetMapping("/verify")
	public String verify(@RequestParam String echostr) {
		return echostr;
	}
	
	@PostMapping("/send_register_code")
	@ApiOperation("发送注册短信验证码")
	public YiwuJson<?> sendRegisterSMSCode(@RequestParam(name="mobileNumber") String mobileNumber)
			throws FormatException, JSMSException{
		validateMobileNumber(mobileNumber);
		jsmsService.sendRegisterSMSCode(mobileNumber);
		return YiwuJson.createBySuccess();
	}
	
	@PostMapping("/send_withdraw_code")
	@ApiOperation("发送提现操作短信验证码")
	public YiwuJson<?> sendWithdrawSMSCode(@RequestParam(name="mobileNumber") String mobileNumber)
			throws FormatException, JSMSException{
		validateMobileNumber(mobileNumber);
		jsmsService.sendWithDrawSMSCode(mobileNumber);
		return YiwuJson.createBySuccess();
	}
	
	@PostMapping("/send_paydeposit_code")
	@ApiOperation("发送转定金短信验证码")
	public YiwuJson<?> sendPaydepositSMSCode(@RequestParam(name="mobileNumber") String mobileNumber)
			throws FormatException, JSMSException{
		validateMobileNumber(mobileNumber);
		jsmsService.sendPaydepositSMSCode(mobileNumber);
		return YiwuJson.createBySuccess();
	}
	
	@PostMapping("/send_setAccount_code")
	@ApiOperation("发送设置提现帐号验证码")
	public YiwuJson<?> sendSetAccountSMSCode(@RequestParam(name="mobileNumber") String mobileNumber) throws FormatException, JSMSException{
		validateMobileNumber(mobileNumber);
		jsmsService.sendSetAccountSMSCode(mobileNumber);
		return YiwuJson.createBySuccess();
	}
	
	private void validateMobileNumber(String mobileNumber) throws FormatException{
		if(null == mobileNumber)
			throw new FormatException("手机号码为空");
		if(!mobileNumber.trim().matches( "^1\\d{10}$"))
			throw new FormatException("手机号码格式不对");
	}
}
