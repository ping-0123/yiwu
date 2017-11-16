package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.CapitalAccountDao;
import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.enums.PaymentMode;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

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
	
	@Override
	public CapitalAccount findOneByDistributerIdAndPaymentMode(Integer distributerId, PaymentMode paymentMode) {
		try {
			return findOneByProperties(new String[]{"distributer.id","paymentMode"}, 
					new Object[]{distributerId,paymentMode});
		} catch (DataNotFoundException e) {
			return null;
		}
	}


	@Override
	public Boolean cancelDefaultAccount(Integer distributerId) {
		StringBuilder hql = new StringBuilder();
		hql.append("UPDATE CapitalAccount");
		hql.append(" SET isDefault = false");
		hql.append(" WHERE distributer.id =:distributerId");
		
		getSession().createQuery(hql.toString())
			.setParameter("distributerId", distributerId)
			.executeUpdate();
		
		return true;
	}

	



}
