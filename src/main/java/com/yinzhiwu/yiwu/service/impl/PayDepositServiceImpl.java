package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.PayDepositDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.PayDeposit;
import com.yinzhiwu.yiwu.enums.IncomeType;
import com.yinzhiwu.yiwu.exception.business.BusinessException;
import com.yinzhiwu.yiwu.service.PayDepositService;

/**
*@Author ping
*@Time  创建时间:2017年11月10日上午9:54:27
*
*/

@Repository
public class PayDepositServiceImpl extends BaseServiceImpl<PayDeposit,Integer > implements PayDepositService{
	
	@Autowired private ApplicationContext applicationContext;
	
	@Autowired public void setBaseDao(PayDepositDao dao){super.setBaseDao(dao);}
	
	/**
	 * listened by {@link CustomerYzwServiceImpl#handlePayDeposit(PayDeposit)}
	 * and  {@link IncomeRecordServiceImpl#handlePayDeposit(PayDeposit)}
	 * 
	 * @param distributer
	 * @param amount
	 * @param fundsFirst
	 * @throws BusinessException
	 */
	@Override
	public void payDeposit(Distributer distributer, float amount,  boolean fundsFirst) throws BusinessException{
		Assert.notNull(distributer);
		if(amount <=0) throw new BusinessException("兑换定金的金额不能为负");
		
		float brokerage = distributer.getIncomeValue(IncomeType.BROKERAGE);
		float funds = distributer.getIncomeValue(IncomeType.FUNDS);
		if(amount>(brokerage + funds)) 
			throw new BusinessException("超出的可兑换定金最大金额");
		float fundsPayed, brokeragePayed;
		if(fundsFirst){
			fundsPayed=amount>funds?funds:amount;
			brokeragePayed=amount>fundsPayed?amount-fundsPayed:0f;
		}else {
			brokeragePayed = amount>brokerage?brokerage:amount;
			fundsPayed	 = amount>brokeragePayed?amount-brokeragePayed:0f;
		}
		
		if(fundsPayed > 0f){
			PayDeposit fundsDeposit = new PayDeposit();
			fundsDeposit.setDistributer(distributer);
			fundsDeposit.setAmount(fundsPayed);
			fundsDeposit.setPayMethod(IncomeType.FUNDS);
			save(fundsDeposit);
			
			applicationContext.publishEvent(fundsDeposit);
		}
		if(brokeragePayed > 0f){
			PayDeposit bpd = new PayDeposit();
			bpd.setDistributer(distributer);
			bpd.setAmount(brokeragePayed);
			bpd.setPayMethod(IncomeType.BROKERAGE);
			save(bpd);
			
			applicationContext.publishEvent(bpd);
			
		}
		
		
	}

}
