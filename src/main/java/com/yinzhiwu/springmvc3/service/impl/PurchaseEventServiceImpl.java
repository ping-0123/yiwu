package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.OrderYzwDao;
import com.yinzhiwu.springmvc3.dao.PurchaseEventDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.income.PurchaseEvent;
import com.yinzhiwu.springmvc3.entity.type.EventType;
import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.service.IncomeEventService;
import com.yinzhiwu.springmvc3.service.PurchaseEventService;

@Service
public class PurchaseEventServiceImpl extends BaseServiceImpl<PurchaseEvent,Integer> implements PurchaseEventService {
	
	@Autowired private DistributerDao distributerDao;
	
	@Autowired private OrderYzwDao orderDao;
	
	@Autowired private IncomeEventService incomeEventService;

	@Autowired
	public void setBaseDao(PurchaseEventDao purchaseEventDao){
		super.setBaseDao(purchaseEventDao);
	}
	
	public void savePurchaseEvent(OrderYzw order) throws DataNotFoundException{
		Assert.notNull(order);
		Assert.notNull(order.getCustomer());
		
		Distributer distributer = distributerDao.findByProperty("customer.id", order.getCustomer().getId()).get(0);
		float amount = orderDao.get_effective_brockerage_base(order);
		
		PurchaseEvent event = new PurchaseEvent(distributer, EventType.PURCHASE_PRODUCTS, amount, order);
		incomeEventService.save(event);
	}
}
