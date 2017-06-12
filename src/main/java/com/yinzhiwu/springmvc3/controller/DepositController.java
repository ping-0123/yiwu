package com.yinzhiwu.springmvc3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.service.DepositService;

@RestController
@RequestMapping("/api/deposit")
public class DepositController extends BaseController{

	@Autowired
	private DepositService depositService;
	
	@PostMapping
	public YiwuJson<Boolean> payDeposit(int distributerId,  float amount, boolean fundsFirst){
		try{
			depositService.payDeposit(distributerId , amount, fundsFirst);
		}catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
		
		return new YiwuJson<>(new Boolean(true));
	}
}
