package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.WithdrawBrokerageDao;
import com.yinzhiwu.yiwu.entity.WithdrawBrokerage;
import com.yinzhiwu.yiwu.event.PayWithdrawEvent;
import com.yinzhiwu.yiwu.event.WithdrawEvent;
import com.yinzhiwu.yiwu.service.JSMSService;
import com.yinzhiwu.yiwu.service.WithdrawBrokerageService;

/**
 * 
 * @author ping
 * @Date 2017年10月29日 下午9:26:28
 *
 */

@Service
public class WithdrawBrokerageServiceImpl extends BaseServiceImpl<WithdrawBrokerage,Integer> implements WithdrawBrokerageService {
	
	@Autowired private ApplicationContext applicationContext;
	
	@Autowired public void setBaseDao(WithdrawBrokerageDao dao){super.setBaseDao(dao);}

	/**
	 * listened by {@link IncomeEventServiceImpl#handleWithdrawBrokerage(WithdrawEvent)}
	 * and {@link JSMSService#handleWithdrawEvent(WithdrawEvent)}
	 */
	@Override
	public WithdrawBrokerage doWithdraw(WithdrawBrokerage withdraw) {
		super.save(withdraw);
		
		applicationContext.publishEvent(new WithdrawEvent(withdraw));
	
		return withdraw;
	}

	/**
	 * listened by {@link MessageServiceImpl#handlePayWithdrawEvent(PayWithdrawEvent)}
	 * and {@link JSMSService#handlePayWithdrawEvent(PayWithdrawEvent)}
	 */
	@Override
	public WithdrawBrokerage doPayWithdraw(WithdrawBrokerage withdraw) {
		super.update(withdraw);
		
		applicationContext.publishEvent(new PayWithdrawEvent(withdraw));
		
		return withdraw;
	}


}
