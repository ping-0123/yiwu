package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.DepositDao;
import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.dao.ProductYzwDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.income.DepositEvent;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw;
import com.yinzhiwu.yiwu.service.DepositService;
import com.yinzhiwu.yiwu.service.IncomeEventService;
import com.yinzhiwu.yiwu.service.OrderYzwService;

@Service
public class DepositServiceImpl extends BaseServiceImpl<DepositEvent, Integer> implements DepositService {

	@Autowired
	private IncomeEventService incomeEventService;

	@Autowired
	private DistributerDao distributerDao;

	@Autowired
	private OrderYzwService orderService;

	@Autowired
	private ProductYzwDao productDao;

	@Autowired
	public void setBaseDao(DepositDao depositDao) {
		super.setBaseDao(depositDao);
	}

	@Override
	public Integer save(DepositEvent e) {
		Assert.notNull(e);
		Assert.notNull(e.getType());
		Assert.notNull(e.getDistributer());

		return incomeEventService.save(e);
	}

	@Override
	public void saveDeposit(int distributerId, float amount, boolean fundsFirst) throws Exception {
		if (amount <= 0)
			throw new Exception("请输入非负的定金金额");
		Distributer distributer = distributerDao.get(distributerId);

		/**
		 * get distributer's borkerage and funds
		 */
		float brokerage = distributer.getIncomeValue(IncomeType.BROKERAGE);
		float funds = distributer.getIncomeValue(IncomeType.FUNDS);
		if (amount > (brokerage + funds))
			throw new Exception("您的账户资金余额不足以支付定金");
		DepositEvent fundsDepoist = null;
		DepositEvent brokerageDeposit = null;

		/**
		 * save in order
		 */
		ProductYzw product;
		if (distributer.isAudit())
			product = productDao.get_audit_deposit_product();
		else
			product = productDao.get_children_deposit_product();
		OrderYzw order = new OrderYzw(distributer.getCustomer(), product, amount, distributer.getFollowedByStore());
		orderService.save(order);

		/**
		 * save deposit event
		 */
		if (!fundsFirst) {
			if (amount > brokerage)
				throw new Exception("您的账户佣金余额不足");
			brokerageDeposit = new DepositEvent(distributer, EventType.PAY_DEPOSIT_BY_BROKERAGE, amount, order);
			incomeEventService.save(brokerageDeposit);
		} else if (amount <= funds) {
			fundsDepoist = new DepositEvent(distributer, EventType.PAY_DEPOSIT_BY_FUNDS, amount, order);
			incomeEventService.save(fundsDepoist);
		} else {
			float fundsAmount = funds;
			float brokerageAmount = amount - funds;
			fundsDepoist = new DepositEvent(distributer, EventType.PAY_DEPOSIT_BY_FUNDS, fundsAmount, order);
			brokerageDeposit = new DepositEvent(distributer, EventType.PAY_DEPOSIT_BY_BROKERAGE, brokerageAmount,
					order);
			incomeEventService.save(fundsDepoist);
			incomeEventService.save(brokerageDeposit);
		}

	}
}
