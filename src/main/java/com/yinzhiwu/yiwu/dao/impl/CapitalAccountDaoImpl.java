package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.CapitalAccountDao;
import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.enums.PaymentMode;

@Repository
public class CapitalAccountDaoImpl extends BaseDaoImpl<CapitalAccount, Integer> implements CapitalAccountDao {

	@Override
	public List<CapitalAccount> findByDistributerId(int distributerId) {
		return findByProperty("distributer.id", distributerId);
	}

	@Override
	public List<CapitalAccount> findByDistributerIdAndPaymentMode(Integer distributerId, PaymentMode paymentMode) {
		return findByProperties(
				new String[]{"distributer.id","paymentMode"}, 
				new Object[]{distributerId,paymentMode});
	}

}
