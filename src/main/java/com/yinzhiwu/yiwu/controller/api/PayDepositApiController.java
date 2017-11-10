package com.yinzhiwu.yiwu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.exception.JSMSException;
import com.yinzhiwu.yiwu.exception.business.BusinessException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.JSMSService;
import com.yinzhiwu.yiwu.service.PayDepositService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/payDeposit")
@Api(description="兑付定金APIs")
public class PayDepositApiController extends BaseController {
	
	@Autowired private PayDepositService depositService;
	@Autowired private JSMSService jsmsService;

	@PostMapping
	@ApiOperation(value="兑付定金")
	public YiwuJson<?> payDeposit(
			@ApiParam(name="amount", value="兑付定金金额, 值大于0", required=true)
			Float amount,
			@RequestParam(value="fundsFirst", required=false, defaultValue="true") 
			@ApiParam(name="fundsFirst", value="是否优先使用基金兑付，默认为true", defaultValue="true")
			boolean fundsFirst,
			@ApiParam(name="code", value="支付定金的短信验证码", required=true) 
			String code) throws BusinessException, JSMSException
	
	{
		Distributer distributer = UserContext.getDistributer();
		if(amount <=0 ) throw new BusinessException("提现金额不能少于零");
		if(!jsmsService.validatePaydepositSMSCode(distributer.getPhoneNo(), code))
			throw new JSMSException("短信码验证失败");
		depositService.payDeposit(distributer, amount, fundsFirst);
		
		return YiwuJson.createBySuccess();
	}
	
	@PostMapping(value="/smsCode")
	@ApiOperation(value="发送兑付定金验证码")
	public YiwuJson<?> sendSmsCode() throws JSMSException{
		Distributer distributer = UserContext.getDistributer();
		jsmsService.sendPaydepositSMSCode(distributer.getPhoneNo());
		
		return YiwuJson.createBySuccess();
	}
}
