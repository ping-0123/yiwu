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
import com.yinzhiwu.yiwu.service.JSMSService.JSMSTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/jsms")
@Api(description="极光短信APIs, 发送、验证短信验证码, 发送短信消息")
public class JsmsApiController extends BaseController{
	
	@Autowired JSMSService jsmsService;
	
	@GetMapping("/verify")
	public String verify(@RequestParam String echostr) {
		return echostr;
	}
	
	@PostMapping("/codes")
	public YiwuJson<?> sendSMSCode(
			@ApiParam(value="有效的11位数手机号码")
			@RequestParam(name="mobileNumber") String mobileNumber, 
			@ApiParam(value="验证模板:REGISTER(注册),WITHDRAW(提现操作), PAYDEPOSIT(支付定金),SET_DEFAULT_CAPITAL_ACCOUNT(设置默认帐号)"
					+ ",ADD_NEW_CAPITAL_ACCOUNT(添加新的提现帐号),UNBIND_MOBILE_NUMBER(帐号解绑手机号码), BIND_MOBILE_NUMBER(帐号绑定手机号码)")
			@RequestParam(name="template") JSMSTemplate template) throws FormatException, JSMSException{
		
		validateMobileNumber(mobileNumber);
		jsmsService.sendSMSCode(mobileNumber, template);
		return YiwuJson.createBySuccess();
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
	
	@PostMapping("/send_set_default_capital_account_code")
	@ApiOperation("发送设置提现帐号的短信验证码")
	public YiwuJson<?> sendSetAccountSMSCode(@RequestParam(name="mobileNumber") String mobileNumber) throws FormatException, JSMSException{
		validateMobileNumber(mobileNumber);
		jsmsService.sendSetDefaultCapitalAccountSMSCode(mobileNumber);
		return YiwuJson.createBySuccess();
	}
	
	@PostMapping("/send_add_new_capital_account_code")
	@ApiOperation("发送新增提现账号的短信验证码")
	public YiwuJson<?> sendAddNewCaptialAccountSMSCode(@RequestParam(name="mobileNumber") String mobileNumber) 
			throws FormatException, JSMSException{
		
		validateMobileNumber(mobileNumber);
		return YiwuJson.createBySuccess(
				jsmsService.sendAddNewCapitalAccountSMSCode(mobileNumber));
	}
	
	@PostMapping("/send_unbind_mobile_number_code")
	@ApiOperation("发送账号解绑手机号码的短信验证码")
	public YiwuJson<?> sendUnBindMobileNumberSMSCode(@RequestParam(name="mobileNumber") String mobileNumber) 
			throws FormatException, JSMSException{
		
		validateMobileNumber(mobileNumber);
		return YiwuJson.createBySuccess(
				jsmsService.sendUnbindMobileNumberSMSCode(mobileNumber));
	}
	
	@PostMapping("/send_bind_mobile_number_code")
	@ApiOperation("发送绑定手机号码的短信验证码")
	public YiwuJson<?> sendBindMobileNumberSMSCode(@RequestParam(name="mobileNumber") String mobileNumber) 
			throws FormatException, JSMSException{
		
		validateMobileNumber(mobileNumber);
		return YiwuJson.createBySuccess(
				jsmsService.sendBindMobileNumberSMSCode(mobileNumber));
	}
	
	private void validateMobileNumber(String mobileNumber) throws FormatException{
		if(null == mobileNumber)
			throw new FormatException("手机号码为空");
		if(!mobileNumber.trim().matches( "^1\\d{10}$"))
			throw new FormatException("手机号码格式不对");
	}
}
