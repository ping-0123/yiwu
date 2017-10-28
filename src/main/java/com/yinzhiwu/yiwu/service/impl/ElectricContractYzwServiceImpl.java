package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.ElectricContractYzwDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.ElectricContractYzw;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.service.ElectricContractYzwService;

@Service
public class ElectricContractYzwServiceImpl extends BaseServiceImpl<ElectricContractYzw, String>
		implements ElectricContractYzwService {

	@Autowired
	public void setBaseDao(ElectricContractYzwDao electricContractYzwDao) {
		super.setBaseDao(electricContractYzwDao);
	}

	@Autowired private OrderYzwDao orderYzwDao;
	
	@Override
	public ElectricContractYzw get(String contractNo) {
		Assert.hasLength(contractNo);
		
		ElectricContractYzw econtract;
		try {
			econtract = super.get(contractNo);
		} catch (DataNotFoundException e1) {
			OrderYzw order;
			try {
				order = orderYzwDao.findByContractNO(contractNo);
			} catch (YiwuException e) {
				return null;
			}
			econtract = new ElectricContractYzw(order);
			super.save(econtract);
		}
		
		return econtract;
	}

	
}
