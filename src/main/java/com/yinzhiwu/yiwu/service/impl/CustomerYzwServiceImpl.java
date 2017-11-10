package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.yinzhiwu.yiwu.dao.CustomerYzwDao;
import com.yinzhiwu.yiwu.entity.PayDeposit;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.CustomerYzwService;

@Service
public class CustomerYzwServiceImpl extends BaseServiceImpl<CustomerYzw, Integer> implements CustomerYzwService {

	@Autowired private CustomerYzwDao customerDao;
	
	@Autowired
	public void setBaseDao(CustomerYzwDao customerYzwDao) {
		super.setBaseDao(customerYzwDao);
	}
	

	@Override
	public CustomerYzw findByWechat(String wechat) {
		return customerDao.findByWeChat(wechat);
	}
	
	@Override
	public CustomerYzw findByMemberCard(String memberCard) throws DataNotFoundException {
		return customerDao.findByMemberCard(memberCard);
	}

	@TransactionalEventListener(classes={PayDeposit.class}, phase=TransactionPhase.BEFORE_COMMIT)
	public void handlePayDeposit(PayDeposit deposit){
		CustomerYzw customer = deposit.getDistributer().getCustomer();
		if(null == customer.getEarnest())
			customer.setEarnest(0f);
		customer.setEarnest(customer.getEarnest() + deposit.getAmount());
		
		update(customer);
	}

	
}
