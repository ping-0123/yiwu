package com.yinzhiwu.yiwu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.WithdrawBrokerage;
import com.yinzhiwu.yiwu.exception.JSMSException;
import com.yinzhiwu.yiwu.exception.business.WithdrawBrokerageException;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.WithdrawBrokerageVO;
import com.yinzhiwu.yiwu.model.view.WithdrawBrokerageVO.WithdrawBrokerageVOConverter;
import com.yinzhiwu.yiwu.service.CapitalAccountService;
import com.yinzhiwu.yiwu.service.JSMSService;
import com.yinzhiwu.yiwu.service.WithdrawBrokerageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
* @author 作者 ping
* @Date 创建时间：2017年10月29日 下午9:41:46
*
*/

@RestController
@RequestMapping("/api/withdraws")
@Api(description="提现APIs")
public class WithdrawBrokerageApiController {
	
	@Autowired private WithdrawBrokerageService withdrawBrokerageService;
	@Autowired private CapitalAccountService capitalAccountService;
	@Autowired private JSMSService jsmsService;
	
	@PostMapping
	@ApiOperation(value="提现操作")
	public YiwuJson<WithdrawBrokerageVO> withdraw(
			@RequestParam(name="amount", required=true) 
			@ApiParam(value="提现金额", required=true) 
			Float amount,
			@RequestParam(name="accountId", required=false) 
			@ApiParam(value="提现账号,为空则体现到默认帐号,如果默认帐号未设置，则提现失败", required=false) 
			Integer accountId, 
			@ApiParam(value="提现验证码",required=true)
			@RequestParam(name="code")
			String code) throws WithdrawBrokerageException, DataNotFoundException, JSMSException
	{
		if(amount<=0)
			throw new WithdrawBrokerageException("提现金额须大于0元");
		
		Distributer distributer = UserContext.getDistributer();
		
		if(!jsmsService.validateWithDrawSMSCode(distributer.getPhoneNo(), code))
			throw new JSMSException("提现短信码验证失败");
		
		CapitalAccount account;
		if(null != accountId)
			account = capitalAccountService.get(accountId);
		else{
			account = distributer.getDefaultCapitalAccount();
			if(null == account)
				throw new WithdrawBrokerageException("请设置默认提现账号");
		}
		
		
		WithdrawBrokerage withdraw = new WithdrawBrokerage();
		withdraw.setDistributer(distributer);
		withdraw.setAmount(amount);
		withdraw.setCapitalAccount(account);
		withdrawBrokerageService.doWithdraw(withdraw);
		
		return YiwuJson.createBySuccess(WithdrawBrokerageVOConverter.INSTANCE.fromPO(withdraw));
	}
}
