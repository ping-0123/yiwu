package com.yinzhiwu.springmvc3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.entity.yzw.ElectricContractYzw;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
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
		try {
			return new YiwuJson<>(new EContractDetailApiView(
					electricContractYzwService.get(contractNo)));
		} catch (DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	@PutMapping("/{contractNo}")
	public YiwuJson<Boolean> update(ElectricContractYzw e , @PathVariable String contractNo) {
		if(!e.getIsConfirmed())
			return new YiwuJson<>("确认合同， 请设置参数isConfirmed:'true'");
		try {
			electricContractYzwService.modify(contractNo, e);
		} catch (IllegalArgumentException | IllegalAccessException | DataNotFoundException e1) {
			return new YiwuJson<>(e1.getMessage());
		}
		return new YiwuJson<>("成功确认合同", new Boolean(true));
	}
}
