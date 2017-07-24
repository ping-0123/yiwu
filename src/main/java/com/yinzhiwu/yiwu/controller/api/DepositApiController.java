package com.yinzhiwu.yiwu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.DepositService;

@RestController
@RequestMapping("/api/event/deposit")
public class DepositApiController extends BaseController{

	@Autowired
	private DepositService depositService;
	
	@PostMapping
	public YiwuJson<Boolean> payDeposit(int distributerId,  float amount, boolean fundsFirst){
		try{
			depositService.saveDeposit(distributerId , amount, fundsFirst);
		}catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
		
		return new YiwuJson<>(new Boolean(true));
	}
}
