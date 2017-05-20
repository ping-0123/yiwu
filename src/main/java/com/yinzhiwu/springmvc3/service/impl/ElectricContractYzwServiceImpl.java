package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.ElectricContractYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.ElectricContractYzw;
import com.yinzhiwu.springmvc3.service.ElectricContractYzwService;

@Service
public class ElectricContractYzwServiceImpl extends BaseServiceImpl<ElectricContractYzw	,String> 
	implements ElectricContractYzwService{
	
	@Autowired
	public void setBaseDao(ElectricContractYzwDao electricContractYzwDao){
		super.setBaseDao(electricContractYzwDao);
	}
	
	
}
