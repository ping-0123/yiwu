package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.CapitalAccountDao;
import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.WithdrawEventDao;
import com.yinzhiwu.springmvc3.entity.CapitalAccount;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.income.WithdrawEvent;
import com.yinzhiwu.springmvc3.entity.type.EventType;
import com.yinzhiwu.springmvc3.service.IncomeEventService;
import com.yinzhiwu.springmvc3.service.WithdrawEventService;

@Service
public class WithdrawEventServiceImpl extends BaseServiceImpl<WithdrawEvent,Integer> implements WithdrawEventService{
	
	@Autowired private DistributerDao distributerDao;
	
	@Autowired private CapitalAccountDao capitalAccountDao;
	
	@Autowired private IncomeEventService incomeEventService;
	
	@Autowired
	public void setBaseDao(WithdrawEventDao withdrawEventDao){
		super.setBaseDao(withdrawEventDao);
	}

	@Override
	public void saveWithdraw(int distributerId, int accountId, float amount) throws Exception {
		if(amount <=0) throw new Exception("请输入非负的提现金额");
		Distributer distributer = distributerDao.get(distributerId);
		CapitalAccount account = capitalAccountDao.get(accountId);
		
		WithdrawEvent event = new WithdrawEvent(distributer, EventType.WITHDRAW, amount, account);
		incomeEventService.save(event);
	}
	
}
