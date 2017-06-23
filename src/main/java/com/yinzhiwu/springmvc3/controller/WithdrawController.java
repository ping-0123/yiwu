package com.yinzhiwu.springmvc3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.service.WithdrawEventService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/event/withdraw")
@Api(value="withdraw")
public class WithdrawController extends BaseController {
	
	@Autowired
	private WithdrawEventService withdrawEventService;
	
	@PostMapping
	@ApiOperation(value="提现操作")
	public YiwuJson<Boolean> withdraw(int distributerId, int accountId, float amount){
		try{
			withdrawEventService.saveWithdraw(distributerId, accountId,amount);
		}catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
		
		return new YiwuJson<>(new Boolean(true));
	}

}
