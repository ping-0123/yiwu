package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.ElectricContractYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.ElectricContractYzw;
import com.yinzhiwu.yiwu.service.ElectricContractYzwService;

@Service
public class ElectricContractYzwServiceImpl extends BaseServiceImpl<ElectricContractYzw	,String> 
	implements ElectricContractYzwService{
	
	@Autowired
	public void setBaseDao(ElectricContractYzwDao electricContractYzwDao){
		super.setBaseDao(electricContractYzwDao);
	}
	
	
}
