package com.yinzhiwu.springmvc3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.entity.yzw.ElectricContractYzw;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.EContractDetailApiView;
import com.yinzhiwu.springmvc3.service.ElectricContractYzwService;

@RestController
@RequestMapping("/api/e-contract")
public class EContractController  extends BaseController{

	@Autowired
	private ElectricContractYzwService electricContractYzwService;
	
	@GetMapping("/{contractNo}")
	public YiwuJson<EContractDetailApiView> get(@PathVariable String contractNo)
	{
		return new YiwuJson<>(new EContractDetailApiView(
				electricContractYzwService.get(contractNo)));
	}
}
