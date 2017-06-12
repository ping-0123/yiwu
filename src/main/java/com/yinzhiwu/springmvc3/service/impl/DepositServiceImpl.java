package com.yinzhiwu.springmvc3.service.impl;

import org.hibernate.mapping.DependantValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.springmvc3.dao.DepositDao;
import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.income.DepositEvent;
import com.yinzhiwu.springmvc3.entity.type.EventType;
import com.yinzhiwu.springmvc3.entity.type.IncomeType;
import com.yinzhiwu.springmvc3.service.DepositService;
import com.yinzhiwu.springmvc3.service.IncomeEventService;

@Service
public class DepositServiceImpl extends BaseServiceImpl<DepositEvent, Integer> implements DepositService{

	@Autowired private IncomeEventService incomeEventService;
	
	@Autowired private DistributerDao distributerDao;
	
	@Autowired
	public  void setBaseDao(DepositDao depositDao){
		super.setBaseDao(depositDao);
	}
	
	@Override
	public Integer save(DepositEvent e){
		Assert.notNull(e);
		Assert.notNull(e.getType());
		Assert.notNull(e.getDistributer());
		
		return incomeEventService.save(e);
	}

	
	@Override
	public void payDeposit(int distributerId, float amount, boolean fundsFirst) throws Exception{
		if(amount<=0)
			throw new Exception("请输入非负的定金金额");
		Distributer distributer = distributerDao.get(distributerId);
		
		/**
		 * get distributer's borkerage and funds
		 */
		float brokerage = 0f;
		float funds = 0f;
		try{
			brokerage = distributer.getDistributerIncome(IncomeType.BROKERAGE).getIncome();
			funds = distributer.getDistributerIncome(IncomeType.FUNDS).getIncome();
		}catch (Exception e) {
			logger.debug(e.getMessage());
		}
		if(amount >(brokerage + funds))
			throw new Exception("您的账户佣金余额不足以支付定金");

		
		if(! fundsFirst){
			if(amount > brokerage)
				throw new Exception("您的账户佣金余额不足");
			DepositEvent e = new DepositEvent(distributer, EventType.PAY_DEPOSIT_BY_BROKERAGE,amount);
			incomeEventService.save(e);
		}else if(amount <= funds){
			DepositEvent e = new DepositEvent(distributer, EventType.PAY_DEPOSIT_BY_FUNDS, amount);
			incomeEventService.save(e);
		}else{
			float fundsAmount = funds;
			float brokerageAmount = amount -funds;
			DepositEvent e1 = new DepositEvent(distributer,EventType.PAY_DEPOSIT_BY_FUNDS,fundsAmount);
			DepositEvent e2 = new DepositEvent(distributer, EventType.PAY_DEPOSIT_BY_BROKERAGE,brokerageAmount);
			incomeEventService.save(e1);
			incomeEventService.save(e2);
		}
		
	}
}
