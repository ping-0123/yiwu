package com.yinzhiwu.springmvc3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.OrderYzwDao;
import com.yinzhiwu.springmvc3.dao.PurchaseEventDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.income.PurchaseEvent;
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
	
	/**
	 * 不排除定金
	 * @param order
	 * @throws DataNotFoundException
	 */
	public void savePurchaseEvent(OrderYzw order){
		Assert.notNull(order);
		Assert.notNull(order.getCustomer());
		
		Distributer distributer;
		try {
			distributer = distributerDao.findByProperty("customer.id", order.getCustomer().getId()).get(0);
		} catch (DataNotFoundException e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
			return;
		}
//		float amount = orderDao.get_effective_brockerage_base(order);
		PurchaseEvent event = new PurchaseEvent(distributer,  order);
		incomeEventService.save(event);
	}
	
	/*
	 * 每晚4点定时执行， 产生对应的佣金收益
	 */
	@Override
	public void saveAllLastDayPurchaseEvents(){
		List<OrderYzw> orders = orderDao.findAllLastDayOrders();
		logger.debug("总共有多少订单" + orders.size());
		for (OrderYzw order : orders) {
			savePurchaseEvent(order);
		}
	}
}
