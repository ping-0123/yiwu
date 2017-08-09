package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.CustomerYzwDao;
import com.yinzhiwu.yiwu.dao.DepositDao;
import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.dao.OrderPayedMethodYzwDao;
import com.yinzhiwu.yiwu.dao.ProductYzwDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.income.DepositEvent;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.OrderPayedMethodYzw;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.entity.yzw.PayedMethodYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw.CustomerAgeType;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.service.DepositService;
import com.yinzhiwu.yiwu.service.IncomeEventService;
import com.yinzhiwu.yiwu.service.OrderYzwService;

@Service
public class DepositServiceImpl extends BaseServiceImpl<DepositEvent, Integer> implements DepositService {

	@Autowired
	private IncomeEventService incomeEventService;

	@Autowired
	private DistributerDao distributerDao;
	@Autowired private CustomerYzwDao customerYzwDao;

	@Autowired
	private OrderYzwService orderService;
	@Autowired private OrderPayedMethodYzwDao orderPayedMethodYzwDao;

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
		if(distributer == null)
			throw new YiwuException("未找到distributerId为：" + distributerId+ "的分销者");
		CustomerYzw customer = distributer.getCustomer();
		if(customer == null ) {
			if(logger.isDebugEnabled())
				logger.debug("distributer: " + distributerId + "未绑定customer");
			throw new Exception("distributer: " + distributerId + "未绑定customer");
		}

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
		 * save deposit order
		 */
		ProductYzw product;
		if (CustomerAgeType.ADULT == distributer.getCustomerAgeType())
			product = productDao.get_audit_deposit_product();
		else
			product = productDao.get_children_deposit_product();
		OrderYzw order = new OrderYzw(customer, product, amount, distributer.getFollowedByStore());
		orderService.save(order);
		//修改客户定金
		if(customer.getEarnest() == null)
			customer.setEarnest(0f);
		customer.setEarnest(amount + customer.getEarnest() );
		customerYzwDao.update(customer);
		//保存支付方式
		OrderPayedMethodYzw payedMethod = new OrderPayedMethodYzw(order, PayedMethodYzw.INNER_PAYED_METHOD, amount);
		orderPayedMethodYzwDao.save(payedMethod);
		/**
		 * agaist the order
		 */
		float againstAmount = -1 * amount;
		OrderYzw agaistOrder = new OrderYzw(customer, product, againstAmount, distributer.getFollowedByStore());
		orderService.save(agaistOrder);
		OrderPayedMethodYzw againstPayedMethod = new OrderPayedMethodYzw(order, PayedMethodYzw.INNER_PAYED_METHOD, againstAmount);
		orderPayedMethodYzwDao.save(againstPayedMethod);
		
		/**
		 * save deposit event
		 */
		float fundsPayed , brokeragePayed;
		if(fundsFirst){
			fundsPayed=amount>funds?funds:amount;
			brokeragePayed = amount>fundsPayed?amount-fundsPayed:0f;
		}else{
			brokeragePayed = amount>brokerage?brokerage:amount;
			fundsPayed	 = amount>brokeragePayed?amount-brokeragePayed:0f;
		}
		if(fundsPayed> 0f){
			fundsDepoist = new DepositEvent(distributer, EventType.PAY_DEPOSIT_BY_FUNDS, fundsPayed, order);
			incomeEventService.save(fundsDepoist);
		}
		if(brokeragePayed>0f){
			brokerageDeposit = new DepositEvent(distributer, EventType.PAY_DEPOSIT_BY_BROKERAGE, brokeragePayed, order);
			incomeEventService.save(brokerageDeposit);
		}

//		Thread.sleep(1000);
	}
}
