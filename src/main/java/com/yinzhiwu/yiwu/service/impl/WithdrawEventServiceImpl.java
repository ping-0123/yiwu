package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.CapitalAccountDao;
import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.dao.WithdrawEventDao;
import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.income.WithdrawEvent;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.service.IncomeEventService;
import com.yinzhiwu.yiwu.service.WithdrawEventService;

@Service
public class WithdrawEventServiceImpl extends BaseServiceImpl<WithdrawEvent, Integer> implements WithdrawEventService {

	@Autowired
	private DistributerDao distributerDao;

	@Autowired
	private CapitalAccountDao capitalAccountDao;

	@Autowired
	private IncomeEventService incomeEventService;

	@Autowired
	public void setBaseDao(WithdrawEventDao withdrawEventDao) {
		super.setBaseDao(withdrawEventDao);
	}

	@Override
	public void saveWithdraw(int distributerId, int accountId, float amount) throws Exception {
		if (amount <= 0)
			throw new Exception("请输入非负的提现金额");
		Distributer distributer = distributerDao.get(distributerId);
		CapitalAccount account = capitalAccountDao.get(accountId);

		WithdrawEvent event = new WithdrawEvent(distributer, EventType.WITHDRAW, amount, account);
		incomeEventService.save(event);
	}

}
