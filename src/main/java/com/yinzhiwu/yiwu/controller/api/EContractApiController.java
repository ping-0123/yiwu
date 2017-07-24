package com.yinzhiwu.yiwu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.yzw.ElectricContractYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.EContractDetailApiView;
import com.yinzhiwu.yiwu.service.ElectricContractYzwService;

@RestController
@RequestMapping("/api/e-contract")
public class EContractApiController  extends BaseController{

	@Autowired
	private ElectricContractYzwService electricContractYzwService;
	
	@GetMapping("/{contractNo}")
	public YiwuJson<EContractDetailApiView> get(@PathVariable String contractNo)
	{
		try{
			ElectricContractYzw eContract = electricContractYzwService.get(contractNo);
			if(eContract == null) return new YiwuJson<>("未能找到合约号为" + contractNo + "的电子合同");
			return new YiwuJson<>(new EContractDetailApiView(eContract));
		}catch (Exception e) {
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
