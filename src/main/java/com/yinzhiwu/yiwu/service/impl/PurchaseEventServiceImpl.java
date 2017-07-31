package com.yinzhiwu.yiwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.dao.PurchaseEventDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.income.PurchaseEvent;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.IncomeEventService;
import com.yinzhiwu.yiwu.service.PurchaseEventService;

@Service(value = "purchaseEventServiceImpl")
public class PurchaseEventServiceImpl extends BaseServiceImpl<PurchaseEvent, Integer> implements PurchaseEventService {

	@Autowired
	private DistributerDao distributerDao;
	@Autowired
	private OrderYzwDao orderDao;
	@Autowired
	private IncomeEventService incomeEventService;

	@Autowired
	public void setBaseDao(PurchaseEventDao purchaseEventDao) {
		super.setBaseDao(purchaseEventDao);
	}

	/**
	 * 不排除定金
	 * 
	 * @param order
	 * @throws DataNotFoundException
	 */
	public void savePurchaseEvent(OrderYzw order) {
		Assert.notNull(order);
		Assert.notNull(order.getCustomer());

		Distributer distributer = distributerDao.findByProperty("customer.id", order.getCustomer().getId()).get(0);
		// float amount = orderDao.get_effective_brockerage_base(order);
		PurchaseEvent event = new PurchaseEvent(distributer, order);
		incomeEventService.save(event);
	}

	/*
	 * 每晚4点定时执行， 产生对应的佣金收益
	 */
	@Override
	public void saveAllLastDayPurchaseEvents() {
		List<OrderYzw> orders = orderDao.findAllLastDayOrders();
		logger.debug("总共有多少订单" + orders.size());
		for (OrderYzw order : orders) {
			savePurchaseEvent(order);
		}
	}
}
