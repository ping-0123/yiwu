package com.yinzhiwu.yiwu.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.JSMSException;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.CustomerVO;
import com.yinzhiwu.yiwu.model.view.CustomerVO.CustomerVOConverter;
import com.yinzhiwu.yiwu.service.CustomerYzwService;
import com.yinzhiwu.yiwu.service.DistributerService;
import com.yinzhiwu.yiwu.service.JJWTService;
import com.yinzhiwu.yiwu.service.JSMSService;
import com.yinzhiwu.yiwu.service.JSMSService.JSMSTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*@Author ping
*@Time  创建时间:2017年10月19日下午4:29:07
*
*/


@RequestMapping(value="/api")
@RestController
@Api("微信端客户登录模块")
public class RegisterLoginApiController extends BaseController {
	
	@Autowired private DistributerService distributerService;
	@Autowired private JSMSService jsmsService;
	@Autowired private JJWTService jjwtService;
	@Autowired private CustomerYzwService customerService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value="/login")
	@ApiOperation("用户微信登录,默认的登录方式")
	public YiwuJson login(
			@ApiParam(name="openId", value="微信openId", required=true) String openId){
		
		if(openId==null || openId.trim().length() ==0){
			return YiwuJson.createByErrorMessage("请传入openId");
		}
		
		Distributer distributer;
		try {
			distributer = distributerService.findByWechatNo(openId);
		} catch (DataNotFoundException e) {
			return YiwuJson.createByErrorMessage("用户尚未注册");
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("token", jjwtService.createDistributerToken(distributer));
		return YiwuJson.createBySuccess(map);
	}
	
	
	@PostMapping(value="loginBymobileNumber")
	@ApiOperation("使用手机验证码登录")
	public YiwuJson<?> loginBymobileNumber(
			@ApiParam(value="手机号码")
			@RequestParam(name="mobileNumber", required =true) String mobileNumber,
			@ApiParam(value="登录验证码")
			@RequestParam(name="code", required=true) String code) throws JSMSException, DataNotFoundException
	{
		jsmsService.validateSMSCode(JSMSTemplate.LOGIN, mobileNumber, code);
		
		Distributer distributer = distributerService.findByPhoneNo(mobileNumber);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("token", jjwtService.createDistributerToken(distributer));
		return YiwuJson.createBySuccess(map);
	}
	
	
	@PostMapping(value="/register")
	@ApiOperation("用户注册")
	 public YiwuJson<?> register(
			 @ApiParam(value="手机号码", required=true) String mobileNumber, 
			 @ApiParam(value="微信openId", required = true) String openId, 
			 @RequestParam(name="memberCard", required=false)
			 @ApiParam(value="老用户的会员卡号", required=false)  String memberCard,
			 @RequestParam(name="invitationCode", required=false) 
			 @ApiParam(value="邀请码", required=false) String invitationCode,
			 @ApiParam(value="注册短信验证码", required=true)  String code) throws JSMSException, YiwuException{
		
		if(!jsmsService.validateRegisterSMSCode(mobileNumber, code))
			throw new JSMSException("短信验证码不正确");
		if(!mobileNumber.matches("^1\\d{10}$"))
			throw new YiwuException("手机号码不合法");
		if(!distributerService.validateMobileNumberBeforeRegister(mobileNumber)){
			throw new YiwuException("手机号码" + mobileNumber + "已注册");
		}
		if(!distributerService.validateOpenIdBeforeRegister(openId)){
			throw new YiwuException("该微信号已注册");
		}
		if(null != memberCard){
			if(!distributerService.validateMembercardBeforeRegister(memberCard))
				throw new YiwuException("输入的会员卡号已被注册");
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("register mobile number is " + mobileNumber);
			logger.debug("register wechat openId is " + openId);
			logger.debug("register memberCard is  " + memberCard);
		}
		
		Distributer distributer = distributerService.doRegister(mobileNumber, openId, memberCard, invitationCode);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("token", jjwtService.createDistributerToken(distributer));
		return YiwuJson.createBySuccess(map);
	 }
	
	
	@Deprecated
	@PostMapping(value="/register/validate/MobileNumber")
	@ApiOperation("注册前验证手机号码, 即将弃用, 被   \"api/register/validate/mobileNumber\"替代")
	public YiwuJson<?> validateMobileNumber(String mobileNumber)
	{
		if (!mobileNumber.matches("^1\\d{10}$"))
			return YiwuJson.createByErrorMessage("请输入合法的11位数手机号码");
		
		try {
			distributerService.findByPhoneNo(mobileNumber);
			return YiwuJson.createByErrorMessage("该号码已注册");
		} catch (DataNotFoundException e) {
			return YiwuJson.createBySuccessMessage("该号码未注册");
		}
	}
	
	@PostMapping(value="/register/validate/mobileNumber")
	@ApiOperation("注册前验证手机号码")
	public YiwuJson<?> validateMobileNumber2(String mobileNumber)
	{
		if (!mobileNumber.matches("^1\\d{10}$"))
			return YiwuJson.createByErrorMessage("请输入合法的11位数手机号码");
		
		try {
			distributerService.findByPhoneNo(mobileNumber);
			return YiwuJson.createByErrorMessage("该号码已注册");
		} catch (DataNotFoundException e) {
			return YiwuJson.createBySuccessMessage("该号码未注册");
		}
	}
	
	@PostMapping(value="/register/validate/openId")
	@ApiOperation("注册前验证微信号")
	public YiwuJson<?> valiateOpenId(String openId){
		try {
			distributerService.findByWechatNo(openId);
			return YiwuJson.createByErrorMessage("该微信号已注册");
		} catch (DataNotFoundException e) {
			return YiwuJson.createBySuccess();
		}
	}
	
	@PostMapping(value="/register/validate/memberCard")
	@ApiOperation("注册前验证会员卡号")
	public YiwuJson<CustomerVO> validateMemberCard(String memberCard){
		try {
			distributerService.findByMemberCard(memberCard);
			return YiwuJson.createByErrorMessage("该会员已注册");
		} catch (DataNotFoundException e1) {
			;
		}
		
		try {
			CustomerYzw customer =  customerService.findByMemberCard(memberCard);
			return YiwuJson.createBySuccess(CustomerVOConverter.INSTANCE.fromPO(customer));
		} catch (DataNotFoundException e) {
			return YiwuJson.createByErrorMessage("会员卡号无效");
		}
	}
}
